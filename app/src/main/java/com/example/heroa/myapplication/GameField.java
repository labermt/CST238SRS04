package com.example.heroa.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameField extends AppCompatActivity {

    private ImageView buttons[][] = new ImageView[11][11];
    public GameController overlord = new GameController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_field);

        PrepField();
        DrawField();
    }

    public void onClick(View v) {
        TextView playerTurn = findViewById(R.id.activePlayerTextField);
        ImageView image = findViewById(v.getId());
        State state = State.PLAYERTWO;
        Location location = GetLocation(v.getId());

        //if isValid...
        if(overlord.turn)
        {
            state = State.PLAYERONE;
            playerTurn.setText("Player Two's Turn");
        }
        else
        {
            playerTurn.setText("Player One's Turn");
        }

        overlord.field.SetTile(location.locX, location.locY, state);

        image.setClickable(false);
        overlord.ToggleTurn();
        DrawField();
        return;
    }

    public Location GetLocation(int id)
    {
        for(int i = 0; i < 11; i++)
        {
            for(int j = 0; j < 11; j++)
            {
                if(buttons[i][j].getId() == id)
                {
                    return new Location(i, j);
                }
            }
        }
        return null;
    }

    public void DrawField()
    {
        for(int i = 0; i < 11; i++)
        {
            for (int j = 0; j < 11; j++)
            {
                if(overlord.field.field[i][j].state == State.PLAYERONE)
                    buttons[i][j].setImageResource(R.drawable.red);
                else if(overlord.field.field[i][j].state == State.PLAYERTWO)
                    buttons[i][j].setImageResource(R.drawable.blue);
            }
        }
    }

    public void PrepField()
    {
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

                if(overlord.field.field[i][j].state != State.EMPTY)
                    buttons[j][i].setClickable(false);
            }
        }
        buttons[0][0].setClickable(false);
        buttons[0][10].setClickable(false);
        buttons[10][0].setClickable(false);
        buttons[10][10].setClickable(false);
    }
}

