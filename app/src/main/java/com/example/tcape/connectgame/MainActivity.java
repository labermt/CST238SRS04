package com.example.tcape.connectgame;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private BoardView boardView;
    private Game game;
    public TextView turn;
    public TextView turnColor;
    private Button resetButton;
    public Switch computer;
    public int redColor;
    public int blueColor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boardView = findViewById(R.id.boardView);
        resetButton = findViewById(R.id.button);
        computer = findViewById(R.id.switch1);
        turn = findViewById(R.id.turn);
        redColor = getResources().getColor(R.color.redColor);
        blueColor = getResources().getColor(R.color.blueColor);
        turnColor = findViewById(R.id.turnColor);
        turnColor.setText(R.string.RED);
        turnColor.setTextColor(getResources().getColor(R.color.redColor));
        game = new Game();
        boardView.setGame(game);
        game.setMainActivity(this);
        boardView.setMainActivity(this);
        newGame();
        boardView.invalidate();

        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               game.resetGame();
               boardView.clearLines();
               boardView.setupGame(game);
               turnColor.setText(R.string.RED);
               turnColor.setTextColor(getResources().getColor(R.color.redColor));
               boardView.invalidate();
            }
        });


    }

    private void newGame() {
        boardView.setupGame(game);
        boardView.invalidate();
    }
}
