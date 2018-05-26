package com.example.heroa.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameField extends AppCompatActivity {

    private ImageView buttons[][] = new ImageView[11][11];
    public GameController overlord = new GameController();
    int playerOneImage = 0;
    int playerTwoImage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_field);

        PrepField();
        DrawField();
    }

    public void onClick(View v) {
        ImageView image = findViewById(v.getId());
        Location location = GetLocation(v.getId());

        if(overlord.isValidMove(location.locX, location.locY))
        {
            UpdateTurnField();
            overlord.PickTile(location.locX, location.locY);
            image.setClickable(false);
            if(overlord.CheckForWin(location.locX, location.locY))
            {
                Toast.makeText(this, "VICTORY!!", Toast.LENGTH_LONG).show();
            }
        }
        else
            Toast.makeText(this, "Can't place there.", Toast.LENGTH_SHORT).show();
        DrawField();
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
                if(overlord.GetTile(i, j).state == State.PLAYERONE)
                    UpdateImage(i, j, playerOneImage);
                else if(overlord.GetTile(i, j).state == State.PLAYERTWO)
                    UpdateImage(i, j, playerTwoImage);
            }
        }
    }

    private void UpdateImage(int x, int y, int image)
    {
        switch(image)
        {
            case 0:
                buttons[x][y].setImageResource(R.drawable.red);
                break;
            case 1:
                buttons[x][y].setImageResource(R.drawable.blue);
                break;
            case 2:
                buttons[x][y].setImageResource(R.drawable.green);
                break;
            case 3:
                buttons[x][y].setImageResource(R.drawable.white);
                break;
            case 4:
                buttons[x][y].setImageResource(R.drawable.black);
                break;
            default:
                break;
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

                if(overlord.GetTile(i, j).state != State.EMPTY)
                    buttons[j][i].setClickable(false);
            }
        }
    }

    public void UpdateTurnField()
    {
        TextView playerTurn = findViewById(R.id.activePlayerTextField);
        if(overlord.turn)
        {
            playerTurn.setText("Player Two's Turn");
        }
        else
        {
            playerTurn.setText("Player One's Turn");
        }
    }
}

