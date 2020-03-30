package com.adrianpratik;

import com.adrianpratik.control.MainWindow;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Main.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene sc = new Scene(root, 1920, 1080);

        MainWindow window = loader.getController();
        window.setScene(sc);
        window.setWidth(sc.getWidth());
        window.setHeight(sc.getHeight());
        window.getMainCanvas().setWidth(1920);
        window.getMainCanvas().setHeight(1080);

        InvalidationListener widthListener = o -> window.setWidth(sc.getWidth());
        InvalidationListener heightListener = o ->  window.setHeight(sc.getHeight());

        sc.widthProperty().addListener(widthListener);
        sc.heightProperty().addListener(heightListener);
        window.start();

        primaryStage.setScene(sc);
        primaryStage.setTitle("Jumanji :)");
        primaryStage.show();

    }
}
