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
    Shape currentShape = spawnNewShape();
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
        if (millis() % 800 < 15 && currentShape.posY < 30) {
            drawBackground();
            currentShape.posY += 1;
            currentShape.refreshBlockedSpaces();
            drawShape();
        }
    }

    public Shape spawnNewShape() {
        int output = (int) Math.floor(random(1, 2.99F));
        System.out.println("spawning new Shape");
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
        for (Block tetronimo : currentShape.blockedSpaces) {
            if (tetronimo.y >= 15 || otherTetronimoBelow) {
                totalBlockedSpaces.addAll(currentShape.blockedSpaces);
                tetrisCheck();
                drawShape();
                currentShape = spawnNewShape();
            }
        }
    }

    private void tetrisCheck() {
        for (int row = 0; row < 17; row++) {
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
            currentShape.posY += 1;
            currentShape.refreshBlockedSpaces();
            drawShape();
        }

        // left movement
        if (key == 'a') {
            boolean otherTetronimoLeft = false;
            for (Block tetronimo : currentShape.blockedSpaces) {
                if (tetronimo.x == 0) {
                    otherTetronimoLeft = true;
                }
                for (Block blocked: totalBlockedSpaces) {
                    if (tetronimo.x == blocked.x + 1 & tetronimo.y == blocked.y) {
                        otherTetronimoLeft = true;
                        break;
                    }
                }
            }
            if (!otherTetronimoLeft) {
                drawBackground();
                currentShape.posX -= 1;
                currentShape.refreshBlockedSpaces();
                drawShape();
            }
        }

        // right movement
        if (key == 'd') {
            boolean otherTetronimoRight = false;
            for (Block tetronimo : currentShape.blockedSpaces) {
                if (tetronimo.x == 9) {
                    otherTetronimoRight = true;
                }
                for (Block blocked: totalBlockedSpaces) {
                    if (tetronimo.x == blocked.x - 1 && tetronimo.y == blocked.y) {
                        otherTetronimoRight = true;
                        break;
                    }
                }
            }
            if (!otherTetronimoRight) {
                drawBackground();
                currentShape.posX += 1;
                currentShape.refreshBlockedSpaces();
                drawShape();
            }
        }

        // rotation
        if (key == 'p') {
            boolean rotationNotPossible = false;
            for (Block nextTetronimo : currentShape.getNextRotationBlockedSpaces()) {
                if (nextTetronimo.x > 9 || nextTetronimo.x < 0) {
                    rotationNotPossible = true;
                }
                for (Block blocked: totalBlockedSpaces) {
                    if (nextTetronimo.x == blocked.x && nextTetronimo.y == blocked.y) {
                        rotationNotPossible = true;
                        break;
                    }
                }
            }
            if (!rotationNotPossible) {
                drawBackground();
                currentShape.rotate();
                drawShape();
            }
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
