package com.example.david.connectgame;


import java.util.ArrayList;

public class PathFinder implements IWinChecker{

    BoardGame game;
    PathNode[][] grid;
    boolean foundLoopOrLine = false;
    public PathFinder(BoardGame g){
        game = g;
    }

    public boolean checkIfWon(){
        foundLoopOrLine = false;
        makePathFinderGrid();
        ArrayList<PathNode> nodes = new ArrayList<>();
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                PathNode n = grid[i][j];
                if(n.isPlayerSquare && n.isUnChecked){
                    n.setAsChecked();
                    nodes.add(n);
                }
            }
        }

        for(PathNode node : nodes){
            runPathFinder(node, node);
        }

        runLineFinder();
        return foundLoopOrLine;
    }

    private void makePathFinderGrid(){
        SquareType player = game.isFirstPlayerTurn ?
                SquareType.FIRST_PLAYER :
                SquareType.SECOND_PLAYER;
        int length = game.size();
        if(grid == null) {
            grid = new PathNode[length][length];
        }

        for(int y=0; y < length; y++) {
            for(int x=0; x < length; x++) {
                SquareType type = game.getType(x, y);
                if (player == type) {
                    grid[y][x] = new PathNode(x,y,true);
                } else {
                    grid[y][x] = new PathNode(x,y,false);
                }
            }
        }
    }

    private ArrayList<PathNode> getValidNeighbors(PathNode current, PathNode parent){
        ArrayList<PathNode> neighbors = new ArrayList<>();
        addIfValid(neighbors, parent, current.location.x-1, current.location.y-1);
        addIfValid(neighbors, parent, current.location.x, current.location.y-1);
        addIfValid(neighbors, parent,current.location.x+1, current.location.y-1);
        addIfValid(neighbors, parent,current.location.x-1, current.location.y);
        addIfValid(neighbors, parent,current.location.x+1, current.location.y);
        addIfValid(neighbors, parent,current.location.x-1, current.location.y+1);
        addIfValid(neighbors, parent, current.location.x, current.location.y+1);
        addIfValid(neighbors, parent,current.location.x+1, current.location.y+1);

        return neighbors;
    }

    private void addIfValid(ArrayList<PathNode> pointList, PathNode parent, int x, int y){
        if(x >= 0 && x < grid.length &&
                y >= 0 && y < grid.length &&
                grid[y][x].isPlayerSquare &&
                grid[y][x].location != parent.location){
            pointList.add(grid[y][x]);
        }
    }

    private void runLineFinder(){
        for(int i = 1; i < grid.length - 1; i++){
            for(int j = 1; j < grid.length - 1; j++){
                if(grid[0][i].isPlayerSquare &&
                grid[grid.length - 1][j].isPlayerSquare ){
                    if(grid[0][i].root == grid[grid.length - 1][j].root){
                        setFound();
                    }
                }
            }
        }
        for(int i = 1; i < grid.length - 1; i++){
            for(int j = 1; j < grid.length - 1; j++){
                if(grid[i][0].isPlayerSquare &&
                grid[j][grid.length - 1].isPlayerSquare) {
                    if (grid[i][0].root == grid[j][grid.length - 1].root) {
                        setFound();
                    }
                }
            }
        }
    }

    private void setFound(){
        foundLoopOrLine = true;
    }

    private void runPathFinder(PathNode node, PathNode parent){
        if(node.isChecked() || foundLoopOrLine)return;
        ArrayList<PathNode> nodes = getValidNeighbors(node, parent);
        for(PathNode n: nodes){
            if(n.root == node.root){
                setFound();
                return;
            }
            else{
                n.root = node.root;
                runPathFinder(n, node);
            }
        }
    }
}
