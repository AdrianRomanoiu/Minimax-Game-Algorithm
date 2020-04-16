package Controller;

import Model.Computer;
import Model.Human;
import View.GameGridView;

public class GameGridController {
    private Human humanModel;
    private GameGridView view;
    private Computer computerModel;

    public GameGridController(Human humanModel, Computer computerModel, GameGridView view){
        this.humanModel = humanModel;
        this.computerModel = computerModel;
        this.view = view;

        this.view.addEasyButtonListener(new DifficultyButtonListener(this.humanModel, this.computerModel,
                                                                     this.view, 0, this));

        this.view.addHardButtonListener(new DifficultyButtonListener(this.humanModel, this.computerModel,
                                                                     this.view, 1, this));
    }

}
