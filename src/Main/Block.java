package Main;

import processing.core.PApplet;

public class Block extends PApplet{
    public int x;
    public int y;
    int color;

    public Block(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    void refreshBlock(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
