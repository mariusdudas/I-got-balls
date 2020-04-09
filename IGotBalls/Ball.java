package IGotBalls;

import java.awt.*;
import java.util.Random;

public class Ball extends Thread {
    private int diameter;
    private int x;
    private int y;
    private int stepX;
    private int stepY;
    private int maxWidth;
    private int maxHeight;
    private Color color;
    private boolean moving;
    private MyPanel panel;

    Ball(MyPanel panel) {
        this.panel = panel;
        moving = true;
        maxHeight = panel.getHeight();
        maxWidth = panel.getWidth();
        // diameter is always positive between 20 and maxHeight / 2
        diameter = (int) (new Random().nextDouble() * maxHeight / 2) + 20;
        // x is always positive between 0 and maxWidth - diameter
        x = (int) (new Random().nextDouble() * (maxWidth - diameter));
        // y is always positive between 0 and maxHeight - diameter
        y = (int) (new Random().nextDouble() * (maxHeight - diameter));
        // stepX and stepY are randomly generated between 1 and 10
        stepX = (int) (new Random().nextDouble() * 9 + 1);
        stepY = (int) (new Random().nextDouble() * 9 + 1);
        // color is obtained by combining r, g, b and alpha random values
        int r = (int) (new Random().nextDouble() * 255);
        int g = (int) (new Random().nextDouble() * 255);
        int b = (int) (new Random().nextDouble() * 255);
        int alpha = (int) (new Random().nextDouble() * 255);
        color = new Color(r, g, b, alpha);
    }

    @Override
    public void run() {
        while (moving) {
            super.run();
            x += stepX;
            y += stepY;
            if (x <= 0) {
                stepX = -stepX;
                x = 0;
            }
            if (x >= maxWidth - diameter) {
                stepX = -stepX;
                x = maxWidth - diameter;
            }
            if (y <= 0) {
                stepY = -stepY;
                y = 0;
            }
            if (y >= maxHeight - diameter) {
                stepY = -stepY;
                y = maxHeight - diameter;
            }
            panel.repaint();
            try {
                sleep(30);
            } catch (InterruptedException ie) {
                System.err.println("Thread was interrupted: " + ie.getLocalizedMessage());
            }
        }
    }

    public void stopTheBall() {
        moving = false;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }
}
