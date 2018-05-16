package com.example.david.connectbydave;

import java.util.ArrayList;

public class Board
{
    private ArrayList<ArrayList<Square>> squares;
    private int length;
    private final Square nullSquare = new Square(SquareType.NULL);

    public Board(int homeRowPieces)
    {
        length = homeRowPieces > 0 ? homeRowPieces : 5;
        length = length * 2 + 1;

        squares = new ArrayList<>();

        for(int col = 0; col < length; col++)
        {
            squares.add(new ArrayList<Square>());
            for(int row = 0; row < length; row++)
            {
                SquareType evenRow = (col%2 == 0 ? SquareType.FREE : SquareType.SECOND_PLAYER );
                SquareType oddRow = (col%2 == 0 ? SquareType.FIRST_PLAYER : SquareType.FREE );

                squares.get(row).add( new Square( (col%2 == 0) ? evenRow : oddRow) );
            }
        }
    }

    public boolean setSquare(int x, int y, SquareType type)
    {
        if( !isInRange(x, y) )
        {
            return false;
        }
        squares.get(x).get(y).set(type);
        return true;
    }

    public SquareType getType(int x, int y)
    {
        if(!isInRange(x, y)) { return SquareType.NULL; }
        return squares.get(x).get(y).getType();
    }

    public Square getSquare(int x, int y)
    {
        return isInRange(x, y) ? squares.get(x).get(y) : nullSquare;
    }

    public ArrayList<ArrayList<Square>> getBoard()
    {
        return squares;
    }

    public boolean isInRange(int x, int y)
    {
        return x >=0 && y >= 0 && x < length && y < length;
    }

    public void reset(int length)
    {
        for(int i=0; i < squares.size(); i++)
        {
            squares.get(i).clear();
        }
        squares.clear();

        this.length = length > 0 ? length : 11;

        squares = new ArrayList<>();

        for(int col = 0; col < length; col++)
        {
            squares.add(new ArrayList<Square>());
            for(int row = 0; row < length; row++)
            {
                SquareType even = (col%2 == 0 ? SquareType.FREE : SquareType.FIRST_PLAYER );
                SquareType odd = (col%2 == 0 ? SquareType.SECOND_PLAYER : SquareType.FREE );

                squares.get(row).add( new Square( (col%2 == 0) ? even : odd) );
            }
        }
    }
    public void reset()
    {
        reset(length);
    }
}
