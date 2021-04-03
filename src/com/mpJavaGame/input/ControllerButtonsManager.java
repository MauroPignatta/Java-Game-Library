package com.mpJavaGame.input;

import com.github.strikerx3.jxinput.XInputButtons;
import com.github.strikerx3.jxinput.enums.XInputButton;

public class ControllerButtonsManager  {

    private ControllerButtons lastButtons;
    private ControllerButtons nowButtons;
    private XInputButtons controllerButtons;

    public ControllerButtonsManager(XInputButtons buttons) {
        this.lastButtons = new ControllerButtons();
        this.nowButtons = new ControllerButtons();
        controllerButtons = buttons;
    }

    public void poll(){
        nowButtons.copy(controllerButtons);
    }

    public void update() {
        lastButtons.copy(nowButtons);
        nowButtons.reset();
    }

    public boolean isButtonUp(XInputButton button) {
        return isButton(button, false, true);
    }

    public boolean isButtonDown(XInputButton button) {
        return isButton(button, true, false);
    }

    public boolean isButtonHeld(XInputButton button) {
        return isButton(button, false, false);
    }

    private boolean isButton(XInputButton button, boolean negateLast, boolean negateNow) {
        switch (button) {
            case A:
                return (negateLast != lastButtons.a) && (negateNow != nowButtons.a);
            case B:
                return (negateLast != lastButtons.b) && (negateNow != nowButtons.b);
            case X:
                return (negateLast != lastButtons.x) && (negateNow != nowButtons.x);
            case Y:
                return (negateLast != lastButtons.y) && (negateNow != nowButtons.y);
            case BACK:
                return (negateLast != lastButtons.back) && (negateNow != nowButtons.back);
            case START:
                return (negateLast != lastButtons.start) && (negateNow != nowButtons.start);
            case LEFT_SHOULDER:
                return (negateLast != lastButtons.lShoulder) && (negateNow != nowButtons.lShoulder);
            case RIGHT_SHOULDER:
                return (negateLast != lastButtons.rShoulder) && (negateNow != nowButtons.rShoulder);
            case LEFT_THUMBSTICK:
                return (negateLast != lastButtons.lThumb) && (negateNow != nowButtons.lThumb);
            case RIGHT_THUMBSTICK:
                return (negateLast != lastButtons.rThumb) && (negateNow != nowButtons.rThumb);
            case DPAD_UP:
                return (negateLast != lastButtons.up) && (negateNow != nowButtons.up);
            case DPAD_DOWN:
                return (negateLast != lastButtons.down) && (negateNow != nowButtons.down);
            case DPAD_LEFT:
                return (negateLast != lastButtons.left) && (negateNow != nowButtons.left);
            case DPAD_RIGHT:
                return (negateLast != lastButtons.right) && (negateNow != nowButtons.right);
            case GUIDE_BUTTON:
                return (negateLast != lastButtons.guide) && (negateNow != nowButtons.guide);
            case UNKNOWN:
        }
        return false;
    }
}
