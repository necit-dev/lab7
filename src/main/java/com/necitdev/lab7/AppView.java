package com.necitdev.lab7;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AppView {
    private final VBox root;
    private final HBox container;
    private final VBox firstColumn;
    private final VBox secondColumn;
    private final VBox mainVbox;
    private final Button workButton;
    private final Button startProcess;
    private final Label appStatusLabel;
    private final Label hairdressersLabel;
    private final Label clientsLeaveLabel;
    private final Label queueCountLabel;
    private final Label processTimeLabel;

    public AppView(HairdresserView[] hairdresserView) {
        secondColumn = new VBox(20);
        firstColumn = new VBox(20);
        container = new HBox(20, firstColumn, secondColumn);
        container.setAlignment(Pos.CENTER);
        root = new VBox(30, container);

        for (int i = 0; i < hairdresserView.length; i++) {
            hairdresserView[i].getRoot().setMinWidth(150);
            if (i%2 == 0){
                firstColumn.getChildren().add(hairdresserView[i].getRoot());
            }else {
                secondColumn.getChildren().add(hairdresserView[i].getRoot());
            }
        }
        workButton = new Button("Пусть кто-то поработает");
        startProcess = new Button("Запустить процесс");
        appStatusLabel = new Label("Статус приложения: IDLE");
        hairdressersLabel = new Label("Парикмахеров занято: 0");
        queueCountLabel = new Label("Клиентов в очереди: 0");
        clientsLeaveLabel = new Label("Клиентов ушло: 0");
        processTimeLabel = new Label("Поток клиентов остановится через: 0");

        mainVbox = new VBox(10, workButton, startProcess, appStatusLabel, processTimeLabel, hairdressersLabel, queueCountLabel, clientsLeaveLabel);
        mainVbox.setAlignment(Pos.CENTER);
        root.getChildren().add(mainVbox);
        root.setAlignment(Pos.CENTER);
    }

    public Button getWorkButton() {
        return workButton;
    }

    public Button getStartProcess() {
        return startProcess;
    }

    public Label getProcessTimeLabel() {
        return processTimeLabel;
    }

    public Pane getRoot() {
        return root;
    }

    public void render(AppState state, int countOfWorking, int leavingClients, int inQueueClients, int processTime) {
        hairdressersLabel.setText("Парикмахеров занято: " + countOfWorking);
        clientsLeaveLabel.setText("Клиентов ушло: " + leavingClients);
        queueCountLabel.setText("Клиентов в очереди: " + inQueueClients);
        processTimeLabel.setText("Поток клиентов остановится через: " + processTime);
        switch (state){
            case IDLE -> {
                appStatusLabel.setText("Статус приложения: IDLE");
            }
            case ONE_OR_MORE_WORKING -> {
                appStatusLabel.setText("Статус приложения: ONE_OR_MORE_WORKING");
            }
            case ALL_WORKING -> {
                appStatusLabel.setText("Статус приложения: ALL_WORKING");
            }
        }
    }

}