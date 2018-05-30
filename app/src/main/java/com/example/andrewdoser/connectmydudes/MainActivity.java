package com.example.andrewdoser.connectmydudes;

import android.content.Context;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[11][11];
    //public Connector[][] buttons = new Connector[11][11];
    public boolean[][] ifTouch = new boolean[11][11];
    public String[][] Cont = new String[11][11];
    public boolean[] win = new boolean[2];
    private boolean player1turn = true;
    private int roundCount;
    private int player1points;
    private int player2points;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    public int originx =0, originy=0, ssx=0,ssy=0;
    public boolean AllowInput= true;
    public boolean didIStart, didIEnd;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);





        String temp = "";
        for (int i = 0; i < 11; i++)
        {
            for(int j = 0; j < 11; j++)
            {

                temp = "button_" + i + "_" + j;
                int resID = getResources().getIdentifier(temp,"id",getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
                ifTouch[i][j] = false;

            }
        }
        for (int i = 0; i < 11; i+=2)
        {
            for(int j = 1; j < 11; j+=2)
            {
               buttons[j][i].setText("X");
               buttons[j][i].setBackgroundColor(getResources().getColor(R.color.lgreen));
            }
        }
        for (int i = 1; i < 11; i+=2)
        {
            for(int j = 0; j < 11; j+=2)
            {
                buttons[j][i].setText("O");
                buttons[j][i].setBackgroundColor(getResources().getColor(R.color.lblue));
            }
        }

/*        if(!player1turn)
        {
            OpponentMove();
        }*/
        final Button button_Reset = findViewById(R.id.button_reset);
        button_Reset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                buttReset();
            }
        });


    }


    public void buttReset()
    {
        for (int i = 0; i < 11; i++)
        {
            for(int j = 0; j < 11; j++)
            {
                buttons[j][i].setText("");
                buttons[j][i].setBackgroundResource(android.R.drawable.btn_default);
                buttons[i][j].setError(null);
            }
        }
        for (int i = 0; i < 11; i+=2)
        {
            for(int j = 1; j < 11; j+=2)
            {
                buttons[j][i].setText("X");
                buttons[j][i].setBackgroundColor(getResources().getColor(R.color.lgreen));
            }
        }
        for (int i = 1; i < 11; i+=2)
        {
            for(int j = 0; j < 11; j+=2)
            {
                buttons[j][i].setText("O");
                buttons[j][i].setBackgroundColor(getResources().getColor(R.color.lblue));
            }
        }
        AllowInput = true;
    }

    public void PlayerWon(String player)
    {

        if (player.equals("X"))
        {
            LetsToast("Player 1 has won!", getResources().getColor(R.color.dgreen));
            player1points++;
            textViewPlayer1.setText("Player 1: " +player1points);
        }
        else if (player.equals("O"))
        {
            LetsToast("Player 2 has won!", getResources().getColor(R.color.dblue));
            player2points++;
            textViewPlayer2.setText("Player 2: " +player2points);


        }
    }
    //getResources().getColor(R.color.dblue)
    public void LetsToast(String we, int color)
    {
        Context context = getApplicationContext();
        CharSequence warning = we;
        int duration = Toast.LENGTH_LONG;
        Toast Twarning = Toast.makeText(context, warning, duration);
        TextView TwarMessage = (TextView) Twarning.getView().findViewById(android.R.id.message);
        TwarMessage.setTextColor(color);
        Twarning.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL,0,0);
        Twarning.show();
    }

    public int[] CheckAiMove(int[] xy)
    {
        Random rand = new Random();
        if ((xy[0] == 0 && xy[1] == 0) || (xy[0] == 10 && xy[1] == 0) || (xy[0] == 10 && xy[1] == 10) || (xy[0] == 0 && xy[1] == 10))
        {
            xy[0] = rand.nextInt(10);
            xy[1] = rand.nextInt(10);
            return CheckAiMove(xy);
        }

        switch(xy[0])
        {
            case 1:
            case 3:
            case 5:
            case 7:
            case 9:
                if(xy[1] == 10 || xy[1] == 0)
                {
                    xy[0] = rand.nextInt(10);
                    return CheckAiMove(xy);
                }
                break;

        }

        return xy;
    }

    public boolean OpponentMove()
    {
        RefillCont();
        Random rand = new Random();
        int[] xy = new int[2];
        xy[0] = rand.nextInt(10);
        xy[1] = rand.nextInt(10);

        xy = CheckAiMove(xy);

        if(Cont[xy[0]][xy[1]].equals(""))
        {
            buttons[xy[0]][xy[1]].setText("O");
            buttons[xy[0]][xy[1]].setBackgroundColor(getResources().getColor(R.color.lblue));
            player1turn = true;
            if(checkForStart("O",xy[0],xy[1]))
            {
                buttReset();
            }
            return true;
        }
        else
        {
            return OpponentMove();
        }

    }

    @Override
    public void onClick(View v) {
        if (AllowInput)
        {
            //If this button's text equals an empty string
            //then the button has not been clicked.
            //Therefore it should change.
            ((Button) v).setError(null);
            if (!((Button) v).getText().toString().equals("")) {
                return;
            }

            if (player1turn) {
                switch (v.getId()) {
                    case R.id.button_0_0:
                        ((Button) v).setError("INVALID");
                        LetsToast("Sorry, You Cannot Place Your Mark There", getResources().getColor(R.color.dred));
                        break;
                    case R.id.button_0_2:
                        ((Button) v).setError("INVALID");
                        LetsToast("Sorry, You Cannot Place Your Mark There", getResources().getColor(R.color.dred));
                        break;
                    case R.id.button_0_4:
                        ((Button) v).setError("INVALID");
                        LetsToast("Sorry, You Cannot Place Your Mark There", getResources().getColor(R.color.dred));
                        break;
                    case R.id.button_0_6:
                        ((Button) v).setError("INVALID");
                        LetsToast("Sorry, You Cannot Place Your Mark There", getResources().getColor(R.color.dred));
                        break;
                    case R.id.button_0_8:
                        ((Button) v).setError("INVALID");
                        LetsToast("Sorry, You Cannot Place Your Mark There", getResources().getColor(R.color.dred));
                        break;
                    case R.id.button_0_10:
                        ((Button) v).setError("INVALID");
                        LetsToast("Sorry, You Cannot Place Your Mark There", getResources().getColor(R.color.dred));
                        break;

                    case R.id.button_10_0:
                        ((Button) v).setError("INVALID");
                        LetsToast("Sorry, You Cannot Place Your Mark There", getResources().getColor(R.color.dred));
                        break;

                    case R.id.button_10_10:
                        ((Button) v).setError("INVALID");
                        LetsToast("Sorry, You Cannot Place Your Mark There", getResources().getColor(R.color.dred));
                        break;
                    case R.id.button_10_2:
                        ((Button) v).setError("INVALID");
                        LetsToast("Sorry, You Cannot Place Your Mark There", getResources().getColor(R.color.dred));
                        break;
                    case R.id.button_10_4:
                        ((Button) v).setError("INVALID");
                        LetsToast("Sorry, You Cannot Place Your Mark There", getResources().getColor(R.color.dred));

                        break;
                    case R.id.button_10_6:
                        ((Button) v).setError("INVALID");
                        LetsToast("Sorry, You Cannot Place Your Mark There", getResources().getColor(R.color.dred));
                        break;
                    case R.id.button_10_8:
                        ((Button) v).setError("INVALID");
                        LetsToast("Sorry, You Cannot Place Your Mark There", getResources().getColor(R.color.dred));
                        break;

                    default:
                        ((Button) v).setText("X");
                        ((Button) v).setBackgroundColor(getResources().getColor(R.color.lgreen));
                        player1turn = false;

                        for (int i = 0; i < 11; i++) {
                            for (int j = 0; j < 11; j++) {
                                if (findViewById(v.getId()) == buttons[i][j]) {
                                    if (checkForStart("X", i, j)) {
                                        buttReset();
                                    }
                                }
                            }
                        }
                        //String temp = v.getTag().toString();
                        //int x = GetMenugs("X",temp);
                        //int y = GetMenugs("Y", temp);
                        //checkForStart("X",x,y);
                }

            }
            if (!player1turn) {

                OpponentMove();
            }

            roundCount++;


        }
    }
    public void RefillCont()
    {
        for(int i = 0; i <11; i++)
        {
            for (int j = 0; j < 11; j++)
            {
                Cont[i][j] = buttons[i][j].getText().toString();
                ifTouch[i][j]=false;
            }
        }
    }


    private boolean checkForStart(String tc, int tx, int ty)
    {
        originx = tx;
        originy = ty;
        ssx=0;
        ssy=0;
        didIEnd = false;
        didIStart = false;

        RefillCont();

        return checkForWin(tc,tx,ty, 0);

    }

    private boolean checkForWin(String tc, int tx, int ty, int trav) {

        trav++;
        if((originx == tx && originy == ty && trav >= 7) || ((didIEnd && didIStart) && trav >= 7))
        {
            PlayerWon(tc);

            return true;
        }

        if (ty == 0) didIStart = true;
        if (ty == 10) didIEnd = true;
        if (tx == 0) didIStart = true;
        if (tx == 10) didIEnd = true;


        boolean didIwin = false;
        if(!ifTouch[tx][ty]) {
            ifTouch[tx][ty] = true;

            if (ty < 10) {
                if (Cont[tx][ty + 1].equals(tc)) {
                    didIwin = checkForWin(tc, tx, (ty + 1), trav);
                }
            }
            if (!didIwin && ty > 0) {
                if (Cont[tx][ty - 1].equals(tc)) {
                    didIwin = checkForWin(tc, tx, (ty - 1), trav);
                }
            }

            if (!didIwin && tx < 10) {
                if (Cont[tx + 1][ty].equals(tc)) {
                    didIwin = checkForWin(tc, (tx + 1), ty, trav);
                }
            }
            if (!didIwin && tx > 0) {
                if (Cont[tx - 1][ty].equals(tc)) {
                    didIwin = checkForWin(tc, (tx - 1), ty, trav);
                }
            }
        }


        return didIwin;

    }

}
