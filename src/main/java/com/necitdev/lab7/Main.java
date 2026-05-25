package com.necitdev.lab7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        AppModel model = new AppModel();
        AppView appView = new AppView();
//
//        new AppController(model, view);

        HairdresserModel hdModel1 = new HairdresserModel();
        HairdresserView hdView1 = new HairdresserView();

        HairdresserModel hdModel2 = new HairdresserModel();
        HairdresserView hdView2 = new HairdresserView();

        HairdresserModel hdModel3 = new HairdresserModel();
        HairdresserView hdView3 = new HairdresserView();

        HairdresserModel hdModel4 = new HairdresserModel();
        HairdresserView hdView4 = new HairdresserView();

        new HairdresserController(hdModel1, hdView1);
        new HairdresserController(hdModel2, hdView2);
        new HairdresserController(hdModel3, hdView3);
        new HairdresserController(hdModel4, hdView4);

        appView.getRoot().getChildren().add(hdView1.getRoot());
        appView.getRoot().getChildren().add(hdView2.getRoot());
        appView.getRoot().getChildren().add(hdView3.getRoot());
        appView.getRoot().getChildren().add(hdView4.getRoot());

        Scene scene = new Scene(appView.getRoot(), 400, 350);
        stage.setTitle("Парикмахерская");
//        stage.setMinWidth(350);
//        stage.setMinHeight(300);
//        stage.setMaxWidth(600);
//        stage.setMaxHeight(500);
        stage.setFullScreen(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}