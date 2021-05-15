package Main;

import processing.core.PApplet;
import processing.sound.SoundFile;

import java.util.ArrayList;
import java.util.Random;


public class Tetris extends PApplet {

    public static void main(String[] args) {
        String[] appArgs = {"Tetris"};
        Tetris Tetris = new Tetris();
        PApplet.runSketch(appArgs, Tetris);
    }

    ////////////
    // VARIABLES
    ////////////
    SoundFile music;
    Shape currentShape = spawnNewShape();
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
        music = new SoundFile(this, "tetris.wav");
        drawBackground();
        drawShape();
        music.loop();
    }

    public void draw() {
        // push block down every second
        if (millis() % 100 < 15 && currentShape.posY < 30) {
            VerticalCollisionCheck();
            drawBackground();
            currentShape.posY += 1;
            currentShape.refreshBlockedSpaces();
            drawShape();
        }
    }

    public Shape spawnNewShape() {
        int output = new Random().nextInt(7);
        if (output == 0) {
            return new SquareTetronimo();
        } else if (output == 1) {
            return new zTetronimo();
        } else if (output == 2) {
            return new sTetronimo();
        }else if (output == 3) {
            return new LTetronimo();
        }
        else if (output == 4) {
            return new LReverseTetronimo();
        } else if (output == 5) {
            return new ITetronimo();
        } else {
            return new tTetronimo();
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
                currentShape = spawnNewShape();
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
                    System.out.println("rotation not possible");
                    rotationNotPossible = true;
                }
                for (Block blocked: totalBlockedSpaces) {
                    if (nextTetronimo.x == blocked.x && nextTetronimo.y == blocked.y) {
                        rotationNotPossible = true;
                        System.out.println("rotation not possible");
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
