package com.example.heroa.myapplication;

public class Field
{
    public Tile[][] field = new Tile[11][11];

    public Field()
    {
        for(int i = 0; i < 11; i++)
        {
            for(int j = 0; j < 11; j++)
            {
                field[i][j] = new Tile();
            }
        }
        BuildField();
    }

    public void ResetCheckedOnField()
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
            for(int x = 1; x < 11; x+=2)
            {
                GetTile(x, y).SetState(State.PLAYERONE);
            }
        }
        //Player Two, starts at second row
        for(int y = 1; y < 11; y += 2)
        {
            for(int x = 0; x < 11; x+=2)
            {
                GetTile(x, y).SetState(State.PLAYERTWO);
            }
        }
    }

    public void SetTile(int xTile, int yTile, State player)
    {
        field[xTile][yTile].SetState(player);
    }

    public State GetTileState(int xTile, int yTile)
    {
        return field[xTile][yTile].GetState();
    }

    public Tile GetTile(int xTile, int yTile)
    {
        return field[xTile][yTile];
    }
}
