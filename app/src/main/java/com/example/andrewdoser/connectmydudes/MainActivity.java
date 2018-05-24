package com.example.andrewdoser.connectmydudes;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[11][11];
    //public Connector[][] buttons = new Connector[11][11];
    public boolean[][] ifTouch = new boolean[11][11];
    public String[][] Cont = new String[11][11];

    private boolean player1turn = true;
    private int roundCount;
    private int player1points;
    private int player2points;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    public int originx =0, originy=0;
    public boolean Checkstart= false;
    public int traverse = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        int tx, ty;
        tx = 0;
        ty = 0;



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
               buttons[j][i].setBackgroundColor(Color.GREEN);
            }
        }
        for (int i = 1; i < 11; i+=2)
        {
            for(int j = 0; j < 11; j+=2)
            {
                buttons[j][i].setText("O");
                buttons[j][i].setBackgroundColor(Color.BLUE);
            }
        }


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
            }
        }
        for (int i = 0; i < 11; i+=2)
        {
            for(int j = 1; j < 11; j+=2)
            {
                buttons[j][i].setText("X");
                buttons[j][i].setBackgroundColor(Color.GREEN);
            }
        }
        for (int i = 1; i < 11; i+=2)
        {
            for(int j = 0; j < 11; j+=2)
            {
                buttons[j][i].setText("O");
                buttons[j][i].setBackgroundColor(Color.BLUE);
            }
        }
    }

    public void PlayerWon(String player)
    {
        Context context = getApplicationContext();
        if (player.equals("X"))
        {
            CharSequence warning = "Player 1 won!";
            int duration = Toast.LENGTH_LONG;
            Toast Twarning = Toast.makeText(context, warning, duration);
            TextView TwarMessage = (TextView) Twarning.getView().findViewById(android.R.id.message);
            TwarMessage.setTextColor(Color.GREEN);
            Twarning.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,0);
        }
        else
        {
            CharSequence warning = "Player 2 won!";
            int duration = Toast.LENGTH_LONG;
            Toast Twarning = Toast.makeText(context, warning, duration);
            TextView TwarMessage = (TextView) Twarning.getView().findViewById(android.R.id.message);
            TwarMessage.setTextColor(Color.GREEN);
            Twarning.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,0);
        }
    }

    @Override
    public void onClick(View v)
    {
        //If this button's text equals an empty string
        //then the button has not been clicked.
        //Therefore it should change.
        if (!((Button) v).getText().toString().equals(""))
        {
            return;
        }

        if(player1turn)
        {
            ((Button) v).setText("X");
            ((Button) v).setBackgroundColor(Color.GREEN);
            player1turn = false;
        }
        else
        {
            ((Button) v).setText("O");
            ((Button) v).setBackgroundColor(Color.BLUE);
            player1turn = true;
        }

        roundCount++;


      /*  for (int i = 0; i < 11; i++)
        {
            for (int j = 0; j < 11; j++)
            {
                if (player1turn)
                {
                    if(checkForWin("X",i,j))
                    {
                        player1points++;
                    }
                }
                else
                {
                    if(checkForWin("O",i,j))
                    {
                        player2points++;
                    }
                }


            }
        }*/


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


    /*private boolean checkForWin(String tc, int tx, int ty) {

        if(Checkstart == false)
        {
            originx = tx;
            originy = ty;
            Checkstart = true;

        }
        RefillCont();

        if(tx < 10)
        {
            if(Cont[tx][ty].equals(tc) && Cont[tx+1][ty].equals(tc) && !ifTouch[tx + 1][ty])
            {
                ifTouch[tx][ty] = true;
                traverse++;
                return checkForWin(tc,tx+1, ty);
            }

        }

        if(tx > 0)
        {
            if (Cont[tx][ty].equals(tc) && Cont[tx-1][ty].equals(tc) && !ifTouch[tx - 1][ty]) {
                ifTouch[tx][ty] = true;
                traverse++;
                return checkForWin(tc,tx - 1, ty);
            }
        }

        if(ty < 10)
        {
            if (Cont[tx][ty].equals(tc) && Cont[tx][ty+1].equals(tc) && !ifTouch[tx][ty + 1]) {
                ifTouch[tx][ty] = true;
                traverse++;
                return checkForWin(tc,tx, ty + 1);
            }
        }
        if(ty > 0)
        {
            if (Cont[tx][ty].equals(tc) && Cont[tx][ty-1].equals(tc) && !ifTouch[tx][ty - 1]) {
                ifTouch[tx][ty] = true;
                traverse++;
                return checkForWin(tc,tx, ty - 1);
            }
        }

        Checkstart = false;

        if(originx == tx && originy == ty && traverse >= 8)
        {
            PlayerWon(tc);
            return true;
        }
        else if((((originx == 0 && tx == 10) || (originy == 0 && ty == 10)) && traverse >= 10))
        {
            PlayerWon(tc);
            return true;
        }

        return false;

    }*/
}
