package Model;

import java.util.ArrayList;
import java.util.List;

public class GameGridBuilder {
    private int difficulty;
    private List<List<Cell>> cells;

    public GameGridBuilder(List<List<Cell>> cellsGrid){
        if (cellsGrid != null){
            int length = cellsGrid.size();
            this.cells = new ArrayList<>();
            for (int i = 0; i < length; i++){ // making a deep copy
                 this.cells.add(new ArrayList<>());
                for (int j = 0; j < length; j++){
                    this.cells.get(i).add(cellsGrid.get(i).get(j).clone());
                }
            }
        }
    }

    public GameGrid build(){
        int size;
        if (this.difficulty == 1){
            size = 8;
        }
        else{
            size = 4;
        }

        return new GameGrid(this.difficulty, size, this.cells);
    }

    public GameGridBuilder makeMove(Move move){
        try{
            this.cells.get(move.getCoordinates().getX()).get(move.getCoordinates().getY())
                    .setArrow(new Arrow(move.getColor(), move.getDirection()));
        }
        catch(InvalidDirectionException e){
            e.printStackTrace();
        }

        return this;
    }

    public GameGridBuilder setDifficulty(int difficulty){
        this.difficulty = difficulty;
        return this;
    }

}
