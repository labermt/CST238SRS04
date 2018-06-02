package com.example.tcape.connectgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private BoardView boardView;
    private Game game;
    public TextView turn;
    public TextView turnColor;
    public TextView winner;
    private Button resetButton;
    public Switch computer;
    public Spinner spinner;
    public int redColor;
    public int blueColor;
    public MenuItem menuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuItem = findViewById(R.id.help);
        boardView = findViewById(R.id.boardView);
        resetButton = findViewById(R.id.button);
        computer = findViewById(R.id.switch1);
        spinner = findViewById(R.id.spinner);
        winner = findViewById(R.id.winner);
        winner.setVisibility(View.INVISIBLE);
        //turn = findViewById(R.id.turn);
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
                //turnColor.setTextColor(getResources().getColor(R.color.redColor));
                boardView.invalidate();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.color_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getItemAtPosition(position).toString()) {
                    case "Orange/Blue":
                        redColor = getResources().getColor(R.color.redColor);
                        blueColor = getResources().getColor(R.color.blueColor);
                        boardView.resetColors();
                        game.changeTurnColor();
                        winner.setTextColor(turnColor.getCurrentTextColor());
                        boardView.invalidate();
                        break;
                    case "Green/Magenta":
                        redColor = getResources().getColor(R.color.greenColor);
                        blueColor = getResources().getColor(R.color.magentaColor);
                        boardView.resetColors();
                        game.changeTurnColor();
                        winner.setTextColor(turnColor.getCurrentTextColor());
                        boardView.invalidate();
                        break;
                    case "Purple/Yellow":
                        redColor = getResources().getColor(R.color.purpleColor);
                        blueColor = getResources().getColor(R.color.yellowColor);
                        boardView.resetColors();
                        game.changeTurnColor();
                        winner.setTextColor(turnColor.getCurrentTextColor());
                        boardView.invalidate();
                        break;
                    case "Black/Grey":
                        redColor = getResources().getColor(R.color.blackColor);
                        blueColor = getResources().getColor(R.color.greyColor);
                        boardView.resetColors();
                        game.changeTurnColor();
                        winner.setTextColor(turnColor.getCurrentTextColor());
                        boardView.invalidate();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.help) {
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void newGame() {
        boardView.setupGame(game);
        boardView.invalidate();
    }
}
