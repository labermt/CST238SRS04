package com.example.david.connectgame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class BoardView extends View {

    public BoardGame game;
    public Rect background;
    public Paint backgroundPaint;
    public ArrayList<SquareView> squares;
    public SquareView focused;
    public SquareView first, second, free, nullSquare;
    public int boardWidth, cellWidth, cellPadding, focusPadding, boardPadding;
    public boolean hasFocus, won;
    public Point focusCell;

    public BoardView(Context c, AttributeSet a) {
        super(c, a);
        setFocusable(true);
        setFocusableInTouchMode(true);

        initializeComponents();
        setupBoard();

        setOnTouchListener( new View.OnTouchListener(){
                                @Override
                                public boolean onTouch(View v, MotionEvent e) {
                                    if(!won) manageTouch(e);
                                    return true;
                                }
                            }
        );



    }

    private void initializeComponents() {
        game = new BoardGame(new Board());
        squares = new ArrayList<>();
        boardWidth = 1430;
        boardPadding = 15;
        cellPadding = 45;
        focusPadding = cellPadding + 10;
        cellWidth = (boardWidth - 2 * boardPadding) / 11;
        hasFocus = false;
        won = false;
        focusCell = new Point();

        createFirst();
        createSecond();
        createFocused();
        createFree();
        createNull();

        background = new Rect(0, 0, boardWidth, boardWidth);
        backgroundPaint = new Paint(C.NULL);
        focused.p.setColor(C.FIRST);
    }
    private void setupBoard(){
        int size = game.size();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if( (y==0 && x==0) ||
                    (y==0 && x == size-1) ||
                    (y==size-1 && x == 0) ||
                    (y==size-1 && x == size-1)
                  ) continue;
                Point pos = getScreenPosition(x,y);
                SquareView s = new SquareView(free,
                        pos.x,
                        pos.y
                );

                squares.add(s);
            }
        }


        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                SquareType t = game.getType(x,y);
                Point pos = getScreenPosition(x,y);
                if(t == SquareType.FIRST_PLAYER){
                    SquareView s = new SquareView(
                            first,
                            pos.x,
                            pos.y
                    );
                    squares.add(s);
                }
                else if(t == SquareType.SECOND_PLAYER){
                    SquareView s = new SquareView(
                            second,
                            pos.x,
                            pos.y
                    );
                    squares.add(s);
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(background, backgroundPaint);
        for (SquareView s : squares) {
            s.Draw(canvas);
        }
        if (hasFocus) focused.Draw(canvas);
    }

    private void createFree() {
        free = new SquareView(cellWidth, cellPadding - 20);
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
        focused = new SquareView(cellWidth, focusPadding);
        focused.p.setColor(C.FIRST);
    }

    private SquareView select(Square s) {
        SquareView square = null;
        switch (s.getType()) {
            case FREE:
                square = new SquareView(free);
                break;
            case FIRST_PLAYER:
                square = new SquareView(first);
                break;
            case SECOND_PLAYER:
                square = new SquareView(second);
                break;
            default:
                square = new SquareView(nullSquare);
        }
        return square;
    }

    private void refreshColor() {
        TextView player1 = ((Activity)getContext()).findViewById(R.id.player1);
        TextView player2 = ((Activity)getContext()).findViewById(R.id.player2);
        if (game.isFirstPlayerTurn) {
            focused.p.setColor(C.FIRST);
            player1.setVisibility(View.VISIBLE);
            player2.setVisibility(View.INVISIBLE);
        } else {
            focused.p.setColor(C.SECOND);
            player2.setVisibility(View.VISIBLE);
            player1.setVisibility(View.INVISIBLE);
        }
    }
    public void turn(){
        Point screenPosition = getScreenPosition(focusCell.x, focusCell.y);
        if(game.isFirstPlayerTurn){
            squares.add( new SquareView(
                    first,
                    screenPosition.x,
                    screenPosition.y
                    ));
        }
        else{
            squares.add( new SquareView(
                    second,
                    screenPosition.x,
                    screenPosition.y
            ));
        }
        hasFocus = false;
        game.takeTurn(focusCell.x,focusCell.y);
        if(game.won()){
            setWin();
        }
        else {
            game.togglePlayer();
            refreshColor();
        }
        invalidate();
    }

    public void reset(){
        game.reset();
        squares.clear();
        initializeComponents();
        Button b = ((Activity) getContext()).findViewById(R.id.button);
        b.setEnabled(true);
        b.setText(R.string.endTurn);
        this.setEnabled(true);
        setupBoard();
        invalidate();
    }

    private Point getCellPosition(int xPixel, int yPixel) {
        return new Point((xPixel - boardPadding) / cellWidth, (yPixel - boardPadding) / cellWidth);
    }
    private Point getScreenPosition(int xCell, int yCell){
        return new Point(
                xCell * cellWidth + boardPadding,
                yCell * cellWidth + boardPadding
        );
    }
    private void setWin(){
        String color =
        game.isFirstPlayerTurn ?
            "Blue" :
            "Red";
        Button b = ((Activity) getContext()).findViewById(R.id.button);
        b.setText(color + " wins!!!");
        b.setEnabled(false);
        this.setEnabled(false);
        won = true;
    }


    private void manageTouch(MotionEvent e){
        focusCell = getCellPosition((int)e.getX(), (int)e.getY());
        SquareType s = game.getType(focusCell.x, focusCell.y);
        Point pos = getScreenPosition(focusCell.x, focusCell.y);
        if(s == SquareType.FREE  && game.isValidTurn(focusCell.x,focusCell.y) ){
            focused.moveTo(
                    pos.x,
                    pos.y
            );
            hasFocus = true;
        }
        else{
            hasFocus = false;
        }
        invalidate();
    }
}
