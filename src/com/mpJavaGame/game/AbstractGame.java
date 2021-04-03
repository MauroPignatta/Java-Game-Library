package com.mpJavaGame.game;

import com.github.strikerx3.jxinput.XInputDevice14;
import com.github.strikerx3.jxinput.exceptions.XInputNotLoadedException;
import com.mpJavaGame.input.ControllerInput;
import com.mpJavaGame.input.KeyInput;
import com.mpJavaGame.input.MouseInput;
import com.sun.istack.internal.Nullable;

import java.awt.*;

public abstract class AbstractGame implements Runnable {

    private static final float NANOSECOND = 1000000000;
    private static final int MIN_FPS = 15;
    private static final int MAX_FPS = 600;

    private int MONITOR_REFRESH_RATE;

    private float uOPTIMAL_TIME;
    private float fOPTIMAL_TIME;
    private float deltaT;

    private boolean running;
    private int currentFPS;
    private int currentUPS;
    private boolean sleep;

    private GameWindow window;
    private Renderer renderer;
    private XInputDevice14 device;

    public AbstractGame(@Nullable GameConfig config) {
        if (config == null) config = new GameConfig();
        init(config);
    }

    private void init(GameConfig config) {
        uOPTIMAL_TIME = NANOSECOND / config.getUpdatePerSecond();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        DisplayMode dm = gs[0].getDisplayMode();
        int hertz = dm.getRefreshRate();
        hertz = (hertz == DisplayMode.REFRESH_RATE_UNKNOWN) ? 60 : hertz;
        MONITOR_REFRESH_RATE = hertz;
        deltaT = 1f / config.getUpdatePerSecond();
        initWindow(config);
        renderer = new Renderer(config.getWidth(), config.getHeight(), this);
        window.makeVisible();

        if (config.isControllerEnabled() && XInputDevice14.isAvailable()) {
            try {
                device = XInputDevice14.getDeviceFor(0);
                ControllerInput.init(device.getComponents());
            } catch (XInputNotLoadedException e) {
                e.printStackTrace();
            }
        }

        setup();
    }

    private void initWindow(GameConfig config) {
        String title = config.getTitle();
        int w = config.getWidth();
        int h = config.getHeight();
        window = new GameWindow(title, w, h);
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
    protected abstract void render(Renderer renderer);

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
                if(device.poll()){
                    ControllerInput.getController().poll();
                }
                inputs();
                update(deltaT);
                KeyInput.getKeyboard().update();
                MouseInput.getMouse().update();
                ControllerInput.getController().update();

                updates++;
                uDelta -= uOPTIMAL_TIME;
            }

            if (fDelta >= fOPTIMAL_TIME) {
                renderer.clear();
                render(renderer);
                window.draw();
                frames++;
                fDelta -= fOPTIMAL_TIME;
            }

            try {
                Thread.sleep(sleep ? 1 : 0);
            } catch (InterruptedException ignored) {
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

    public GameWindow getWindow() {
        return this.window;
    }

    public KeyInput getKeyboard() {
        return KeyInput.getKeyboard();
    }

    public MouseInput getMouse() {
        return MouseInput.getMouse();
    }
}
