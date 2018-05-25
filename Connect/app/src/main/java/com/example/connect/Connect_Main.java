package com.example.connect;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

//Reference for how to make the grid and layout:
//https://www.youtube.com/watch?v=tFilQ48HR08&t=107s
//https://www.youtube.com/watch?v=6KEXuS-ex-U
//https://www.youtube.com/watch?v=weISJLiDSxk
//https://www.youtube.com/watch?v=dPqqojFGV4U

//FloodFill method reference:
//https://www.geeksforgeeks.org/flood-fill-algorithm-implement-fill-paint/

public class Connect_Main extends Activity {

    final static int maxN=11; //board size
    private Context context;
    private ImageView[][] IVCell = new ImageView[maxN][maxN];
    private Drawable[] drawCell= new Drawable[4]; //0 is empty, 1 is first player, 2 is second player/bot, 3 is background
    private  TextView turn;
    private boolean twoPlayer = false;
    private int[][] valueCell = new int[maxN][maxN]; //0 is empty, 1 is player1, 2 is player2/bot
    private int winner_play; //who is winner? 0 is none, 1 is player1, 2 is player2/bot
    private boolean firstMove;
    private int xMove, yMove; //x and y axis of cell => define position of cell
    private int turnPlay; //whose turn?
    private boolean[][] visitCheck = new boolean[maxN][maxN]; //Creates a fake board to see if you visited the cell
    private int[][] floodBoard = new int[maxN][maxN]; //Creates a fake board to check for win

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        context=this;
        setListen();
        loadResources();
        designBoardGame();
    }

    @SuppressLint("SetTextI18n")
    private void setListen(){
        Button singleplay =  findViewById(R.id.BotPlay);
        Button twoplay = findViewById(R.id.TwoPlay);
        turn = findViewById(R.id.Turn);

        singleplay.setText("Single Player Game");
        twoplay.setText("Two Player Game");
        turn.setText("Press button Play Game");

        singleplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twoPlayer=false;
                init_game();
                play_game();
            }
        });
        twoplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twoPlayer=true;
                init_game();
                play_game();
            }
        });
    }

    private void play_game() {
        Random random = new Random();
        turnPlay= random.nextInt(2)+1; //r.nextint(2) return value 0 or 1
        
        if(turnPlay==1)
        {
            Toast.makeText(context,"First Player goes first!",Toast.LENGTH_SHORT).show();
            firstPlayerTurn();
        }
        else{
            if(twoPlayer){
                Toast.makeText(context,"Second Player goes first!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context,"Bot Player goes first!", Toast.LENGTH_SHORT).show();
            }
            secondPlayerTurn();
        }
    }

    @SuppressLint("SetTextI18n")
    private void firstPlayerTurn() {
        turn.setText("First Player");
        firstMove=false;
        isClicked=false;
    }

    @SuppressLint("SetTextI18n")
    private void secondPlayerTurn() {
        //if(bot) and bot first it will always choose the center (5,5)
        if(twoPlayer){
            turn.setText("Second Player");
            firstMove=false;
            isClicked=false;
        }
        else {
            turn.setText("Bots Turn");
            if(firstMove){
                firstMove=false;
                xMove=5; yMove=5;
                make_a_move();
            }
            else{
                findBotMove();
            }
        }
    }

    private void findBotMove() {
        randomBotMove();
        make_a_move();
    }

    private void randomBotMove() {
        int done = 0;
        for(int i=0;i<maxN;i++){
            if(done == 1)
            {
                break;
            }
            label:
            for (int j = 0; j < maxN; j++) {
                switch (j) {
                    case 0:

                        break;
                    case 10:

                        break;
                    default:
                        if (valueCell[i][j] == 0 && validMove(i, j)) {
                            xMove = i;
                            yMove = j;
                            done = 1;
                            break label;
                        }
                        break;
                }
            }
        }
    }

    private boolean validMove(int x, int y) {
        if(x==0&&y==0){
            return false;
        }
        else if(x==0&&y==10)
        {
            return false;
        }
        else if(x == 10 && y == 0)
        {
            return false;
        }
        else if(x==10&&y==10){
            return false;
        }

        if(x == 0)
        {
            if(valueCell[x][y-1]==turnPlay){
                return valueCell[x][y + 1] == turnPlay;
            }
        }
        else if(x==10){
            if(valueCell[x][y-1]==turnPlay){
                return valueCell[x][y + 1] == turnPlay;
            }
        }
        else if(y==0){
            if(valueCell[x-1][y]==turnPlay){
                return valueCell[x + 1][y] == turnPlay;
            }
        }
        else if(y ==10){
            if(valueCell[x-1][y]==turnPlay){
                return valueCell[x + 1][y] == turnPlay;
            }
        }
        else {
            if (valueCell[x - 1][y] == turnPlay) {
                return valueCell[x + 1][y] == turnPlay;
            } else if (valueCell[x][y - 1] == turnPlay) {
                return valueCell[x][y + 1] == turnPlay;
            }
        }
        return false;
    }

    @SuppressLint("SetTextI18n")
    private void make_a_move() {
        if(!CheckWinner()) {
            if (validMove(xMove, yMove)) {
                IVCell[xMove][yMove].setImageDrawable(drawCell[turnPlay]);
                valueCell[xMove][yMove] = turnPlay;

                if (noEmptyCell()) {
                    Toast.makeText(context, "Draw!", Toast.LENGTH_SHORT).show();
                } else if (CheckWinner()) {
                    if (winner_play == 1) {
                        Toast.makeText(context, "Winner is Player 1", Toast.LENGTH_SHORT).show();
                        turn.setText("Winner is Player 1");
                    } else {
                        Toast.makeText(context, "Winner is Player 2", Toast.LENGTH_SHORT).show();
                        turn.setText("Winner is Player 2");
                    }
                } else {
                    if (turnPlay == 1) {
                        turnPlay = (1 + 2) - turnPlay;
                        secondPlayerTurn();
                    } else {
                        turnPlay = 3 - turnPlay;
                        firstPlayerTurn();
                    }
                }
            } else {
                Toast.makeText(context, "Invalid Move: Choose Again!", Toast.LENGTH_SHORT).show();
                if (turnPlay == 1) {
                    firstPlayerTurn();
                } else {
                    secondPlayerTurn();
                }
            }
        }
        else {
            Toast.makeText(context,"To Play Again Press Play Game", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean CheckWinner() {
        //check recent xMove, yMove to see if you can win

        int oppositePlayer = 3 - turnPlay;

        if(winner_play!=0) return true;

        for(int i = 0; i<maxN; i++)
        {
            for(int j = 0; j<maxN; j++){
                if(valueCell[i][j] == oppositePlayer){
                    floodBoard[i][j] = 0;
                }
                else {
                    floodBoard[i][j] = valueCell[i][j];
                }
                visitCheck[i][j] = false;
            }
        }


        floodWin(0,0);

        for(int i = 0; i<maxN; i++){
            for(int j =0; j<maxN; j++){
                if(floodBoard[i][j] == 0){
                    winner_play=turnPlay;
                    return true;
                }
            }
        }

        return false;
    }

    private void floodWin(int x, int y){
        if (x < 0 || x > 10 || y < 0 || y > 10) {
            return;
        }

        if(visitCheck[x][y]) return;
        visitCheck[x][y] = true;

        if(floodBoard[x][y] == turnPlay){
            if(x==0){
                floodWin(x,y-1);
            }
            if(x==10){
                floodWin(x,y-1);
            }
            if(y==0){
                floodWin(x-1,y);
            }
            if(y==10){
                floodWin(x-1,y);
            }
            return;
        }

        if(floodBoard[x][y] != turnPlay){
            floodBoard[x][y] = turnPlay;
        }

        floodWin(x + 1, y);
        floodWin(x - 1, y);
        floodWin(x, y + 1);
        floodWin(x, y - 1);

    }

    private boolean noEmptyCell() {
        for(int i = 0;i<maxN;i++)
        {
            for(int j=0;j<maxN;j++)
            {
                switch (j) {
                    case 0:
                        break;
                    case 10:
                        break;
                    default:
                        if (valueCell[i][j] == 0) return false;
                        break;
                }
            }
        }

        for(int i = 0;i<maxN;i++) {
            for (int j = 0; j < maxN; j++) {
                switch (i) {
                    case 0:
                        break;
                    case 10:
                        break;
                    default:
                        if (valueCell[i][j] == 0) return false;
                        break;
                }
            }
        }

        return true;
    }

    private void init_game() {
        //this will create UI before game start
        //for game control we need some vars
        firstMove=true;
        winner_play=0;
        for(int i=0;i<maxN;i++)
        {
            for(int j=0;j<maxN;j++)
            {
                if(i == 0 || i == 2 || i == 4 || i == 6 || i == 8 || i == 10){
                    if(j % 2 != 0) {
                        IVCell[i][j].setImageDrawable(drawCell[2]);
                        valueCell[i][j] = 2;
                    }
                    else {
                        IVCell[i][j].setImageDrawable(drawCell[0]); //default/empty cell
                        valueCell[i][j]=0;
                    }
                }
                else {
                    if(j % 2 == 0){
                        IVCell[i][j].setImageDrawable(drawCell[1]);
                        valueCell[i][j] = 1;
                    }
                    else {
                        IVCell[i][j].setImageDrawable(drawCell[0]); //default/empty cell
                        valueCell[i][j]=0;
                    }
                }
            }
        }
    }

    private void loadResources(){
        drawCell[3]= context.getResources().getDrawable(R.drawable.cell_bg);
        drawCell[0]=null; //empty cell
        drawCell[1]=context.getResources().getDrawable(R.drawable.flame2); //first player
        drawCell[2]=context.getResources().getDrawable(R.drawable.water3); //second player/bot
    }

    private boolean isClicked; //track to see if the player clicked a cell or not
    @SuppressLint("NewApi")
    private void designBoardGame(){
        //create layoutparams to optimize size of cell
        //create a horizontal linearlayout for a row
        //whixh contains maxN imageView in
        //need to find out size of cell first
        int sizeOfCell = Math.round(ScreenWidth()/maxN);
        LinearLayout.LayoutParams LPRow= new LinearLayout.LayoutParams(sizeOfCell*maxN, sizeOfCell);
        LinearLayout.LayoutParams LPCell= new LinearLayout.LayoutParams(sizeOfCell,sizeOfCell);

        LinearLayout linBoardGame= findViewById(R.id.LinBoardGame);

        for(int i = 0; i < maxN; i++){
            LinearLayout linRow = new LinearLayout(context);
            for(int j = 0;j<maxN; j++){
                IVCell[i][j]= new ImageView(context);
                //make a cell
                //need to set background default for cell
                //cell has 3 stus, empty(default),player1,player2/bot
                IVCell[i][j].setBackground(drawCell[3]);
                final int x = i;
                final int y = j;
                IVCell[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(valueCell[x][y] == 0) {
                            if (turnPlay == 1 || !isClicked) //player turn
                            {
                                isClicked = true;
                                xMove = x;
                                yMove = y;
                                make_a_move();
                            }
                        }
                    }
                });
                linRow.addView(IVCell[i][j],LPCell);
            }
            linBoardGame.addView(linRow,LPRow);
        }
    }

    private float ScreenWidth(){
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }
}
