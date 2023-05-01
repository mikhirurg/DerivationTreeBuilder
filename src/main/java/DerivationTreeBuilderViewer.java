import converter.WhileASCIIDerivationTreeConverter;
import derivation.rules.DerivationTreeBuilder;
import derivation.rules.DerivationTreeNode;
import derivation.rules.whilelang.WhileDerivationTreeBuilder;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
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

        VBox vBox = new VBox();
        BorderPane firstLine = new BorderPane();

        TextArea programArea = new TextArea();
        programArea.setFont(Font.font("Consolas", FontWeight.THIN, 16));
        programArea.autosize();
        Button button = new Button("Build");
        button.setAlignment(Pos.BASELINE_CENTER);

        firstLine.setCenter(programArea);
        firstLine.setRight(button);

        TextArea viewer = new TextArea();
        viewer.setEditable(false);
        viewer.setFont(Font.font("Consolas", FontWeight.THIN, 16));
        viewer.autosize();
        vBox.getChildren().add(firstLine);
        vBox.getChildren().add(viewer);

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

                DerivationTreeBuilder builder = new WhileDerivationTreeBuilder();
                DerivationTreeNode derivationTree = builder.buildDerivationTree(p, new WhileState(new HashMap<>()));
                WhileASCIIDerivationTreeConverter asciiDerivationTreeConverter = new WhileASCIIDerivationTreeConverter();

                viewer.setText(asciiDerivationTreeConverter.convert(derivationTree) + "\n" + builder.getState().getTextRepresentation());
            } catch (ParseCancellationException e) {
                viewer.setText("Program parsing error at: " + e.getMessage());
            } catch (UninitializedVariableException e) {
                viewer.setText("Unable to build a derivation tree: " + e.getMessage());
            }
        });

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setTitle("Derivation Tree Builder v0.1 (C) Mikhail Ushakov");

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}