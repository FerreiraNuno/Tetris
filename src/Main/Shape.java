package Main;


import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;

public class Shape extends PApplet {
    int currentPositionInRotation = 0;
    int posX;
    int posY;
    ArrayList<Block> blockedSpaces = new ArrayList<>();
    int color;
    int[][][] rotationArray;
    Tetris game;

    Shape(Tetris game) {
        this.game = game;
        this.posX = 4;
        this.posY = 0;
        choseShape();
        for (int i = 0; i < rotationArray[currentPositionInRotation % 4].length; i++) {
            int x = rotationArray[currentPositionInRotation % 4][i][0];
            int y = rotationArray[currentPositionInRotation % 4][i][1];
            blockedSpaces.add(new Block(posX + x, posY + y, color));
        }
    }

    void choseShape() {
        int output = new Random().nextInt(7);
        if (output == 0) {
            rotationArray = new int[][][]{{{0, 0}, {1, 0}, {0, 1}, {1, 1}},
                    {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
                    {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
                    {{0, 0}, {1, 0}, {0, 1}, {1, 1}}
            };
            color = color(184, 64, 64);
        } else if (output == 1) {
            rotationArray = new int[][][]{{{0, 0}, {0, -1}, {-1, -1}, {+1, 0}},
                    {{0, 0}, {+1, 0}, {+1, -1}, {0, +1}},
                    {{0, 0}, {0, -1}, {-1, -1}, {+1, 0}},
                    {{0, 0}, {+1, 0}, {+1, -1}, {0, +1}}
            };
            color = color(64, 64, 186);
        } else if (output == 2) {
            rotationArray = new int[][][]{{{0, 0}, {-1, 0}, {0, -1}, {+1, -1}},
                    {{0, 0}, {+1, 0}, {+1, +1}, {0, -1}},
                    {{0, 0}, {-1, 0}, {0, -1}, {+1, -1}},
                    {{0, 0}, {+1, 0}, {+1, +1}, {0, -1}}
            };
            color = color(210, 210, 64);
        }else if (output == 3) {
            rotationArray = new int[][][]{{{0, 0}, {0, -1}, {0, +1}, {-1, +1}},
                    {{0, 0}, {+1, 0}, {-1, 0}, {-1, -1}},
                    {{0, 0}, {0, -1}, {0, +1}, {+1, -1}},
                    {{0, 0}, {-1, 0}, {+1, 0}, {+1, +1}},
            };
            color = color(64, 184, 184);
        } else if (output == 4) {
            rotationArray = new int[][][]{{{0, 0}, {0, -1}, {0, +1}, {+1, +1}},
                    {{0, 0}, {+1, 0}, {-1, 0}, {-1, +1}},
                    {{0, 0}, {0, -1}, {0, +1}, {-1, -1}},
                    {{0, 0}, {-1, 0}, {+1, 0}, {+1, -1}},
            };
            color = color(184, 64, 184);
        } else if (output == 5) {
            rotationArray = new int[][][]{{{0, 0}, {0, -1}, {0, -2}, {0, +1}},
                    {{-2, 0}, {-1, 0}, {0, 0}, {+1, 0}},
                    {{0, 0}, {0, -1}, {0, -2}, {0, +1}},
                    {{-2, 0}, {-1, 0}, {0, 0}, {+1, 0}},
            };
            color = color(250, 140, 0);
        } else {
            rotationArray = new int[][][]{{{0, 0}, {-1, 0}, {+1, 0}, {0, +1}},
                    {{0, 0}, {0, -1}, {-1, 0}, {0, +1}},
                    {{0, 0}, {-1, 0}, {+1, 0}, {0, -1}},
                    {{0, 0}, {0, +1}, {0, -1}, {+1, 0}},
            };
            color = color(64, 186, 64);
        }
    }

    void refreshBlockedSpaces() {
        for (int i = 0; i < rotationArray[currentPositionInRotation % 4].length ;i++) {
            int x = rotationArray[currentPositionInRotation % 4][i][0];
            int y = rotationArray[currentPositionInRotation % 4][i][1];
            blockedSpaces.get(i).refreshBlock(posX + x, posY + y);
        }
    }

    ArrayList<Block> getNextRotationBlockedSpaces() {
        ArrayList<Block> nextBlockedSpaces = new ArrayList<>();
        for (int i = 0; i < rotationArray[currentPositionInRotation % 4].length; i++) {
            int x = rotationArray[(currentPositionInRotation + 1) % 4][i][0];
            int y = rotationArray[(currentPositionInRotation + 1) % 4][i][1];
            nextBlockedSpaces.add(new Block(posX + x, posY + y, color));
        }
        // TODO change this one
        return nextBlockedSpaces;
    }

    void rotate() {
        boolean rotationNotPossible = false;
        for (Block nextTetronimo : getNextRotationBlockedSpaces()) {
            if (nextTetronimo.x > 9 || nextTetronimo.x < 0) {
                System.out.println("rotation not possible");
                rotationNotPossible = true;
            }
            for (Block blocked: game.totalBlockedSpaces) {
                if (nextTetronimo.x == blocked.x && nextTetronimo.y == blocked.y) {
                    rotationNotPossible = true;
                    System.out.println("rotation not possible");
                    break;
                }
            }
        }
        if (!rotationNotPossible) {
            currentPositionInRotation += 1;
            for (int i = 0; i < rotationArray[currentPositionInRotation % 4].length ;i++) {
                int x = rotationArray[currentPositionInRotation % 4][i][0];
                int y = rotationArray[currentPositionInRotation % 4][i][1];
                blockedSpaces.get(i).refreshBlock(posX + x, posY + y);
                refreshBlockedSpaces();
            }
        }
    }

    void drop() {
        posY += 1;
        refreshBlockedSpaces();
    }

    void moveLeft() {
        boolean otherTetronimoLeft = false;
        for (Block tetronimo : blockedSpaces) {
            if (tetronimo.x == 0) {
                otherTetronimoLeft = true;
            }
            for (Block blocked: game.totalBlockedSpaces) {
                if (tetronimo.x == blocked.x + 1 & tetronimo.y == blocked.y) {
                    otherTetronimoLeft = true;
                    break;
                }
            }
        }
        if (!otherTetronimoLeft) {
            posX -= 1;
            refreshBlockedSpaces();
        }
    }

    void moveRight() {
        boolean otherTetronimoRight = false;
        for (Block tetronimo : blockedSpaces) {
            if (tetronimo.x == 9) {
                otherTetronimoRight = true;
            }
            for (Block blocked: game.totalBlockedSpaces) {
                if (tetronimo.x == blocked.x - 1 && tetronimo.y == blocked.y) {
                    otherTetronimoRight = true;
                    break;
                }
            }
        }
        if (!otherTetronimoRight) {
            posX += 1;
            refreshBlockedSpaces();
        }
    }
}