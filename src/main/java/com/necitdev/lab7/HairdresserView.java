package com.necitdev.lab7;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HairdresserView {
    private final VBox root;
    private final Label statusLabel;
    private final Label timerLabel;
    private final Button startWorkButton;

    private final String noTime = "-- сек";
    private final String statusText = "Статус: ";

    public HairdresserView() {
        statusLabel = new Label(statusText + "IDLE");
        timerLabel = new Label(noTime);
        startWorkButton = new Button("Начать стрижку");


        root = new VBox(10,statusLabel, timerLabel, startWorkButton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(16));

        statusLabel.setText(statusText + "IDLE");
        startWorkButton.setDisable(false);
    }

    public Pane getRoot() {
        return root;
    }

    public Label getStatusLabel() {
        return statusLabel;
    }

    public Label getTimerLabel() {
        return timerLabel;
    }

    public Button getStartWorkButton() {
        return startWorkButton;
    }

    public void render(HairdresserState state, int seconds) {
        timerLabel.setText(seconds + " сек");

        switch (state) {
            case IDLE -> {
                timerLabel.setText("--- сек");
                statusLabel.setText(statusText + "IDLE");
                startWorkButton.setDisable(false);
            }
            case WORKING -> {
                statusLabel.setText(statusText + "WORKING");
                startWorkButton.setDisable(true);
            }
        }
    }
}
