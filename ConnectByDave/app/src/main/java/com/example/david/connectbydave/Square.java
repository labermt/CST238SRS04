package com.example.david.connectbydave;



public class Square {

    private SquareType type;
    public boolean isFirstPlayer() { return type == SquareType.FIRST_PLAYER; }
    public boolean isSecondPlayer() { return type == SquareType.SECOND_PLAYER; }
    public boolean isFree() { return type == SquareType.FREE; }

    public Square(SquareType type){
        this.type = type;
    }
    public void set(SquareType newType){
        type = newType;
    }

    public SquareType getType()
    {
        return type;
    }
}
