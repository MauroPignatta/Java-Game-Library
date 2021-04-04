package com.mpJavaGame.input;

public enum ControllerButton {
    A {
        @Override
        boolean isButton(ControllerButtons now, ControllerButtons last,
                         boolean negateNow, boolean negateLast) {
            return (negateLast != last.a) && (negateNow != now.a);
        }
    },
    B {
        @Override
        boolean isButton(ControllerButtons now, ControllerButtons last,
                         boolean negateNow, boolean negateLast) {
            return (negateLast != last.b) && (negateNow != now.b);
        }
    },
    X {
        @Override
        boolean isButton(ControllerButtons now, ControllerButtons last,
                         boolean negateNow, boolean negateLast) {
            return (negateLast != last.x) && (negateNow != now.x);
        }
    },
    Y {
        @Override
        boolean isButton(ControllerButtons now, ControllerButtons last,
                         boolean negateNow, boolean negateLast) {
            return (negateLast != last.y) && (negateNow != now.y);
        }
    },
    BACK {
        @Override
        boolean isButton(ControllerButtons now, ControllerButtons last,
                         boolean negateNow, boolean negateLast) {
            return (negateLast != last.back) && (negateNow != now.back);
        }
    },
    START {
        @Override
        boolean isButton(ControllerButtons now, ControllerButtons last,
                         boolean negateNow, boolean negateLast) {
            return (negateLast != last.start) && (negateNow != now.start);
        }
    },
    LEFT_SHOULDER {
        @Override
        boolean isButton(ControllerButtons now, ControllerButtons last,
                         boolean negateNow, boolean negateLast) {
            return (negateLast != last.lShoulder) && (negateNow != now.lShoulder);
        }
    },
    RIGHT_SHOULDER {
        @Override
        boolean isButton(ControllerButtons now, ControllerButtons last,
                         boolean negateNow, boolean negateLast) {
            return (negateLast != last.rShoulder) && (negateNow != now.rShoulder);
        }
    },
    LEFT_STICK {
        @Override
        boolean isButton(ControllerButtons now, ControllerButtons last,
                         boolean negateNow, boolean negateLast) {
            return (negateLast != last.lThumb) && (negateNow != now.lThumb);
        }
    },
    RIGHT_STICK {
        @Override
        boolean isButton(ControllerButtons now, ControllerButtons last,
                         boolean negateNow, boolean negateLast) {
            return (negateLast != last.rThumb) && (negateNow != now.rThumb);
        }
    },
    DPAD_UP {
        @Override
        boolean isButton(ControllerButtons now, ControllerButtons last,
                         boolean negateNow, boolean negateLast) {
            return (negateLast != last.up) && (negateNow != now.up);
        }
    },
    DPAD_DOWN {
        @Override
        boolean isButton(ControllerButtons now, ControllerButtons last,
                         boolean negateNow, boolean negateLast) {
            return (negateLast != last.down) && (negateNow != now.down);
        }
    },
    DPAD_LEFT {
        @Override
        boolean isButton(ControllerButtons now, ControllerButtons last,
                         boolean negateNow, boolean negateLast) {
            return (negateLast != last.left) && (negateNow != now.left);
        }
    },
    DPAD_RIGHT {
        @Override
        boolean isButton(ControllerButtons now, ControllerButtons last,
                         boolean negateNow, boolean negateLast) {
            return (negateLast != last.right) && (negateNow != now.right);
        }
    },
    GUIDE_BUTTON {
        @Override
        boolean isButton(ControllerButtons now, ControllerButtons last,
                         boolean negateNow, boolean negateLast) {
            return (negateLast != last.guide) && (negateNow != now.guide);
        }
    };

    abstract boolean isButton(ControllerButtons now, ControllerButtons last,
                              boolean negateNow, boolean negateLast);
}
