package com.example.ap;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class Scene2_Controller implements Initializable, Serializable {
    private static final long serialVersionUID = 1L;
    private Score score=new Score(0);
    @FXML
    private Canvas gameCanvas;
    private AnimationTimer animationTimer;
    private GraphicsContext gc ;
    private Stage stage;
    private Scene scene;
    @FXML
    private Stick stick ;
    private gameData data=new gameData(0);
    private boolean isExtending = false;
    private boolean isRotating = false;
    private boolean isClicked = false;
    private boolean safe=false;
    private double extensionSpeed = 2.0;
    private double initialXPos = 166.0;
    private double initialYPos = 255.0;
    @FXML
    private ImageView image;
    private StickHeroCharacter Hero;
    @FXML
    private Rectangle Plateform1;
    @FXML
    private Rectangle Plateform2;
    @FXML
    private Label statusLabel;

    private void gc_create(){
        gc = gameCanvas.getGraphicsContext2D();

    }
    private void setGameSetiting(){

        gameCanvas.setOnMousePressed(this::handleMousePress);
        gameCanvas.setOnMouseReleased(this::handleMouseRelease);
    }

    private void stick_create(){
        double initialWidth = 5.0;
        double initialHeight = 1.0;
        stick=new Stick(initialXPos - initialWidth / 2,initialYPos - initialHeight,5.0,1.0,extensionSpeed,isExtending,isRotating);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        media.play();
        gc_create();
        stick_create();
        setGameSetiting();
        Hero=StickHeroCharacter.getInstance(image);
        // Start the animation timer
        startAnimationTimer();

    }
    private void startAnimationTimer() {
        try {
            final boolean[] flag = {false};
            animationTimer = new AnimationTimer() {
                @Override
                public void handle(long currentNanoTime) {
                    if (isClicked && isExtending) {
                        extendStick();
                        drawStick();
                    }
                    if (isRotating) {
                        rotateStick();
                        drawStick();
                        Hero.drawplayer(image,stick,checkIsAlive(),statusLabel,score,data,animationTimer);
                        flag[0] = true;
                    }
                }
            };
            animationTimer.start();
        } catch (NullPointerException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
    }
    private void restartGame() {
        Plateform1.setWidth(Plateform2.getWidth());
        Plateform2.setWidth(100);
        image.setLayoutY(197);
        image.setLayoutY(90);
        stick_create();
        startAnimationTimer();
    }
    private void newCanvas() {
        gameCanvas = new Canvas(833, 411);
        gc = gameCanvas.getGraphicsContext2D();
    }
    private void extendStick() {
        try {
            if (isExtending) {
                double currentHeight = stick.getHeight();
                double newHeight = currentHeight + extensionSpeed;

                // Limit the maximum height (adjust as needed)
                double maxHeight = 600;
                if (newHeight <= maxHeight) {
                    stick.setHeight(newHeight);
                    stick.setY(stick.getY() - extensionSpeed);
                }
            } else {
                // Stop extending when the stick reaches the maximum height
                isExtending = false;
                // Start rotating after extension
                isRotating = true;
            }
        } catch (ArithmeticException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
    }

    public boolean checkIsAlive() {
        // Convert local coordinates to scene coordinates
        double imageSceneX = image.localToScene(image.getBoundsInLocal()).getMinX();
        // Convert scene coordinates to canvas coordinates
        double imageCanvasX = gameCanvas.sceneToLocal(new Point2D(imageSceneX, 0)).getX();
        // Get the initial and final positions of Plateform2
        double platform2InitialX = Plateform2.getBoundsInParent().getMinX();
        double platform2FinalX = Plateform2.getBoundsInParent().getMaxX();
        if (imageCanvasX+stick.getHeight()+50> platform2FinalX || imageCanvasX+stick.getHeight()+50 < platform2InitialX) {
            return true;
        } else {
            return false;
        }
    }
    private void stopAnimationTimer() {
        animationTimer.stop();
    }
    private void rotateStick() {
        // Rotate around the initial point by 90 degrees to the right
        stick.setRotate(stick.getRotate() + 1); // Adjust the rotation speed as needed
        if (stick.getRotate() >= 90) {
            // Stop rotating when it reaches 90 degrees
            isRotating = false;
        }
    }
    // Method to handle mouse press event
    private void handleMousePress(MouseEvent event) {
        if (!isClicked && !isExtending && !isRotating) {
            isClicked = true;
            isExtending = true;
        }
    }

    // Method to handle mouse release event
    private void handleMouseRelease(MouseEvent event) {
        // Reset flags for the next click
        if (isExtending) {
            isExtending = false;
            // Start rotating after extension
            isRotating = true;
        }
    }
    private void drawStick() {

        gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
        // Draw the stick on the canvas
        gc.setFill(Color.BLACK);
        gc.save(); // Save current graphics context state
        gc.translate(initialXPos, initialYPos);
        gc.rotate(stick.getRotate()); // Rotate around the initial point
        gc.fillRect(-stick.getWidth() / 2, -stick.getHeight(), stick.getWidth(), stick.getHeight());
        gc.restore(); // Restore graphics context state
    }
    public void switchToScene3(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Scene3.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
    }
}




