package com.example.david.connectbydave;

import android.content.Context;
import android.view.View;

public class BoardView extends View
{
    private Board board;
    BoardView(Context context, int width, int height)
    {
        super(context);
        board = new Board(width, height);
        //setOnHoverListener(); //highlight cell
        //setOnTouchListener(); //select cell if free
    }
}
