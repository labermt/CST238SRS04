package com.example.tcape.connectgame;

import android.graphics.Color;

public class Dot {
    public char player;
    public float xCoord;
    public float yCoord;
    public int xPosition;
    public int yPosition;
    public int dotColor;
    public boolean taken;
    public boolean edge;
    public boolean visited;
    public boolean corner;

    Dot(int xPosition, int yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.dotColor = Color.WHITE;
    }
}


