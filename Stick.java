package com.example.ap;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Stick extends Rectangle {

    private double extensionSpeed;
    private boolean isExtending;
    private boolean isRotating;

    public Stick(double xPos, double yPos, double width, double height, double extensionSpeed,boolean val1,boolean val2) {
        super(xPos - width / 2, yPos - height, width, height);
        this.extensionSpeed = extensionSpeed;
        this.isExtending = val1;
        this.isRotating = val2;

    }

    public void extend() {
        if (isExtending) {
            double currentHeight = getHeight();
            double newHeight = currentHeight + extensionSpeed;

            // Limit the maximum height (adjust as needed)
            double maxHeight = 600;
            if (newHeight <= maxHeight) {
                setHeight(newHeight);
                setY(getY() - extensionSpeed);
            } else {
                isExtending = false;
                isRotating = true;
            }
        }
    }

    public void rotate() {
        if (isRotating) {
            setRotate(getRotate() + 1); // Adjust the rotation speed as needed
            if (getRotate() >= 90) {
                isRotating = false;
            }
        }
    }

    public void drawStick(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.save();
        gc.translate(getX() + getWidth() / 2, getY() + getHeight());
        gc.rotate(getRotate());
        gc.fillRect(-getWidth() / 2, -getHeight(), getWidth(), getHeight());
        gc.restore();
    }

    // Getters and setters

    public double getExtensionSpeed() {
        return extensionSpeed;
    }

    public boolean isExtending() {
        return isExtending;
    }

    public void setExtending(boolean extending) {
        isExtending = extending;
    }

    public boolean isRotating() {
        return isRotating;
    }

    public void setRotating(boolean rotating) {
        isRotating = rotating;
    }
}




