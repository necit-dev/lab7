package com.necitdev.lab7;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayDeque;
import java.util.Queue;

public class AppController {
    private AppModel model;
    private AppView view;
    private HairdresserController[] hairdresserControllers;
    private Timeline appTimer;

    public AppController(AppModel model, AppView view, HairdresserController[] hairdresserControllers) {
        this.model = model;
        this.view = view;
        this.hairdresserControllers = hairdresserControllers;
        for (int i = 0; i < hairdresserControllers.length; i++) {
            final int this_i = i;
            hairdresserControllers[i].setTimeline(new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                hairdresserControllers[this_i].tick();
                // Больше единицы из-за счета 5, 4, 3, 2, 1 без 0, ноль не показывается
                if (hairdresserControllers[this_i].getHairdresserModel().getState() == HairdresserState.IDLE) {
                    model.handleEvent(AppEvent.CLIENT_REMOVE);
                    if (!model.getClientsInQueue().isEmpty()){
                        processWorkStart(model.getClientsInQueue().poll());
                    }
                    updateUI();
                }
            })));
            hairdresserControllers[i].getTimeline().setCycleCount(Animation.INDEFINITE);
        }
        view.getWorkButton().setOnAction(e -> workHairdresser());
        view.getStartProcess().setOnAction(e -> processAppStart());
        appTimer = new Timeline(new KeyFrame(Duration.seconds(0.001), e -> {
            appTick();
        }));
        appTimer.setCycleCount(Animation.INDEFINITE);
    }

    private void appTick(){
        int currentTime = model.getProcessTime();
        if (currentTime > 1) {
            model.setProcessTime(--currentTime);
            int timer;
            try {
                timer = Integer.parseInt(view.getIntensityTextField().getText());
            } catch (Exception e) {
                timer = 2000;
            }
            if (currentTime% timer == 0) workHairdresser();
        }else {
            appTimer.stop();
            model.setProcessTime(0);
            view.getStartProcess().setDisable(false);
            view.getIntensityTextField().setDisable(false);
        }
        updateUI();
    }

    private void workHairdresser() {
        final double random = Math.random();
        int coefficient = 1;
        if (random < 0.333) {
            coefficient = 2;
        }else if (random < 0.667) {
            coefficient = 3;
        }
        System.out.println(random);
        if (model.getState() == AppState.ALL_WORKING) {
            if (model.getClientsInQueue().size() < 5) {
                model.getClientsInQueue().add(coefficient);
            }else {
                System.out.println("Все заняты");
                model.leaveClient();
            }

        }
        processWorkStart(coefficient);
        updateUI();
    }

    private void processAppStart() {
        view.getStartProcess().setDisable(true);
        view.getIntensityTextField().setDisable(true);
        model.setProcessTime(60000);
        appTimer.play();
        workHairdresser();
        updateUI();
    }

    private void processWorkStart(int coefficient) {
        for (int i = 0; i < model.getHairdressersCount(); i++) {
            if (hairdresserControllers[i].getHairdresserModel().getState() == HairdresserState.IDLE){
                hairdresserControllers[i].startProcess(coefficient);
                model.handleEvent(AppEvent.CLIENT_ADD);
                break;
            }
        }
    }

    private void updateUI() {
        view.render(model.getState(), model.getHairdressersWorking(), model.getLeavingClients(), model.getClientsInQueue().size(), model.getProcessTime());
    }

}
