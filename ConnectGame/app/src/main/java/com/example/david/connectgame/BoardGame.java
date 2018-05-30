package com.example.david.connectgame;

import android.graphics.Point;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class BoardGame {
    private Board board;
    private Random rand;
    public boolean isFirstPlayerTurn;

    public BoardGame(Board board){
        this.board = board;
        isFirstPlayerTurn = true;
        rand = new Random();
    }

    public void reset() {
        board.reset();
    }

    public boolean isValidTurn(int x, int y) {
        if(board.getType(x,y) != SquareType.FREE) return false;

        SquareType player = isFirstPlayerTurn ?
                SquareType.FIRST_PLAYER :
                SquareType.SECOND_PLAYER;

        SquareType up = board.getType(x,y-1);
        SquareType down = board.getType(x,y+1);
        if(up == down && up == player) return true;

        SquareType left = board.getType(x-1,y);
        SquareType right = board.getType(x+1,y);
        if(left == right && left == player) return true;

        return false;
    }

    public void takeTurn(int x, int y){
        if(isFirstPlayerTurn){
            board.setSquare(x,y,SquareType.FIRST_PLAYER);
        }
        else{
            board.setSquare(x,y,SquareType.SECOND_PLAYER);
        }
    }
    public void togglePlayer() {
        isFirstPlayerTurn = isFirstPlayerTurn ? false : true;
    }
    public int size(){
            return board.size();
    }
    public SquareType getType(int x, int y){
        return board.getType(x,y);
    }
    public boolean won(){
        SquareType player = isFirstPlayerTurn ?
                SquareType.FIRST_PLAYER :
                SquareType.SECOND_PLAYER;

        Boolean[][] grid = makeGrid();
        Boolean[][] bruteForceGrid = new Boolean[grid.length][grid.length];
        boolean homeRowIsVertical = false;
            SquareType type = getType(1,2);
            homeRowIsVertical = (player == type);

        if(homeRowIsVertical){
            for(int i = 1; i < grid.length-1; i++){
                for(int j = 1; j < grid.length-1; j++){
                    bruteForceGrid = copyBoolArray(grid);
                    bruteForceGrid[i][0] = true;
                    bruteForceGrid[j][grid.length-1] = true;
                    floodFill(bruteForceGrid, 0, 0);
                    if(hasFalse(bruteForceGrid)){
                        return true;
                    }
                }
            }
        }
        else {
            for(int i = 1; i < grid.length-1; i++){
                for(int j = 1; j < grid.length-1; j++){
                    bruteForceGrid = copyBoolArray(grid);
                    bruteForceGrid[0][i] = true;
                    bruteForceGrid[grid.length-1][j] = true;
                    floodFill(bruteForceGrid, 0, 0);
                    if(hasFalse(bruteForceGrid)){
                        return true;
                    }
                }
            }
        }

/*
        if(homeRowIsVertical){
            for(int i = 2; i < grid.length-2; i+=2){
                for(int j = 2; j < grid.length-2; j+=2){
                    copyBoolArray(grid, bruteForceGrid);
                    bruteForceGrid[i][1] = true;
                    bruteForceGrid[j][grid.length-2] = true;
                    floodFill(bruteForceGrid, 0, 0);
                    if(hasFalse(bruteForceGrid)){
                        return true;
                    }
                }
            }
        }
        else {
            for (int i = 2; i < grid.length-2; i += 2) {
                for (int j = 2; j < grid.length-2; j += 2) {
                    copyBoolArray(grid, bruteForceGrid);
                    bruteForceGrid[1][i] = true;
                    bruteForceGrid[grid.length-2][j] = true;
                    floodFill(bruteForceGrid, 0, 0);
                    if (hasFalse(bruteForceGrid)) {
                        return true;
                    }
                }
            }
        }*/
        return false;
    }
    public void autoTurn() {
        int x = rand.nextInt(12);
        int y = rand.nextInt(12);
        while (!isValidTurn(x, y) ){
            x = (x + 7) % 12;
            y = (y + 5) % 12;
        }
        takeTurn(x,y);
    }

    private void floodFill(Boolean[][] grid,int x, int y) {
        if(
                x < 0 || x >= grid.length ||
                y < 0 || y >= grid.length ||
                grid[y][x] == true
          ) return;

        grid[y][x] = true;
        floodFill(grid, x - 1, y - 1);
        floodFill(grid, x, y - 1);
        floodFill(grid, x + 1, y - 1);

        floodFill(grid, x - 1, y);
        floodFill(grid, x + 1, y);

        floodFill(grid, x - 1, y + 1);
        floodFill(grid, x, y + 1);
        floodFill(grid, x + 1, y + 1);
    }



    private Boolean[][] makeGrid(){
        SquareType player = isFirstPlayerTurn ?
                SquareType.FIRST_PLAYER :
                SquareType.SECOND_PLAYER;
        int length = size()+2;
        Boolean[][] grid = new Boolean[length][length];
        //True = player square
        for(int y=0; y < length; y++) {
            for(int x=0; x < length; x++) {
                if(
                        x==0 || x==length-1 ||
                        y == 0 || y == length-1
                   ){
                    grid[y][x] = false;
                    continue;
                }
                SquareType type = board.getType(x, y);
                if (player == type) {
                    grid[y][x] = true;
                } else {
                    grid[y][x] = false;
                }
            }
        }
        return grid;
    }

    private Boolean[][] copyBoolArray(Boolean[][] source){
        Boolean[][] copy = new Boolean[source.length][source.length];
        for(int i = 0; i < source.length; i++){
            for(int j = 0; j < source.length; j++){
                copy[i][j] = source[i][j];
            }
        }
        return copy;
    }

    private void copyBoolArray(Boolean[][] source, Boolean[][] destination){
        for(int i = 0; i < source.length; i++){
            for(int j = 0; j < source.length; j++){
                destination[i][j] = source[i][j];
            }
        }
    }

    private boolean hasFalse(Boolean[][] grid){
        for(int i = 0; i < grid.length; i++)
            for(int j = 0; j < grid.length; j++)
                if(grid[i][j] == false) return true;
        return false;
    }
}
