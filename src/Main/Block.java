package Main;

public class Block {
    public int x;
    public int y;
    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void refreshBlock(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
