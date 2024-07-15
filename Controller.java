package com.example.ap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;

public class Controller {

        private Stage stage;
       private Scene scene;
        private Parent root;

        public void switchToScene1(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        public void switchToScene2(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        public void switchToScene3(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("Scene3.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

}