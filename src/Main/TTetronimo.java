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

    TTetronimo() {
        int color = color(184, 64, 64);
        /*
        for (int[] block : rotationArray[0]) {
            blockedSpace1 = new Block(posX, posY, color);
            blockedSpace2 = new Block(posX + 1, posY, color);
            blockedSpace3 = new Block(posX - 1, posY, color);
            blockedSpace4 = new Block(posX, posY + 1, color);
            blockedSpaces.add(blockedSpace1);
            blockedSpaces.add(blockedSpace2);
            blockedSpaces.add(blockedSpace3);
            blockedSpaces.add(blockedSpace4);
        }

         */


        blockedSpace1 = new Block(posX, posY, color);
        blockedSpace2 = new Block(posX + 1, posY, color);
        blockedSpace3 = new Block(posX - 1, posY, color);
        blockedSpace4 = new Block(posX, posY + 1, color);
        blockedSpaces.add(blockedSpace1);
        blockedSpaces.add(blockedSpace2);
        blockedSpaces.add(blockedSpace3);
        blockedSpaces.add(blockedSpace4);

    }

    void refreshBlockedSpaces() {
        blockedSpace1.refreshBlock(posX, posY);
        blockedSpace2.refreshBlock(posX + 1, posY);
        blockedSpace3.refreshBlock(posX - 1, posY);
        blockedSpace4.refreshBlock(posX, posY + 1);
    }

    int getColor() {
        // GREEN
        return color(64, 186, 64);
    }

    void rotate() {
    }

    ArrayList<Block> getNextRotationBlockedSpaces() {
        ArrayList<Block> nextBlockedSpaces = new ArrayList<>();

        // TODO change this one
        return blockedSpaces;
    }

}
