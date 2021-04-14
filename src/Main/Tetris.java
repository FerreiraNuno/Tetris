package Main;

import processing.core.PApplet;

import java.util.ArrayList;

public class Tetris extends PApplet {

    public static void main(String[] args) {
        String[] appArgs = {"Tetris"};
        Tetris Tetris = new Tetris();
        PApplet.runSketch(appArgs, Tetris);
    }

    ////////////
    // VARIABLES
    ////////////

    int white = color(255, 255, 255);
    int red = color(184, 64, 64);

    // Block Size
    public static int bs = 60;

    Shape currentShape = new Shape();
    ArrayList<Shape> oldShapes = new ArrayList<>();
    ArrayList<Block> TotalBlockedSpaces = new ArrayList<>();

    ////////////
    // METHODS
    ////////////

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
        VerticalCollisionCheck();
    }

    public void VerticalCollisionCheck() {
        boolean otherTetronimoBelow = false;
        for (Block tetronimo: currentShape.blockedSpaces) {
            for (Block blocked: TotalBlockedSpaces) {
                if (tetronimo.x == blocked.x && tetronimo.y == blocked.y - 1) {
                    otherTetronimoBelow = true;
                    break;
                }
            }
        }
        if (currentShape.posY >= 14 || otherTetronimoBelow) {
            TotalBlockedSpaces.addAll(currentShape.blockedSpaces);// TODO use this line in the tetrisCheck() method
            oldShapes.add(currentShape);
            currentShape = new Shape();
            tetrisCheck();
            drawSquare(currentShape.posX, currentShape.posY);
        }
    }


    private void tetrisCheck() {
        int tetrisCount= 0;
        // TODO check if at ANY line (Y-Axis from bottom to top) there is a Tetris.
        //  If yes, then remove that line and continue the check.
        //  after all Tetris checks, make the blocks fall down by the number of
        //  Tetrisses made (reduce Y number of old block in "oldShapes"
        boolean isTetris = false;
    }

    public void keyPressed() {
        // down movement
        if (key == 's') {
            drawBackground();
            VerticalCollisionCheck();
            currentShape.posY += 1;
            currentShape.refreshBlockedSpaces();
            drawSquare(currentShape.posX, currentShape.posY);
        }
        // left movement
        boolean otherTetronimoLeft = false;
        for (Block tetronimo: currentShape.blockedSpaces) {
            for (Block blocked: TotalBlockedSpaces) {
                if (tetronimo.x == blocked.x + 1 && tetronimo.y == blocked.y) {
                    otherTetronimoLeft = true;
                    break;
                }
            }
        }
        if (key == 'a' &&! otherTetronimoLeft) {
            drawBackground();
            if (currentShape.posX > 0) {
                currentShape.posX -= 1;
                currentShape.refreshBlockedSpaces();
            }
            drawSquare(currentShape.posX, currentShape.posY);
        }
        // right movement
        boolean otherTetronimoRight = false;
        for (Block tetronimo: currentShape.blockedSpaces) {
            for (Block blocked: TotalBlockedSpaces) {
                if (tetronimo.x == blocked.x - 1 && tetronimo.y == blocked.y) {
                    otherTetronimoRight = true;
                    break;
                }
            }
        }
        if (key == 'd' &&! otherTetronimoRight) {
            drawBackground();
            if (currentShape.posX < 8) {
                currentShape.posX += 1;
                currentShape.refreshBlockedSpaces();
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
