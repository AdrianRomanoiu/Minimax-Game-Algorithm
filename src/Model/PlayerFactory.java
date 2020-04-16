package Model;

import javafx.scene.paint.Color;

public class PlayerFactory {

    public Player getPlayer(String playerType, GameGrid gameGrid, Color color){
        if (playerType.equalsIgnoreCase("HUMAN")){
            return new Human(gameGrid, color);
        }
        else if (playerType.equalsIgnoreCase("COMPUTER")){
            return new Computer(gameGrid, color);
        }

        System.out.println("Invalid player type. Null will be returned");
        return null;
    }

}
