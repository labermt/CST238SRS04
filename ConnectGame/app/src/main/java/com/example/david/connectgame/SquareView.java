package com.example.david.connectgame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class SquareView {
    public Rect r1, r2;
    public int pad;
    public Paint p;

    public SquareView(int x, int width, int pad, Paint pnt) {
        p = pnt;
        this.pad = pad;
        r1 = new Rect(x, x+pad, x+width, x+width-pad);
        r2 = new Rect(x+pad, x, x+width-pad, x+width);
    }
    public SquareView(SquareView other, int x, int y) {
        p = other.p;
        pad = other.pad;
        r1 = new Rect(other.r1); r1.offsetTo(x,y+pad);
        r2 = new Rect(other.r2); r2.offsetTo(x+pad,y);
    }
    public SquareView(SquareView other) {
        p = other.p;
        pad = other.pad;
        r1 = new Rect(other.r1);
        r2 = new Rect(other.r2);
    }
    public SquareView(int width, int pad) {
        p = new Paint();
        this.pad = pad;
        r1 = new Rect(0,pad,width, width-pad);
        r2 = new Rect(pad,0,width-pad, width);

    }


    //        squareView.transform(yCellIndex * cellWidth + boardPadding, yCellIndex * cellWidth + boardPadding);
    public void transform(int dx,int dy){
        r1.offset(dx,dy);
        r2.offset(dx,dy);
    }

    //      squareView.moveTo(xScreenPosition, yScreenPosition)
    public void moveTo(int x,int y){
        r1.offsetTo(x,y+pad);
        r2.offsetTo(x+pad,y);
        /*
        r1 = new Rect(x, x+pad, x+width, x+width-pad);
        r2 = new Rect(x+pad, x, x+width-pad, x+width);
        * */
    }
    public void Draw(Canvas c) {
        c.drawRect(r1,p);
        c.drawRect(r2,p);
    }
}
