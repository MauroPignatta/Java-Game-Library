package com.mpJavaGame.loop;

import com.mpJavaGame.input.KeyInput;
import com.mpJavaGame.input.MouseInput;

import java.awt.*;

public abstract class Loop implements Runnable {

    private static final float NANOSECOND = 1000000000;
    private static final int MIN_FPS = 15;
    private static final int MAX_FPS = 600;
    private static final int MIN_UPS = 15;
    private static final int MAX_UPS = 20000;

    private final int MONITOR_REFRESH_RATE;

    private final float uOPTIMAL_TIME;
    private float fOPTIMAL_TIME;
    private final float deltaT;

    private boolean running;
    private int currentFPS;
    private int currentUPS;
    private boolean sleep;

    /**
     * Creates a new game Loop
     *
     * @param UPS Amount of updates per seconds.
     *            This determines how many times the loop will iterate in a second.
     */
    public Loop(int UPS) {
        UPS = Math.min(MAX_UPS, Math.max(UPS, MIN_UPS));
        uOPTIMAL_TIME = NANOSECOND / UPS;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        DisplayMode dm = gs[0].getDisplayMode();
        int hertz = dm.getRefreshRate();
        hertz = (hertz == DisplayMode.REFRESH_RATE_UNKNOWN) ? 60 : hertz;
        MONITOR_REFRESH_RATE = hertz;
        deltaT = 1f / UPS;
        init();
    }

    private void init() {
        setup();
    }

    /**
     * Used for setting up variables in your program.
     */
    protected abstract void setup();

    /**
     * This method is called first inside de loop.
     * Use it for getting inputs from the user.
     */
    protected abstract void inputs();

    /**
     * This method is called after de inputs() method.
     * Used for updating variables in your program.
     */
    protected abstract void update(float deltaT);

    /**
     * This is the last method called inside the loop.
     * Used for rendering the objects to the screen.
     */
    protected abstract void render();

    @Override
    public void run() {
        running = true;
        float uDelta = 0;
        float fDelta = 0;
        int frames = 0;
        int updates = 0;
        long timer = System.currentTimeMillis();
        long lastTime = System.nanoTime();

        while (running) {
            long now = System.nanoTime();
            uDelta += (now - lastTime);
            fDelta += (now - lastTime);
            lastTime = now;


            if (uDelta >= uOPTIMAL_TIME) {
                inputs();
                update(deltaT);
                KeyInput.getKeyboard().update();
                MouseInput.getMouse().update();
                updates++;
                uDelta -= uOPTIMAL_TIME;
            }

            if (fDelta >= fOPTIMAL_TIME) {
                render();
                frames++;
                fDelta -= fOPTIMAL_TIME;
            }

            try {
                Thread.sleep(sleep ? 1 : 0);
            } catch (InterruptedException e) {
            }


            if (System.currentTimeMillis() - timer >= 1000) {
                currentFPS = frames;
                currentUPS = updates;
                updates = 0;
                frames = 0;
                timer += 1000;
            }

        }
    }

    /**
     * Starts the loop
     */
    public void start() {
        if (running)
            return;
        new Thread(this, "GameLoop").start();
    }

    /**
     * Stops the game loop
     */
    public void stop() {
        running = false;
    }

    /**
     * Locks the frames per second to a specific value.
     *
     * @param fps frames per second.
     *            Range: 15 - 600.
     */
    public void capFPS(int fps) {
        if (fps < MIN_FPS) {
            fps = MIN_FPS;
        }
        if (fps > MAX_FPS) {
            fps = MAX_FPS;
        }

        fOPTIMAL_TIME = NANOSECOND / fps;
    }

    public void sleep(boolean sleep) {
        this.sleep = sleep;
    }

    /**
     * Set the frames per second in order to match the monitor refresh rate.
     *
     * @param vSync if vsync == true
     *              the fps will be capped at the same refresh rate of your screen.
     */
    public void setVSync(boolean vSync) {
        capFPS(MONITOR_REFRESH_RATE);
    }

    /**
     * Returns the frames per seconds of the game
     */
    public int getCurrentFPS() {
        return currentFPS;
    }

    public int getCurrentUPS() {
        return currentUPS;
    }

}
