package Model;

import javafx.scene.paint.Color;

public class Human extends Player {

    public Human(GameGrid gg, Color color){
        super(gg);
        super.color = color;
    }

    public void restart(){
        if (super.difficulty == 1){
            gameGrid = new GameGridBuilder(null).setDifficulty(super.difficulty).build();
        }
        else{
            gameGrid = new GameGridBuilder(null).build();
        }
    }

    public void setGameGrid(GameGrid gg) {
        gameGrid = gg;
    }

}
