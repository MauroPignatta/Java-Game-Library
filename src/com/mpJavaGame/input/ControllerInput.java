package com.mpJavaGame.input;

import com.github.strikerx3.jxinput.XInputComponents;
import com.github.strikerx3.jxinput.enums.XInputAxis;

public class ControllerInput {

    private static ControllerInput controller;

    private ControllerButtonsManager buttons;
    private ControllerAxes axes;

    private boolean enable = true;

    public ControllerInput(XInputComponents input) {
        this.buttons = new ControllerButtonsManager(input.getButtons());
        this.axes = new ControllerAxes(input.getAxes());
    }

    public static void init(XInputComponents input) {
        if (controller == null)
            controller = new ControllerInput(input);
    }

    public static ControllerInput getController() {
        if (controller == null)
            throw new RuntimeException("Controller is not initialized");

        return controller;
    }

    public void poll() {
        buttons.poll();
    }

    public void update() {
        buttons.update();
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isButtonUp(ControllerButton button) {
        return enable && buttons.isButtonUp(button);
    }

    public boolean isButtonDown(ControllerButton button) {
        return enable && buttons.isButtonDown(button);
    }

    public boolean isButtonHeld(ControllerButton button) {
        return enable && buttons.isButtonHeld(button);
    }

    public float getAxis(XInputAxis axis) {
        return axes.getAxisValue(axis);
    }
}
