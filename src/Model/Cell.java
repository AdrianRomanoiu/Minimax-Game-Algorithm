package Model;

public class Cell implements Cloneable{
    private Arrow arrow;
    private Touple<Integer, Integer> gridPosition;

    public Cell(int x, int y){
        this.arrow = null;
        this.gridPosition = new Touple(x, y);
    }

    public Cell(Arrow arrow, Touple<Integer, Integer> gridPosition){
        this.arrow = arrow;
        this.gridPosition = gridPosition;
    }

    public Arrow getArrow() {
        return arrow;
    }

    public void setArrow(Arrow arrow) {
        this.arrow = arrow;
    }

    public Touple getGridPosition() { return this.gridPosition; }

    @Override
    public String toString(){
        String arrow = (this.arrow == null) ? "empty" : this.arrow.toString();

        return "(" + this.getGridPosition().getX() + ", " + this.getGridPosition().getY() + ") "
                   + " -> " + arrow;
    }

    @Override
    public Cell clone(){
        return new Cell(this.arrow, this.gridPosition);
    }

}
