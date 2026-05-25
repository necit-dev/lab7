package com.necitdev.lab7;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class AppView {
    private final VBox root;

    private final Button btn;

    public AppView() {
        btn = new Button("Нажми на кнопку");

        root = new VBox(15, btn);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(16));

    }

    public Button getBtn() {
        return btn;
    }

    public Parent getRoot() {
        return root;
    }
}