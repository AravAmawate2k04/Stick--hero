package com.example.ap;

import java.io.*;

class gameData implements Serializable {
    private int score;

    public gameData(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }
    public void setScore(final int score) {
        this.score = score;
    }

}
