package com.necitdev.lab7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AppModel model = new AppModel();
        AppView view = new AppView();

        new AppController(model, view);
        Scene scene = new Scene(view.getRoot(), 400, 350);
        stage.setTitle("Парикмахерская");
        stage.setMinWidth(350);
        stage.setMinHeight(300);
        stage.setMaxWidth(600);
        stage.setMaxHeight(500);
        stage.setFullScreen(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}