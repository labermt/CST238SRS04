package com.bradley.bergstrom.connectgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView topX,bottomX,leftO,rightO;
    private boolean ended;
    private int prevTouched;
    private char currentPlayer;
    private Button[][] board;
    private boolean isPokemon;
    private char[][] touched;
    private int numbTouched;
    private TextView winnerBox;
    private final Object X = 'X';
    private final Object O = 'O';
    public boolean isEnded(){
        return ended;
    }
    public void changeCurrentPlayer(){

        if(currentPlayer=='O'){
            winnerBox.setText("Player 1s Turn");
            currentPlayer='X';
        } else {
            winnerBox.setText("Player 2s Turn");
            currentPlayer='O';
        }
    }

    public boolean checkEnd(int i, int j){
        //TODO: check if the game has been won yet
        //if last move in top right corner
            if(i==0 && j == 1){
                if (touched[0][2] == touched[0][3] && touched[0][1] == touched[0][2] && touched [0][2]==touched[1][1] && touched[1][1]==touched[1][3] && touched[1][3] == touched[2][1] && touched[2][1]==touched[2][2] && touched[2][2] == touched[2][3]){
                    winnerBox.setText(currentPlayer+" is the Winner!!!");
                }
            }
        //if last move in bottom right corner

        //if last move in top left corner

        //if last move in bottom left corner

        //else
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
       // Bundle extras = getIntent().getExtras();
        currentPlayer='X';
        winnerBox=(TextView)findViewById(R.id.winnerBox);
        ended=false;

        if(getIntent().hasExtra("isPokemon")){
            isPokemon=getIntent().getBooleanExtra("isPokemon",true);
        } else {
            if (getIntent().getExtras() != null) {
                if(getIntent().getExtras().getInt("Tag")==1){
                    startActivity(new Intent(GameActivity.this,PlayerPopUp.class));
                }
                if(getIntent().hasExtra("isPokemon")){

                    isPokemon = true;
                }
            }
        }

        if(isPokemon==true){
            topX=(ImageView)findViewById(R.id.topX);
            topX.setBackgroundResource(R.drawable.ash);
            bottomX=(ImageView)findViewById(R.id.bottomX);
            bottomX.setBackgroundResource(R.drawable.ash);
            leftO=(ImageView) findViewById(R.id.leftO);
            leftO.setBackgroundResource(R.drawable.gary);
            rightO=(ImageView) findViewById(R.id.rightO);
            rightO.setBackgroundResource(R.drawable.gary);
        }

        String ButtonId;
        int resId;
        board = new Button[11][11];
        touched= new char[11][11];

        for(int i =0; i < 11; i++){
            for(int j = 1; j < 12; j++){
                ButtonId = "button_"+i+j;
                resId = getResources().getIdentifier(ButtonId, "id", getPackageName());
                board[i][j-1] = ((Button) findViewById(resId));
                board[i][j-1].setOnClickListener(this);

            }
        }
        for (int i = 0; i < 11; i+=2)
        {
            for(int j = 1; j < 11; j+=2)
            {
                if(isPokemon == true){
                    board[j][i].setBackgroundResource(R.drawable.ash);
                    board[j][i].setTag(X);

                } else {
                    board[j][i].setBackgroundResource(R.drawable.x);
                    board[j][i].setTag(X);

                    // board[j][i].setTextSize(50);

                    //board[j][i].setBackgroundColor(getResources().getColor(R.color.lgreen));
                }
                touched[j][i]='X';
                numbTouched++;
            }
        }
        for (int i = 1; i < 11; i+=2)
        {
            for(int j = 0; j < 11; j+=2)
            {
                if(isPokemon == true){
                    board[j][i].setBackgroundResource(R.drawable.gary);
                    board[j][i].setTag(O);
                } else {
                    board[j][i].setBackgroundResource(R.drawable.o);
                    board[j][i].setTag(O);
                    //board[j][i].setBackgroundColor(getResources().getColor(R.color.lblue));
                }
                touched[j][i]='O';
                numbTouched++;
            }
        }
        prevTouched=numbTouched;

       //TODO newGame();
      // Game();
    }
    public boolean isTouched(int i, int j){
        if(touched[i][j] != 'X' && touched[i][j] != 'O'){
            return false;
        } else {
            return true;
        }
    }

    public boolean isClicked(int i,int j){
        if(board[i][j].getTag() == X || board[i][j].getTag() == O){
            return true;
        } else {
            return false;
        }
    }


    public void Game(){
            int last_i = 0;
            int last_j = 1;
        //while(isEnded()!=true){
            if(prevTouched < numbTouched){

                for(int i =0; i < 11; i++) {
                    for (int j = 0; j < 11; j++) {
                        if(isTouched(i,j)==false &&  isClicked(i,j)==true){
                            Object tag =  board[i][j].getTag();
                            if(tag==X){
                                touched[i][j] = 'X';
                                last_i=i;
                                last_j=j;
                            } else if(tag == O){
                                touched[i][j] = 'O';
                                last_i=i;
                                last_j=j;
                            }

                        }
                    }
                }
                prevTouched++;
               // String derp = String.valueOf(touched[last_i][last_j]+ String.valueOf(last_i) + " " + String.valueOf(last_j));
               // winnerBox.setText(derp);
            }
            checkEnd(last_i,last_j);
        //}
    }


    @Override
    public void onClick (View v){
        if(v.getTag() == O || v.getTag() == X){
            //they dont get to click on tiles that are already clicked
        } else {
            if (currentPlayer == 'X') {
                if (isPokemon == true) {
                    v.setBackgroundResource(R.drawable.ash);
                    v.setTag(X);
                } else {
                    v.setBackgroundResource(R.drawable.x);
                    v.setTag(X);

                }

            } else {
                if (isPokemon == true) {
                    v.setBackgroundResource(R.drawable.gary);
                    v.setTag(O);
                } else {
                    v.setBackgroundResource(R.drawable.o);
                    v.setTag(O);
                }

            }

            changeCurrentPlayer();
            numbTouched++;
           // winnerBox.setText(String.valueOf(numbTouched)+" "+String.valueOf(prevTouched));
            Game();
        }
    }
}

