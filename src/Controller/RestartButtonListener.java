package Controller;

import Model.Computer;
import Model.Human;
import View.GameGridView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class RestartButtonListener implements EventHandler<MouseEvent> {
    private Human humanModel;
    private GameGridView view;
    private Computer computerModel;
    private GameGridController controller;

    public RestartButtonListener(Human hm, Computer cm, GameGridView view, GameGridController controller){
        this.humanModel = hm;
        this.computerModel = cm;
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void handle(MouseEvent event) {
        this.view.createStartScene();
        this.controller = new GameGridController(this.humanModel, this.computerModel, this.view);
    }

}
