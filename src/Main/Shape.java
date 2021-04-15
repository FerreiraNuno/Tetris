package Main;

import processing.core.PApplet;

import java.util.ArrayList;

public class Shape extends PApplet {
    int posX;
    int posY;
    int color;

    Block blockedSpace1;
    Block blockedSpace2;
    Block blockedSpace3;
    Block blockedSpace4;

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
    }
}
