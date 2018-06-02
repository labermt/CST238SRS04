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

    Dot(){
        this.player = ' ';
        this.dotColor = Color.WHITE;
        this.xCoord = 0f;
        this.yCoord = 0f;
        this.taken = false;
        this.edge = false;
        this.visited = false;

    }

    Dot(char play, float x, float y, int color, boolean taken){
        this.player = play;
        this.xCoord = x;
        this.yCoord = y;
        this.dotColor = color;
        this.taken = taken;
        this.edge = false;
        this.visited = false;
    }

    Dot(int xPosition, int yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.dotColor = Color.WHITE;
    }
}


