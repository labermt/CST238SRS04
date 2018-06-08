package com.example.david.connectgame;

import android.graphics.Point;


public class Checker implements IWinChecker {
    BoardGame game;
    int size;
    boolean[][] grid;
    boolean hasLine, hasLoop;

    public Checker(BoardGame g){
        game = g;
    }
    @Override
    public boolean checkIfWon() {
        hasLine = hasLoop = false;
        size = game.size()+2;
        grid = new boolean[size][size];

        build(false);
        checkLine();
        build(true);
        checkLoop();

        return hasLine || hasLoop;
    }

    void build(boolean buildForLoop){
        for(int y = 0; y < size; y++){
            for(int x = 0; x < size; x++){
                if(x == 0 || y == 0 || x == size-1 || y == size-1) {
                    grid[y][x] = !buildForLoop;
                }
                else if(game.getType(x-1,y-1) == game.getCurrentPlayerType()){
                    grid[y][x] = true;
                }
                else{
                    grid[y][x] = false;
                }
            }
        }
        flood();
    }
    void flood(){
        floodFill(1,1);
        int i = game.size();
    }
    void checkLoop() {
        for (int y = 1; y < size-1; y++) {
            for (int x = 1; x < size-1; x++) {
                if (grid[y][x] == false) {
                    hasLoop = true;
                }
            }
        }
    }

    void checkLine(){
        boolean homeRowIsVertical = game.getType(0,1) == game.getCurrentPlayerType();
        boolean row1 = true, row2 = true;
        if(homeRowIsVertical){
            for(int i = 1; i < size-1; i++){
                if(grid[1][i] == true) row1 = false;
                if(grid[size-2][i] == true) row2 = false;
            }
        }
        else{
            for(int i = 1; i < size-1; i++){
                if(grid[i][1] == true) row1 = false;
                if(grid[i][size-2] == true) row2 = false;
            }
        }
        hasLine = row1 || row2;
    }

    void floodFill(int x, int y){
        if(!inRange(x,y)) return;
        if(grid[y][x] == true) return;

        grid[y][x] = true;
        floodFill(x,y-1);
        floodFill(x,y+1);
        floodFill(x-1,y);
        floodFill(x+1,y);
    }
    boolean inRange(int x, int y){
        return x >= 0 && x < size && y >= 0 && y < size;
    }
    boolean inRange(Point p){
        return inRange(p.x,p.y);
    }


    boolean unTouchedCell(int x, int y){
        return grid[y][x] == false;
    }
    boolean unTouchedCell(Point p){
        return unTouchedCell(p.x, p.y);
    }
}

