package Main;


public class Square extends Shape{

    // IDENTIFYING BLOCK IS TOP LEFT BLOCK

    Square() {
        blockedSpaces.add(new Block(posX, posY, color));
        blockedSpaces.add(new Block(posX + 1, posY, color));
        blockedSpaces.add(new Block(posX, posY + 1, color));
        blockedSpaces.add(new Block(posX + 1, posY + 1, color));
    }

    void refreshBlockedSpaces() {
        /*
        blockedSpace1.refreshBlock(posX, posY);
        blockedSpace2.refreshBlock(posX + 1, posY);
        blockedSpace3.refreshBlock(posX, posY + 1);
        blockedSpace4.refreshBlock(posX + 1, posY + 1);

         */
    }

    int getColor() {
        // RED
        return color(184, 64, 64);
    }
}
