package com.example.heroa.myapplication;

public class GameController
{
    Field field;
    State turn;

    void ToggleTurn() {
        if (turn == State.PLAYERONE) {
            turn = State.PLAYERTWO;
        }
        else
        {
            turn = State.PLAYERONE;
        }
    }

    boolean isValidMove(int xTile, int yTile)
    {
        boolean valid = false;

        if(field.GetTileState(xTile, yTile) == State.EMPTY)
            valid = true;

        return valid;
    }

    void PickTile(int xTile, int yTile)
    {

    }

}

