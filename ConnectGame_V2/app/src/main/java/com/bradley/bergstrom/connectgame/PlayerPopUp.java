package com.bradley.bergstrom.connectgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class    PlayerPopUp extends Activity implements View.OnClickListener{
    private ImageButton x;
    private ImageButton o;
    private ImageButton rook;
    private ImageButton train;
    private ImageButton pawn;
    private ImageButton hat;
    private int picked;
    private TextView playerText;
    private  Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_player_one);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int picked = 0;
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        playerText = (TextView) findViewById(R.id.PlayerOnePopUp);
        getWindow().setLayout((int)(width*.8),(int) (height*.7));

        o = (ImageButton) findViewById(R.id.o_button);
        o.setTag("o");

        x = (ImageButton) findViewById(R.id.x_button);
        x.setTag("x");

        rook = (ImageButton) findViewById(R.id.rook_button);
        rook.setTag("rook");

        pawn = (ImageButton) findViewById(R.id.pawn_button);
        pawn.setTag("pawn");

        train = (ImageButton) findViewById(R.id.trian_button);
        train.setTag("train");

        hat = (ImageButton) findViewById(R.id.hat_button);
        hat.setTag("hat");

        i = new Intent(PlayerPopUp.this,GameActivity.class);
        o.setOnClickListener(this);
        x.setOnClickListener(this);
        rook.setOnClickListener(this);
        pawn.setOnClickListener(this);
        train.setOnClickListener(this);
        hat.setOnClickListener(this);

    }

    @Override
    public void onClick (View v){
        if(String.valueOf(v.getTag())!="taken") {
            v.setBackgroundResource(R.drawable.taken);

            if (picked == 0) {
                i.putExtra("Player 1", String.valueOf(v.getTag()));
                playerText.setText("Choose a character Player 2");

            } else if (picked == 1) {
                playerText.setText("Entering game...");
                i.putExtra("Player 2", String.valueOf(v.getTag()));
                startActivity(i);
            }

            picked++;
            v.setTag("taken");
        }
    }
}
