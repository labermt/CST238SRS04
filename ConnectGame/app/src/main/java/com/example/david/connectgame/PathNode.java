package com.example.david.connectgame;

import android.graphics.Point;


public class PathNode {
    public boolean isUnChecked = true;
    public boolean isPlayerSquare;
    public Point location;
    public Point root;
    public PathNode(Point location, Point root, boolean isPlayerSquare){
        this.isPlayerSquare = isPlayerSquare;
        this.location = new Point(location);
        this.root = new Point(root);
    }
    public PathNode(Point initialLocation, boolean isPlayerSquare){
        this.isPlayerSquare = isPlayerSquare;
        location = new Point(initialLocation);
        root = new Point(location);
    }
    public PathNode(int xLoc, int yLoc, int xRoot, int yRoot, boolean isPlayerSquare){
        this.isPlayerSquare = isPlayerSquare;
        location = new Point(xLoc, yLoc);
        root = new Point(xRoot, yRoot);
    }
    public PathNode(int xLoc, int yLoc, boolean isPlayerSquare){
        this.isPlayerSquare = isPlayerSquare;
        location = new Point(xLoc, yLoc);
        root = new Point(location);
    }
    public boolean isEmpty(){
        return !isPlayerSquare;
    }
    public boolean isChecked(){
        return !isUnChecked;
    }
    public void setAsChecked(){
        isUnChecked = false;
    }

    @Override
    public boolean equals(Object obj) {
        PathNode o = (PathNode)obj;
        if(o == null) return false;
        return location.equals(o.location);
    }
}
