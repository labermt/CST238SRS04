package com.bradley.bergstrom.connectgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

public class OptionsPopUp extends Activity{
    private Switch pokemon;
    private ImageButton back;
    private boolean isPokemon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_options);
        isPokemon= false;

        pokemon = (Switch) findViewById(R.id.secret_switch);
        pokemon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true) {
                    isPokemon = true;
                } else {
                    isPokemon = false;
                }
            }
        });
        back = ((ImageButton) findViewById(R.id.options_back));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(OptionsPopUp.this, MainActivity.class);
                if(isPokemon=true){
                    myIntent.putExtra("isPokemon",1);
                } else {
                    myIntent.putExtra("isPokemon",0);
                }

                startActivity(myIntent);
            }
        });

    }
}
