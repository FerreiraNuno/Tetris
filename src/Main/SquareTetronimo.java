package Main;


public class SquareTetronimo extends Shape{

    int red = color(184, 64, 64);
    SquareTetronimo() {
        blockedSpaces.add(new Block(posX, posY, red));
        blockedSpaces.add(new Block(posX + 1, posY, red));
        blockedSpaces.add(new Block(posX, posY + 1, red));
        blockedSpaces.add(new Block(posX + 1, posY + 1, red));
    }

    void refreshBlockedSpaces() {
        blockedSpaces.get(0).refreshBlock(posX, posY);
        blockedSpaces.get(1).refreshBlock(posX + 1, posY);
        blockedSpaces.get(2).refreshBlock(posX, posY + 1);
        blockedSpaces.get(3).refreshBlock(posX + 1, posY + 1);
    }
}
