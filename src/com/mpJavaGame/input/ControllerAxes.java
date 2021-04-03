package com.mpJavaGame.input;

import com.github.strikerx3.jxinput.XInputAxes;
import com.github.strikerx3.jxinput.enums.XInputAxis;

public class ControllerAxes {

    private static final float STICK_DEAD_ZONE = 0.15f;
    private static final float TRIGGER_DEAD_ZONE = 0.1f;

    private final XInputAxes axes;

    public ControllerAxes(XInputAxes axes) {
        this.axes = axes;
    }

    public float getAxisValue(XInputAxis axis){
        float deadZone = STICK_DEAD_ZONE;
        float axisValue = axes.get(axis);
        if(axis.equals(XInputAxis.LEFT_THUMBSTICK_Y) || axis.equals(XInputAxis.RIGHT_THUMBSTICK_Y))
            axisValue = -axisValue;
        if(axis.equals(XInputAxis.LEFT_TRIGGER) || axis.equals(XInputAxis.RIGHT_TRIGGER)){
            deadZone = TRIGGER_DEAD_ZONE;
        }

        return Math.abs(axisValue) >= deadZone ? axisValue: 0f;
    }
}
