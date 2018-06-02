package com.example.tcape.connectgame;

public class Line {
    public float startX;
    public float startY;
    public float endX;
    public float endY;
    public int lineColor;
    public char player;

    Line(float sX, float sY, float eX, float eY, int lineColor, char player){
        this.startX = sX;
        this.startY = sY;
        this.endX = eX;
        this.endY = eY;
        this.lineColor = lineColor;
        this.player = player;

    }
}
