package com.example.connect;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static final String OPPONENT = "com.example.connect.OPPONENT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGameCPU(View view) {
        Intent intent = new Intent(this, ConnectActivity.class);
        intent.putExtra(OPPONENT, "1");
        startActivity(intent);
    }

    public void startGamePlayer(View view) {
        Intent intent = new Intent(this, ConnectActivity.class);
        intent.putExtra(OPPONENT, "0");
        startActivity(intent);
    }

    public void viewRules(View view) {
        Intent intent = new Intent(this, ViewRulesActivity.class);
        startActivity(intent);
    }
}
