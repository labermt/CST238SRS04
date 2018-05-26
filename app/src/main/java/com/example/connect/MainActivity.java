package com.example.connect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnStart(View view)
    {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void OnInfo(View view)
    {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }
}
