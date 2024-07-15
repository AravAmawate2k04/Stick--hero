package com.example.ap;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;

public class StickHeroCharacter {


        private static StickHeroCharacter instance;

        public static StickHeroCharacter getInstance(ImageView imageView) {
            if (instance == null) {
                instance = new StickHeroCharacter();
            }
            return instance;
        }

    public void drawplayer(ImageView image, Rectangle stick, Boolean safe, Label statusLabel, Score score, gameData data, AnimationTimer animationTimer) {
        try {
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(image);
            translate.setDuration(Duration.millis(1000));
            translate.setByX(stick.getHeight());
            translate.setAutoReverse(true);

            translate.play();

            translate.setOnFinished(e -> {
                if(safe){
                    statusLabel.setText("You lost!");
                    score.addscore();
                    data.setScore(1);
                    try {
                        Storegame.serialize();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    animationTimer.stop();
                }
                else{
                    statusLabel.setText("You won!");
                    score.addscore();
                    data.setScore(1);
                    try {
                        Storegame.deserialize();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    animationTimer.stop();;

                }
            });
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

}
