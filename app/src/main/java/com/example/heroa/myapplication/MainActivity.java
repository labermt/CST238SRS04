package com.example.heroa.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    int player1Color = 0;
    int player2Color = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar p1Selector = findViewById(R.id.p1SeekBar);
        SeekBar p2Selector = findViewById(R.id.p2SeekBar);
        player1OnChange(player1Color);
        player2OnChange(player2Color);

        p1Selector.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                player1Color = progress;
                player1OnChange(progress);
            }
        });

        p2Selector.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                player2Color = progress;
                player2OnChange(progress);
            }
        });
    }

    public void PlayGame(View view)
    {
        Intent intent = new Intent(this, GameField.class);
        intent.putExtra("player1Color", player1Color);
        intent.putExtra("player2Color", player2Color);
        startActivity(intent);
    }

    public void Rules(View view)
    {

    }

    void player1OnChange(int color)
    {
        ImageView image = findViewById(R.id.p1ImageView);

        switch(color)
        {
            case 0:
                image.setImageResource(R.drawable.red);
                break;
            case 1:
                image.setImageResource(R.drawable.blue);
                break;
            case 2:
                image.setImageResource(R.drawable.green);
                break;
            case 3:
                image.setImageResource(R.drawable.white);
                break;
            case 4:
                image.setImageResource(R.drawable.black);
                break;
            default:
                break;
        }
    }

    void player2OnChange(int color)
    {
        ImageView image = findViewById(R.id.p2ImageView);

        switch(color)
        {
            case 0:
                image.setImageResource(R.drawable.red);
                break;
            case 1:
                image.setImageResource(R.drawable.blue);
                break;
            case 2:
                image.setImageResource(R.drawable.green);
                break;
            case 3:
                image.setImageResource(R.drawable.white);
                break;
            case 4:
                image.setImageResource(R.drawable.black);
                break;
            default:
                break;
        }
    }
}
