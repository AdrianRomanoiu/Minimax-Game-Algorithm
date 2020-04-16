package View;

import Model.Touple;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ButtonFormation {
    private Image dotImage;
    private Image arrowImage;
    private List<Touple<Button, String>> buttons;

    public ButtonFormation(){
        this.buttons = new ArrayList<>();

        try {
            this.arrowImage = new Image(new FileInputStream("H:\\An 3\\Sem 2\\PS\\Project\\media\\arrow.png"),
                    20, 20, false, false);

            this.dotImage = new Image(new FileInputStream("H:\\An 3\\Sem 2\\PS\\Project\\media\\dot.png"),
                    20, 20, false, false);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }

        Button center = new Button("");
        center.setShape(new Circle(5.0));
        center.setVisible(false);
        center.setGraphic(new ImageView(this.dotImage));
        buttons.add(new Touple<>(center, "C"));

        int rotation = 0;
        List<String> directions = Arrays.asList("V", "SV", "S", "SE", "E", "NE", "N", "NV"); //starting from V and going
        for (String direction : directions){                                                //counter clockwise
            Button tmpButton = new Button("");
            ImageView tmpImageView = new ImageView(this.arrowImage);

            tmpButton.setGraphic(tmpImageView);
            tmpButton.setRotate(rotation);
            tmpButton.setVisible(false);

            this.buttons.add(new Touple<>(tmpButton, direction));

            rotation -= 45;
        }
    }

    public void setVisible(boolean value) {
        for (Touple<Button, String> b : this.buttons){
            b.getX().setVisible(value);
        }
    }

    public void setButtonPositions(int x, int y) {
        int offset = 40;
        int length = buttons.size();

        int[] dx = {0, -1, -1, 0, 1, 1,  1,  0, -1};
        int[] dy = {0,  0,  1, 1, 1, 0, -1, -1, -1};

        for (int i = 0; i < length; i++){
            this.buttons.get(i).getX().setLayoutX(x + (dx[i] * offset));
            this.buttons.get(i).getX().setLayoutY(y + (dy[i] * offset));
            this.buttons.get(i).getX().toFront();
        }
    }

    public List<Touple<Button, String>> getButtons() { return this.buttons; }

}
