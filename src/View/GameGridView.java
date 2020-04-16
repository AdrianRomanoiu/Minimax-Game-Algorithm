package View;

import Model.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.event.EventHandler;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameGridView{
    private Human humanModel;
    private Computer computerModel;

    private int cellSize = 120;
    private final int widthOffset = 160;
    private final int heightOffset = 60;
    private final int sceneWidth = 800;
    private final int sceneHeight = 600;

    private List<List<Rectangle>> rectangles;

    private int x;
    private int y;
    private int buttonFormationCurrentI;
    private int buttonFormationCurrentJ;

    private Button easyButton;
    private Button hardButton;
    private Button restartButton;
    private ButtonFormation buttonFormation;

    private Text text;
    private Text endGameStatus;

    private Image dot;
    private Image arrow;

    public Scene scene;
    private Group root;
    private Group rootStart;

    public GameGridView(Human humanModel, Computer computerModel){
        this.humanModel = humanModel;
        this.computerModel = computerModel;

        this.endGameStatus = new Text();
        this.endGameStatus.setLayoutX(350);
        this.endGameStatus.setLayoutY(40);
        this.endGameStatus.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        this.scene = new Scene(new Group(), this.sceneWidth, this.sceneHeight);
        this.createStartScene();
    }

    public Scene getScene() {
        return this.scene;
    }

    public int getSceneWidth() {
        return this.sceneWidth;
    }

    public int getSceneHeight() {
        return this.sceneHeight;
    }

    public int getWidthOffset() {
        return this.widthOffset;
    }

    public int getHeightOffset() {
        return this.heightOffset;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Touple<Integer, Integer> getButtonFormationCell(){
        return new Touple<Integer, Integer>(buttonFormationCurrentI, buttonFormationCurrentJ);
    }

    public void setModel(GameGrid gg){
        this.humanModel.setGameGrid(gg);
    }

    public ButtonFormation getButtonFormation() {
        return this.buttonFormation;
    }

    public void createGameScene(){
        if (this.humanModel.getGameGrid().getDifficulty() == 1){ //making change in dimension for the hard-difficulty game mode
            this.cellSize = 60;
        }
        else{
            this.cellSize = 120;
        }

        try {
            this.arrow = new Image(new FileInputStream("H:\\An 3\\Sem 2\\PS\\Project\\media\\arrow.png"),
                    this.cellSize, this.cellSize, false, false);
            this.dot = new Image(new FileInputStream("H:\\An 3\\Sem 2\\PS\\Project\\media\\dot.png"),
                    this.cellSize, this.cellSize, false, false);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }

        this.restartButton = new Button("Restart");
        this.restartButton.setPrefWidth(320);
        this.restartButton.setLayoutX(240);
        this.restartButton.setLayoutY(this.sceneHeight - 20);

        this.root = new Group();
        this.buttonFormation = new ButtonFormation();
        for (Touple<Button, String> b : this.buttonFormation.getButtons()){
            root.getChildren().add(b.getX());
        }
        this.root.getChildren().add(restartButton);

        this.createGrid(this.humanModel.getGameGrid().getSize());
        this.scene.setRoot(this.root);
    }

    public void createStartScene(){
        this.text = new Text("To start please select your difficulty");
        this.text.setLayoutX(100);
        this.text.setLayoutY(250);
        this.text.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        this.easyButton = new Button("Easy");
        this.easyButton.setPrefWidth(320);
        this.easyButton.setLayoutX(250);
        this.easyButton.setLayoutY(280);

        this.hardButton = new Button("Hard");
        this.hardButton.setPrefWidth(320);
        this.hardButton.setLayoutX(250);
        this.hardButton.setLayoutY(330);

        this.rootStart = new Group();
        this.rootStart.getChildren().addAll(this.text, this.easyButton, hardButton);
        this.scene.setRoot(this.rootStart);
    }

    private void createGrid(int size){
        this.rectangles = new ArrayList<>();

        for (int i = 0; i < size; i++){
            this.rectangles.add(new ArrayList<>());
            for (int j = 0; j < size; j++){
                Rectangle tmp = new Rectangle(j * this.cellSize + this.widthOffset,
                        i * this.cellSize + this.heightOffset,
                        this.cellSize, this.cellSize);
                tmp.setFill(((i + j) % 2 == 0) ? Color.GREEN : Color.ORANGE);

                this.rectangles.get(i).add(tmp);
                this.root.getChildren().add(tmp);
            }
        }
    }

    public void addButtonsListener(EventHandler<MouseEvent> e, Button button){
        for(Touple<Button, String> b : this.buttonFormation.getButtons()){
            if (button == b.getX()){
                b.getX().setOnMouseClicked(e);
                break;
            }
        }
    }

    public void addRectangleListener(EventHandler<MouseEvent> e, int i, int j){
        this.rectangles.get(i).get(j).setOnMouseClicked(e);
    }

    public void addSceneListener(EventHandler<MouseEvent> e){
        this.scene.setOnMouseClicked(e);
    }

    public void addEasyButtonListener(EventHandler<MouseEvent> e){
        this.easyButton.setOnMouseClicked(e);
    }

    public void addHardButtonListener (EventHandler<MouseEvent> e){
        this.hardButton.setOnMouseClicked(e);
    }

    public void addRestartButtonListener(EventHandler<MouseEvent> e) { this.restartButton.setOnMouseClicked(e); }

    public void setButtonFormationPosition(int i, int j) {
        this.buttonFormationCurrentI = i;
        this.buttonFormationCurrentJ = j;

        this.y = i * this.cellSize + this.heightOffset + this.cellSize / 2 - 15;
        this.x = j * this.cellSize + this.widthOffset + this.cellSize / 2 - 20;

        List<String> possibleMoves = this.humanModel.getGameGrid().getPossibleMoves(new Touple<>(i, j));
        for (Touple<Button, String> b : this.buttonFormation.getButtons()){
            if (!possibleMoves.contains(b.getY())){
                b.getX().setVisible(false);
            }
        }

        this.buttonFormation.setButtonPositions(x, y);
    }

    public void drawMove(Move move){
        ImageView tmp = new ImageView(this.arrow);
        if (move.getDirection() == "C") { //this is the center
            tmp = new ImageView(this.dot);
        }

        int y = move.getCoordinates().getX() * this.cellSize + this.heightOffset;
        int x = move.getCoordinates().getY() * this.cellSize + this.widthOffset;

        HashMap<String, Double> map = new HashMap<String, Double>() {{
            put("C", 0.0);
            put("V", 0.0);
            put("SV", -45.0);
            put("S", -90.0);
            put("SE", -135.0);
            put("E", -180.0);
            put("NE", -225.0);
            put("N", -270.0);
            put("NV", -315.0);
        }};

        this.adjustColor(tmp, false);
        tmp.setRotate(map.get(move.getDirection()));
        tmp.setLayoutX(x);
        tmp.setLayoutY(y);

        this.root.getChildren().add(tmp);
    }

    public void drawMove(Button pressedButton){
        ImageView tmp = new ImageView(this.arrow);
        if (pressedButton.getLayoutX() == this.x && pressedButton.getLayoutY() == this.y) { //this is the center button
            tmp = new ImageView(this.dot);
        }

        this.adjustColor(tmp, true);
        tmp.setRotate(pressedButton.getRotate());
        tmp.setLayoutX(this.x - this.cellSize / 2 + 20);
        tmp.setLayoutY(this.y - this.cellSize / 2 + 15);

        this.buttonFormation.setVisible(false);
        this.root.getChildren().add(tmp);
    }

    private void adjustColor(ImageView iv, boolean isHuman){ //if it's not human then it's surely a computer
        ColorAdjust ca = new ColorAdjust();
        ca.setHue(((isHuman) ? this.humanModel : this.computerModel).getColor().getHue());
        ca.setSaturation(((isHuman) ? this.humanModel : this.computerModel).getColor().getSaturation());
        ca.setBrightness(((isHuman) ? this.humanModel : this.computerModel).getColor().getBrightness() / 2);

        iv.setEffect(ca);
    }

    public void showEndGameStatus(String text, Color color){
        this.endGameStatus.setText(text);
        this.endGameStatus.setFill(color);

        this.root.getChildren().add(this.endGameStatus);
    }

}