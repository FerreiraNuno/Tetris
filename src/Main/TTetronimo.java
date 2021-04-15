package Main;


import java.util.ArrayList;

public class TTetronimo extends Shape {

    // first loop: rotational position
    // second loop: block
    // third loop: x and y axis
    int[][][] rotationArray = {{{0, 0}, {-1, 0}, {+1, 0}, {0, +1}},
                               {{0, 0}, {0, -1}, {-1, 0}, {0, +1}},
                               {{0, 0}, {-1, 0}, {+1, 0}, {0, -1}},
                               {{0, 0}, {0, +1}, {0, -1}, {+1, 0}},
             };
    int currentPositionInRotation = 0;
    int color = color(184, 64, 64);


    TTetronimo() {
        for (int i = 0; i < rotationArray[currentPositionInRotation % 4].length; i++) {
            int x = rotationArray[currentPositionInRotation % 4][i][0];
            int y = rotationArray[currentPositionInRotation % 4][i][1];
            blockedSpaces.add(new Block(posX + x, posY + y, color));
        }
    }

    void refreshBlockedSpaces() {
        for (int i = 0; i < rotationArray[currentPositionInRotation % 4].length ;i++) {
            int x = rotationArray[currentPositionInRotation % 4][i][0];
            int y = rotationArray[currentPositionInRotation % 4][i][1];
            blockedSpaces.get(i).refreshBlock(posX + x, posY + y);
        }
    }

    int getColor() {
        // GREEN
        return color(64, 186, 64);
    }

    ArrayList<Block> getNextRotationBlockedSpaces() {
        ArrayList<Block> nextBlockedSpaces = new ArrayList<>();
        for (int i = 0; i < rotationArray[currentPositionInRotation % 4].length; i++) {
            int x = rotationArray[currentPositionInRotation % 4][i][0];
            int y = rotationArray[currentPositionInRotation % 4][i][1];
            nextBlockedSpaces.add(new Block(posX + x, posY + y, color));
        }
        // TODO change this one
        return nextBlockedSpaces;
    }

    void rotate() {
        currentPositionInRotation += 1;
        for (int i = 0; i < rotationArray[currentPositionInRotation % 4].length ;i++) {
            int x = rotationArray[currentPositionInRotation % 4][i][0];
            int y = rotationArray[currentPositionInRotation % 4][i][1];
            blockedSpaces.get(i).refreshBlock(posX + x, posY + y);
            refreshBlockedSpaces();
        }
    }

}
