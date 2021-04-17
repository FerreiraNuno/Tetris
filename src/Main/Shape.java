package Main;

import processing.core.PApplet;

import java.util.ArrayList;

/*



 */

public class Shape extends PApplet {
    int posX;
    int posY;
    int color = color(64, 64, 64);
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

    ArrayList<Block> getNextRotationBlockedSpaces() {
        return blockedSpaces;
    }


    void rotate() {
    }

}
