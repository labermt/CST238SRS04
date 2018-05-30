package com.example.connect;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity
{
    private Button[][] bGrid = new Button[11][11];
    private TextView turn_view;
    private String turn = "X";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        turn_view = findViewById(R.id.textTurn);

        bGrid[0][0] = findViewById(R.id.b0_0);
        bGrid[0][1] = findViewById(R.id.b0_1);
        bGrid[0][2] = findViewById(R.id.b0_2);
        bGrid[0][3] = findViewById(R.id.b0_3);
        bGrid[0][4] = findViewById(R.id.b0_4);
        bGrid[0][5] = findViewById(R.id.b0_5);
        bGrid[0][6] = findViewById(R.id.b0_6);
        bGrid[0][7] = findViewById(R.id.b0_7);
        bGrid[0][8] = findViewById(R.id.b0_8);
        bGrid[0][9] = findViewById(R.id.b0_9);
        bGrid[0][10] = findViewById(R.id.b0_10);
        bGrid[1][0] = findViewById(R.id.b1_0);
        bGrid[1][1] = findViewById(R.id.b1_1);
        bGrid[1][2] = findViewById(R.id.b1_2);
        bGrid[1][3] = findViewById(R.id.b1_3);
        bGrid[1][4] = findViewById(R.id.b1_4);
        bGrid[1][5] = findViewById(R.id.b1_5);
        bGrid[1][6] = findViewById(R.id.b1_6);
        bGrid[1][7] = findViewById(R.id.b1_7);
        bGrid[1][8] = findViewById(R.id.b1_8);
        bGrid[1][9] = findViewById(R.id.b1_9);
        bGrid[1][10] = findViewById(R.id.b1_10);
        bGrid[2][0] = findViewById(R.id.b2_0);
        bGrid[2][1] = findViewById(R.id.b2_1);
        bGrid[2][2] = findViewById(R.id.b2_2);
        bGrid[2][3] = findViewById(R.id.b2_3);
        bGrid[2][4] = findViewById(R.id.b2_4);
        bGrid[2][5] = findViewById(R.id.b2_5);
        bGrid[2][6] = findViewById(R.id.b2_6);
        bGrid[2][7] = findViewById(R.id.b2_7);
        bGrid[2][8] = findViewById(R.id.b2_8);
        bGrid[2][9] = findViewById(R.id.b2_9);
        bGrid[2][10] = findViewById(R.id.b2_10);
        bGrid[3][0] = findViewById(R.id.b3_0);
        bGrid[3][1] = findViewById(R.id.b3_1);
        bGrid[3][2] = findViewById(R.id.b3_2);
        bGrid[3][3] = findViewById(R.id.b3_3);
        bGrid[3][4] = findViewById(R.id.b3_4);
        bGrid[3][5] = findViewById(R.id.b3_5);
        bGrid[3][6] = findViewById(R.id.b3_6);
        bGrid[3][7] = findViewById(R.id.b3_7);
        bGrid[3][8] = findViewById(R.id.b3_8);
        bGrid[3][9] = findViewById(R.id.b3_9);
        bGrid[3][10] = findViewById(R.id.b3_10);
        bGrid[4][0] = findViewById(R.id.b4_0);
        bGrid[4][1] = findViewById(R.id.b4_1);
        bGrid[4][2] = findViewById(R.id.b4_2);
        bGrid[4][3] = findViewById(R.id.b4_3);
        bGrid[4][4] = findViewById(R.id.b4_4);
        bGrid[4][5] = findViewById(R.id.b4_5);
        bGrid[4][6] = findViewById(R.id.b4_6);
        bGrid[4][7] = findViewById(R.id.b4_7);
        bGrid[4][8] = findViewById(R.id.b4_8);
        bGrid[4][9] = findViewById(R.id.b4_9);
        bGrid[4][10] = findViewById(R.id.b4_10);
        bGrid[5][0] = findViewById(R.id.b5_0);
        bGrid[5][1] = findViewById(R.id.b5_1);
        bGrid[5][2] = findViewById(R.id.b5_2);
        bGrid[5][3] = findViewById(R.id.b5_3);
        bGrid[5][4] = findViewById(R.id.b5_4);
        bGrid[5][5] = findViewById(R.id.b5_5);
        bGrid[5][6] = findViewById(R.id.b5_6);
        bGrid[5][7] = findViewById(R.id.b5_7);
        bGrid[5][8] = findViewById(R.id.b5_8);
        bGrid[5][9] = findViewById(R.id.b5_9);
        bGrid[5][10] = findViewById(R.id.b5_10);
        bGrid[6][0] = findViewById(R.id.b6_0);
        bGrid[6][1] = findViewById(R.id.b6_1);
        bGrid[6][2] = findViewById(R.id.b6_2);
        bGrid[6][3] = findViewById(R.id.b6_3);
        bGrid[6][4] = findViewById(R.id.b6_4);
        bGrid[6][5] = findViewById(R.id.b6_5);
        bGrid[6][6] = findViewById(R.id.b6_6);
        bGrid[6][7] = findViewById(R.id.b6_7);
        bGrid[6][8] = findViewById(R.id.b6_8);
        bGrid[6][9] = findViewById(R.id.b6_9);
        bGrid[6][10] = findViewById(R.id.b6_10);
        bGrid[7][0] = findViewById(R.id.b7_0);
        bGrid[7][1] = findViewById(R.id.b7_1);
        bGrid[7][2] = findViewById(R.id.b7_2);
        bGrid[7][3] = findViewById(R.id.b7_3);
        bGrid[7][4] = findViewById(R.id.b7_4);
        bGrid[7][5] = findViewById(R.id.b7_5);
        bGrid[7][6] = findViewById(R.id.b7_6);
        bGrid[7][7] = findViewById(R.id.b7_7);
        bGrid[7][8] = findViewById(R.id.b7_8);
        bGrid[7][9] = findViewById(R.id.b7_9);
        bGrid[7][10] = findViewById(R.id.b7_10);
        bGrid[8][0] = findViewById(R.id.b8_0);
        bGrid[8][1] = findViewById(R.id.b8_1);
        bGrid[8][2] = findViewById(R.id.b8_2);
        bGrid[8][3] = findViewById(R.id.b8_3);
        bGrid[8][4] = findViewById(R.id.b8_4);
        bGrid[8][5] = findViewById(R.id.b8_5);
        bGrid[8][6] = findViewById(R.id.b8_6);
        bGrid[8][7] = findViewById(R.id.b8_7);
        bGrid[8][8] = findViewById(R.id.b8_8);
        bGrid[8][9] = findViewById(R.id.b8_9);
        bGrid[8][10] = findViewById(R.id.b8_10);
        bGrid[9][0] = findViewById(R.id.b9_0);
        bGrid[9][1] = findViewById(R.id.b9_1);
        bGrid[9][2] = findViewById(R.id.b9_2);
        bGrid[9][3] = findViewById(R.id.b9_3);
        bGrid[9][4] = findViewById(R.id.b9_4);
        bGrid[9][5] = findViewById(R.id.b9_5);
        bGrid[9][6] = findViewById(R.id.b9_6);
        bGrid[9][7] = findViewById(R.id.b9_7);
        bGrid[9][8] = findViewById(R.id.b9_8);
        bGrid[9][9] = findViewById(R.id.b9_9);
        bGrid[9][10] = findViewById(R.id.b9_10);
        bGrid[10][0] = findViewById(R.id.b10_0);
        bGrid[10][1] = findViewById(R.id.b10_1);
        bGrid[10][2] = findViewById(R.id.b10_2);
        bGrid[10][3] = findViewById(R.id.b10_3);
        bGrid[10][4] = findViewById(R.id.b10_4);
        bGrid[10][5] = findViewById(R.id.b10_5);
        bGrid[10][6] = findViewById(R.id.b10_6);
        bGrid[10][7] = findViewById(R.id.b10_7);
        bGrid[10][8] = findViewById(R.id.b10_8);
        bGrid[10][9] = findViewById(R.id.b10_9);
        bGrid[10][10] = findViewById(R.id.b10_10);
    }

    public void OnButtonClick(View view)
    {
        int row = 0;
        int col = 0;
        for (int i = 0; i < 11; i++)
        {
            for (int j = 0; j < 11; j++)
            {
                if (view.getId() == bGrid[i][j].getId())
                {
                    row = i;
                    col = j;
                }
            }
        }
        if (!TextIsEmpty(row, col))
            Toast.makeText(getBaseContext(), "That space is already used!", Toast.LENGTH_LONG).show();
        else
        {
            if (!IsSpaceValid(row, col))
                Toast.makeText(getBaseContext(), "You can only play between two of your pieces!", Toast.LENGTH_LONG).show();
            else
            {
                bGrid[row][col].setText(turn);
                if (turn == "X")
                    bGrid[row][col].setTextColor(Color.rgb(204,0,0));
                else
                    bGrid[row][col].setTextColor(Color.rgb(0,153,204));
                NextTurn();
            }
        }
    }

    Boolean TextIsEmpty(int r, int c)
    {
        if (bGrid[r][c].getText().equals(""))
            return true;
        else
            return false;
    }

    Boolean IsSpaceValid(int r, int c)
    {
        if (r > 0 && r < 10) // (r-1,c) and (r+1,c)
        {
            if (bGrid[r-1][c].getText().equals(turn) && bGrid[r+1][c].getText().equals(turn))
                return true;
        }
        if (c > 0 && c < 10) // (r,c-1) and (r,c+1)
        {
            if (bGrid[r][c-1].getText().equals(turn) && bGrid[r][c+1].getText().equals(turn))
                return true;
        }
        return false;
    }

    void NextTurn()
    {
        if (turn.equals("X"))
            turn = "O";
        else
            turn = "X";
        turn_view.setText(turn + "'s turn!");
    }
}