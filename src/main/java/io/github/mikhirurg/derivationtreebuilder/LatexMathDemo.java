package io.github.mikhirurg.derivationtreebuilder;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LatexMathDemo extends Application {

    @Override
    public void start(Stage stage) {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        stage.setWidth(screenBounds.getWidth() / 2);
        stage.setHeight(screenBounds.getHeight() / 2);

        BorderPane pane = new BorderPane();

        TeXFormula teXFormula = new TeXFormula("\\textbf{HI, Bro!}$\\sum_{i=1}^{100}{i}$");


        TeXIcon teXIcon = teXFormula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);

        ImageView imageView = new ImageView();

        BufferedImage bufferedImage = new BufferedImage(teXIcon.getIconWidth(), teXIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bufferedImage.createGraphics();
        teXIcon.paintIcon(new JPanel(), g, 0, 0);

        imageView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));

        pane.setCenter(imageView);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Latex Demo");

        stage.show();
    }
}
