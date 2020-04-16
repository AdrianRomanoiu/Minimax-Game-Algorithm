package Model;

import javafx.scene.paint.Color;

public class Computer extends Player{
    private Minimax minimax;

    public Computer(GameGrid gameGrid, Color color){
        super(gameGrid);
        super.color = color;
        this.minimax = new Minimax();
    }

    public Move calculateNextMove() {
        Move toReturn = this.minimax.minimaxDriver(gameGrid);
        if (toReturn != null) {
            toReturn.setColor(this.color);
        }

        return toReturn;
    }

}
