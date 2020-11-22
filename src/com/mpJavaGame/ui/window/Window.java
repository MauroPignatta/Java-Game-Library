package com.mpJavaGame.ui.window;

import com.mpJavaGame.helper.ImageHelper;
import com.mpJavaGame.input.KeyInput;
import com.mpJavaGame.input.MouseInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class Window {

    private boolean isFullScreen;
    private Dimension size, resize;
    private Dimension screenSize;
    public static float sx, sy;

    private Canvas canvas;
    private JFrame frame;

    private long lastSwitch;

    private RenderingHints hints;
    private boolean antiAliasing;

    public Window(String title, int width, int height) {
        init(title, width, height);
    }

    private void init(String title, int width, int height) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        size = new Dimension(width, height);
        resize = new Dimension(width, height);
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        sx = 1f;
        sy = 1f;

        canvas = new Canvas();
        canvas.setSize(size);
        canvas.setVisible(true);
        canvas.setFocusable(false);
        frame.add(canvas);
        addKeyboardSupport();
        addMouseSupport();


        hints = new RenderingHints(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        hints.put(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        lastSwitch = System.currentTimeMillis();
    }

    public void setWindowIcon(BufferedImage icon) {
        frame.setIconImage(icon);
    }

    public void setTitle(String title) {
        frame.setTitle(title);
    }

    private void addKeyboardSupport() {
        KeyInput k = KeyInput.getKeyboard();
        frame.addKeyListener(k.getKeyListener());
    }

    private void addMouseSupport() {
        MouseInput m = MouseInput.getMouse();
        canvas.addMouseListener(m.getListener());
        canvas.addMouseMotionListener((MouseMotionListener) m.getListener());
        canvas.addMouseWheelListener((MouseWheelListener) m.getListener());
    }

    public Dimension getScreenSize() {
        return screenSize;
    }

    public void fullScreen() {
        long now = System.currentTimeMillis();
        if (!isFullScreen && now - lastSwitch > 500) {
            isFullScreen = true;
            resize(screenSize.width, screenSize.height);
            lastSwitch = now;
        }
    }

    public void windowed() {
        long now = System.currentTimeMillis();
        if (isFullScreen && now - lastSwitch > 500) {
            isFullScreen = false;
            resize(resize.width, resize.height);
            lastSwitch = now;
        }
    }

    public void resize(int width, int height) {
        boolean wasVisible = frame.isVisible();
        if (!isFullScreen) {
            resize.width = width;
            resize.height = height;
        }
        restartWindow(isFullScreen);
        canvas.setSize(width, height);
        calculateScale();

        if (wasVisible)
            makeVisible();
    }

    public Graphics2D getGraphics() {
        if (canvas.getBufferStrategy() == null) {
            canvas.createBufferStrategy(3);
        }
        Graphics2D g = (Graphics2D) canvas.getBufferStrategy().getDrawGraphics();
        clearScreen(g);
        g.scale(sx, sy);
        if (antiAliasing)
            g.setRenderingHints(hints);
        return g;
    }

    /**
     * Disposes graphics and shows it in the screen.
     */
    public void endDrawingGraphics() {
        disposeGraphics();
        showGraphics();
    }

    public void makeVisible() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void clearScreen(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.clearRect(0, 0, screenSize.width, screenSize.height);
        g.fillRect(0, 0, screenSize.width, screenSize.height);
    }

    private void calculateScale() {
        sx = (float) canvas.getWidth() / size.width;
        sy = (float) canvas.getHeight() / size.height;
    }

    private void disposeGraphics() {
        canvas.getBufferStrategy().getDrawGraphics().dispose();
    }

    private void showGraphics() {
        canvas.getBufferStrategy().show();
    }

    private void restartWindow(boolean undecorated) {
        frame.setVisible(false);
        frame.dispose();
        frame.setUndecorated(undecorated);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void screenshot() {
        try {
            Robot robot = new Robot();
            BufferedImage image = robot.createScreenCapture(screenshotBounds());
            String time = LocalDateTime.now().toString();
            time = time.replace("T", "_");
            time = time.replace("-", "").replace(":", "").replace(".", "");
            ImageHelper.saveImage(image, "png", time + ".png");
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private Rectangle screenshotBounds() {
        Rectangle rectangle = frame.getBounds();
        Point p = canvas.getLocationOnScreen();
        rectangle.x = p.x;
        rectangle.y = p.y;
        rectangle.width = canvas.getWidth();
        rectangle.height = canvas.getHeight();
        return rectangle;
    }

    // Getters & setters

    public void setAntiAliasing(boolean antiAliasing) {
        this.antiAliasing = antiAliasing;
    }

    public int getWidth() {
        return size.width;
    }

    public int getHeight() {
        return size.height;
    }

    public float getScaleX() {
        return sx;
    }

    public float getScaleY() {
        return sy;
    }
}
