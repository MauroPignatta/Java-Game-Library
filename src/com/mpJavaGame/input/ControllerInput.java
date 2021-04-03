package com.mpJavaGame.input;

import com.github.strikerx3.jxinput.XInputComponents;
import com.github.strikerx3.jxinput.enums.XInputButton;

public class ControllerInput {

    private static final float STICK_DEAD_ZONE = 0.15f;
    private static final float TRIGGER_DEAD_ZONE = 0.1f;

    private static ControllerInput controller;

    private ControllerButtonsManager buttons;
    private ControllerAxes axes;

    private boolean enable = true;

    public ControllerInput(XInputComponents input) {
        this.buttons = new ControllerButtonsManager(input.getButtons());
        this.axes = new ControllerAxes(input.getAxes());
    }

    public static void init(XInputComponents input){
        if(controller == null)
            controller = new ControllerInput(input);
    }

    public static ControllerInput getController(){
        if(controller == null)
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

    public boolean isButtonUp(XInputButton button){
        return enable && buttons.isButtonUp(button);
    }

    public boolean isButtonDown(XInputButton button){
        return enable && buttons.isButtonDown(button);
    }

    public boolean isButtonHeld(XInputButton button){
        return enable && buttons.isButtonHeld(button);
    }

}
