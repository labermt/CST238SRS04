package com.example.david.connectbydave;

import java.util.ArrayList;

public class Board
{
    private ArrayList<ArrayList<Square>> squares;
    private int width, height;
    public Board(int xSquares, int ySquares)
    {
        width = xSquares > 0 ? xSquares : 8;
        height = ySquares > 0 ? ySquares : 8;
        squares = new ArrayList<>();

        for(int col = 0; col < width; col++)
        {
            squares.add(new ArrayList<Square>());
            for(int row = 0; row < height; row++)
            {
                SquareType even = (col%2 == 0 ? SquareType.FREE : SquareType.FIRST_PLAYER );
                SquareType odd = (col%2 == 0 ? SquareType.SECOND_PLAYER : SquareType.FREE );

                squares.get(row).add( new Square( (col%2 == 0) ? even : odd) );
            }
        }
    }

    public boolean setSquare(int x, int y, SquareType type)
    {
        if( isInRange(x, y) )
        { return false; }
        squares.get(x).get(y).set(type);
        return true;
    }
    public Square get(int x, int y)
    {
        return isInRange(x, y) ? squares.get(x).get(y) : null;
    }

    public ArrayList<ArrayList<Square>> getBoard()
    {
        return squares;
    }

    public boolean isInRange(int x, int y)
    {
        return x >=0 && y >= 0 && x < width && y < height;
    }
}
