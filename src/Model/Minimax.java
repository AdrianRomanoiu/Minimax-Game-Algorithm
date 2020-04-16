package Model;

import javafx.scene.paint.Color;
import java.util.*;

public class Minimax {
    private Move nextMove;

    public Minimax(){
        this.nextMove = null;
    }

    public Integer getScore(GameGrid gameGrid){ //score is represented by the number of possible remaining moves
        int score = 0;
        int length = gameGrid.getSize();

        for (int i = 0; i < length; i++){
            for (int j = 0; j < length; j++){
                score += gameGrid.getPossibleMoves(new Touple(i, j)).size();
            }
        }

        return score;
    }

    public Move minimaxDriver(GameGrid currentState){
        this.nextMove = null;
        this.minimax(0, currentState, true, this.generateScenarios(currentState), 2);
        return this.nextMove;
    }

    //Generate all possible scenarios starting from a given grid state
    // and store them as well as the move that got the scenario there
    public Map<GameGrid, Move> generateScenarios(GameGrid gameGrid){
        Map<GameGrid, Move> toReturn = new HashMap<>();
        List<Cell> freeCells = gameGrid.getFreeCells();

        for (Cell currentCell : freeCells){
            List<String> directions = gameGrid.getPossibleMoves(currentCell.getGridPosition());

            for (String direction : directions){
                Move currentMove = new Move(currentCell.getGridPosition(), direction, Color.BLACK);
                toReturn.put(new GameGridBuilder(gameGrid.getCells()).makeMove(currentMove).build(), currentMove);
            }
        }

        return toReturn;
    }

    public Integer minimax(int depth, GameGrid current, boolean isMax, Map<GameGrid, Move> scenarios, int maxDepth){
        if (depth == maxDepth){
            return this.getScore(current);
        }

        List<Integer> values = new ArrayList<>();
        Set<GameGrid> currentScenarios = scenarios.keySet();

        for (GameGrid gg : currentScenarios){
            Map<GameGrid, Move> nextScenarios = this.generateScenarios(gg);
            values.add(this.minimax(depth + 1, gg, !isMax, nextScenarios, maxDepth));
        }

        if (isMax) {
            if (depth == 0) { //setting the nextMove variable to the best move determined by the min-max algorithm
                if (values.size() > 0){
                    int maxIndex = values.indexOf(Collections.max(values));
                    int i = -1;

                    //the game grid configuration for the best move has the same index in the key-set as the index
                    //of the max value in the values array
                    for (GameGrid gg : currentScenarios){
                        i++;

                        //after we get the respective game grid index we get the corresponding move
                        if (i == maxIndex){
                            this.nextMove = scenarios.get(gg);
                            break;
                        }
                    }
                }
            }

            return (values.size() > 0) ? Collections.max(values) : 0;
        }
        else{
            return (values.size() > 0) ? Collections.min(values) : 99999;
        }
    }

}
