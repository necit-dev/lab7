package com.necitdev.lab7;

public class AppController {
    private AppModel model;
    private AppView view;
    private HairdresserController[] hairdresserControllers;

    public AppController(AppModel model, AppView view, HairdresserController[] hairdresserControllers) {
        this.model = model;
        this.view = view;
        this.hairdresserControllers = hairdresserControllers;
        view.getWorkButton().setOnAction(e -> workHairdresser());

    }

    private void workHairdresser() {
        if (model.getState() == AppState.ALL_WORKING) {
            System.out.println("Все заняты");
        }
        for (int i = 0; i < model.getHairdressersCount(); i++) {
            if (hairdresserControllers[i].getHairdresserModel().getState() == HairdresserState.IDLE){
                hairdresserControllers[i].startProcess();
                model.handleEvent(AppEvent.CLIENT_ADD);
                break;
            }
        }
        updateUI();
    }

    private void updateUI() {
        view.render(model.getState(), model.getHairdressersWorking());
    }

}
