package Main;


import java.util.ArrayList;

public class ITetronimo extends Shape {

    // first loop: rotational position
    // second loop: block
    // third loop: x and y axis
    int[][][] rotationArray = {{{0, 0}, {0, -1}, {0, -2}, {0, +1}},
            {{-2, 0}, {-1, 0}, {0, 0}, {+1, 0}}
    };
    int currentPositionInRotation = 0;
    int orange = color(250, 140, 0);


    ITetronimo() {
        for (int i = 0; i < rotationArray[currentPositionInRotation % 2].length; i++) {
            int x = rotationArray[currentPositionInRotation % 2][i][0];
            int y = rotationArray[currentPositionInRotation % 2][i][1];
            blockedSpaces.add(new Block(posX + x, posY + y, orange));
        }
    }

    void refreshBlockedSpaces() {
        for (int i = 0; i < rotationArray[currentPositionInRotation % 2].length ;i++) {
            int x = rotationArray[currentPositionInRotation % 2][i][0];
            int y = rotationArray[currentPositionInRotation % 2][i][1];
            blockedSpaces.get(i).refreshBlock(posX + x, posY + y);
        }
    }

    ArrayList<Block> getNextRotationBlockedSpaces() {
        ArrayList<Block> nextBlockedSpaces = new ArrayList<>();
        for (int i = 0; i < rotationArray[currentPositionInRotation % 2].length; i++) {
            int x = rotationArray[(currentPositionInRotation + 1) % 2][i][0];
            int y = rotationArray[(currentPositionInRotation + 1) % 2][i][1];
            nextBlockedSpaces.add(new Block(posX + x, posY + y, orange));
        }
        return nextBlockedSpaces;
    }

    void rotate() {
        currentPositionInRotation += 1;
        for (int i = 0; i < rotationArray[currentPositionInRotation % 2].length ;i++) {
            int x = rotationArray[currentPositionInRotation % 2][i][0];
            int y = rotationArray[currentPositionInRotation % 2][i][1];
            blockedSpaces.get(i).refreshBlock(posX + x, posY + y);
            refreshBlockedSpaces();
        }
    }

}
