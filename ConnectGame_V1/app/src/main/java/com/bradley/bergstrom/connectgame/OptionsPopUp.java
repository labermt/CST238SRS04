package com.bradley.bergstrom.connectgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        pokemon = (Switch) findViewById(R.id.secret_switch);
        back = ((ImageButton) findViewById(R.id.options_back));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(pokemon.isChecked()){
                    isPokemon = true;
                } else {
                    isPokemon = false;
                }
                Intent myIntent = new Intent(OptionsPopUp.this, MainActivity.class);
                myIntent.putExtra("isPokemon",isPokemon);


                startActivity(myIntent);
            }
        });

    }
}
