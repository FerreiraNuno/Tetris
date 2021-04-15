package Main;

import processing.core.PApplet;

import java.util.ArrayList;

public class Shape extends PApplet {
    int posX;
    int posY;
    int color = color(64, 64, 64);
    Block blockedSpace1;
    Block blockedSpace2;
    Block blockedSpace3;
    Block blockedSpace4;
    ArrayList<Block> blockedSpaces = new ArrayList<>();

    Shape() {
        this.posX = 4;
        this.posY = 0;
    }

    void refreshBlockedSpaces() {
    }

    int getColor() {
        return this.color;
    }
}
