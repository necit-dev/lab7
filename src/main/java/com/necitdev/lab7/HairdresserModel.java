package com.necitdev.lab7;

public class HairdresserModel {
    private HairdresserState state = HairdresserState.IDLE;
    private final int standardWork;
    private int secondsLeft = 0;
    public int getSecondsLeft() {return secondsLeft;}
    public void setSecondsLeft(int secondsLeft) {this.secondsLeft = secondsLeft;}

    public HairdresserModel(int standardWork) {
        this.standardWork = standardWork;
    }

    public int getStandardWork() {
        return standardWork;
    }

    public HairdresserState getState() {
        return state;
    }

    public HairdresserState handleEvent(HairdresserEvent e) {
        switch (state) {
            case IDLE -> {
                if (e == HairdresserEvent.WORK_START) state = HairdresserState.WORKING;
            }
            case WORKING -> {
                if (e == HairdresserEvent.WORK_END) state = HairdresserState.IDLE;
            }
        }
        return state;
    }
}
