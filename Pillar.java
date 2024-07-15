package com.example.ap;
import javafx.scene.shape.Rectangle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Platform extends Rectangle {

    public Platform(double x, double y, double width, double height, Color color) {
        super(x, y, width, height);
        setFill(color);
    }
}
