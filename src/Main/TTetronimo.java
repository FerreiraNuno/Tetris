package Main;


public class TTetronimo extends Shape {


    // IDENTIFYING BLOCK IS MIDDLE BLOCK

    TTetronimo() {
        int color = color(184, 64, 64);
        blockedSpace1 = new Block(posX, posY, color);
        blockedSpace2 = new Block(posX + 1, posY, color);
        blockedSpace3 = new Block(posX - 1, posY, color);
        blockedSpace4 = new Block(posX, posY + 1, color);
        blockedSpaces.add(blockedSpace1);
        blockedSpaces.add(blockedSpace2);
        blockedSpaces.add(blockedSpace3);
        blockedSpaces.add(blockedSpace4);
    }

    void refreshBlockedSpaces() {
        blockedSpace1.refreshBlock(posX, posY);
        blockedSpace2.refreshBlock(posX + 1, posY);
        blockedSpace3.refreshBlock(posX - 1, posY);
        blockedSpace4.refreshBlock(posX, posY + 1);
    }

    int getColor() {
        // GREEN
        return color(64, 186, 64);
    }
}
