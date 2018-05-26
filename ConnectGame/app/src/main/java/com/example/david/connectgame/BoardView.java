package com.example.david.connectgame;

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
import android.widget.Toast;

import java.util.ArrayList;


public class BoardView extends View {


    public Board board;
    public BoardGame game;
    public Rect background;
    public Paint backgroundPaint;
    public ArrayList<SquareView> squares;
    public SquareView focused;
    public SquareView first, second, free, nullSquare;
    public int boardWidth, cellWidth, cellPadding, boardPadding;
    public boolean hasFocus;
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
                                    manageTouch(e);
                                    return true;
                                }
                            }
        );



    }

    private void initializeComponents() {
        board = new Board();
        game = new BoardGame(board);
        squares = new ArrayList<>();
        boardWidth = 1430;
        boardPadding = 15;
        cellPadding = 45;
        cellWidth = (boardWidth - 2 * boardPadding) / 11;
        hasFocus = false;
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
        int size = board.size();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                SquareView s = new SquareView(free);
                s.transform(x * cellWidth + boardPadding, y * cellWidth + boardPadding);
                squares.add(s);
            }
        }
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                Square sq = board.getSquare(x, y);
                SquareType t = sq.getType();
                //if (t == SquareType.FREE) continue;
                if(t == SquareType.FIRST_PLAYER){
                    SquareView s = new SquareView(first, x * cellWidth+boardPadding, y * cellWidth+boardPadding);
                    squares.add(s);
                }
                if(t == SquareType.SECOND_PLAYER){
                    SquareView s = new SquareView(second, x * cellWidth+boardPadding, y * cellWidth+boardPadding);
                    squares.add(s);
                }
                //SquareView s = select(sq);
                //s.transform(x * cellWidth + boardPadding, y * cellWidth + boardPadding);
                //squares.add(s);
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
        focused = new SquareView(cellWidth, cellPadding + 10);
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

    public void toggleTurn() {
        TextView textView = findViewById(R.id.player);
        if (focused.p.getColor() == C.FIRST) {
            focused.p.setColor(C.SECOND);
            textView.setBackgroundColor(C.SECOND);
        } else {
            focused.p.setColor(C.FIRST);
            textView.setBackgroundColor(C.FIRST);
        }
    }

    private Point getCellPosition(int xPixel, int yPixel) {
        return new Point((xPixel - boardPadding) / cellWidth, (yPixel - boardPadding) / cellWidth);
    }

    private void manageTouch(MotionEvent e){
        Point p = getCellPosition((int)e.getX(), (int)e.getY());
        SquareType s = board.getType(p.x, p.y);
        if(s == SquareType.FREE  && game.isValidTurn(game.isFirstPlayerTurn, p.x,p.y) ){
            focused.moveTo(p.x*cellWidth+boardPadding,p.y*cellWidth+boardPadding);
            hasFocus = true;
        }
        else{
            hasFocus = false;
        }
        invalidate();
    }

}

/*
        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(game.isValidTurn(game.isFirstPlayerTurn,focusCell.x,focusCell.y)){
                    SquareView player = game.isFirstPlayerTurn ? first : second;
                    game.takeTurn(game.isFirstPlayerTurn,focusCell.x,focusCell.y);
                    squares.add(
                            new SquareView(player, focusCell.x * cellWidth + boardPadding, focusCell.y * cellWidth + boardPadding)
                    );

                    if(game.won(game.isFirstPlayerTurn)){
                        ((Button)findViewById(R.id.button)).setText("YOU WON!");
                        findViewById(R.id.button).setFocusable(false);
                        return;
                    }
                    toggleTurn();
                }
                else{
                    Toast.makeText(v.getContext(), "Select an open tile surrounded by your color", Toast.LENGTH_SHORT);
                }
            }
        });
        */
