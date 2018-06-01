package com.example.david.connectgame;

import android.graphics.Point;
import android.support.v4.util.Pair;

import java.util.ArrayList;

public class LoopOrLineChecker implements IWinChecker {
    BoardGame game;
    boolean won = false;

    public LoopOrLineChecker(BoardGame g){
        game = g;
    }
    public boolean checkIfWon(){
        won = false;
        //check for any loops
        //ArrayList<Point> points = game.getAllPlayerCells(game.getCurrentPlayerType());
        boolean[][] checkedForLines = new boolean[game.size()][game.size()];
        Point p = game.getLastPoint();
        recurseLoopPath(p);

        Pair<ArrayList<Point>,ArrayList<Point>> homeRows = game.getHomeRowPoints();
        boolean homeRowIsVertical = isVerticalHomeRow();
        for(Point pts: homeRows.first){
            //recurseLinePath(checkedForLines, p, homeRows.second);
        }
        return won;
    }

    private void recurseLoopPath(Point current){
        recurseLoopPath(new boolean[game.size()][game.size()], current, current, current, true);
    }

    private void recurseLoopPath(boolean[][] checked, Point current, Point parent, Point root, boolean firstTurn){
        if(won) return;
        if(!firstTurn && !validPosition(current, parent)) return;
        if(checked[current.y][current.x]) return;
        if(current.equals(root) && !firstTurn){
            won = true;
            return;
        }



        if(!firstTurn) checked[current.y][current.x] = true;
        recurseLoopPath(checked, new Point(current.x, current.y - 1), current, root, false);//up
        recurseLoopPath(checked, new Point(current.x + 1, current.y), current, root, false);//right
        recurseLoopPath(checked, new Point(current.x, current.y + 1), current, root, false);//down
        recurseLoopPath(checked, new Point(current.x - 1, current.y), current, root, false);//left
    }

    private void checkIfChecked(boolean[][] checked, ArrayList<Point> points){
        for(Point p : points){
            if(checked[p.y][p.x]) won = true;
        }
    }
    private void recurseLinePath(boolean[][] checked, Point current, ArrayList<Point> oppositeHomeRow){
        recurseLinePath(checked, current, new Point(-99,-99), isVerticalHomeRow());
    }


    private void recurseLinePath(boolean[][] checked, Point current, Point parent, boolean hasVerticalHomeRow){
        if(won) return;
        if(!validPosition(current, parent)) return;
        if(checked[current.y][current.x]) return;

        checked[current.y][current.x] = true;
        recurseLinePath(checked, new Point(current.x,current.y-1), current, hasVerticalHomeRow);//up
        recurseLinePath(checked, new Point(current.x+1,current.y), current, hasVerticalHomeRow);//right
        recurseLinePath(checked, new Point(current.x,current.y+1), current, hasVerticalHomeRow);//down
        recurseLinePath(checked, new Point(current.x-1,current.y), current, hasVerticalHomeRow);//left
    }

    private boolean validCell(Point current) {
        if  ( current.x < 0 || current.x >= game.size() ||
                current.y < 0 || current.y >= game.size() ) {
            return false;
        }

        return game.getType(current.x,current.y) == game.getCurrentPlayerType();
    }
    private boolean validPosition(Point current, Point parent) {
        if  ( current.x < 0 || current.x >= game.size() ||
                current.y < 0 || current.y >= game.size() ) {
            return false;
        }
        return game.getType(current.x,current.y) == game.getCurrentPlayerType() && current != parent;
    }
    private boolean isVerticalHomeRow(){
        return (game.getType(0,1) == game.getCurrentPlayerType());
    }

}
