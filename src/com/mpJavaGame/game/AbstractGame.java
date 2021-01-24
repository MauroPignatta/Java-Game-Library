package com.mpJavaGame.game;

import com.mpJavaGame.input.KeyInput;
import com.mpJavaGame.input.MouseInput;

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

    /**
     * Creates a new game Loop
     *
     * @param UPS Amount of updates per seconds.
     *            This determines how many times the loop will iterate in a second.
     */
    public AbstractGame(GameConfig config) {
        if(config == null) config = new GameConfig();
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
        window.makeVisible();
        setup();
    }

    private void initWindow(GameConfig config){
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
    protected abstract void render(Graphics2D g2d);

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
                render(window.getGraphics());
                window.endDrawingGraphics();
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

    public GameWindow getWindow(){
        return this.window;
    }

    public KeyInput getKeyboard(){return KeyInput.getKeyboard();}

    public MouseInput getMouse(){return MouseInput.getMouse();}
}
