package com.necitdev.lab7;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class HairdresserController {
    private HairdresserModel hairdresserModel;
    private HairdresserView hairdresserView;

    private Timeline timeline;

    public HairdresserController(HairdresserModel hairdresserModel, HairdresserView hairdresserView) {
        this.hairdresserModel = hairdresserModel;
        this.hairdresserView = hairdresserView;

    }


    public void tick() {
        int current = hairdresserModel.getSecondsLeft();
        if (current > 1) {
            hairdresserModel.setSecondsLeft(--current);
        }else {
            timeline.stop();
            hairdresserModel.handleEvent(HairdresserEvent.WORK_END);
        }
        updateUI();
    }

    public void startProcess(int coefficient) {
        boolean isIDLEState = hairdresserModel.getState() == HairdresserState.IDLE;
        if (isIDLEState) {
            hairdresserModel.handleEvent(HairdresserEvent.WORK_START);
            hairdresserModel.setSecondsLeft(hairdresserModel.getStandardWork()*coefficient);
            timeline.play();
        }
        updateUI();
    }

    private void updateUI() {
        hairdresserView.render(hairdresserModel.getState(), hairdresserModel.getSecondsLeft());
    }

    public HairdresserModel getHairdresserModel() {
        return hairdresserModel;
    }

    public HairdresserView getHairdresserView() {
        return hairdresserView;
    }

    public Timeline getTimeline() {
        return timeline;
    }
    public void setTimeline(Timeline timeline){
        this.timeline = timeline;
    }

}
