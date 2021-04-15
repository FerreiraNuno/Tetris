package Main;


public class Square extends Shape{


    Square() {
        blockedSpace1 = new Block(posX, posY, color);
        blockedSpace2 = new Block(posX + 1, posY, color);
        blockedSpace3 = new Block(posX, posY + 1, color);
        blockedSpace4 = new Block(posX + 1, posY + 1, color);
        blockedSpaces.add(blockedSpace1);
        blockedSpaces.add(blockedSpace2);
        blockedSpaces.add(blockedSpace3);
        blockedSpaces.add(blockedSpace4);
    }

    void refreshBlockedSpaces() {
        blockedSpace1.refreshBlock(posX, posY);
        blockedSpace2.refreshBlock(posX + 1, posY);
        blockedSpace3.refreshBlock(posX, posY + 1);
        blockedSpace4.refreshBlock(posX + 1, posY + 1);
    }

    int getColor() {
        // RED
        return color(184, 64, 64);
    }
}
