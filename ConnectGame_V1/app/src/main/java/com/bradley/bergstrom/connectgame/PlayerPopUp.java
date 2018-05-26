package com.bradley.bergstrom.connectgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;

public class PlayerPopUp extends Activity {
    ImageButton next;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_player_one);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int) (height*.7));

        next = (ImageButton) findViewById(R.id.player1next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.popup_player_two);
                next = (ImageButton) findViewById(R.id.player2next);
                next.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        startActivity(new Intent(PlayerPopUp.this,GameActivity.class));
                    }
                });
            }
        });
    }
}
