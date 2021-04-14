package Main;

import java.util.ArrayList;

public class Shape {
    int posX;
    int posY;

    Block blockedSpace1 = new Block(posX, posY);
    Block blockedSpace2 = new Block(posX + 1, posY);
    Block blockedSpace3 = new Block(posX, posY + 1);
    Block blockedSpace4 = new Block(posX + 1, posY + 1);

    ArrayList<Block> blockedSpaces = new ArrayList<>();

    Shape() {
        this.posX = 4;
        this.posY = 0;
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
}
