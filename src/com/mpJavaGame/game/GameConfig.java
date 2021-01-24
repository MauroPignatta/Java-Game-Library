package com.mpJavaGame.game;

public class GameConfig {

    private static final int DEFAULT_UPS = 60;
    private static final String DEFAULT_TITLE = "My Java Game";
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;

    private static final int MIN_UPS = 15;
    private static final int MAX_UPS = 20000;

    private int updatePerSecond;
    private String title;
    private int width;
    private int height;

    public GameConfig() {
        this.updatePerSecond = DEFAULT_UPS;
        this.title = DEFAULT_TITLE;
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
    }

    public int getUpdatePerSecond() {
        return updatePerSecond;
    }

    public void setUpdatePerSecond(int updatePerSecond) {
        this.updatePerSecond = Math.min(MAX_UPS, Math.max(updatePerSecond, MIN_UPS));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
