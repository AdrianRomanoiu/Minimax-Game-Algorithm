package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameGrid{
    private int size;
    private int difficulty;
    private List<List<Cell>> cells;

    public GameGrid(int difficulty, int size, List<List<Cell>> cells){
        if (difficulty != 0 && difficulty != 1){
            System.out.println("Difficulty can be either 0-(Easy) or 1-(Hard). Anything else will be set to (Easy).");
        }

        this.difficulty = difficulty;
        if (this.difficulty == 1){
            this.size = 8;
        }
        else{
            this.size = 4;
        }

        if (cells == null){
            this.cells = new ArrayList<>(); //Create the rows for the grid.
            for (int i = 0; i < this.size; i++){
                this.cells.add(new ArrayList<>()); //For each row create the columns.
                for (int j = 0; j < this.size; j++){
                    this.cells.get(i).add(new Cell(i, j)); //For each pair (row, column) create the cell.
                }
            }
        }
        else{
            this.cells = cells;
        }
    }

    public int getSize(){
        return this.size;
    }

    public int getDifficulty(){ return this.difficulty; }

    public List<List<Cell>> getCells() { return this.cells; }

    @Override
    public String toString(){
        String toReturn = "";

        int rows = this.cells.size();
        int columns = this.cells.get(0).size();

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                toReturn += cells.get(i).get(j).toString() + "\n";
            }
        }

        return toReturn;
    }

    public boolean noMoreMoves(){
        List<Cell> cells = this.getFreeCells();
        for (Cell c : cells){
            if (this.getPossibleMoves(c.getGridPosition()).size() > 0){
                return false;
            }
        }

        return true;
    }

    public void makeMove(Move move){
        try{
            this.cells.get(move.getCoordinates().getX()).get(move.getCoordinates().getY())
                    .setArrow(new Arrow(move.getColor(), move.getDirection()));
        }
        catch(InvalidDirectionException e){
            e.printStackTrace();
        }
    }

    public List<Cell> getFreeCells(){
        List<Cell> freeCells = new ArrayList<>();

        for (int i = 0; i < this.size; i++){
            for (int j = 0; j < this.size; j++){
                if (this.cells.get(i).get(j).getArrow() == null){
                    freeCells.add(new Cell(i, j));
                }
            }
        }

        return freeCells;
    }

    public boolean isCellFree(int i, int j){
        return this.cells.get(i).get(j).getArrow() == null;

    }

    public List<String> getPossibleMoves(Touple<Integer, Integer> gridCoordinates){
        List<Integer> dx = Arrays.asList(-1, 0, 1,  0, -1, 1,  1, -1);
        List<Integer> dy = Arrays.asList( 0, 1, 0, -1,  1, 1, -1, -1);
        List<String> possibilities = new ArrayList<>(Arrays.asList("N", "S", "V", "E", "C"));

        if (this.difficulty == 1){
            possibilities.addAll(Arrays.asList("NE", "NV", "SE", "SV"));
        }

        int length = dx.size(); //dx and dy have the same size
        for (int i = 0; i < length; i++){
            Integer x = gridCoordinates.getX();
            Integer y = gridCoordinates.getY();
            while (x + dx.get(i) >= 0 && y + dy.get(i) >= 0 && x + dx.get(i) < this.size && y + dy.get(i) < this.size){
                x += dx.get(i);
                y += dy.get(i);

                if (this.cells.get(x).get(y).getArrow() != null){
                    possibilities.remove(this.cells.get(x).get(y).getArrow().getDirection());
                }
            }
        }

        return possibilities;
    }

}
