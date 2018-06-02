package com.bradley.bergstrom.connectgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TutorialActivity extends Activity {
    private ImageButton back;
    private boolean isPokemon;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.how_to_win);

        isPokemon = false;
        if(getIntent().hasExtra("isPokemon")){
            isPokemon= getIntent().getExtras().getBoolean("isPokemon");
        }
        back = (ImageButton) findViewById(R.id.tutorialBackBUtton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(TutorialActivity.this,MainActivity.class);
                if(isPokemon != false){
                    i.putExtra("isPokemon",isPokemon);
                }
                startActivity(i);
            }
        });
    }
}
