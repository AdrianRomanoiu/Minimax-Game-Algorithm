import Controller.GameGridController;
import Model.*;
import View.GameGridView;
import javafx.application.Application;
import javafx.scene.Scene;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application{
    private static Scene scene;

    public static void main(String[] args){
        GameGrid gameGrid = null;
        Human humanModel = (Human) new PlayerFactory().getPlayer("HUMAN", gameGrid, Color.RED);
        Computer computerModel = (Computer) new PlayerFactory().getPlayer("COMPUTER", gameGrid, Color.BLUE);

        GameGridView view = new GameGridView(humanModel, computerModel);
        GameGridController controller = new GameGridController(humanModel, computerModel, view);

        scene = view.getScene();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("My Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
