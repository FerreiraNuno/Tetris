package Main;

import processing.core.PApplet;

import java.util.ArrayList;

public class Tetris extends PApplet {

    public static void main(String[] args) {
        String[] appArgs = {"Tetris"};
        Tetris Tetris = new Tetris();
        PApplet.runSketch(appArgs, Tetris);
    }

    int white = color(255, 255, 255);
    int red = color(184, 64, 64);

    // Block Size
    public static int bs = 60;

    Shape currentShape = new Shape();
    ArrayList<Shape> oldShapes = new ArrayList<>();


    // Screen Size according to Block Size
    public void settings() {
        size(10 * bs, 16 * bs);
    }

    // setup before the game even starts
    public void setup() {
        drawBackground();
        drawSquare(currentShape.posX, currentShape.posY);
    }

    public void draw() {
        // push block down every second
        if (millis() % 1000 < 15 && currentShape.posY < 30) {
            drawBackground();
            currentShape.posY += 1;
            currentShape.refreshBlockedSpaces();
            drawSquare(currentShape.posX, currentShape.posY);
        }
        if (currentShape.posY >= 14) {
            oldShapes.add(currentShape);
            currentShape = new Shape();
            drawSquare(currentShape.posX, currentShape.posY);
        }
    }

    public void keyPressed() {
        // left movement
        if (key == 'a') {
            drawBackground();
            if (currentShape.posX > 0) {
                currentShape.posX -= 1;
                currentShape.refreshBlockedSpaces();
            }
            drawSquare(currentShape.posX, currentShape.posY);
        }
        // right movement
        if (key == 'd') {
            drawBackground();
            if (currentShape.posX < 8) {
                currentShape.posX += 1;
                currentShape.refreshBlockedSpaces();
            }
            drawSquare(currentShape.posX, currentShape.posY);
        }
        // down movement
        if (key == 's') {
            drawBackground();
            if (currentShape.posY < 14) {
                currentShape.posY += 1;
            }
            drawSquare(currentShape.posX, currentShape.posY);
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
        for (Shape shape : oldShapes) {
            drawSquare(shape.posX, shape.posY);
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
