package Model;

import javafx.scene.paint.Color;

import java.util.Arrays;

public class Arrow {
    private Color color;
    private String direction;

    public Arrow(Color color, String direction) throws InvalidDirectionException{
        if (!Arrays.asList("N", "S", "V", "E", "C", "NE", "NV", "SE", "SV").contains(direction)){
            throw new InvalidDirectionException("Invalid direction for arrow!");
        }

        this.direction = direction;
        this.color = color;
    }

    public String getDirection(){
        return this.direction;
    }

    @Override
    public String toString(){
        return this.direction + " / " + this.color;
    }

}
