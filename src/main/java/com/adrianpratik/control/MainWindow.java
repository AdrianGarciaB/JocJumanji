package com.adrianpratik.control;

import com.adrianpratik.sprites.Box;
import com.adrianpratik.sprites.Card;
import com.adrianpratik.sprites.Table;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    public static final double baseWidth = 1920;
    public static final double baseHeight = 1080;
    public static double width = baseWidth;
    public static double height = baseHeight;
    public static double diferenceWidth = 1f;
    public static double diferenceHeight = 1f;

    private Scene scene;
    private static GraphicsContext gc;

    private Table table;

    @FXML
    Canvas mainCanvas;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.0017), new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event) {
            gc.clearRect(0, 0,  width, height);
            table.draw();
        }
    })
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = mainCanvas.getGraphicsContext2D();
        timeline.setCycleCount(Timeline.INDEFINITE);
        table = new Table("images/table.png");

    }

    public void setScene(Scene sc) {
        scene = sc;
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Point2D point = new Point2D(mouseEvent.getX(),mouseEvent.getY());
                System.out.println("click");
            }
        });
    }

    public void setWidth(double width) {
        mainCanvas.setWidth(width);
        MainWindow.width = width;
        MainWindow.diferenceWidth = width / baseWidth;


    }

    public void setHeight(double height) {
        mainCanvas.setHeight(height);
        MainWindow.height = height;
        MainWindow.diferenceHeight = height / baseHeight;
    }

    public void start(){
        timeline.play();
    }

    public Canvas getMainCanvas() {
        return mainCanvas;
    }

    public static GraphicsContext getGraphicsContext() {
        return gc;
    }
}
