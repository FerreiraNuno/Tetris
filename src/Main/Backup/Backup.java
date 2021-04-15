package Main.Backup;

import processing.core.PApplet;

public class Backup extends PApplet {

    int white = color(255, 255, 255);
    int red = color(184, 64, 64);
    int green = color(64, 186, 64);
    int blue = color(64, 64, 186);
    int yellow = color(184, 184, 64);
    int cyan = color(64, 184, 184);
    int lila = color(184, 64, 184);

    // Block Size
    public static int bs = 60;

    int posX = 4;
    int posY = 0;

    // Screen Size according to Block Size
    public void settings() {
        size(10 * bs, 16 * bs);
    }

    // setup before the game even starts
    public void setup() {
        drawBackground();
        drawSquare(posX, posY);
    }

    public void draw() {
        if (millis() % 1000 < 15 && posY < 14) {
            drawBackground();
            posY += 1;
            drawSquare(posX, posY);
        }
    }

    public void keyPressed() {
        if (key == 'a') {
            drawBackground();
            if (posX > 0) {
                posX -= 1;
            }
            drawSquare(posX, posY);
        }

        if (key == 'd') {
            drawBackground();
            if (posX < 8) {
                posX += 1;
            }
            drawSquare(posX, posY);
        }
        if (key == 's') {
            drawBackground();
            if (posY < 14) {
                posY += 1;
            }
            drawSquare(posX, posY);
        }
    }

    void drawBackground() {
        background(white);
        // creates horizontal lines for visual orientation
        for (int i = 0; i < 10; i++) {
            strokeWeight(2);
            line(bs * i, 0, bs * i, bs * 16);
        }
        // creates vertical lines for visual orientation
        for (int i = 0; i < 16; i++) {
            strokeWeight(2);
            line(0, bs * i, bs * 16 , bs * i);
        }
    }

    void drawSquare(int x, int y) {
        fill(red);
        square(x*bs, y*bs, bs);
        square(x*bs + bs, y*bs, bs);
        square(x*bs, y*bs + bs, bs);
        square(x*bs + bs, y*bs + bs, bs);
    }

}