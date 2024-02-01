package io.github.mikhirurg.derivationtreebuilder;

import com.formdev.flatlaf.FlatIntelliJLaf;
import io.github.mikhirurg.derivationtreebuilder.converter.WhileDerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.converter.whilelang.WhileASCIIDerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.converter.whilelang.WhileLatexDerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.DerivationTreeBuilder;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.DerivationTreeNode;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.whilelang.WhileDerivationTreeBuilder;
import io.github.mikhirurg.derivationtreebuilder.syntax.ThrowableErrorListener;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.WhileListener;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.arithmeticexp.exceptions.UninitializedVariableException;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.gen.WhileLexer;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.gen.WhileParser;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.statements.WhileStatement;
import javafx.scene.layout.BorderPane;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class SwingTreeBuilderViewer {

    private final static int IPAD_X = 30;
    private final static int IPAD_Y = 10;

    private enum DerivationTreeConversionType {
        ASCII("ASCII"), LATEX("LateX");

        private static List<String> namesArray = null;

        private final String name;
        private DerivationTreeConversionType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static DerivationTreeConversionType fromString(String stringName) {
            switch (stringName) {
                case "LateX":
                    return LATEX;
                case "ASCII":
                default:
                    return ASCII;
            }
        }

        public static List<String> getStringNames() {
            if (namesArray == null) {
                ArrayList<String> names = new ArrayList<>();
                for (DerivationTreeConversionType type : DerivationTreeConversionType.values()) {
                    names.add(type.getName());
                }

                namesArray = names;
            }

            return namesArray;
        }
    }

    private static void addComponentsToPane(Container pane) {
        pane.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        JTextArea programArea = new JTextArea();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 6;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.ipadx = IPAD_X;
        constraints.ipady = IPAD_Y;

        programArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));

        JScrollPane programScrollPane = new JScrollPane(programArea);
        pane.add(programScrollPane, constraints);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 5;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.ipadx = IPAD_X;
        constraints.ipady = IPAD_Y;

        JCheckBox isExplicitState = new JCheckBox(App.getString("checkbox.is_explicit_state"));
        controlPanel.add(isExplicitState);

        JLabel depthLabel = new JLabel(App.getString("label.depth_text_label"));
        controlPanel.add(depthLabel);

        JTextField depthField = new JTextField();
        depthField.setColumns(4);
        depthField.setText("50");
        controlPanel.add(depthField);

        JLabel conversionTypeString = new JLabel(App.getString("label.conversion_type"));
        controlPanel.add(conversionTypeString);

        String[] names = new String[DerivationTreeConversionType.getStringNames().size()];
        names = DerivationTreeConversionType.getStringNames().toArray(names);
        JComboBox<String> conversionType = new JComboBox<>(names);
        conversionType.setSelectedIndex(0);
        controlPanel.add(conversionType);

        JButton build = new JButton(App.getString("button.build"));
        controlPanel.add(build);

        pane.add(controlPanel, constraints);

        JTextArea viewer = new JTextArea();
        viewer.setEditable(false);
        viewer.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));

        JScrollPane viewerScrollPane = new JScrollPane(viewer);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 6;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.ipadx = IPAD_X;
        constraints.ipady = IPAD_Y;

        pane.add(viewerScrollPane, constraints);

        build.addActionListener(l -> {
            WhileLexer whileLexer = new WhileLexer(CharStreams.fromString(programArea.getText()));

            CommonTokenStream tokens = new CommonTokenStream(whileLexer);
            WhileParser parser = new WhileParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(new ThrowableErrorListener());

            try {
                ParseTree tree = parser.prog();
                WhileListener listener = new WhileListener();
                ParseTreeWalker walker = new ParseTreeWalker();

                walker.walk(listener, tree);
                WhileStatement p = listener.getProgram();

                long depthVal = Long.parseLong(depthField.getText());

                boolean isExplicit = isExplicitState.isSelected();

                DerivationTreeBuilder builder = new WhileDerivationTreeBuilder(depthVal);
                DerivationTreeNode derivationTree = builder.buildDerivationTree(p, new WhileState(new HashMap<>()));

                WhileDerivationTreeConverter derivationTreeConverter = null;

                if (DerivationTreeConversionType.fromString((String) Objects.requireNonNull(conversionType.getSelectedItem())) == DerivationTreeConversionType.LATEX) {
                    derivationTreeConverter = new WhileLatexDerivationTreeConverter(isExplicit);
                } else {
                    derivationTreeConverter = new WhileASCIIDerivationTreeConverter(isExplicit);
                }

                viewer.setText(derivationTreeConverter.convert(derivationTree) + "\n" + builder.getState().getTextRepresentation());
            } catch (ParseCancellationException e) {
                viewer.setText(App.getString("error.parsing") + e.getMessage());
            } catch (UninitializedVariableException e) {
                viewer.setText(App.getString("error.derivation_tree") + e.getMessage());
            } catch (NumberFormatException e) {
                viewer.setText(App.getString("error.wrong_depth_value") + e.getMessage());
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame(App.getString("title"));

        double width = App.getScreenDimension().getWidth() / 2.5;
        double height = App.getScreenDimension().getHeight() / 2.5;

        frame.setPreferredSize(new Dimension((int) width, (int) height));

        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());
        frame.pack();

        frame.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingTreeBuilderViewer::createAndShowGUI);
    }
}
