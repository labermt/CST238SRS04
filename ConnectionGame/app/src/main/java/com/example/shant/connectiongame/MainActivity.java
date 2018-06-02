package com.example.shant.connectiongame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Random;


public class MainActivity extends AppCompatActivity
{
    private Button[][] ButtonArray;
    private String[][] FloodButtonArray = new String[7][7];
    private Boolean[][] BooleanButtonArray = new Boolean[7][7];
    private Boolean x_turn = true;
    private LinearLayout ConnectBoard;
    private TextView GameStatus;
    private Button ResetGame;
    private Button Tutorial;
    private Button LastOTurn;
    private void CreateBoard()
    {
        /* https://stackoverflow.com/questions/7195056/how-do-i-programmatically-add-buttons-into-layout-one-by-one-in-several-lines */
        GameStatus = new TextView(this);
        ResetGame = new Button(this);
        Tutorial = new Button(this);
        LastOTurn = new Button(this);
        ResetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBoard();
            }
        });
        ConnectBoard = new LinearLayout(this);
        ConnectBoard.setOrientation(LinearLayout.VERTICAL);
        ButtonArray = new Button[7][7];
        for (int i = 0; i<7; i++)
        {
            GameStatus.setText("x turn");
            LinearLayout Row = new LinearLayout(this);
            Row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < 7; j++)
            {
                ButtonArray[i][j] = new Button(this);
                final Button btnTag = ButtonArray[i][j];
                final int y_value = j;
                GameStatus.setText("x turn");
                btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                btnTag.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (btnTag.getText() != "")
                        {
                            return;
                        }
                        else
                        {
                            if (y_value == 0)
                            {
                                Toast.makeText(MainActivity.this, "Cannot mark here!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (y_value == 6)
                            {
                                Toast.makeText(MainActivity.this, "Cannot mark here!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            btnTag.setText("x");
                            btnTag.setBackgroundColor(Color.BLUE);
                            btnTag.setTextColor(Color.WHITE);
                            x_turn = false;
                            GameStatus.setText("o turn");
                            int count = 0;
                            Random random = new Random();
                            int random_row;
                            int random_column;
                            do
                            {
                                do
                                {
                                    random_row = random.nextInt(7);

                                }
                                while (random_row == 0 || random_row == 6);
                                random_column = random.nextInt(7);
                                count++;
                            }
                            while (ButtonArray[random_row][random_column].getText() != "" && count != 49);
                            LastOTurn.setBackgroundColor(Color.RED);
                            LastOTurn.setTextColor(Color.WHITE);
                            ButtonArray[random_row][random_column].setText("o");
                            ButtonArray[random_row][random_column].setTextColor(Color.WHITE);
                            ButtonArray[random_row][random_column].setBackgroundColor(Color.YELLOW);
                            ButtonArray[random_row][random_column].setTextColor(Color.BLACK);
                            LastOTurn = ButtonArray[random_row][random_column];
                            if (CheckForWin())
                            {
                                String winner = x_turn? "x wins":"o wins";
                                GameStatus.setText(winner);
                                ResetGame.setVisibility(View.VISIBLE);
                                for (int i=0; i<7; i++)
                                {
                                    for (int j = 0; j<7; j++)
                                    {
                                        ButtonArray[i][j].setEnabled(false);
                                    }
                                }
                            }
                            x_turn = true;
                            if (CheckForWin())
                            {
                                String winner = x_turn? "x wins":"o wins";
                                GameStatus.setText(winner);
                                ResetGame.setVisibility(View.VISIBLE);
                                for (int i=0; i<7; i++)
                                {
                                    for (int j = 0; j<7; j++)
                                    {
                                        ButtonArray[i][j].setEnabled(false);
                                    }
                                }
                            }
                        }
                    }
                });
                if (i == 0 && j == 0)
                {
                    btnTag.setEnabled(false);
                    btnTag.setBackgroundColor(Color.BLACK);
                    btnTag.setText("n");
                }
                else if (i==6 && j == 6)
                {
                    btnTag.setEnabled(false);
                    btnTag.setBackgroundColor(Color.BLACK);
                    btnTag.setText("n");
                }
                else if (i == 0 && j==6)
                {
                    btnTag.setEnabled(false);
                    btnTag.setBackgroundColor(Color.BLACK);
                    btnTag.setText("n");
                }
                else if (i == 6 && j == 0)
                {
                    btnTag.setEnabled(false);
                    btnTag.setBackgroundColor(Color.BLACK);
                    btnTag.setText("n");
                }
                else if (i%2 == 1 && j%2 == 0)
                {
                    btnTag.setText("o");
                    btnTag.setTextColor(Color.WHITE);
                    btnTag.setBackgroundColor(Color.RED);
                }
                else if (i%2 == 0 && j%2 == 1)
                {
                    btnTag.setText("x");
                    btnTag.setTextColor(Color.WHITE);
                    btnTag.setBackgroundColor(Color.BLUE);
                }
                btnTag.setLayoutParams(new LinearLayout.LayoutParams(150,150));
                btnTag.setId(j + 1 + (i * 4));

                Row.addView(btnTag);
            }
            ConnectBoard.addView(Row);
        }
        LinearLayout GameStatusDisplay = new LinearLayout(this);
        GameStatusDisplay.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        GameStatusDisplay.addView(GameStatus);
        ConnectBoard.addView(GameStatusDisplay);

        LinearLayout ResetGameDisplay = new LinearLayout(this);
        ResetGameDisplay.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ResetGameDisplay.addView(ResetGame);
        ResetGame.setText("Reset Game");

        LinearLayout TutorialDisplay = new LinearLayout(this);
        TutorialDisplay.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        TutorialDisplay.addView(Tutorial);
        Tutorial.setText("Tutorial");

        ConnectBoard.addView(ResetGameDisplay);
        ConnectBoard.addView(TutorialDisplay);

        setContentView(ConnectBoard);
        GameStatus.setText("x turn");
        //setContentView(R.layout.main);
    }

    private void UpdateBoard()
    {
        setContentView(ConnectBoard);
    }
    private Boolean CheckForWin()
    {
        /*
        int count = 0;
        for (int i = 0; i<7; i++)
        {
            CharSequence BoxCharacter = ButtonArray[i][0].getText();
            for (int j=1; j<7; j++)
            {
                if (ButtonArray[i][j].getText() == BoxCharacter)
                {
                    count++;
                }
            }
            if (count == 6)
            {
                return true;
            }
            count = 0;
        }
        count = 0;
        for (int i = 0; i<7; i++)
        {
            CharSequence BoxCharacter = ButtonArray[0][i].getText();
            for (int j=1; j<7; j++)
            {
                if (ButtonArray[j][i].getText() == BoxCharacter)
                {
                    count++;
                }
            }
            if (count == 6)
            {
                return true;
            }
            count = 0;
        }
        return false;
        */

        Boolean turn;
        if (x_turn)
        {
            turn = x_turn;
        }
        else
        {
            turn = false;
        }

        if (turn)
        {
            for (int i = 0; i < 7; i++)
            {
                for (int j = 0; j < 7; j++)
                {
                    if (ButtonArray[i][j].getText().toString() == "o")
                    {
                        FloodButtonArray[i][j] = "";
                    }
                    else if (ButtonArray[i][j].getText().toString() == "n")
                    {
                        //FloodButtonArray[i][j].setEnabled(true);
                        FloodButtonArray[i][j] = "";
                    }
                    else
                    {
                        FloodButtonArray[i][j] = ButtonArray[i][j].getText().toString();
                    }
                    BooleanButtonArray[i][j] = false;
                }
            }
        }
        else
        {
            for (int i = 0; i < 7; i++)
            {
                for (int j = 0; j < 7; j++)
                {
                    if (ButtonArray[i][j].getText() == "x")
                    {
                        FloodButtonArray[i][j] = "";
                    }
                    else if (ButtonArray[i][j].getText().toString() == "n")
                    {
                        //FloodButtonArray[i][j].setEnabled(true);
                        FloodButtonArray[i][j] = "";
                    }
                    else
                    {
                        FloodButtonArray[i][j] = ButtonArray[i][j].getText().toString();
                    }
                    BooleanButtonArray[i][j] = false;

                }
            }
        }
        CheckForWinByFloodFill(0,0, turn);
        if (turn)
        {
            for (int i = 0; i<7; i++)
            {
                for (int j=0; j<7; j++)
                {
                    if (FloodButtonArray[i][j] != "x")
                    {
                        return true;
                    }
                }
            }
        }
        else
        {
            for (int i = 0; i<7; i++)
            {
                for (int j=0; j<7; j++)
                {
                    if (FloodButtonArray[i][j] != "o")
                    {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    private void CheckForWinByFloodFill(int x, int y, boolean turn)
    {
        if (x < 0 || x > 6 || y <0 || y > 6)
        {
            return;
        }
        if (BooleanButtonArray[x][y])
        {
            return;
        }
        else
        {
            BooleanButtonArray[x][y] = true;
        }
        if (turn)
        {
            if (FloodButtonArray[x][y] == "x")
            {
                if (x == 0)
                {
                    CheckForWinByFloodFill(x, y-1, turn);
                }
                if (x == 7)
                {
                    CheckForWinByFloodFill(x, y-1, turn);
                }
                if (y == 0)
                {
                    CheckForWinByFloodFill(x-1, y, turn);
                }
                if (y == 7)
                {
                    CheckForWinByFloodFill(x-1, y, turn);
                }
                return;
            }
        }
        else
        {
            if (FloodButtonArray[x][y] == "o")
            {
                if (x == 0)
                {
                    CheckForWinByFloodFill(x, y-1, turn);
                }
                if (x == 7)
                {
                    CheckForWinByFloodFill(x, y-1, turn);
                }
                if (y == 0)
                {
                    CheckForWinByFloodFill(x-1, y, turn);
                }
                if (y == 7)
                {
                    CheckForWinByFloodFill(x-1, y, turn);
                }
                return;
            }
        }
        if (turn)
        {
            if (FloodButtonArray[x][y] != "x")
            {
                FloodButtonArray[x][y] = "x";
            }
        }
        else
        {
            if (FloodButtonArray[x][y] != "o")
            {
                FloodButtonArray[x][y] = "o";
            }
        }
        CheckForWinByFloodFill(x+1, y, turn);
        CheckForWinByFloodFill(x-1, y, turn);
        CheckForWinByFloodFill(x, y+1, turn);
        CheckForWinByFloodFill(x, y-1, turn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        CreateBoard();
    }
}

