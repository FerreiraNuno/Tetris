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
    Shape currentShape = new Shape(this);
    ArrayList<Block> totalBlockedSpaces = new ArrayList<>(); // Array with all Instances of Block Objects that are currently an obstacle

    ////////////
    // METHODS
    ////////////
    public static int blockSize = 50;
    public static int height = 20;
    public void settings() {
        size(10 * blockSize, height * blockSize);
    }

    // setup before the game even starts
    public void setup() {
        drawBackground();
        drawShape();
    }

    public void draw() {
        // push block down every second
        if (frameCount % 10 == 0) {
            VerticalCollisionCheck();
            drawBackground();
            currentShape.drop();
            drawShape();
        }
    }


    public void VerticalCollisionCheck() {
        boolean otherTetronimoBelow = false;
        for (Block tetronimo: currentShape.blockedSpaces) {
            for (Block blocked: totalBlockedSpaces) {
                if (tetronimo.x == blocked.x && tetronimo.y == blocked.y - 1) {
                    otherTetronimoBelow = true;
                    break;
                }
            }
        }
        for (Block tetronimo : currentShape.blockedSpaces) {
            //
            if (tetronimo.y >= height - 1 || otherTetronimoBelow && tetronimo.y > 1) {
                totalBlockedSpaces.addAll(currentShape.blockedSpaces);
                tetrisCheck();
                drawShape();
                currentShape = new Shape(this);
                break;
            }
        }
    }

    private void tetrisCheck() {
        for (int row = 0; row < height + 1; row++) {
            boolean isTetris = false;
            int rowBlockCounter = 0;
            for (Block block : totalBlockedSpaces) {
                if (block.y == row) {
                    rowBlockCounter += 1;
                }
            }
            ArrayList<Block> blocksToRemove = new ArrayList<>();
            if (rowBlockCounter == 10) {
                isTetris = true;
                for (Block block : totalBlockedSpaces) {
                    if (block.y == row) {
                        blocksToRemove.add(block);
                    }
                }
                totalBlockedSpaces.removeAll(blocksToRemove);
            }
            if (isTetris) {
                for (int rowToBeRemoved = row - 1; rowToBeRemoved > 0; rowToBeRemoved--) {
                    for (Block block : totalBlockedSpaces) {
                        if (block.y == rowToBeRemoved) {
                            block.y += 1;
                        }
                    }
                }
            }
        }
    }

    public void keyPressed() {
        // down movement
        if (key == 's') {
            drawBackground();
            VerticalCollisionCheck();
            currentShape.drop();
            drawShape();
        }
        // left movement
        if (key == 'a') {
            currentShape.moveLeft();
            drawBackground();
            drawShape();
        }
        // right movement
        if (key == 'd') {
            currentShape.moveRight();
            drawBackground();
            drawShape();
        }
        // rotation
        if (key == 'p') {
            currentShape.rotate();
            drawBackground();
            drawShape();
        }
    }

    void drawBackground() {
        background(color(255, 255, 255));
        // creates horizontal lines for visual orientation
        for (int i = 0; i < 10; i++) {
            strokeWeight(2);
            line(blockSize * i, 0, blockSize * i, blockSize * height);
        }
        // creates vertical lines for visual orientation
        for (int i = 0; i < height; i++) {
            strokeWeight(2);
            line(0, blockSize * i, blockSize * height , blockSize * i);
        }
        // draws placed Blocks
        for (Block block : totalBlockedSpaces) {
            fill(block.color);
            square(block.x*blockSize, block.y*blockSize, blockSize);
        }
    }

    void drawShape() {
        for (Block block : currentShape.blockedSpaces) {
            int x = block.x;
            int y = block.y;
            fill(block.color);
            square(x*blockSize, y*blockSize, blockSize);
        }


        //square(x*bs + bs, y*bs, bs);
        //square(x*bs, y*bs + bs, bs);
        //square(x*bs + bs, y*bs + bs, bs);
    }
}
