package Model;

import javafx.scene.paint.Color;

public abstract class Player {
    Color color;
    int difficulty;
    static GameGrid gameGrid;

    public Player (GameGrid gg){
        gameGrid = gg;
    }

    public Color getColor() { return color; }

    public GameGrid getGameGrid() { return gameGrid; }

    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }

    public void makeMove(Move m) {
        gameGrid.makeMove(m);
    }

}
