package com.example.david.connectgame;

import android.graphics.Point;
import android.util.Pair;

import java.util.ArrayList;
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
        floodFill(grid,0,0);
        //true = original player square or checked
        boolean result = hasFalse(grid);
        return result;
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
        int length = size();
        Boolean[][] grid = new Boolean[length][length];
        //True = player square
        for(int y=0; y < length; y++) {
            for(int x=0; x < length; x++) {
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

    private boolean hasFalse(Boolean[][] grid){
        for(int i = 0; i < grid.length; i++)
            for(int j = 0; j < grid.length; j++)
                if(grid[i][j] == false) return true;
        return false;
    }

    private void alternateFlood(Integer[][] grid, int x, int y){
        if(
                x < 0 || x >= grid.length ||
                        y < 0 || y >= grid.length ||
                        grid[y][x] == 0 ) return;
        grid[y][x]++;

        alternateFlood(grid, x - 1, y - 1);
        alternateFlood(grid, x, y - 1);
        alternateFlood(grid, x + 1, y - 1);

        alternateFlood(grid, x - 1, y);
        alternateFlood(grid, x + 1, y);

        alternateFlood(grid, x - 1, y + 1);
        alternateFlood(grid, x, y + 1);
        alternateFlood(grid, x + 1, y + 1);

    }

    private Integer[][] makeAlternateGrid(){
        SquareType player = isFirstPlayerTurn ?
                SquareType.FIRST_PLAYER :
                SquareType.SECOND_PLAYER;
        int length = size();
        Integer[][] grid = new Integer[length][length];
        //0 = not player, 1 = player
        for(int y=0; y < length; y++) {
            for(int x=0; x < length; x++) {
                SquareType type = board.getType(x, y);
                if (player == type) {
                    grid[y][x] = 1;
                } else {
                    grid[y][x] = 0;
                }
            }
        }
        return grid;
    }
}
