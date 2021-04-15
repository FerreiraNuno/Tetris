package Main;

import processing.core.PApplet;

import java.util.ArrayList;

public class Square extends Shape{
    Block blockedSpace1 = new Block(posX, posY, color);
    Block blockedSpace2 = new Block(posX + 1, posY, color);
    Block blockedSpace3 = new Block(posX, posY + 1, color);
    Block blockedSpace4 = new Block(posX + 1, posY + 1, color);
    ArrayList<Block> blockedSpaces = new ArrayList<>();

    Square() {
        color = color(184, 64, 64);
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
