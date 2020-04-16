package Controller;

import Model.*;
import View.GameGridView;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class ButtonListener implements EventHandler<MouseEvent> {
    private Human humanModel;
    private GameGridView view;
    private Computer computerModel;
    private Touple<Button, String> buttonData;

    public ButtonListener(Human humanModel, Computer computerModel, GameGridView view, Touple<Button, String> bd){
        this.view = view;
        this.humanModel = humanModel;
        this.computerModel = computerModel;
        this.buttonData = bd;
    }

    @Override
    public void handle(MouseEvent event) {
        this.humanModel.makeMove(new Move(this.view.getButtonFormationCell(), this.buttonData.getY(),
                this.humanModel.getColor()));
        this.view.drawMove(this.buttonData.getX());

        Move computerMove = computerModel.calculateNextMove();
        if (computerMove == null){
            this.view.showEndGameStatus("You won!", Color.GREEN);
            return;
        }
        else{
            this.view.drawMove(computerMove);
            this.computerModel.makeMove(computerMove);
        }

        if (this.humanModel.getGameGrid().noMoreMoves()){
            this.view.showEndGameStatus("You lost!", Color.RED);
        }
    }

}
