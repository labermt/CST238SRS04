package com.example.tcape.connectgame;

import android.graphics.Color;


public class Game {

    public Dot[][] cells;
    public Dot[][] checkCells;
    public char currentPlayer;
    public boolean gameOver;
    private MainActivity mainActivity;


    public Game(){
        cells = new Dot[11][11];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++){
                cells[i][j] = new Dot(i, j);
            }
        }
        checkCells = new Dot[11][11];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++){
                checkCells[i][j] = new Dot(i, j);
            }
        }
        currentPlayer = 'R';
        gameOver = false;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 'R' ? 'B' : 'R');
        if (currentPlayer == 'R'){
            mainActivity.turnColor.setText(R.string.RED);
            mainActivity.turnColor.setTextColor(mainActivity.redColor);
        }
        else {
            mainActivity.turnColor.setText(R.string.BLUE);
            mainActivity.turnColor.setTextColor(mainActivity.blueColor);
        }

    }

    public char getCell(int x, int y) {
        return cells[x][y].player;
    }

    private void floodFill(Dot dot, int x, int y){
        if (x < 0 || x > 10 || y < 0 || y > 10) return;

        if (checkCells[x][y].visited) return;
        checkCells[x][y].visited = true;

        if (cells[x][y].dotColor == dot.dotColor) {
            if (x == 0){
                floodFill(dot, x, y - 1);
            }
            if (x == 10){
                floodFill(dot, x, y - 1);
            }
            if (y == 0){
                floodFill(dot, x - 1 , y);
            }
            if (y == 10){
                floodFill(dot, x - 1, y);
            }
            return;
        }

        if (cells[x][y].dotColor != dot.dotColor){
            checkCells[x][y].dotColor = dot.dotColor;
        }

        floodFill(dot, x + 1, y);
        floodFill(dot, x - 1, y);
        floodFill(dot, x, y + 1);
        floodFill(dot, x, y - 1);
    }

    public void setCheckCells(Dot dot){

        checkCells[0][0].dotColor = dot.dotColor;
        checkCells[0][10].dotColor = dot.dotColor;
        checkCells[10][0].dotColor = dot.dotColor;
        checkCells[10][10].dotColor = dot.dotColor;

        for(int i = 0; i < 11; i++){
            for (int j = 0; j < 11; j++){
                if (cells[i][j].dotColor == dot.dotColor){
                    checkCells[i][j].dotColor = dot.dotColor;

                }
            }
        }
    }

    public void resetCheckCells(){
        for(int i = 0; i < 11; i++){
            for (int j = 0; j < 11; j++){
                checkCells[i][j].dotColor = Color.WHITE;
            }
        }
    }

    public boolean checkWin(Dot dot, int x, int y){
        floodFill(dot, x, y);
        for (int i = 0; i < 11; i++){
            for (int j = 0; j < 11; j++){
                if (checkCells[i][j].dotColor != dot.dotColor){
                    gameOver = true;
                    return true;
                }
            }
        }
        return false;
    }

    public void resetGame(){
        cells = new Dot[11][11];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++){
                cells[i][j] = new Dot(i, j);
            }
        }
        checkCells = new Dot[11][11];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++){
                checkCells[i][j] = new Dot(i, j);
            }
        }
        currentPlayer = 'R';
        gameOver = false;
    }

    public void resetVisited() {
       for (int i = 0; i < 11; i++){
           for (int j = 0; j < 11; j++){
               checkCells[i][j].visited = false;
           }
       }
    }
}
