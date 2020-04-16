package Model;

import javafx.scene.paint.Color;

public class Move {
    private Color color;
    private String direction;
    private Touple<Integer, Integer> coordinates;

    public Move(Touple coordinates, String direction, Color color){
        this.coordinates = coordinates;
        this.direction = direction;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public String getDirection() {
        return direction;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Touple<Integer, Integer> getCoordinates() {
        return coordinates;
    }

}
