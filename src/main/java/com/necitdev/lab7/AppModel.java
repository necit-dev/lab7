package com.necitdev.lab7;

public class AppModel {
    private final int hairdressersCount;
    private int hairdressersWorking = 0;
    private int leavingClients = 0;
    private AppState state = AppState.IDLE;

    public int getLeavingClients() {
        return leavingClients;
    }

    public void leaveClient() {
        leavingClients++;
    }

    public AppModel(int countOfHairdressers) {
        this.hairdressersCount = countOfHairdressers;
    }

    public AppState getState() {
        return state;
    }

    public int getHairdressersCount() {
        return hairdressersCount;
    }

    public int getHairdressersWorking() {
        return hairdressersWorking;
    }

    public AppState handleEvent(AppEvent event) {
        switch (state) {
            case IDLE -> {
                if (event == AppEvent.CLIENT_ADD) addVisitor();
            }
            case ONE_OR_MORE_WORKING -> {
                if (event == AppEvent.CLIENT_ADD) addVisitor();
                if (event == AppEvent.CLIENT_REMOVE) removeVisitor();
            }
            case ALL_WORKING -> {
                if (event == AppEvent.CLIENT_REMOVE) removeVisitor();
            }
        }
        return state;
    }
    private void addVisitor() {
        if (hairdressersWorking < hairdressersCount) {
            hairdressersWorking++;
        }
        if (hairdressersWorking >= hairdressersCount) {
            state = AppState.ALL_WORKING;
        }else {
            state = AppState.ONE_OR_MORE_WORKING;
        }
    }
    private void removeVisitor() {
        if (hairdressersWorking > 0) {
            hairdressersWorking--;
        }
        if (hairdressersWorking == 0) {
            state = AppState.IDLE;
        }else {
            state = AppState.ONE_OR_MORE_WORKING;
        }
    }
}