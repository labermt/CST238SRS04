package com.example.david.connectgame;

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

    public Board()
    {
        int homeRowPieces = 5;
        length = homeRowPieces > 0 ? homeRowPieces : 5;
        length = length * 2 + 1;

        squares = new ArrayList<>();

        for(int y = 0; y < length; y++)
        {
            squares.add(new ArrayList<Square>());
            for(int x = 0; x < length; x++)
            {
                SquareType evenRow = (y%2 == 0 ? SquareType.FREE : SquareType.SECOND_PLAYER );
                SquareType oddRow = (y%2 == 0 ? SquareType.FIRST_PLAYER : SquareType.FREE );

                squares.get(y).add( new Square( (x%2 == 0) ? evenRow : oddRow) );
            }
        }
    }

    public boolean setSquare(int x, int y, SquareType type)
    {
        if( !isInRange(x, y) )
        {
            return false;
        }
        squares.get(y).get(x).set(type);
        return true;
    }

    public SquareType getType(int x, int y)
    {
        if(!isInRange(x, y)) { return SquareType.NULL; }
        return squares.get(y).get(x).getType();
    }

    public Square getSquare(int x, int y)
    {
        return isInRange(x, y) ? squares.get(y).get(x) : nullSquare;
    }

    public ArrayList<ArrayList<Square>> getBoard()
    {
        return squares;
    }

    public boolean isInRange(int x, int y)
    {
        return x >=0 && y >= 0 && x < length && y < length;
    }

    public void Reset()
    {
        for(int i=0; i < squares.size(); i++)
        {
            squares.get(i).clear();
        }
        squares.clear();

        squares = new ArrayList<>();

        for(int y = 0; y < length; y++)
        {
            squares.add(new ArrayList<Square>());
            for(int x = 0; x < length; x++)
            {
                SquareType evenRow = (y%2 == 0 ? SquareType.FREE : SquareType.SECOND_PLAYER );
                SquareType oddRow = (y%2 == 0 ? SquareType.FIRST_PLAYER : SquareType.FREE );

                squares.get(y).add( new Square( (x%2 == 0) ? evenRow : oddRow) );
            }
        }
    }

    public int size() { return length; }
}
