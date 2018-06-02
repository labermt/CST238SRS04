package com.example.yennypurwanto.connectmarvelheros;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;

public class GameAlg {

    private BoardTile[][] matrix;
    MainActivity main;
    uGraph graph;
    TileStatus currentPlayer;
    TileStatus P1;
    TileStatus P2;
    boolean endGame;
    boolean activeButton;

    public GameAlg(){
        P1 = TileStatus.SM;
        P2 = TileStatus.GL;
        currentPlayer = P1;

        matrix = new BoardTile[11][11];
        for(int i =0; i < 11;i++)
        {
            for(int j =0; j < 11;j++)
            {
                matrix[i][j] = new BoardTile();
            }
        }

        graph = new uGraph();
        StartNewGame();
    }

    public void StartNewGame(){
        boardSetup();
        endGame = false;
    }

    public boolean isEndGame(){
        return endGame;
    }

    public void deactivateButton(){
        activeButton = false;
    }

    public void changePlayer(){
        currentPlayer = (currentPlayer == P1? P2: P1);
    }

    public BoardTile getButtonId(int x, int y){
        return matrix[x][y];
    }

    private void boardSetup(){

        //deactivate corner tiles
        matrix[0][0].deactivateTile();
        matrix[0][0].changeStatus(TileStatus.CORNER);

        matrix[10][10].deactivateTile();
        matrix[10][10].changeStatus(TileStatus.CORNER);

        matrix[0][10].deactivateTile();
        matrix[0][10].changeStatus(TileStatus.CORNER);

        matrix[10][0].deactivateTile();
        matrix[10][0].changeStatus(TileStatus.CORNER);

        //setting up P1 Tiles
        for(int i = 0; i < 11; i+=2){
            for(int j = 1; j < 10; j+=2){
                matrix[i][j].changeStatus(P1);
                matrix[i][j].setPosition(i, j);
                matrix[i][j].deactivateTile();

                //add Vertex obj
                Vertex tempVert = new Vertex(matrix[i][j]);
                graph.insertVertex(tempVert);
            }
        }

        //setting up P2 Tiles
        for(int i = 1; i < 10; i+=2){
            for(int j = 0; j < 11; j+=2){
                matrix[i][j].changeStatus(P2);
                matrix[i][j].setPosition(i, j);
                matrix[i][j].deactivateTile();

                //add Vertex obj
                Vertex tempVert = new Vertex(matrix[i][j]);
                graph.insertVertex(tempVert);
            }
        }
    }

    public boolean validMove(int x, int y){
        boolean valid = false;

        if(matrix[x][y].getActiveStatus()){
            if(currentPlayer == P1){
                //cannot fill in col[0] and col[10]
                if(y > 0 && y < 10){
                    valid = true;
                }
            }
            if(currentPlayer == P2){
                //cannot fill in row[0] and row[10]
                if(x > 0 && x < 10){
                    valid = true;
                }
            }
        }
        return valid;
    }

    public void makeMove(int x, int y){

        if(!endGame){
            if (validMove(x, y)) {
                matrix[x][y].changeStatus(currentPlayer);
                matrix[x][y].deactivateTile();
                changePlayer();

                ArrayList<Vertex> thisList = new ArrayList<>();

                try{
                    Vertex vtop = graph.getSingleVertex(x-1, y, currentPlayer);
                    thisList.add(vtop);
                }
                catch (Exception e){}

                try{
                    Vertex vbottom = graph.getSingleVertex(x+1, y, currentPlayer);
                    thisList.add(vbottom);
                }
                catch (Exception e){}

                try{
                    Vertex vleft = graph.getSingleVertex(x, y-1, currentPlayer);
                    thisList.add(vleft);
                }
                catch (Exception e){}

                try{
                    Vertex vright = graph.getSingleVertex(x, y+1, currentPlayer);
                    thisList.add(vright);
                }
                catch (Exception e){}

                //bidirectional info for alg
                thisList.get(0).insertEdge(thisList.get(1), currentPlayer);
                thisList.get(1).insertEdge(thisList.get(0), currentPlayer);

                //check winning player then send mssg
                if(checkWinGame(x,y, currentPlayer)){
                    endGame = true;
                }
            }
        }
    }

    public boolean checkWinGame(int x, int y, TileStatus tile){
        boolean win = false;

        /*ArrayList<Position> startPos = new ArrayList<>();
        ArrayList<Position> endPos = new ArrayList<>();

        if(currentPlayer == P1){
            //checking winning via Line mode
            startPos.add(new Position(1,0));
            startPos.add(new Position(3,0));
            startPos.add(new Position(5,0));
            startPos.add(new Position(7,0));
            startPos.add(new Position(9,0));

            endPos.add(new Position(1,10));
            endPos.add(new Position(3,10));
            endPos.add(new Position(5,10));
            endPos.add(new Position(7,10));
            endPos.add(new Position(9,10));
        }
        else
        {
            //checking winning via Line mode
            startPos.add(new Position(0,1));
            startPos.add(new Position(0,3));
            startPos.add(new Position(0,5));
            startPos.add(new Position(0,7));
            startPos.add(new Position(0,9));

            endPos.add(new Position(10,1));
            endPos.add(new Position(10,3));
            endPos.add(new Position(10,5));
            endPos.add(new Position(10,7));
            endPos.add(new Position(10,9));
        }

        //run search
        for(Position sp: startPos){
            for (Position ep: endPos){
                if(graph.breadthfirstSearch(sp, ep)){
                    win = true;
                }
            }
        }

        //checking winning via Circular mode
        if(graph.breadthfirstSearch(new Position(x, y), new Position(x,y))){
            win = true;
        }*/

        return win;
    }

}
