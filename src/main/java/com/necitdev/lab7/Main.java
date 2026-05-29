package com.necitdev.lab7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        final int COUNT_OF_HAIRDRESSERS = 4;
        HairdresserController[] hairdresserControllers = new HairdresserController[COUNT_OF_HAIRDRESSERS];
        HairdresserView[] hdviews = new HairdresserView[COUNT_OF_HAIRDRESSERS];

        for (int i = 0; i < COUNT_OF_HAIRDRESSERS; i++) {
            hdviews[i] = new HairdresserView();
            hairdresserControllers[i] = new HairdresserController(new HairdresserModel(i+3), hdviews[i]);
        }


        AppModel model = new AppModel(COUNT_OF_HAIRDRESSERS);
        AppView appView = new AppView(hdviews);




        new AppController(model, appView, hairdresserControllers);

        Scene scene = new Scene(appView.getRoot(), 400, 650);
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