package com.necitdev.lab7;

public class AppController {
    private AppModel model;
    private AppView view;

    public AppController(AppModel model, AppView view) {
        this.model = model;
        this.view = view;

        view.getBtn().setOnAction(e -> {
            System.out.println("Кнопка нажата");
        });;
    }
}
