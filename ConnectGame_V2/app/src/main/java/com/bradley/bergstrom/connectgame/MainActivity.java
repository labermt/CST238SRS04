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
    Button howToWin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.StartButton);
        optionsButton=(Button) findViewById(R.id.OptionsButton);
        howToWin =(Button)findViewById(R.id.TutorialButton);


        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this,GameActivity.class);
                i.putExtra("Tag",1);

                if (getIntent().hasExtra("isPokemon")) {
                    i.putExtra("isPokemon",getIntent().getExtras().getInt("isPokemon"));
                }
                startActivity(i);

            }
        });
        optionsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,OptionsPopUp.class));
            }
        });

        howToWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TutorialActivity.class));
            }
        });
    }
}
