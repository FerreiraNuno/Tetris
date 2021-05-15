package Main;

import java.util.ArrayList;

interface GameEngineInterface {
    Shape spawnNewShape();
    void tetrisCheck();
}

interface ShapeInterface {
    void createBlocks();
    ArrayList<Block> getBlocks();
    void rotate();
    void moveLeft();
    void moveRight();
    void drop();
    void verticalCollisionCheck();
    void lateralCollisionCheck();
}

interface BlockInterface {
    void updateBlockPosition();
}
