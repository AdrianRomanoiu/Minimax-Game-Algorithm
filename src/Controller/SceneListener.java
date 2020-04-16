package Controller;

import View.GameGridView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class SceneListener implements EventHandler<MouseEvent> {
    private GameGridView view;

    public SceneListener(GameGridView view){
        this.view = view;
    }

    private boolean isOutsideGameArea(double x, double y){
        return x < this.view.getWidthOffset() || x > this.view.getSceneWidth() - this.view.getWidthOffset() ||
                y < this.view.getHeightOffset() || y > this.view.getSceneHeight() - this.view.getHeightOffset();

    }

    @Override
    public void handle(MouseEvent m){
        if (isOutsideGameArea(m.getSceneX(), m.getSceneY())){
            this.view.getButtonFormation().setVisible(false);
        }
    }

}
