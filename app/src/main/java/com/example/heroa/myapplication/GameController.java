package com.example.heroa.myapplication;

public class GameController
{
    public Field field = new Field();
    public boolean turn = true;

    public void ToggleTurn() {
        if (turn) {
            turn = false;
        }
        else
        {
            turn = true;
        }
    }

    public boolean isValidMove(int xTile, int yTile)
    {
        boolean valid = false;

        if(field.GetTileState(xTile, yTile) == State.EMPTY)
            valid = true;

        return valid;
    }

    public void PickTile(int xTile, int yTile)
    {

    }

}

