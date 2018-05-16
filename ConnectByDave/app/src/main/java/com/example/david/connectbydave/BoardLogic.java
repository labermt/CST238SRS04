package com.example.david.connectbydave;

public class BoardLogic
{
    private Board board;

    public void create(int homeRowPieces)
    {
        if(board == null)
        {
            board = new Board(homeRowPieces);
        }
        else board.reset(homeRowPieces);
    }

    public boolean isValidMove(int x, int y, boolean isFirstPlayer)
    {
        if( board.getSquare(x, y).getType() != SquareType.FREE ) return false;

        SquareType player = isFirstPlayer ? SquareType.FIRST_PLAYER : SquareType.SECOND_PLAYER;

        Square up = board.getSquare(x , y - 1);
        Square down = board.getSquare(x , y + 1);
        Square left = board.getSquare(x - 1 , y);
        Square right = board.getSquare(x + 1 , y);
        boolean verticalMatch = ( up.getType() == player && down.getType() == player );
        boolean horizontalMatch = ( left.getType() == player && right.getType() == player );

        return verticalMatch || horizontalMatch;
    }

    public boolean takeTurn(int x, int y, boolean isFirstPlayer)
    {
        SquareType player = isFirstPlayer ? SquareType.FIRST_PLAYER : SquareType.SECOND_PLAYER;
        if(isValidMove(x, y, isFirstPlayer))
        {
            board.setSquare(x, y, player);
            return true;
        }
        return false;
    }
}
