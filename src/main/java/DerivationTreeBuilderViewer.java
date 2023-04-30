import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DerivationTreeBuilderViewer extends Application {
    @Override
    public void start(Stage stage) {

        stage.setWidth(500);
        stage.setHeight(500);

        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(8);
        flowPane.setHgap(4);
        flowPane.setPrefWrapLength(300);

        VBox vBox = new VBox();
        HBox top = new HBox(new VBox(new Button("+"), new Separator(), new Label("<S, s> -> s'")), new VBox(new Button("+"), new Separator(), new Label("<while b do S, s'> -> s''")));
        top.setMaxWidth(Double.MAX_VALUE);

        Separator separator = new Separator();
        separator.setMaxWidth(Double.MAX_VALUE);
        separator.setOrientation(Orientation.HORIZONTAL);
        HBox middle = new HBox(separator, new Label("if B[[b]]s = tt"));
        middle.setMaxWidth(Double.MAX_VALUE);

        Label bottom = new Label("<while b do S, s> -> s''");
        bottom.setMaxWidth(Double.MAX_VALUE);

        vBox.setFillWidth(true);

        vBox.getChildren().addAll(top, middle, bottom);

        flowPane.getChildren().add(vBox);

        Scene scene = new Scene(flowPane);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}