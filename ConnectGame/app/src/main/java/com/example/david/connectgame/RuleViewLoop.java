package com.example.david.connectgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class RuleViewLoop extends View{
    ArrayList<SquareView> squares;
    Rect background;
    Paint backPaint;

    int width, height;

    public RuleViewLoop(Context c, AttributeSet a) {
        super(c, a);
        setFocusable(true);
        setFocusableInTouchMode(true);

        setupBoard();

    }
    private void setupBoard(){
        width = 700;
        squares = new ArrayList<>();
        background = new Rect(0,0,width,height);
        backPaint = new Paint(C.NULL);
        makeRule1Squares();
    }

    private void makeRule1Squares(){
        int boardPad = 5;
        int cellPad = 15;
        int cellWidth = (width - (2 * boardPad) ) / 7;
        Paint freeP = new Paint(C.FREE);
        Paint firstP = new Paint(C.FIRST);
        Paint secondP = new Paint(C.SECOND);
        SquareView free = new SquareView(0,cellWidth,cellPad-5,freeP);
        SquareView first = new SquareView(0,cellWidth,cellPad,firstP);
        SquareView second = new SquareView(0,cellWidth,cellPad,secondP);

        add(free,2,0,cellWidth,cellPad);
        add(free,4,0,cellWidth,cellPad);
        add(free,1,1,cellWidth,cellPad);
        add(free,5,1,cellWidth,cellPad);
        add(free,0,2,cellWidth,cellPad);
        add(free,6,2,cellWidth,cellPad);
        add(free,1,3,cellWidth,cellPad);
        add(free,5,3,cellWidth,cellPad);
        add(free,0,4,cellWidth,cellPad);
        add(free,2,4,cellWidth,cellPad);
        add(free,4,4,cellWidth,cellPad);
        add(free,6,4,cellWidth,cellPad);
        add(free,1,5,cellWidth,cellPad);
        add(free,3,5,cellWidth,cellPad);
        add(free,5,5,cellWidth,cellPad);
        add(free,2,6,cellWidth,cellPad);
        add(free,4,6,cellWidth,cellPad);

        add(first,0,1,cellWidth,cellPad);
        add(first,2,1,cellWidth,cellPad);
        add(first,3,1,cellWidth,cellPad);
        add(first,4,1,cellWidth,cellPad);
        add(first,6,1,cellWidth,cellPad);
        add(first,2,2,cellWidth,cellPad);
        add(first,4,2,cellWidth,cellPad);
        add(first,0,3,cellWidth,cellPad);
        add(first,2,3,cellWidth,cellPad);
        add(first,3,3,cellWidth,cellPad);
        add(first,4,3,cellWidth,cellPad);
        add(first,6,3,cellWidth,cellPad);
        add(first,0,5,cellWidth,cellPad);
        add(first,2,5,cellWidth,cellPad);
        add(first,4,5,cellWidth,cellPad);
        add(first,6,5,cellWidth,cellPad);

        add(second,1,0,cellWidth,cellPad);
        add(second,3,0,cellWidth,cellPad);
        add(second,5,0,cellWidth,cellPad);
        add(second,1,2,cellWidth,cellPad);
        add(second,3,2,cellWidth,cellPad);
        add(second,5,2,cellWidth,cellPad);
        add(second,1,4,cellWidth,cellPad);
        add(second,3,4,cellWidth,cellPad);
        add(second,5,4,cellWidth,cellPad);
        add(second,1,6,cellWidth,cellPad);
        add(second,3,6,cellWidth,cellPad);
        add(second,5,6,cellWidth,cellPad);
    }



    private int getPos(int cellIndex, int cellWidth, int boardPad){
        return (cellWidth * cellIndex) + boardPad;
    }
    private void add(SquareView square, int x,int y, int cellWidth, int boardPad){
        squares.add(new SquareView(square, getPos(x,cellWidth,boardPad), getPos(y,cellWidth,boardPad)));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(background, backPaint);
        for(SquareView s: squares){
            s.Draw(canvas);
        }
    }
}
