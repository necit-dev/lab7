package com.necitdev.lab7;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AppView {
    private final VBox root;


    public AppView() {
        root = new VBox(40);

    }

    public Pane getRoot() {
        return root;
    }
}