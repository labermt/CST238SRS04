package com.example.andrewdoser.connectmydudes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[11][11];
    //public Connector[][] buttons = new Connector[11][11];

    private boolean player1turn = true;
    private int roundCount;
    private int player1points;
    private int player2points;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;


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
            }
        }

        Button button_Reset = findViewById(R.id.button_reset);
        button_Reset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });


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
        }
        else
        {
            ((Button) v).setText("O");
        }

        roundCount++;

    }

    private boolean checkForWin() {
        String[][] field = new String[11][11];

        for (int i = 0; i < 11; i++)
        {
            for(int j = 0; j < 11; j++)
            {
                //field[i][j] = buttons[i][j].butt.getText().toString();
            }
        }

        for (int i = 0; i< 11; i++)
        {
            //if()
        }
        return true;
    }
}
