package com.example.tcape.connectgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BoardView extends View {

    private static final Random RANDOM = new Random();
    private static final int GRID_THICK = 7;
    private static final int CELL_MARGIN = 20;
    private static final int CIRCLE_RADIUS = 20;
    private int width, height; /*, cellW, cellH;*/
    private float cellW, cellH;
    private Paint gridPaint, dotPaint, linePaint, borderPaint;
    private Game game;
    private List<Line> lines;
    private MainActivity mainActivity;
    private Random random;

    public BoardView(Context context) {
        super(context);
        init();
    }

    public BoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
       init();
    }

    public BoardView(Context context, @Nullable AttributeSet attrs, int defstyle ){
        super(context, attrs, defstyle);
        init();
    }

    private void init(){
        gridPaint = new Paint();
        gridPaint.setAntiAlias(true);
        dotPaint = new Paint();
        dotPaint.setAntiAlias(true);
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(19f);
        borderPaint = new Paint();
        borderPaint.setStrokeWidth(7f);
        borderPaint.setStyle(Paint.Style.STROKE);
        lines = new ArrayList<>();
        cellW = cellH = 118;
        width = height = 1288;
    }

    public void setMainActivity(MainActivity activity){
        mainActivity = activity;
    }

    public void setGame(Game g) {
        game = g;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = View.MeasureSpec.getSize(widthMeasureSpec);
        height = View.MeasureSpec.getSize(heightMeasureSpec);
        cellW = width / 11 + 1;
        cellH = height / 11 + 1;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas){

        canvas.drawColor(Color.WHITE);
        drawGrid(canvas);
        drawDots(canvas);
        drawLines(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (game.gameOver)
            return false;

        int x = (int) (event.getX() / cellW);
        int y = (int) (event.getY() / cellH);

        if(event.getAction() == MotionEvent.ACTION_DOWN){

            if (!checkValidMove(x, y)){
                return false;
            }
            game.cells[x][y].xCoord = (float) cellW * (x + 1) - cellW / 2;
            game.cells[x][y].yCoord = (float) cellH * (y + 1) - cellH / 2;
            game.cells[x][y].taken = true;
            if (game.currentPlayer == 'R'){
                game.cells[x][y].dotColor = mainActivity.redColor;
                game.cells[x][y].player = 'R';
                if (!game.cells[x][y].edge){
                    if (game.cells[x - 1][y].player == game.currentPlayer &&
                            game.cells[x + 1][y].player == game.currentPlayer){
                        // create a line to connect dots
                        lines.add(new Line(game.cells[x - 1][y].xCoord, game.cells[x - 1][y].yCoord,
                                game.cells[x + 1][y].xCoord, game.cells[x + 1][y].yCoord, game.cells[x + 1][y].dotColor ));
                    }
                    if (game.cells[x][y - 1].player == game.currentPlayer &&
                            game.cells[x][y + 1].player == game.currentPlayer){
                        // create a line to connect dots
                        lines.add(new Line(game.cells[x][y - 1].xCoord, game.cells[x][y - 1].yCoord,
                                game.cells[x][y + 1].xCoord, game.cells[x][y + 1].yCoord, game.cells[x][y + 1].dotColor));
                    }
                }
                if (y == 0 || y == 10){
                    lines.add(new Line(game.cells[x - 1][y].xCoord, game.cells[x - 1][y].yCoord,
                            game.cells[x + 1][y].xCoord, game.cells[x + 1][y].yCoord, game.cells[x + 1][y].dotColor ));
                }

            }
            if (game.currentPlayer == 'B'){
                game.cells[x][y].dotColor = mainActivity.blueColor;
                game.cells[x][y].player = 'B';
                if (!game.cells[x][y].edge){
                    if (game.cells[x - 1][y].player == game.currentPlayer &&
                            game.cells[x + 1][y].player == game.currentPlayer){
                        // create a line to connect dots
                        lines.add(new Line(game.cells[x - 1][y].xCoord, game.cells[x - 1][y].yCoord,
                                game.cells[x + 1][y].xCoord, game.cells[x + 1][y].yCoord, game.cells[x + 1][y].dotColor ));
                    }
                    if (game.cells[x][y - 1].player == game.currentPlayer &&
                            game.cells[x][y + 1].player == game.currentPlayer){
                        // create a line to connect dots
                        lines.add(new Line(game.cells[x][y - 1].xCoord, game.cells[x][y - 1].yCoord,
                                game.cells[x][y + 1].xCoord, game.cells[x][y + 1].yCoord, game.cells[x][y + 1].dotColor));
                    }
                }
                if (x == 0 || x == 10){
                    lines.add(new Line(game.cells[x][y - 1].xCoord, game.cells[x][y - 1].yCoord,
                            game.cells[x][y + 1].xCoord, game.cells[x][y + 1].yCoord, game.cells[x][y + 1].dotColor));
                }
            }
            game.resetVisited();
            game.resetCheckCells();
            game.setCheckCells(game.cells[x][y]);
            game.checkWin(game.cells[x][y], 0 ,0);
            invalidate();
            game.changePlayer();
        }
        if (game.gameOver){
            if (game.cells[x][y].dotColor == mainActivity.redColor){
                Toast.makeText(getContext(), "Red Wins!",
                        Toast.LENGTH_LONG).show();
                return super.onTouchEvent(event);
            }
            if (game.cells[x][y].dotColor == mainActivity.blueColor){
                Toast.makeText(getContext(), "Blue Wins!",
                        Toast.LENGTH_LONG).show();
                return super.onTouchEvent(event);
            }

        }

        if (mainActivity.computer.isChecked()){
            computerMove();
            game.changePlayer();
            return super.onTouchEvent(event);
        }

        return false;
    }

    private void computerMove(){

        int x = -1;
        int y = -1;
        if(!game.gameOver){
            do{
                x = RANDOM.nextInt(10);
                y = RANDOM.nextInt(10);
            } while(!checkValidMove(x, y));
        }

        game.cells[x][y].xCoord = (float) cellW * (x + 1) - cellW / 2;
        game.cells[x][y].yCoord = (float) cellH * (y + 1) - cellH / 2;
        game.cells[x][y].taken = true;

        if (game.currentPlayer == 'R'){
            game.cells[x][y].dotColor = mainActivity.redColor;
            game.cells[x][y].player = 'R';
            if (!game.cells[x][y].edge){
                if (game.cells[x - 1][y].player == game.currentPlayer &&
                        game.cells[x + 1][y].player == game.currentPlayer){
                    // create a line to connect dots
                    lines.add(new Line(game.cells[x - 1][y].xCoord, game.cells[x - 1][y].yCoord,
                            game.cells[x + 1][y].xCoord, game.cells[x + 1][y].yCoord, game.cells[x + 1][y].dotColor ));
                }
                if (game.cells[x][y - 1].player == game.currentPlayer &&
                        game.cells[x][y + 1].player == game.currentPlayer){
                    // create a line to connect dots
                    lines.add(new Line(game.cells[x][y - 1].xCoord, game.cells[x][y - 1].yCoord,
                            game.cells[x][y + 1].xCoord, game.cells[x][y + 1].yCoord, game.cells[x][y + 1].dotColor));
                }
            }
            if (y == 0 || y == 10){
                lines.add(new Line(game.cells[x - 1][y].xCoord, game.cells[x - 1][y].yCoord,
                        game.cells[x + 1][y].xCoord, game.cells[x + 1][y].yCoord, game.cells[x + 1][y].dotColor ));
            }

        }
        if (game.currentPlayer == 'B'){
            game.cells[x][y].dotColor = mainActivity.blueColor;
            game.cells[x][y].player = 'B';
            if (!game.cells[x][y].edge){
                if (game.cells[x - 1][y].player == game.currentPlayer &&
                        game.cells[x + 1][y].player == game.currentPlayer){
                    // create a line to connect dots
                    lines.add(new Line(game.cells[x - 1][y].xCoord, game.cells[x - 1][y].yCoord,
                            game.cells[x + 1][y].xCoord, game.cells[x + 1][y].yCoord, game.cells[x + 1][y].dotColor ));
                }
                if (game.cells[x][y - 1].player == game.currentPlayer &&
                        game.cells[x][y + 1].player == game.currentPlayer){
                    // create a line to connect dots
                    lines.add(new Line(game.cells[x][y - 1].xCoord, game.cells[x][y - 1].yCoord,
                            game.cells[x][y + 1].xCoord, game.cells[x][y + 1].yCoord, game.cells[x][y + 1].dotColor));
                }
            }
            if (x == 0 || x == 10){
                lines.add(new Line(game.cells[x][y - 1].xCoord, game.cells[x][y - 1].yCoord,
                        game.cells[x][y + 1].xCoord, game.cells[x][y + 1].yCoord, game.cells[x][y + 1].dotColor));
            }
        }
        game.resetVisited();
        game.resetCheckCells();
        game.setCheckCells(game.cells[x][y]);
        game.checkWin(game.cells[x][y], 0 ,0);

        invalidate();

        if (game.gameOver){
        //game.changePlayer();
            if (game.cells[x][y].dotColor == mainActivity.redColor){
                Toast.makeText(getContext(), "Red Wins!",
                        Toast.LENGTH_LONG).show();
                return;
                //Toast.makeText(mainActivity, "Red Wins!", Toast.LENGTH_LONG).show();
            }
            if (game.cells[x][y].dotColor == mainActivity.blueColor){
                Toast.makeText(getContext(), "Blue Wins!",
                        Toast.LENGTH_LONG).show();
                return;
                //Toast.makeText(mainActivity, "Blue Wins!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean checkValidMove(int x, int y){
        //boolean pass = false;
        if (game.cells[x][y].taken){
            return false;
        }

        if (!game.cells[x][y].edge){
            if (game.cells[x - 1][y].player == game.currentPlayer &&
                    game.cells[x + 1][y].player == game.currentPlayer){
                return true;
            }
            if (game.cells[x][y - 1].player == game.currentPlayer &&
                    game.cells[x][y + 1].player == game.currentPlayer){
                return true;
            }
        }

        if (x == 0 || x == 10){
            if (game.currentPlayer == 'B') {
                return true;
            }
            else return false;
        }

        if (y == 0 || y == 10){
            if (game.currentPlayer == 'R'){
                return true;
            }
            else return false;
        }

        return false;
    }

    private void drawGrid(Canvas canvas){
        for (int i = 0; i < 10; i++){

            float left = cellW * (i+1) - 5;
            float right = left + GRID_THICK;
            float top = 0;
            float bottom = height;

            canvas.drawRect(left, top, right, bottom, gridPaint);

            float left2 = 0;
            float right2 = width;
            float top2 = cellH * (i+1) - 5;
            float bottom2 = top2 + GRID_THICK;

            canvas.drawRect(left2, top2, right2, bottom2, gridPaint);
        }
    }

    private void drawDots(Canvas canvas){
        for (int i = 0; i < 11; i++){
            for (int j = 0; j < 11; j++){
                dotPaint.setColor(game.cells[i][j].dotColor);
                canvas.drawCircle(game.cells[i][j].xCoord, game.cells[i][j].yCoord, 37.0f, dotPaint);
            }
        }
    }

    private void drawLines(Canvas canvas) {
        for (Line line : lines) {
            linePaint.setColor(line.lineColor);
            canvas.drawLine(line.startX, line.startY, line.endX, line.endY, linePaint);
        }
    }

    public void clearLines(){
        lines.clear();
    }

    public void setupGame(Game g){

        game.cells[0][0].taken = true;
        game.cells[0][10].taken = true;
        game.cells[10][0].taken = true;
        game.cells[10][10].taken = true;

        for (int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                if (i % 2 != 0 && j % 2 == 0){
                    game.cells[i][j].player = 'R';
                    game.cells[i][j].dotColor = mainActivity.redColor;
                    game.cells[i][j].xCoord = (float) cellW * (i + 1) - cellW / 2;
                    game.cells[i][j].yCoord = (float) cellH * (j + 1) - cellH / 2;
                    game.cells[i][j].taken = true;
                }
                if (i % 2 == 0 && j % 2 != 0){
                    game.cells[i][j].player = 'B';
                    game.cells[i][j].dotColor = mainActivity.blueColor;
                    game.cells[i][j].xCoord = (float) cellW * (i + 1) - cellW / 2;
                    game.cells[i][j].yCoord = (float) cellH * (j + 1) - cellH / 2;
                    game.cells[i][j].taken = true;
                }
                if (i == 0 || j == 0 || i == 10 || j == 10){
                    game.cells[i][j].edge = true;
                }
            }
        }
    }
}
