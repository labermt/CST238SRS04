package com.example.connect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.graphics.Color;
import android.view.View;

public class ViewRulesActivity extends AppCompatActivity {

    Button[] button;
    Button[][] button2;
    Button[] button3;
    Button[] button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rules);
        button = new Button[3];
        button2 = new Button[3][3];
        button3 = new Button[3];
        button4 = new Button[3];
        button[0] = findViewById(R.id.button122);
        button[1] = findViewById(R.id.button123);
        button[2] = findViewById(R.id.button124);
        button2[0][0] = findViewById(R.id.button125);
        button2[0][1] = findViewById(R.id.button126);
        button2[0][2] = findViewById(R.id.button127);
        button2[1][0] = findViewById(R.id.button128);
        button2[1][1] = findViewById(R.id.button129);
        button2[1][2] = findViewById(R.id.button130);
        button2[2][0] = findViewById(R.id.button131);
        button2[2][1] = findViewById(R.id.button132);
        button2[2][2] = findViewById(R.id.button133);
        button3[0] = findViewById(R.id.button134);
        button3[1] = findViewById(R.id.button135);
        button3[2] = findViewById(R.id.button136);
        button4[0] = findViewById(R.id.button137);
        button4[1] = findViewById(R.id.button138);
        button4[2] = findViewById(R.id.button139);
        button[0].setBackgroundColor(Color.BLUE);
        button[1].setBackgroundColor(Color.WHITE);
        button[2].setBackgroundColor(Color.BLUE);
        button2[0][0].setBackgroundColor(Color.BLUE);
        button2[0][1].setBackgroundColor(Color.BLUE);
        button2[0][2].setBackgroundColor(Color.BLUE);
        button2[1][0].setBackgroundColor(Color.BLUE);
        button2[1][1].setBackgroundColor(Color.RED);
        button2[1][2].setBackgroundColor(Color.BLUE);
        button2[2][0].setBackgroundColor(Color.BLUE);
        button2[2][1].setBackgroundColor(Color.BLUE);
        button2[2][2].setBackgroundColor(Color.BLUE);
        button3[0].setBackgroundColor(Color.BLUE);
        button3[1].setBackgroundColor(Color.BLUE);
        button3[2].setBackgroundColor(Color.BLUE);
        button4[0].setBackgroundColor(Color.RED);
        button4[1].setBackgroundColor(Color.RED);
        button4[2].setBackgroundColor(Color.RED);
        }

    public void press(View view){
        button[1].setBackgroundColor(Color.BLUE);
    }


}
