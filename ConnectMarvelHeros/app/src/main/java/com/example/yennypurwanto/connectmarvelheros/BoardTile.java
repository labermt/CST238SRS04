package com.example.yennypurwanto.connectmarvelheros;

import android.widget.ImageButton;

import static com.example.yennypurwanto.connectmarvelheros.TileStatus.BLANK;

enum TileStatus{BLANK, BP, SW, GL, SM, CORNER}

public class BoardTile {

    private boolean activeTile;
    private TileStatus status;
    private Position position;

    public BoardTile(){
        status = BLANK;
        activeTile = true;
        position = new Position();
    }

    public TileStatus getStatus(){
        return status;
    }

    public boolean getActiveStatus(){
        return activeTile;
    }

    public void deactivateTile(){
        activeTile = false;
    }

    public void changeStatus(TileStatus inStat){
        status = inStat;
    }

    public boolean equals(BoardTile inTile) {
        boolean outEquals = false;

        if (status.equals(inTile.getStatus()) && activeTile == inTile.getActiveStatus()){
            outEquals = true;
        }
        return outEquals;
    }

    public void setPosition(float x, float y){
        position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }
}
