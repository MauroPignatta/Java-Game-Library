package com.mpJavaGame.input;

import com.github.strikerx3.jxinput.XInputButtons;

public class ControllerButtonsManager {

    private ControllerButtons lastButtons;
    private ControllerButtons nowButtons;
    private XInputButtons controllerButtons;

    public ControllerButtonsManager(XInputButtons buttons) {
        this.lastButtons = new ControllerButtons();
        this.nowButtons = new ControllerButtons();
        controllerButtons = buttons;
    }

    public void poll() {
        nowButtons.copy(controllerButtons);
    }

    public void update() {
        lastButtons.copy(nowButtons);
        nowButtons.reset();
    }

    public boolean isButtonUp(ControllerButton button) {
        return button.isButton(nowButtons, lastButtons, true, false);
    }

    public boolean isButtonDown(ControllerButton button) {
        return button.isButton(nowButtons, lastButtons, false, true);
    }

    public boolean isButtonHeld(ControllerButton button) {
        return button.isButton(nowButtons, lastButtons, false, false);
    }
}
