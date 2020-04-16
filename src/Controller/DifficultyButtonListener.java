package Controller;

import Model.*;
import View.GameGridView;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class DifficultyButtonListener implements EventHandler<MouseEvent> {
    private int difficulty;
    private Human humanModel;
    private GameGridView view;
    private Computer computerModel;
    private GameGridController controller;

    public DifficultyButtonListener(Human hm, Computer cm , GameGridView v, int d, GameGridController c){
        this.humanModel = hm;
        this.computerModel = cm;
        this.view = v;
        this.controller = c;
        this.difficulty = d;
    }

    private void addCellListenerToAllCells(){
        int length = this.humanModel.getGameGrid().getSize();
        for (int i = 0; i < length; i++){
            for (int j = 0; j < length; j++){
                this.view.addRectangleListener(new CellListener(this.humanModel.getGameGrid(), this.view, i, j), i, j);
            }
        }
    }

    private void addButtonListenerToAllButtons(){
        for (Touple<Button, String> b : this.view.getButtonFormation().getButtons()){
            this.view.addButtonsListener(new ButtonListener(this.humanModel, this.computerModel, this.view, b),
                                         b.getX());
        }
    }

    @Override
    public void handle(MouseEvent event) {
        this.humanModel.setDifficulty(this.difficulty);
        this.humanModel.restart();
        this.view.setModel(this.humanModel.getGameGrid());
        this.view.createGameScene();
        this.view.addSceneListener(new SceneListener(this.view));
        this.view.addRestartButtonListener(new RestartButtonListener(this.humanModel, this.computerModel,
                                           this.view, this.controller));

        this.addCellListenerToAllCells();
        this.addButtonListenerToAllButtons();
    }

}
