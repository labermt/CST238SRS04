package com.example.heroa.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class GameField extends AppCompatActivity implements View.OnClickListener {

    private ImageButton buttons[][] = new ImageButton[11][11];
    public GameController overlord = new GameController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_field);

        for(int i = 0; i < 11; i++)
        {
            for(int j = 0; j < 11; j++)
            {
                String buttonID = "button";
                if(i == 10)
                    buttonID += "A";
                else
                    buttonID += i;

                if(j == 10)
                    buttonID += "A";
                else
                    buttonID += j;

                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[j][i] = findViewById(resID);

                if(overlord.field.field[j][i].GetState() == State.PLAYERONE)
                    findViewById(resID).setBackgroundResource(R.drawable.peace);
                else if(overlord.field.field[j][i].GetState() == State.PLAYERTWO)
                    findViewById(resID).setBackgroundResource(R.drawable.biohazard);
            }
        }
    }

    @Override
    public void onClick(View v) {
        //button.setBackgroundResource(R.drawable.peace);
        //if (!((Button) v).getBackground().equals("@drawable/blank")){
           // return;
        //}

        //if(playerTurn){
        //    ((ImageButton)v).setBackgroundResource(R.drawable.peace);
        //} else {
        //    ((ImageButton)v).setBackgroundResource(R.drawable.biohazard);
        //}
        setContentView(R.layout.activity_game_field);
        TextView playerTurn = findViewById(R.id.activePlayerTextField);

        if(overlord.turn)
        {
            playerTurn.setText("Player One");
            ((ImageButton)v).setBackgroundResource(R.drawable.biohazard);
            overlord.ToggleTurn();
        } else {
            playerTurn.setText("Player Two");
            ((ImageButton)v).setBackgroundResource(R.drawable.peace);
            overlord.ToggleTurn();
        }

    }
}