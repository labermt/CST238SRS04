package com.example.david.connectgame;

import android.graphics.Point;
import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.Random;

public class BoardGame {
    private Board board;
    private Random rand;
    private IWinChecker winChecker;
    public boolean isFirstPlayerTurn;
    private Point lastPoint;

    public BoardGame(Board board){
        this.board = board;

        winChecker = new Checker(this);

        isFirstPlayerTurn = true;
        rand = new Random();
        lastPoint = new Point();
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
        lastPoint.x = x;
        lastPoint.y = y;
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
        return winChecker.checkIfWon();
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
    public ArrayList<Point> getAllPlayerCells(SquareType player){
        ArrayList<Point> points = new ArrayList<>();
        for(int y = 0; y < size(); y++){
            for(int x = 0; x < size(); x++){
                if(getType(x,y) == player){
                    points.add(new Point(x,y));
                }
            }
        }
        return points;
    }
    public SquareType getCurrentPlayerType(){
        if(isFirstPlayerTurn){
            return SquareType.FIRST_PLAYER;
        }
        return SquareType.SECOND_PLAYER;
    }
    public Point getLastPoint(){
        return lastPoint;
    }
    public Pair<ArrayList<Point>,ArrayList<Point>> getHomeRowPoints(){
        ArrayList row1 = new ArrayList<>();
        ArrayList row2 = new ArrayList<>();
        SquareType player = getCurrentPlayerType();
        for(int i = 1; i < size()-1; i+=2){
            if(getType(i,0) == player){
                row1.add(new Point(i,0));
                row2.add(new Point(i,size()-1));
            }
            else{
                row1.add(new Point(0,i));
                row2.add(new Point(size()-1, i));
            }
        }
        return new Pair(row1,row2);
    }
}
