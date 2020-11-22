package com.mpJavaGame.input;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {

    private boolean[] buttons;
    private boolean[] lastButtons;

    private Point location;
    private int scrollUp;
    private int scrollDown;

    public Mouse() {
        this.buttons = new boolean[3];
        this.lastButtons = new boolean[3];
        this.location = new Point();
    }

    public void update(){
        System.arraycopy(buttons, 0, lastButtons, 0, buttons.length);
    }

    public Point getLocation() {
        return location;
    }

    public boolean isButtonPressed(MouseButton button) {
        return buttons[button.ordinal()];
    }

    public boolean isButtonUp(MouseButton button) {
        return !buttons[button.ordinal()] && lastButtons[button.ordinal()];
    }

    public boolean isButtonDown(MouseButton button) {
        return buttons[button.ordinal()] && !lastButtons[button.ordinal()];
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        location = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        location = e.getPoint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        try {
            buttons[e.getButton() - 1] = true;
        } catch (Exception ignored) {}
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        try {
            buttons[e.getButton() - 1] = false;
        } catch (Exception ignored) {}
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scrollUp = e.getWheelRotation();
        scrollDown = e.getWheelRotation();
    }

    public int getScroll(int dir) {
        int ret = scrollUp;
        if (dir > 0) {
            ret = scrollDown;
            scrollDown = 0;
        } else {
            scrollUp = 0;
        }
        return ret;
    }

    // not used
    public void mouseClicked(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

}
