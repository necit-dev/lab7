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

    private final String noTime = "-- сек";
    private final String statusText = "Статус: ";

    public HairdresserView() {
        statusLabel = new Label(statusText + "IDLE");
        timerLabel = new Label(noTime);


        root = new VBox(10,statusLabel, timerLabel);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(16));

        statusLabel.setText(statusText + "IDLE");
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



    public void render(HairdresserState state, int seconds) {
        timerLabel.setText(seconds + " сек");

        switch (state) {
            case IDLE -> {
                timerLabel.setText("--- сек");
                statusLabel.setText(statusText + "IDLE");
            }
            case WORKING -> {
                statusLabel.setText(statusText + "WORKING");
            }
        }
    }
}
