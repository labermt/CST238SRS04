package com.example.david.connectgame;

import java.util.Random;

public class BoardGame {
    private Board board;
    private Random rand;
    public boolean isFirstPlayerTurn;

    public BoardGame(Board board){
        this.board = board;
        isFirstPlayerTurn = true;
        rand = new Random();
    }

    public void Reset() {
        board.Reset();
    }

    public boolean isValidTurn(boolean isFirstPlayerTurn, int x, int y) {
        Square s = board.getSquare(x,y);
        if(s.getType() != SquareType.FREE) return false;

        SquareType player = isFirstPlayerTurn ? SquareType.FIRST_PLAYER : SquareType.SECOND_PLAYER;
        SquareType up = board.getType(x,y-1);
        SquareType down = board.getType(x,y+1);
        if(up == down && up == player) return true;

        SquareType left = board.getType(x-1,y);
        SquareType right = board.getType(x+1,y);
        if(left == right && left == player) return true;

        return false;
    }

    public boolean takeTurn(boolean isFirstPlayerTurn, int x, int y){
        if(!isValidTurn(isFirstPlayerTurn,x,y)) {
            return false;
        }
        SquareType player = isFirstPlayerTurn ?
                SquareType.FIRST_PLAYER :
                SquareType.SECOND_PLAYER;
        board.setSquare(x,y,player);
        return true;
    }

    public boolean won(boolean isFirstPlayerTurn){
        SquareType player = isFirstPlayerTurn ? SquareType.FIRST_PLAYER : SquareType.SECOND_PLAYER;
        int length = board.size();

        Boolean[][] grid = new Boolean[length+2][length+2];
        //create grid of board with extra padding. True = player square or checked.
        for(int y=0; y < length+2; y++) {
            for(int x=0; x < length+2; x++) {
                if(y==0 || y==length+1 || x==0 || x==length+1){
                    grid[y][x] = true;
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
        floodFill(grid,1,1);

        return !isItFull(grid);
    }
    public void autoTurn() {
        int x = rand.nextInt(12);
        int y = rand.nextInt(12);
        while (!takeTurn(isFirstPlayerTurn, x, y) ){
            x = (x + 7) % 12;
            y = (y + 5) % 12;
        }
    }

    private void floodFill(Boolean[][] grid,int x, int y) {
        if (grid[y][x] == false) {
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
    }

    private boolean isItFull(Boolean[][] grid){
        //idea from Mitch Besser
        //if the grid has any cells that were not reached
        //than there is either a loop, or the board is intersected

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++)
            {
                if(!grid[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
