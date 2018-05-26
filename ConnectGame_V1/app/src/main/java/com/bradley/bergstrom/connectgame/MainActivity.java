package com.bradley.bergstrom.connectgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    Button optionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.StartButton);
        optionsButton=(Button) findViewById(R.id.OptionsButton);

        final Intent i = new Intent(MainActivity.this,GameActivity.class);
        i.putExtra("Tag",1);

        if (getIntent().hasExtra("isPokemon")) {
            i.putExtra("isPokemon",getIntent().getStringExtra("isPokemon"));
        }
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(i);

            }
        });
        optionsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,OptionsPopUp.class));
            }
        });
    }
}
