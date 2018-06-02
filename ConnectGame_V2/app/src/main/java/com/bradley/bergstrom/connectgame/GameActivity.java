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
    private Button resetButton;
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
    public String player_1_icon;
    public String player_2_icon;
    public void changeCurrentPlayer(){

        if(currentPlayer=='O'){
            if(isEnded()==false){
                winnerBox.setText("Player 1s Turn");
            }
            currentPlayer='X';
        } else {
            if(isEnded()==false) {
                winnerBox.setText("Player 2s Turn");
            }
            currentPlayer='O';
        }
    }

    private void isMadeItAcross(int[] Square){
        int[] side1 =new int[2];
        int[] side2 = new int[2];
        if(touched[Square[0]][Square[1]] == 'X'){
            side1[0] = 98;
            side2[0] = 98;
            side1[1] = 0;
            side2[1] = 11;
        } else {
            side1[0] = 0;
            side2[0] = 11;
            side1[1] = 98;
            side2[1] = 98;
        }

    }
    public void traverseAcrossBoard(int[] side){
        int[] options;

        for(int i =0; i < 11;i+=2){
            if(side[0]==98){
                int[] square = {i,0};
                options = findallPaths( square,0);

            }
        }

    }
    public int[] findallPaths(int[]LastSquare, int LastDirection){
        int[] helper = new int[5];
        helper[0] = 0;
        if(hasLeft(LastSquare) && LastDirection != 1){
            helper[0]++;
            helper[1] = 1;
        } else if(hasRight(LastSquare) && LastDirection != 2){
            helper[0]++;
            helper[2] = 1;
        } else if(hasUp(LastSquare) && LastDirection != 3){
            helper[0]++;
            helper[3]=1;
        } else if(hasDown(LastSquare) && LastDirection != 4){
            helper[0]++;
            helper[4] = 1;
        }

        return helper;

    }

    private void isFullCircle(int[] FirstSquare, int[] LastSquare, int LastDirection){
        int last = LastDirection;
        int[] CurrentSquare = {LastSquare[0],LastSquare[1]};
        if(hasLeft(LastSquare) && LastDirection != 1){
            last = 2;
            CurrentSquare[0]=LastSquare[0]-1;
            if(FirstSquare[0]==CurrentSquare[0] && FirstSquare[1]==CurrentSquare[1]){
                ended=true;
            } else {
                isFullCircle(FirstSquare,CurrentSquare,last);
            }
        } else if(hasRight(LastSquare) && LastDirection != 2){
            last = 1;
            CurrentSquare[0]=LastSquare[0]+1;
            if(FirstSquare[0]==CurrentSquare[0] && FirstSquare[1]==CurrentSquare[1]){
                ended=true;
            } else {
                isFullCircle(FirstSquare,CurrentSquare,last);
            }
        } else if(hasUp(LastSquare) && LastDirection != 3){
            last = 4;
            CurrentSquare[1]=LastSquare[1]-1;
            if(FirstSquare[0]==CurrentSquare[0] && FirstSquare[1]==CurrentSquare[1]){
                ended=true;
            } else {
                isFullCircle(FirstSquare,CurrentSquare,last);
            }
        } else if(hasDown(LastSquare) && LastDirection != 4){
            last = 3;
            CurrentSquare[1]=LastSquare[1]+1;
            if(FirstSquare[0]==CurrentSquare[0] && FirstSquare[1]==CurrentSquare[1]){
                ended=true;
            } else {
                isFullCircle(FirstSquare,CurrentSquare,last);
            }
        }
    }

    public boolean checkEnd(int i, int j){
        //TODO: check if the game has been won yet
        int[] currentLocation = {i,j};

        isFullCircle(currentLocation,currentLocation,0);
       // isMadeItAcross(currentLocation);

        if(isEnded()){
            if(currentPlayer=='X'){
                winnerBox.setText("Player 1 Wins!!!");
            } else {
                winnerBox.setText("Player 2 Wins!!!");
            }
        }

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

                    if(getIntent().getExtras().getInt("isPokemon")==1){
                        isPokemon=true;
                    } else {
                        isPokemon=false;
                    }
        } else {
            if (getIntent().getExtras() != null) {
                if(getIntent().getExtras().getInt("Tag")==1){
              //      TODO:UNCOMMENT THIS OUT
                    startActivity(new Intent(GameActivity.this,PlayerPopUp.class));
                }
                if(getIntent().hasExtra("isPokemon")){
                    if(getIntent().getExtras().getInt("isPokemon")==1){
                        isPokemon=true;
                    } else {
                        isPokemon=false;
                    }
                }
            }
        }

       if (isPokemon!=true && getIntent().hasExtra("Player 1")){
           player_1_icon = getIntent().getStringExtra("Player 1");
           player_2_icon = getIntent().getStringExtra("Player 2");

           switch(player_1_icon) {
            case "x":
                topX = (ImageView) findViewById(R.id.topX);
                topX.setBackgroundResource(R.drawable.x);
                bottomX = (ImageView) findViewById(R.id.bottomX);
                bottomX.setBackgroundResource(R.drawable.x);
                break;
            case "o":
                topX = (ImageView) findViewById(R.id.topX);
                topX.setBackgroundResource(R.drawable.o);
                bottomX = (ImageView) findViewById(R.id.bottomX);
                bottomX.setBackgroundResource(R.drawable.o);
                break;
            case "rook":
                topX = (ImageView) findViewById(R.id.topX);
                topX.setBackgroundResource(R.drawable.rook);
                bottomX = (ImageView) findViewById(R.id.bottomX);
                bottomX.setBackgroundResource(R.drawable.rook);
                break;
            case "hat":
                topX = (ImageView) findViewById(R.id.topX);
                topX.setBackgroundResource(R.drawable.top_hat);
                bottomX = (ImageView) findViewById(R.id.bottomX);
                bottomX.setBackgroundResource(R.drawable.top_hat);
                break;
            case "pawn":
                topX = (ImageView) findViewById(R.id.topX);
                topX.setBackgroundResource(R.drawable.chess_piece_bishop);
                bottomX = (ImageView) findViewById(R.id.bottomX);
                bottomX.setBackgroundResource(R.drawable.chess_piece_bishop);
                break;
            case "train":
                topX = (ImageView) findViewById(R.id.topX);
                topX.setBackgroundResource(R.drawable.train);
                bottomX = (ImageView) findViewById(R.id.bottomX);
                bottomX.setBackgroundResource(R.drawable.train);
                break;
        }
        switch(player_2_icon){
            case "x":
                leftO=(ImageView) findViewById(R.id.leftO);
                leftO.setBackgroundResource(R.drawable.x);
                rightO=(ImageView) findViewById(R.id.rightO);
                rightO.setBackgroundResource(R.drawable.x);
                break;
            case "o":
                leftO=(ImageView) findViewById(R.id.leftO);
                leftO.setBackgroundResource(R.drawable.o);
                rightO=(ImageView) findViewById(R.id.rightO);
                rightO.setBackgroundResource(R.drawable.o);
                break;
            case "pawn":
                leftO=(ImageView) findViewById(R.id.leftO);
                leftO.setBackgroundResource(R.drawable.chess_piece_bishop);
                rightO=(ImageView) findViewById(R.id.rightO);
                rightO.setBackgroundResource(R.drawable.chess_piece_bishop);
                break;
            case "rook":
                leftO=(ImageView) findViewById(R.id.leftO);
                leftO.setBackgroundResource(R.drawable.rook);
                rightO=(ImageView) findViewById(R.id.rightO);
                rightO.setBackgroundResource(R.drawable.rook);
                break;
            case "hat":
                leftO=(ImageView) findViewById(R.id.leftO);
                leftO.setBackgroundResource(R.drawable.top_hat);
                rightO=(ImageView) findViewById(R.id.rightO);
                rightO.setBackgroundResource(R.drawable.top_hat);
                break;
            case "train":
                leftO=(ImageView) findViewById(R.id.leftO);
                leftO.setBackgroundResource(R.drawable.train);
                rightO=(ImageView) findViewById(R.id.rightO);
                rightO.setBackgroundResource(R.drawable.train);
                break;
        }
        } else if (isPokemon == true){
            topX=(ImageView)findViewById(R.id.topX);
            topX.setBackgroundResource(R.drawable.ash);
            bottomX=(ImageView)findViewById(R.id.bottomX);
            bottomX.setBackgroundResource(R.drawable.ash);
            leftO=(ImageView) findViewById(R.id.leftO);
            leftO.setBackgroundResource(R.drawable.gary);
            rightO=(ImageView) findViewById(R.id.rightO);
            rightO.setBackgroundResource(R.drawable.gary);
        }


        resetButton = (Button) findViewById(R.id.reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent derp = new Intent(GameActivity.this,GameActivity.class);

                if(isPokemon!=true){
                    derp.putExtra("Tag",1);
                } else {
                    derp.putExtra("isPokemon",1);
                }
                startActivity(derp);
            }
        });

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
                    setResourceForPlayer(j,i,'X');
                    //board[j][i].setBackgroundResource(R.drawable.x);
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
                    //board[j][i].setBackgroundResource(R.drawable.o);
                    setResourceForPlayer(j,i,'O');
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
            int last_i = 99;
            int last_j = 99;
        //while(isEnded()!=true){
            if(prevTouched < numbTouched){

                for(int i =0; i < 11; i++) {
                    for (int j = 0; j < 11; j++) {
                        if(isTouched(i,j)==false &&  isClicked(i,j)==true){
                            Object tag =  board[i][j].getTag();
                            if(tag==X){
                                touched[i][j] = 'X';
                                if(last_i==99 && last_j == 99){
                                    last_i=i;
                                    last_j=j;
                                }
                            } else if(tag == O){
                                touched[i][j] = 'O';
                                if(last_i==99 && last_j == 99){
                                    last_i=i;
                                    last_j=j;
                                }
                            }

                        }
                    }
                }
                prevTouched++;
                setResourceForPlayer(last_i,last_j,currentPlayer);
               // String derp = String.valueOf(touched[last_i][last_j]+ String.valueOf(last_i) + " " + String.valueOf(last_j));
               // winnerBox.setText(derp);
            }
        if(last_i!=99 && last_j != 99){
            checkEnd(last_i,last_j);
        }


            changeCurrentPlayer();
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
                    //v.setBackgroundResource(R.drawable.x);
                    v.setTag(X);

                }

            } else {
                if (isPokemon == true) {
                    v.setBackgroundResource(R.drawable.gary);
                    v.setTag(O);
                } else {
                    //v.setBackgroundResource(R.drawable.o);
                    v.setTag(O);
                }

            }

            numbTouched++;
           // winnerBox.setText(String.valueOf(numbTouched)+" "+String.valueOf(prevTouched));
            Game();
        }
    }

    public boolean hasLeft(int[] Square){
        if(Square[0] == 0){
            return false;
        }
        if(touched[Square[0]][Square[1]]==touched[Square[0]-1][Square[1]]) {
            return true;
        } else {
            return false;
        }
    }
    public boolean hasRight(int[] Square){
        if(Square[0] == 10){
            return false;
        }
        if(touched[Square[0]][Square[1]]==touched[Square[0]+1][Square[1]]) {
            return true;
        } else {
            return false;
        }
    }
    public boolean hasUp(int[] Square){
        if(Square[1]==0){
            return false;
        }
        if(touched[Square[0]][Square[1]]==touched[Square[0]][Square[1]-1]) {
            return true;
        } else {
            return false;
        }
    }
    public boolean hasDown(int[] Square){
        if(Square[1]==10){
            return false;
        }
        if(touched[Square[0]][Square[1]]==touched[Square[0]][Square[1]+1]) {
            return true;
        } else {
            return false;
        }
    }

    public void setResourceForPlayer(int i, int j, char player){
        //String player_1_icon1 = "rook";
        if(player == 'X' && player_1_icon!=null){
           switch (player_1_icon){
                case "x": board[i][j].setBackgroundResource(R.drawable.x); break;
                case "o":board[i][j].setBackgroundResource(R.drawable.o);break;
                case "rook":board[i][j].setBackgroundResource(R.drawable.rook);break;
                case "pawn":board[i][j].setBackgroundResource(R.drawable.chess_piece_bishop); break;
                case "hat":board[i][j].setBackgroundResource(R.drawable.top_hat); break;
                case "train": board[i][j].setBackgroundResource(R.drawable.train); break;
            }
        } else if(player_2_icon != null){
            switch (player_2_icon){
                case "x": board[i][j].setBackgroundResource(R.drawable.x); break;
                case "o":board[i][j].setBackgroundResource(R.drawable.o);break;
                case "rook":board[i][j].setBackgroundResource(R.drawable.rook);break;
                case "pawn":board[i][j].setBackgroundResource(R.drawable.chess_piece_bishop); break;
                case "hat":board[i][j].setBackgroundResource(R.drawable.top_hat); break;
                case "train": board[i][j].setBackgroundResource(R.drawable.train); break;
           }
       }
    }
}

