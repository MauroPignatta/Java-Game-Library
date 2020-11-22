package com.mpJavaGame.state;

import java.awt.*;

public abstract class State {

    private String name;

    public State(String name) {
        this.name = name;
    }

    public abstract void inputs();

    public abstract void update(float delta);

    public abstract void render(Graphics2D g);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
