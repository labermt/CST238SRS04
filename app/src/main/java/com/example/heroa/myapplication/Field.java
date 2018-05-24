package com.example.heroa.myapplication;

public class Field
{
    Tile field[][] = new Tile[11][11];

    Field()
    {
        BuildField();
    }

    void ResetCheckedOnField()
    {
        for(int x = 0; x < 11; x++)
        {
            for(int y = 0; y < 11; y++)
            {
                field[x][y].SetChecked(false);
            }
        }
    }

    void BuildField()
    {
        //Player One, starts at first row
        for(int y = 0; y < 11; y += 2)
        {
            for(int x = 1; y < 11; x+=2)
            {
                field[x][y].state = State.PLAYERONE;
            }
        }
        //Player Two, starts at second row
        for(int y = 1; y < 11; y += 2)
        {
            for(int x = 0; y < 11; x+=2)
            {
                field[x][y].state = State.PLAYERTWO;
            }
        }
    }

    void SetTile(int xTile, int yTile, State player)
    {
        field[xTile][yTile].SetState(player);
    }

    State GetTileState(int xTile, int yTile)
    {
        return field[xTile][yTile].GetState();
    }
}
