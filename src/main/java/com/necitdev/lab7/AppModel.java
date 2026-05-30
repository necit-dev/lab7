package com.necitdev.lab7;

import java.util.ArrayDeque;
import java.util.Queue;

public class AppModel {
    private final int hairdressersCount;
    private int hairdressersWorking = 0;
    private int leavingClients = 0;
    private AppState state = AppState.IDLE;
    private Queue<Integer> clientsInQueue = new ArrayDeque<>();
    private int processTime = 0;

    public int getProcessTime() {
        return processTime;
    }

    public void setProcessTime(int processTime) {
        this.processTime = processTime;
    }

    public int getLeavingClients() {
        return leavingClients;
    }

    public void leaveClient() {
        leavingClients++;
    }

    public Queue<Integer> getClientsInQueue() {
        return clientsInQueue;
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