package com.example.david.connectgame;

public class BoardGame {
    private Board board;
    public boolean isFirstPlayerTurn;
    public BoardGame(Board board){
        this.board = board;
        isFirstPlayerTurn = true;
    }
    public void Reset() {
        board.Reset();
    }

    public boolean Won(boolean isFirstPlayerTurn) {

        return false;
    }
}
