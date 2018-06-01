package com.example.david.connectgame;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.Stack;

public class Checker implements IWinChecker {
    BoardGame game;
    int size;
    boolean[][] grid;
    boolean won;

    public Checker(BoardGame g){
        game = g;
    }
    @Override
    public boolean checkIfWon() {
        won = false;
        size = game.size();
        grid = new boolean[size][size];

        build();
        flood();
        edgeCaseLine();
        //edgeCaseLoop();
        winCheck();

        return won;
    }
    void build(){
        for(int y = 0; y < size; y++){
            for(int x = 0; x < size; x++){
                if(game.getType(x,y) == game.getCurrentPlayerType()){
                    grid[y][x] = true;
                }
            }
        }
    }
    void flood(){
        floodFill(0,0);
    }
    void edgeCaseLoop(){
        if(won) return;
        ArrayList<Point> points = new ArrayList<>();
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                if(grid[y][x]) points.add(new Point(x,y));
            }
        }
        for(Point p : points){
            if(isRealLoop(p)) {
                won = true;
                return;
            }
        }
    }
    void edgeCaseLine(){
        boolean homeRowIsVertical = game.getType(0,1) == game.getCurrentPlayerType();
        boolean row1 = true, row2 = true;
        if(homeRowIsVertical){
            for(int i = 0; i < size; i++){
                if(grid[0][i] == true) row1 = false;
                if(grid[size-1][i] == true) row2 = false;
            }
        }
        else{
            for(int i = 0; i < size; i++){
                if(grid[i][0] == true) row1 = false;
                if(grid[i][size-1] == true) row2 = false;
            }
        }
        if(row1 || row2) won = true;
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
        return x >=0 && x < size && y >= 0 && y < size;
    }
    boolean inRange(Point p){
        return p.x >=0 && p.x < size && p.y >= 0 && p.y < size;
    }
    boolean unTouchedCell(int x, int y){
        return grid[y][x] == false;
    }
    boolean unTouchedCell(Point p){
        return unTouchedCell(p.x, p.y);
    }
    void winCheck(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(grid[i][j] == false) {
                    won = true;
                    return;
                }
            }
        }
    }

    boolean isRealLoop(Point p) {
        ArrayList<Point> points = new ArrayList();
        Stack<Point> todo = new Stack();
        todo.push(p);
        return isRealLoop(todo, points);
    }

    boolean isRealLoop(Stack<Point> todo, ArrayList<Point> checked) {
        Point p = todo.pop();
        checked.add(p);

        Point up = new Point(p.x, p.y-1);
        if(!inRange(up))return false;
        if(unTouchedCell(up) && !checked.contains(up)){
            todo.push(up);
        }
        Point down = new Point(p.x, p.y+1);
        if(!inRange(down))return false;
        if(unTouchedCell(down) && !checked.contains(down)){
            todo.push(down);
        }
        Point left = new Point(p.x-1, p.y);
        if(!inRange(left))return false;
        if(unTouchedCell(left) && !checked.contains(left)){
            todo.push(left);
        }
        Point right = new Point(p.x+1, p.y);
        if(!inRange(right))return false;
        if(unTouchedCell(right) && !checked.contains(right)){
            todo.push(right);
        }
        return true;
    }


}


/*

for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){

            }
        }

return inRange(p.x,p.y-1) &&
                    inRange(p.x,p.y+1) &&
                    inRange(p.x-1,p.y) &&
                    inRange(p.x+1,p.y);

 */