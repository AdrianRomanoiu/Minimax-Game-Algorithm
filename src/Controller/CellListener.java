package Controller;

import Model.GameGrid;
import View.GameGridView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class CellListener implements EventHandler<MouseEvent> {
    private int i;
    private int j;
    private GameGridView view;
    private GameGrid gameGrid;

    public CellListener(GameGrid gameGrid, GameGridView view, int i, int j){
        this.gameGrid = gameGrid;
        this.view = view;
        this.i = i;
        this.j = j;
    }

    @Override
    public void handle(MouseEvent m){
        if (this.gameGrid.isCellFree(this.i, this.j)){
            this.view.getButtonFormation().setVisible(true);
            this.view.setButtonFormationPosition(this.i, this.j);
        }
    }

}