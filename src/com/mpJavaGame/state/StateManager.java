package com.mpJavaGame.state;


import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class StateManager {

    private static StateManager manager;

    private Map<String, State> states;
    private State currentState;

    private StateManager() {
    }

    public static StateManager getManager() {
        if (manager == null)
            manager = new StateManager();

        return manager;
    }

    public void addState(State state) {
        if (states == null)
            states = new HashMap<>();

        if (!states.containsKey(state.getName()) && !states.containsValue(state))
            states.put(state.getName(), state);
    }

    public void removeState(String key) {
        if (states == null)
            states = new HashMap<>();

        states.remove(key);
    }

    public boolean setCurrentState(String key) {
        if (states.containsKey(key)) {
            currentState = states.get(key);
        }
        return states.containsKey(key);
    }

    public boolean setCurrentState(State state) {
        if (state != null) {
            currentState = state;
        }
        return state != null;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void inputs() {
        if (currentState != null)
            currentState.inputs();
    }

    public void update(float delta) {
        if (currentState != null)
            currentState.update(delta);
    }

    public void render(Graphics2D g) {
        if (currentState != null)
            currentState.render(g);
    }

}
