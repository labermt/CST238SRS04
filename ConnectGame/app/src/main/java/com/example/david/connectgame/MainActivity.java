package com.example.david.connectgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button, reset, info;
    private BoardView board;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.player2).setVisibility(View.INVISIBLE);
        button = findViewById(R.id.button);
        board = findViewById(R.id.boardView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(board.hasFocus){
                    board.turn();
                }
                else{
                    Toast.makeText(
                            v.getContext(),
                            "Select an open tile surrounded by your color",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

        reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board.reset();
            }
        });
    }

    public void infoClicked(View v){
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }
}
