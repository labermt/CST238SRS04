package com.example.heroa.myapplication;

public class GameController
{
    public Field field = new Field();
    public boolean turn = true;
    private Location origin = new Location(0, 0);
    boolean leftFound = false;
    boolean rightFound = false;
    boolean topFound = false;
    boolean bottomFound = false;

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
        boolean valid = true;

        if(field.GetTileState(xTile, yTile) != State.EMPTY)
            valid = false;
        if(!turn && yTile == 0 || !turn && yTile == 10)
            valid = false;
        if(turn && xTile == 0 || turn && xTile == 10)
            valid = false;

        return valid;
    }

    public Tile GetTile(int tileX, int tileY)
    {
        return field.field[tileX][tileY];
    }

    public void PickTile(int tileX, int tileY)
    {
        if(turn)
            field.field[tileX][tileY].SetState(State.PLAYERONE);
        else
            field.field[tileX][tileY].SetState(State.PLAYERTWO);

        ToggleTurn();
    }

    public boolean CheckForWin(int tileX, int tileY)
    {
        boolean win;
        origin.locX = tileX;
        origin.locY = tileY;

        win = CheckForWinRecurse(tileX, tileY, 0);
        ResetChecks();

        return win;
    }

    private boolean CheckForWinRecurse(int tileX, int tileY, int recursions)
    {
        GetTile(tileX, tileY).SetChecked(true);
        if(tileX == 0)
            leftFound = true;
        if(tileX == 10)
            rightFound = true;
        if(tileY == 0)
            topFound = true;
        if(tileY == 10)
            bottomFound = true;
        if(topFound && bottomFound)
            return true;
        if(leftFound && rightFound)
            return true;


        if(tileX - 1 > -1 && GetTile(tileX, tileY).state == GetTile(tileX - 1, tileY).state){
            if(GetTile(tileX - 1, tileY).checked && IsOrigin(tileX - 1, tileY, recursions))
            {
                return true;
            }
            else if(!GetTile(tileX - 1, tileY).checked){
                if (CheckForWinRecurse(tileX - 1, tileY, recursions + 1))
                    return true;
            }
        }
        if(tileX + 1 < 11 && GetTile(tileX, tileY).state == GetTile(tileX + 1, tileY).state){
            if(GetTile(tileX + 1, tileY).checked && IsOrigin(tileX + 1, tileY, recursions))
            {
                return true;
            }
            else if(!GetTile(tileX + 1, tileY).checked){
                if(CheckForWinRecurse(tileX + 1, tileY, recursions + 1))
                    return true;
            }
        }
        if(tileY -1 >= 0 && GetTile(tileX, tileY).state == GetTile(tileX, tileY - 1).state){
            if(GetTile(tileX, tileY - 1).checked && IsOrigin(tileX, tileY - 1, recursions))
            {
                return true;
            }
            else if(!GetTile(tileX, tileY - 1).checked){
                if(CheckForWinRecurse(tileX, tileY - 1, recursions + 1))
                    return true;
            }
        }
        if(tileY + 1 < 11 && GetTile(tileX, tileY).state == GetTile(tileX, tileY + 1).state){
            if(GetTile(tileX, tileY + 1).checked && IsOrigin(tileX, tileY + 1, recursions))
            {
                return true;
            }
            else if(!GetTile(tileX, tileY + 1).checked){
                if(CheckForWinRecurse(tileX, tileY + 1, recursions + 1))
                    return true;
            }
        }
        return false;
    }

    public boolean IsOrigin(int tileX, int tileY, int recursions)
    {
        return (recursions > 2 && origin.locX == tileX && origin.locY == tileY);
    }

    private void ResetChecks()
    {
        topFound = false;
        bottomFound = false;
        leftFound = false;
        rightFound = false;
        field.ResetCheckedOnField();
    }

}

