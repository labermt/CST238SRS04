package com.example.david.connectgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;


public class BoardView extends View {


    private Board board;
    private Rect background;
    private Paint backgroundPaint;
    private ArrayList<SquareView> squares;
    private SquareView focused;
    private SquareView first,second,free,nullSquare;
    private int boardWidth, cellWidth, cellPadding, boardPadding;
    private boolean hasFocus;

    public BoardView(Context c, AttributeSet a) {
        super(c,a);
        setFocusable(true);
        setFocusableInTouchMode(true);

        board = new Board();
        squares = new ArrayList<SquareView>();
        boardWidth = 1430;
        boardPadding = 15;
        cellPadding = 45;
        cellWidth = (boardWidth-2*boardPadding) / 11;

        createFirst();
        createSecond();
        createFocused();
        createFree();
        createNull();

        background = new Rect(0,0,boardWidth, boardWidth);
        backgroundPaint = new Paint(C.NULL);
        for(int y = 0; y < board.size(); y++) {
            for (int x = 0; x < board.size(); x++) {
                SquareView s = new SquareView(free);
                s.transform(x * cellWidth + boardPadding, y * cellWidth + boardPadding);
                squares.add(s);
            }
        }
        for(int y = 0; y < board.size(); y++) {
            for (int x = 0; x < board.size(); x++) {
                Square sq = board.getSquare(x, y);
                if(sq.getType() == SquareType.FREE) continue;
                SquareView s = select(sq);
                s.transform(x * cellWidth + boardPadding, y * cellWidth + boardPadding);
                squares.add(s);
            }
        }
        focused.transform(5 * cellWidth + boardPadding,3 * cellWidth + boardPadding);
        focused.p.setColor(C.FIRST);
        hasFocus = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(background, backgroundPaint);
        for(SquareView s: squares) {
            s.Draw(canvas);
        }
        if(hasFocus) focused.Draw(canvas);
    }

    private void createFree() {
        free = new SquareView(cellWidth, cellPadding-20);
        free.p.setColor(C.FREE);
    }
    private void createFirst() {
        first = new SquareView(cellWidth, cellPadding);
        first.p.setColor(C.FIRST);
    }
    private void createSecond() {
        second = new SquareView(cellWidth, cellPadding);
        second.p.setColor(C.SECOND);
    }
    private void createNull() {
        nullSquare = new SquareView(cellWidth, cellPadding);
        nullSquare.p.setColor(C.NULL);
    }
    private void createFocused() {
        focused = new SquareView(cellWidth, cellPadding-20);
        focused.p.setColor(C.SECOND);
    }
    private SquareView select(Square s)
    {
        SquareView square = null;
        switch(s.getType())
        {
            case FREE: square = new SquareView(free); break;
            case FIRST_PLAYER: square = new SquareView(first); break;
            case SECOND_PLAYER: square = new SquareView(second); break;
            default: square = new SquareView(nullSquare);
        }
        return square;
    }
    private void toggleFocused()
    {
        if(focused.p.getColor() == C.FIRST) {
            focused.p.setColor(C.SECOND);
        }
        else {
            focused.p.setColor(C.FIRST);
        }
    }
}
