package Main;

import processing.core.PApplet;
import processing.sound.*;

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

    //Color for background
    int white = color(255, 255, 255);
    SoundFile music;
    // Block Size
    public static int bs = 60;
    // Current Shape that can be moved
    Shape currentShape = spawnNewShape(); // TODO create function to choose Shape at random
    // Array with all Instances of Block Objects that are currently
    // creating an obstacle for the currentShape
    ArrayList<Block> totalBlockedSpaces = new ArrayList<>();

    ////////////
    // METHODS
    ////////////

    // Screen Size according to Block Size
    public void settings() {
        size(10 * bs, 16 * bs);
    }

    // setup before the game even starts
    public void setup() {
        //music = new SoundFile(this, "tetris.wav");
        drawBackground();
        drawShape();
        //music.play();
    }

    public void draw() {
        // push block down every second
        VerticalCollisionCheck();
        if (millis() % 1000 < 15 && currentShape.posY < 30) {
            drawBackground();
            currentShape.posY += 1;
            currentShape.refreshBlockedSpaces();
            drawShape();
        }
    }

    public Shape spawnNewShape() {
        return new TTetronimo();
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
        if (currentShape.posY >= 14 || otherTetronimoBelow) {
            totalBlockedSpaces.addAll(currentShape.blockedSpaces);
            currentShape = spawnNewShape(); //TODO change Square() to Shape
            tetrisCheck();
            drawShape();
        }
    }

    private void tetrisCheck() {
        // TODO check if at ANY line (Y-Axis from bottom to top) there is a Tetris.
        //  If yes, then remove that line and make all blocks above drop down by one.
        System.out.println("new Tetris check");
        for (int row = 0; row < 17; row++) {
            boolean isTetris = false;
            int rowBlockCounter = 0;
            for (Block block : totalBlockedSpaces) {
                if (block.y == row) {
                    rowBlockCounter += 1;
                }
            }
            if (rowBlockCounter > 0) {
                System.out.println(rowBlockCounter);
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
                System.out.println("dropping by one");
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
            currentShape.posY += 1;
            currentShape.refreshBlockedSpaces();
            drawShape();
        }
        // left movement
        boolean otherTetronimoLeft = false;
        for (Block tetronimo: currentShape.blockedSpaces) {
            if (tetronimo.x == 0) {
                otherTetronimoLeft = true;
            }
            for (Block blocked: totalBlockedSpaces) {
                if (tetronimo.x == blocked.x + 1 & tetronimo.y == blocked.y) {
                    System.out.println(otherTetronimoLeft);
                    break;
                }
            }
        }
        if (key == 'a' & !otherTetronimoLeft) {
            drawBackground();
            currentShape.posX -= 1;
            currentShape.refreshBlockedSpaces();
            drawShape();
        }
        // right movement
        boolean otherTetronimoRight = false;
        for (Block tetronimo: currentShape.blockedSpaces) {
            if (tetronimo.x == 15) {
                otherTetronimoRight = true;
            }
            for (Block blocked: totalBlockedSpaces) {
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
            drawShape();
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
        for (Block block : totalBlockedSpaces) {
            square(block.x*bs, block.y*bs, bs);
        }
    }

    void drawShape() {
        for (Block block : currentShape.blockedSpaces) {
            int x = block.x;
            int y = block.y;
            fill(currentShape.getColor());
            square(x*bs, y*bs, bs);
        }

        //square(x*bs + bs, y*bs, bs);
        //square(x*bs, y*bs + bs, bs);
        //square(x*bs + bs, y*bs + bs, bs);
    }
}
