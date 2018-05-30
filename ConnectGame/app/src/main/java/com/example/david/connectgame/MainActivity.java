package com.example.david.connectgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private BoardView board;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        board = findViewById(R.id.boardView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(board.hasFocus){
                    Toast.makeText(v.getContext(), "You have selected a tile", Toast.LENGTH_SHORT).show();
                    //SquareView player = ((board.game.isFirstPlayerTurn) ? board.first : board.second);
                    //board.game.takeTurn(board.game.isFirstPlayerTurn,board.focusCell.x,board.focusCell.y);
                    //board.squares.add(
                    //        new SquareView(player, board.focusCell.x * board.cellWidth + board.boardPadding, board.focusCell.y * board.cellWidth + board.boardPadding)
                    //);

                    //if(board.game.won(board.game.isFirstPlayerTurn)){
                    //    ((Button)findViewById(R.id.button)).setText(R.string.won);
                    //    findViewById(R.id.button).setFocusable(false);
                    //    return;
                    //}
                    //board.toggleTurn();
                }
                else{
                    Toast.makeText(v.getContext(), "Select an open tile surrounded by your color", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
