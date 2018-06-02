package com.example.yennypurwanto.connectmarvelheros;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.UUID;


public class MainActivity extends Activity {

    private GameAlg game;
    GridLayout myLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new GameAlg();
        TextView playermssg = findViewById(R.id.mssgbox);

        myLayout = findViewById(R.id.board);
        myLayout.setColumnCount(11);
        myLayout.setRowCount(11);
        myLayout.setRowOrderPreserved(true);
        myLayout.setColumnOrderPreserved(true);

        //Board setup (Blanking all tiles)
        for(int i = 0; i < 11; i++) {
            for(int j = 0; j < 11; j++){
                ImageButton myButton = new ImageButton(this);
                myButton.setImageResource(R.drawable.whiteblank_img);
                myButton.setId(View.generateViewId());
                LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                params.height = 130;
                params.width = 130;
                myButton.setCropToPadding(true);
                myButton.setPaddingRelative(5, 5, 5, 5);
                myButton.setLayoutParams(params);
                myButton.setBackgroundColor(111);

                myButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int currViewIndex = -1;
                        for (int i = 0; i < 121; i++) {
                            if (v.equals(myLayout.getChildAt(i))) {
                                currViewIndex = i;
                            }
                        }
                        //getting the X and Y values
                        int rowVal = currViewIndex / 11;
                        int colVal = currViewIndex % 11;

                        game.makeMove(rowVal, colVal);
                        UpdateDisplay();
                    }
                });
                myLayout.addView(myButton);
            }
        }
        UpdateDisplay();

        Button startNewButton = findViewById(R.id.newGame_button);
        startNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                game.StartNewGame();
                UpdateDisplay();
            }

        });
    }

    private void UpdateDisplay()
    {
        int gridLayoutIndex = 0;
        for(int i=0; i < 11; i++)
        {
            for(int j =0; j < 11; j++)
            {
                ImageButton aButton = (ImageButton)myLayout.getChildAt(gridLayoutIndex);
                TileStatus aStatus = game.getButtonId(i,j).getStatus();
                switch(aStatus)
                {
                    case BP:
                        aButton.setImageResource(R.drawable.bphanter_img);
                        break;
                    case SW:
                        aButton.setImageResource(R.drawable.scarletw_img);
                        break;
                    case BLANK:
                        aButton.setImageResource(R.drawable.whiteblank_img);
                        break;
                    case GL:
                        aButton.setImageResource(R.drawable.greenlantern);
                        break;
                    case CORNER:
                        aButton.setImageResource(R.drawable.corners);
                        break;
                    case SM:
                        aButton.setImageResource(R.drawable.superman);
                        break;
                }
                gridLayoutIndex++;
            }
        }
    }

}
