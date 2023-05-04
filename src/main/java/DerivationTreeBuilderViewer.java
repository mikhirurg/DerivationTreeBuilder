import converter.WhileASCIIDerivationTreeConverter;
import derivation.rules.DerivationTreeBuilder;
import derivation.rules.DerivationTreeNode;
import derivation.rules.whilelang.WhileDerivationTreeBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import parser.syntax.ThrowableErrorListener;
import parser.syntax.whilelang.WhileListener;
import parser.syntax.whilelang.arithmeticexp.exceptions.UninitializedVariableException;
import parser.syntax.whilelang.gen.WhileLexer;
import parser.syntax.whilelang.gen.WhileParser;
import parser.syntax.whilelang.statements.WhileStatement;
import states.WhileState;

import java.util.HashMap;

public class DerivationTreeBuilderViewer extends Application {
    @Override
    public void start(Stage stage) {

        BorderPane vertical = new BorderPane();
        BorderPane firstLine = new BorderPane();

        VBox controls = new VBox();
        HBox secondLine = new HBox();

        CheckBox isExplicitState = new CheckBox("Explicit state representation");
        isExplicitState.setSelected(true);

        TextField depthTextField = new TextField(Long.toString(DerivationTreeBuilder.DERIVATION_DEPTH_LIMIT));
        Label depthTextLabel = new Label("Derivation depth:");
        HBox depth = new HBox();
        depth.getChildren().add(depthTextLabel);
        depth.getChildren().add(depthTextField);

        secondLine.getChildren().add(isExplicitState);
        secondLine.getChildren().add(depth);
        secondLine.setSpacing(50);

        TextArea programArea = new TextArea();
        programArea.setFont(Font.font("Consolas", FontWeight.THIN, 16));
        programArea.autosize();
        BorderPane buttonPane = new BorderPane();
        Button button = new Button("Build");

        firstLine.setCenter(programArea);
        firstLine.setRight(buttonPane);

        controls.getChildren().add(firstLine);
        controls.getChildren().add(secondLine);
        controls.setSpacing(10);

        buttonPane.setCenter(button);
        buttonPane.prefWidthProperty().bind(button.widthProperty().multiply(1.5));

        TextArea viewer = new TextArea();
        viewer.setEditable(false);
        viewer.setFont(Font.font("Consolas", FontWeight.THIN, 16));
        viewer.autosize();
        vertical.setTop(controls);
        vertical.setCenter(viewer);

        button.setOnAction(actionEvent -> {
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

                long depthVal = Long.parseLong(depthTextField.getText());

                boolean isExplicit = isExplicitState.isSelected();

                DerivationTreeBuilder builder = new WhileDerivationTreeBuilder(depthVal);
                DerivationTreeNode derivationTree = builder.buildDerivationTree(p, new WhileState(new HashMap<>()));
                WhileASCIIDerivationTreeConverter asciiDerivationTreeConverter = new WhileASCIIDerivationTreeConverter(isExplicit);

                viewer.setText(asciiDerivationTreeConverter.convert(derivationTree) + "\n" + builder.getState().getTextRepresentation());
            } catch (ParseCancellationException e) {
                viewer.setText("Program parsing error at: " + e.getMessage());
            } catch (UninitializedVariableException e) {
                viewer.setText("Unable to build a derivation tree: " + e.getMessage());
            } catch (NumberFormatException e) {
                viewer.setText("Wrong value for the depth param!: " + e.getMessage());
            }
        });

        Scene scene = new Scene(vertical);
        stage.setScene(scene);
        stage.setTitle("Derivation Tree Builder v0.1 (C) Mikhail Ushakov");

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}