package com.example.connect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.graphics.Color;
import android.widget.TextView;

public class ConnectActivity extends AppCompatActivity {
    String turn;
    int cpu;
    boolean[][] blue;
    boolean[][] red;
    TextView victoryTextbox;
    TextView turnTextbox;
    Button[][] button;
    Button button3;
    Button button5;
    Button button7;
    Button button9;
    Button button13;
    Button button15;
    Button button17;
    Button button19;
    Button button21;
    Button button23;
    Button button25;
    Button button27;
    Button button29;
    Button button31;
    Button button33;
    Button button35;
    Button button37;
    Button button39;
    Button button41;
    Button button43;
    Button button45;
    Button button47;
    Button button49;
    Button button51;
    Button button53;
    Button button55;
    Button button57;
    Button button59;
    Button button61;
    Button button63;
    Button button65;
    Button button67;
    Button button69;
    Button button71;
    Button button73;
    Button button75;
    Button button77;
    Button button79;
    Button button81;
    Button button83;
    Button button85;
    Button button87;
    Button button89;
    Button button91;
    Button button93;
    Button button95;
    Button button97;
    Button button99;
    Button button101;
    Button button103;
    Button button105;
    Button button107;
    Button button109;
    Button button113;
    Button button115;
    Button button117;
    Button button119;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        Intent intent = getIntent();
        cpu = Integer.parseInt(intent.getStringExtra(MainActivity.OPPONENT));

        turn = "blue";
        blue = new boolean[11][11];
        red = new boolean[11][11];
        button = new Button[11][11];
        victoryTextbox = findViewById(R.id.victoryTextbox);
        turnTextbox = findViewById(R.id.turnTextbox);
        turnTextbox.setTextColor(Color.BLUE);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                if(IsEven(i) && !IsEven(j)){
                    red[i][j] = true;
                }
                else red[i][j] = false;
            }
        }
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                if(IsEven(j) && !IsEven(i)){
                    blue[i][j] = true;
                }
                else blue[i][j] = false;
            }
        }
        button3 = findViewById(R.id.button3);
        button5 = findViewById(R.id.button5);
        button7 = findViewById(R.id.button7);
        button9 = findViewById(R.id.button9);
        button13 = findViewById(R.id.button13);
        button15 = findViewById(R.id.button15);
        button17 = findViewById(R.id.button17);
        button19 = findViewById(R.id.button19);
        button21 = findViewById(R.id.button21);
        button23 = findViewById(R.id.button23);
        button25 = findViewById(R.id.button25);
        button27 = findViewById(R.id.button27);
        button29 = findViewById(R.id.button29);
        button31 = findViewById(R.id.button31);
        button33 = findViewById(R.id.button33);
        button35 = findViewById(R.id.button35);
        button37 = findViewById(R.id.button37);
        button39 = findViewById(R.id.button39);
        button41 = findViewById(R.id.button41);
        button43 = findViewById(R.id.button43);
        button45 = findViewById(R.id.button45);
        button47 = findViewById(R.id.button47);
        button49 = findViewById(R.id.button49);
        button51 = findViewById(R.id.button51);
        button53 = findViewById(R.id.button53);
        button55 = findViewById(R.id.button55);
        button57 = findViewById(R.id.button57);
        button59 = findViewById(R.id.button59);
        button61 = findViewById(R.id.button61);
        button63 = findViewById(R.id.button63);
        button65 = findViewById(R.id.button65);
        button67 = findViewById(R.id.button67);
        button69 = findViewById(R.id.button69);
        button71 = findViewById(R.id.button71);
        button73 = findViewById(R.id.button73);
        button75 = findViewById(R.id.button75);
        button77 = findViewById(R.id.button77);
        button79 = findViewById(R.id.button79);
        button81 = findViewById(R.id.button81);
        button83 = findViewById(R.id.button83);
        button85 = findViewById(R.id.button85);
        button87 = findViewById(R.id.button87);
        button89 = findViewById(R.id.button89);
        button91 = findViewById(R.id.button91);
        button93 = findViewById(R.id.button93);
        button95 = findViewById(R.id.button95);
        button97 = findViewById(R.id.button97);
        button99 = findViewById(R.id.button99);
        button101 = findViewById(R.id.button101);
        button103 = findViewById(R.id.button103);
        button105 = findViewById(R.id.button105);
        button107 = findViewById(R.id.button107);
        button109 = findViewById(R.id.button109);
        button113 = findViewById(R.id.button113);
        button115 = findViewById(R.id.button115);
        button117 = findViewById(R.id.button117);
        button119 = findViewById(R.id.button119);

        button[0][2] = findViewById(R.id.button3);
        button[0][4] = findViewById(R.id.button5);
        button[0][6] = findViewById(R.id.button7);
        button[0][8] = findViewById(R.id.button9);
        button[1][1] = findViewById(R.id.button13);
        button[1][3] = findViewById(R.id.button15);
        button[1][5] = findViewById(R.id.button17);
        button[1][7] = findViewById(R.id.button19);
        button[1][9] = findViewById(R.id.button21);
        button[2][0]= findViewById(R.id.button23);
        button[2][2] = findViewById(R.id.button25);
        button[2][4] = findViewById(R.id.button27);
        button[2][6] = findViewById(R.id.button29);
        button[2][8] = findViewById(R.id.button31);
        button[2][10] = findViewById(R.id.button33);
        button[3][1] = findViewById(R.id.button35);
        button[3][3] = findViewById(R.id.button37);
        button[3][5] = findViewById(R.id.button39);
        button[3][7] = findViewById(R.id.button41);
        button[3][9] = findViewById(R.id.button43);
        button[4][0] = findViewById(R.id.button45);
        button[4][2] = findViewById(R.id.button47);
        button[4][4] = findViewById(R.id.button49);
        button[4][6] = findViewById(R.id.button51);
        button[4][8] = findViewById(R.id.button53);
        button[4][10] = findViewById(R.id.button55);
        button[5][1] = findViewById(R.id.button57);
        button[5][3] = findViewById(R.id.button59);
        button[5][5] = findViewById(R.id.button61);
        button[5][7] = findViewById(R.id.button63);
        button[5][9] = findViewById(R.id.button65);
        button[6][0] = findViewById(R.id.button67);
        button[6][2] = findViewById(R.id.button69);
        button[6][4] = findViewById(R.id.button71);
        button[6][6] = findViewById(R.id.button73);
        button[6][8] = findViewById(R.id.button75);
        button[6][10] = findViewById(R.id.button77);
        button[7][1] = findViewById(R.id.button79);
        button[7][3] = findViewById(R.id.button81);
        button[7][5] = findViewById(R.id.button83);
        button[7][7] = findViewById(R.id.button85);
        button[7][9] = findViewById(R.id.button87);
        button[8][0] = findViewById(R.id.button89);
        button[8][2] = findViewById(R.id.button91);
        button[8][4] = findViewById(R.id.button93);
        button[8][6] = findViewById(R.id.button95);
        button[8][8] = findViewById(R.id.button97);
        button[8][10] = findViewById(R.id.button99);
        button[9][1] = findViewById(R.id.button101);
        button[9][3] = findViewById(R.id.button103);
        button[9][5] = findViewById(R.id.button105);
        button[9][7] = findViewById(R.id.button107);
        button[9][9] = findViewById(R.id.button109);
        button[10][2] = findViewById(R.id.button113);
        button[10][4] = findViewById(R.id.button115);
        button[10][6] = findViewById(R.id.button117);
        button[10][8] = findViewById(R.id.button119);

        button[0][1] = findViewById(R.id.button2);
        button[0][3] = findViewById(R.id.button4);
        button[0][5] = findViewById(R.id.button6);
        button[0][7] = findViewById(R.id.button8);
        button[0][9] = findViewById(R.id.button10);
        button[1][0] = findViewById(R.id.button12);
        button[1][2] = findViewById(R.id.button14);
        button[1][4] = findViewById(R.id.button16);
        button[1][6] = findViewById(R.id.button18);
        button[1][8] = findViewById(R.id.button20);
        button[1][10] = findViewById(R.id.button22);
        button[2][1] = findViewById(R.id.button24);
        button[2][3] = findViewById(R.id.button26);
        button[2][5] = findViewById(R.id.button28);
        button[2][7] = findViewById(R.id.button30);
        button[2][9] = findViewById(R.id.button32);
        button[3][0] = findViewById(R.id.button34);
        button[3][2] = findViewById(R.id.button36);
        button[3][4] = findViewById(R.id.button38);
        button[3][6] = findViewById(R.id.button40);
        button[3][8] = findViewById(R.id.button42);
        button[3][10] = findViewById(R.id.button44);
        button[4][1] = findViewById(R.id.button46);
        button[4][3] = findViewById(R.id.button48);
        button[4][5] = findViewById(R.id.button50);
        button[4][7] = findViewById(R.id.button52);
        button[4][9] = findViewById(R.id.button54);
        button[5][0] = findViewById(R.id.button56);
        button[5][2] = findViewById(R.id.button58);
        button[5][4] = findViewById(R.id.button60);
        button[5][6] = findViewById(R.id.button62);
        button[5][8] = findViewById(R.id.button64);
        button[5][10] = findViewById(R.id.button66);
        button[6][1] = findViewById(R.id.button68);
        button[6][3] = findViewById(R.id.button70);
        button[6][5] = findViewById(R.id.button72);
        button[6][7] = findViewById(R.id.button74);
        button[6][9] = findViewById(R.id.button76);
        button[7][0] = findViewById(R.id.button78);
        button[7][2] = findViewById(R.id.button80);
        button[7][4] = findViewById(R.id.button82);
        button[7][6] = findViewById(R.id.button84);
        button[7][8] = findViewById(R.id.button86);
        button[7][10] = findViewById(R.id.button88);
        button[8][1] = findViewById(R.id.button90);
        button[8][3] = findViewById(R.id.button92);
        button[8][5] = findViewById(R.id.button94);
        button[8][7] = findViewById(R.id.button96);
        button[8][9] = findViewById(R.id.button98);
        button[9][0] = findViewById(R.id.button100);
        button[9][2] = findViewById(R.id.button102);
        button[9][4] = findViewById(R.id.button104);
        button[9][6] = findViewById(R.id.button106);
        button[9][8] = findViewById(R.id.button108);
        button[9][10] = findViewById(R.id.button110);
        button[10][1] = findViewById(R.id.button112);
        button[10][3] = findViewById(R.id.button114);
        button[10][5] = findViewById(R.id.button116);
        button[10][7] = findViewById(R.id.button118);
        button[10][9] = findViewById(R.id.button120);

        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                if(blue[i][j]){
                    button[i][j].setBackgroundColor(Color.BLUE);
                }
                else if(red[i][j]){
                    button[i][j].setBackgroundColor(Color.RED);
                }
            }
        }



    }

    public boolean IsEven(int number){
        return number % 2 == 0;
    }
    public void VictoryBlue(){
        boolean[][] visited = new boolean[11][11];
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(1,0, visited, blue, 1, 10, "blue", 1, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(3,0, visited, blue, 1, 10, "blue", 3, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(5,0, visited, blue, 1, 10, "blue", 5, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(7,0, visited, blue, 1, 10, "blue", 7, 0, 0, true);
                                for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(9,0, visited, blue, 1, 10, "blue", 9, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(1,0, visited, blue, 3, 10, "blue", 1, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(3,0, visited, blue, 3, 10, "blue", 3, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(5,0, visited, blue, 3, 10, "blue", 5, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(7,0, visited, blue, 3, 10, "blue", 7, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(9,0, visited, blue, 3, 10, "blue", 9, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(1,0, visited, blue, 5, 10, "blue", 1, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(3,0, visited, blue, 5, 10, "blue", 3, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(5,0, visited, blue, 5, 10, "blue", 5, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(7,0, visited, blue, 5, 10, "blue", 7, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(9,0, visited, blue, 5, 10, "blue", 9, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(1,0, visited, blue, 7, 10, "blue", 1, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(3,0, visited, blue, 7, 10, "blue", 3, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(5,0, visited, blue, 7, 10, "blue", 5, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(7,0, visited, blue, 7, 10, "blue", 7, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(9,0, visited, blue, 7, 10, "blue", 9, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(1,0, visited, blue, 9, 10, "blue", 1, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(3,0, visited, blue, 9, 10, "blue", 3, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(5,0, visited, blue, 9, 10, "blue", 5, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(7,0, visited, blue, 9, 10, "blue", 7, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(9,0, visited, blue, 9, 10, "blue", 9, 0, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(1,2, visited, blue, 9, 10, "blue", 1, 2, 0, false);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(1,4, visited, blue, 9, 10, "blue", 1, 4, 0, false);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(1,6, visited, blue, 9, 10, "blue", 1, 6, 0, false);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(1,8, visited, blue, 9, 10, "blue", 1, 8, 0, false);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(3,2, visited, blue, 9, 10, "blue", 3, 2, 0, false);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(3,4, visited, blue, 9, 10, "blue", 3, 4, 0, false);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(3,6, visited, blue, 9, 10, "blue", 3, 6, 0, false);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(3,8, visited, blue, 9, 10, "blue", 3, 8, 0, false);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(5,2, visited, blue, 9, 10, "blue", 5, 2, 0, false);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(5,4, visited, blue, 9, 10, "blue", 5, 4, 0, false);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(5,6, visited, blue, 9, 10, "blue", 5, 6, 0, false);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(5,8, visited, blue, 9, 10, "blue", 5, 8, 0, false);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(7,2, visited, blue, 9, 10, "blue", 7, 2, 0, false);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(7,4, visited, blue, 9, 10, "blue", 7, 4, 0, false);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(7,6, visited, blue, 9, 10, "blue", 7, 6, 0, false);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(7,8, visited, blue, 9, 10, "blue", 7, 8, 0, false);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
}

    public void VictoryRed(){
        boolean[][] visited = new boolean[11][11];
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 1, visited, red, 10, 1, "red", 0, 1, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 3, visited, red, 10, 1, "red", 0, 3, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 5, visited, red, 10, 1, "red", 0, 5, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 7, visited, red, 10, 1, "red", 0, 7, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 9, visited, red, 10, 1, "red", 0, 9, 0, true);
                                for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 1, visited, red, 10, 3, "red", 0, 1, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 3, visited, red, 10, 3, "red", 0, 3, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 5, visited, red, 10, 3, "red", 0, 5, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 7, visited, red, 10, 3, "red", 0, 7, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 9, visited, red, 10, 3, "red", 0, 9, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 1, visited, red, 10, 5, "red", 0, 1, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 3, visited, red, 10, 5, "red", 0, 3, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 5, visited, red, 10, 5, "red", 0, 5, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 7, visited, red, 10, 5, "red", 0, 7, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 9, visited, red, 10, 5, "red", 0, 9, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 1, visited, red, 10, 7, "red", 0, 1, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 3, visited, red, 10,7, "red", 0, 3, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 5, visited, red, 10, 7, "red", 0, 5, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 7, visited, red, 10, 7, "red", 0, 7, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 9, visited, red, 10, 7, "red", 0, 9, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 1, visited, red, 10, 9, "red", 0, 1, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 3, visited, red, 10, 9, "red", 0, 3, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 5, visited, red, 10, 9, "red", 0, 5, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 7, visited, red, 10, 9, "red", 0, 7, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(0, 9, visited, red, 10, 9, "red", 0, 9, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(2, 1, visited, red, 9, 10, "red", 2, 1, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(4, 1, visited, red, 9, 10, "red", 4, 1, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(6, 1, visited, red, 9, 10, "red", 6, 1, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(8, 1, visited, red, 9, 10, "red", 8, 1, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(2, 3, visited, red, 9, 10, "red", 2, 3, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(4, 3, visited, red, 9, 10, "red", 4, 3, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(6, 3, visited, red, 9, 10, "red", 6, 3, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(8, 3, visited, red, 9, 10, "red", 8, 3, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(2, 5, visited, red, 9, 10, "red", 2, 5, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(4, 5, visited, red, 9, 10, "red", 4, 5, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(6, 5, visited, red, 9, 10, "red", 6, 5, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(8, 5, visited, red, 9, 10, "red", 8, 5, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(2, 7, visited, red, 9, 10, "red", 2, 7, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(4, 7, visited, red, 9, 10, "red", 4, 7, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(6, 7, visited, red, 9, 10, "red", 6, 7, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
        DFS(8, 7, visited, red, 9, 10, "red", 8, 7, 0, true);
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                visited[i][j] = false;
            }
        }
}

    public void DFS(int x, int y, boolean[][] visited, boolean[][] matrix, int dest_x, int dest_y, String color, int start_x, int start_y, int iterations, boolean startingPoint){

    if (x >= 11 || y >= 11)
        return;
    if(x < 0 || y < 0)
        return;
    if(!matrix[x][y])
        return;
    if(x == start_x && y == start_y && iterations > 3){
        if(color.equals("blue")){
            victoryTextbox.setText("Blue wins!");
            victoryTextbox.setTextColor(Color.BLUE);
            turnTextbox.setText("");
        }
        else if(color.equals("red")){
            victoryTextbox.setText("Red wins!");
            victoryTextbox.setTextColor(Color.RED);
            turnTextbox.setText("");
        }
        final ConnectActivity this_ = this;
        android.os.Handler handler = new android.os.Handler();
            handler.postDelayed(new Runnable() {
            public void run() {
             Intent intent = new Intent(this_, MainActivity.class);
        startActivity(intent);
        }
}, 5000);

        }
    if(visited[x][y])
        return;

    visited[x][y] = true;
    if(startingPoint){
    if(x == dest_x && y == dest_y){
        if(color.equals("blue")){
            victoryTextbox.setText("Blue wins!");
            victoryTextbox.setTextColor(Color.BLUE);
            turnTextbox.setText("");
        }
        else if(color.equals("red")){
            victoryTextbox.setText("Red wins!");
            victoryTextbox.setTextColor(Color.RED);
            turnTextbox.setText("");
        }
        final ConnectActivity this_ = this;
        android.os.Handler handler = new android.os.Handler();
            handler.postDelayed(new Runnable() {
            public void run() {
             Intent intent = new Intent(this_, MainActivity.class);
        startActivity(intent);
        }
}, 3000);

}
}
    iterations++;
    DFS(x-1, y, visited, matrix, dest_x, dest_y, color, start_x, start_y, iterations, startingPoint);
    DFS(x, y-1, visited, matrix, dest_x, dest_y, color, start_x, start_y, iterations, startingPoint);
    DFS(x, y+1, visited, matrix, dest_x, dest_y, color, start_x, start_y, iterations, startingPoint);
    DFS(x+1, y, visited, matrix, dest_x, dest_y, color, start_x, start_y, iterations, startingPoint);
    }

    public void makeCPUMove(){
        boolean done = false;
        java.util.Random r = new java.util.Random();
        while(!done){
            int i = r.nextInt(11);
            int j = r.nextInt(11);
            if(!red[i][j] && !blue[i][j]){
                    if(!(i == 0 && j == 0) && !(i == 10 && j == 10)
                            && !(i == 0 && j == 10) && !(i == 10 && j == 0)
                            && j != 0 && j != 10){
                        red[i][j] = true;
                        button[i][j].setBackgroundColor(Color.RED);
                        done = true;
                    }
                }
}
    }

    public void buttonPress(View v){
        boolean invalid_move = false;
        switch (v.getId()) {
            case R.id.button3:
                if(turn.equals("red") && !blue[0][2] && !red[0][2]){
                    red[0][2] = true;
                    button3.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button5:
                if(turn.equals("red") && !blue[0][4] && !red[0][4]){
                    red[0][4] = true;
                    button5.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button7:
                if(turn.equals("red") && !blue[0][6] && !red[0][6]){
                    red[0][6] = true;
                    button7.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button9:
                if(turn.equals("red") && !blue[0][8] && !red[0][8]){
                    red[0][8] = true;
                    button9.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button13:
                if(turn.equals("blue") && !red[1][1] && !blue[1][1]){
                    blue[1][1] = true;
                    button13.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[1][1] && !red[1][1]){
                    red[1][1] = true;
                    button13.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button15:
                if(turn.equals("blue") && !red[1][3] && !blue[1][3]){
                    blue[1][3] = true;
                    button15.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[1][3] && !red[1][3]){
                    red[1][3] = true;
                    button15.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button17:
                if(turn.equals("blue") && !red[1][5]&& !blue[1][5]){
                    blue[1][5] = true;
                    button17.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[1][5]&& !red[1][5]){
                    red[1][5] = true;
                    button17.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button19:
                if(turn.equals("blue") && !red[1][7]&& !blue[1][7]){
                    blue[1][7] = true;
                    button19.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[1][7] && !red[1][7]){
                    red[1][7] = true;
                    button19.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button21:
                if(turn.equals("blue") && !red[1][9] && !blue[1][9]){
                    blue[1][9] = true;
                    button21.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[1][9] && !red[1][9]){
                    red[1][9] = true;
                    button21.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button23:
                if(turn.equals("blue") && !red[2][0] && !blue[2][0]){
                    blue[2][0] = true;
                    button23.setBackgroundColor(Color.BLUE);
                }
                else invalid_move = true;
                break;
            case R.id.button25:
                if(turn.equals("blue") && !red[2][2] && !blue[2][2]){
                    blue[2][2] = true;
                    button25.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[2][2] && !red[2][2]){
                    red[2][2] = true;
                    button25.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button27:
                if(turn.equals("blue") && !red[2][4] && !blue[2][4]){
                    blue[2][4] = true;
                    button27.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[2][4] && !red[2][0]){
                    red[2][4] = true;
                    button27.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button29:
                if(turn.equals("blue") && !red[2][6] && !blue[2][6]){
                    blue[2][6] = true;
                    button29.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[2][6] && !red[2][6]){
                    red[2][6] = true;
                    button29.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button31:
                if(turn.equals("blue") && !red[2][8] && !blue[2][8]){
                    blue[2][8] = true;
                    button31.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[2][8] && !red[2][8]){
                    red[2][8] = true;
                    button31.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button33:
                if(turn.equals("blue") && !red[2][10] && !blue[2][10]){
                    blue[2][10] = true;
                    button33.setBackgroundColor(Color.BLUE);
                }
                else invalid_move = true;
                break;
            case R.id.button35:
                if(turn.equals("blue") && !red[3][1] && !blue[3][1]){
                    blue[3][1] = true;
                    button35.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[3][1] && !red[3][1]){
                    red[3][1] = true;
                    button35.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button37:
                if(turn.equals("blue") && !red[3][3] && !blue[3][3]){
                    blue[3][3] = true;
                    button37.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[3][3] && !red[3][3]){
                    red[3][3] = true;
                    button37.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button39:
                if(turn.equals("blue") && !red[3][5] && !blue[3][5]){
                    blue[3][5] = true;
                    button39.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[3][5] && !red[3][5]){
                    red[3][5] = true;
                    button39.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button41:
                if(turn.equals("blue") && !red[3][7] && !blue[3][7]){
                    blue[3][7] = true;
                    button41.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[3][7] && !red[3][7]){
                    red[3][7] = true;
                    button41.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button43:
                if(turn.equals("blue") && !red[3][9] && !blue[3][9]){
                    blue[3][9] = true;
                    button43.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[3][9] && !red[3][9]){
                    red[3][9] = true;
                    button43.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button45:
                if(turn.equals("blue") && !red[4][0] && !blue[4][0]){
                    blue[4][0] = true;
                    button45.setBackgroundColor(Color.BLUE);
                }
                else invalid_move = true;
                break;
            case R.id.button47:
                if(turn.equals("blue") && !red[4][2] && !blue[4][2]){
                    blue[4][2] = true;
                    button47.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[4][2] && !red[4][2]){
                    red[4][2] = true;
                    button47.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button49:
                if(turn.equals("blue") && !red[4][4] && !blue[4][4]){
                    blue[4][4] = true;
                    button49.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[4][4] && !red[4][4]){
                    red[4][4] = true;
                    button49.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button51:
                if(turn.equals("blue") && !red[4][6] && !blue[4][6]){
                    blue[4][6] = true;
                    button51.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[4][6] && !red[4][6]){
                    red[4][6] = true;
                    button51.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button53:
                if(turn.equals("blue") && !red[4][8] && !blue[4][8]){
                    blue[4][8] = true;
                    button53.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[4][8] && !red[4][8]){
                    red[4][8] = true;
                    button53.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button55:
                if(turn.equals("blue") && !red[4][10] && !blue[4][10]){
                    blue[4][10] = true;
                    button55.setBackgroundColor(Color.BLUE);
                }
                else invalid_move = true;
                break;
            case R.id.button57:
                if(turn.equals("blue") && !red[5][1] && !blue[5][1]){
                    blue[5][1] = true;
                    button57.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[5][1] && !red[5][1]){
                    red[5][1] = true;
                    button57.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button59:
                if(turn.equals("blue") && !red[5][3] && !blue[5][3]){
                    blue[5][3] = true;
                    button59.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[5][3] && !red[5][3]){
                    red[5][3] = true;
                    button59.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button61:
                if(turn.equals("blue") && !red[5][5] && !blue[5][5]){
                    blue[5][5] = true;
                    button61.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[5][5] && !red[5][5]){
                    red[5][5] = true;
                    button61.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button63:
                if(turn.equals("blue") && !red[5][7] && !blue[5][7]){
                    blue[5][7] = true;
                    button63.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[5][7] && !red[5][7]){
                    red[5][7] = true;
                    button63.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button65:
                if(turn.equals("blue") && !red[5][9] && !blue[5][9]){
                    blue[5][9] = true;
                    button65.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[5][9] && !red[5][9]){
                    red[5][9] = true;
                    button65.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button67:
                if(turn.equals("blue") && !red[6][0] && !blue[6][0]){
                    blue[6][0] = true;
                    button67.setBackgroundColor(Color.BLUE);
                }
                else invalid_move = true;
                break;
            case R.id.button69:
                if(turn.equals("blue") && !red[6][2] && !blue[6][2]){
                    blue[6][2] = true;
                    button69.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[6][2] && !red[6][2]){
                    red[6][2] = true;
                    button69.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button71:
                if(turn.equals("blue") && !red[6][4] && !blue[6][4]){
                    blue[6][4] = true;
                    button71.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[6][4] && !red[6][4]){
                    red[6][4] = true;
                    button71.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button73:
                if(turn.equals("blue") && !red[6][6] && !blue[6][6]){
                    blue[6][6] = true;
                    button73.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[6][6] && !red[6][6]){
                    red[6][6] = true;
                    button73.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button75:
                if(turn.equals("blue") && !red[6][8] && !blue[6][8]){
                    blue[6][8] = true;
                    button75.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[6][8] && !red[6][8]){
                    red[6][8] = true;
                    button75.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            case R.id.button77:
                if(turn.equals("blue") && !red[6][10] && !blue[6][10]){
                    blue[6][10] = true;
                    button77.setBackgroundColor(Color.BLUE);
                }
                else invalid_move = true;
                break;

            case R.id.button79:
                if(turn.equals("blue") && !red[7][1] && !blue[7][1]){
                    blue[7][1] = true;
                    button79.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[7][1] && !red[7][1]){
                    red[7][1] = true;
                    button79.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;

            case R.id.button81:
                if(turn.equals("blue") && !red[7][3] && !blue[7][3]){
                    blue[7][3] = true;
                    button81.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[7][3] && !red[7][3]){
                    red[7][3] = true;
                    button81.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;

            case R.id.button83:
                if(turn.equals("blue") && !red[7][5] && !blue[7][5]){
                    blue[7][5] = true;
                    button83.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[7][5] && !red[7][5]){
                    red[7][5] = true;
                    button83.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;

            case R.id.button85:
                if(turn.equals("blue") && !red[7][7] && !blue[7][7]){
                    blue[7][7] = true;
                    button85.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[7][7] && !red[7][7]){
                    red[7][7] = true;
                    button85.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;

            case R.id.button87:
                if(turn.equals("blue") && !red[7][9] && !blue[7][9]){
                    blue[7][9] = true;
                    button87.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[7][9] && !red[7][9]){
                    red[7][9] = true;
                    button87.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;

            case R.id.button89:
                if(turn.equals("blue") && !red[8][0] && !blue[8][0]){
                    blue[8][0] = true;
                    button89.setBackgroundColor(Color.BLUE);
                }
                else invalid_move = true;
                break;

            case R.id.button91:
                if(turn.equals("blue") && !red[8][2] && !blue[8][2]){
                    blue[8][2] = true;
                    button91.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[8][2] && !red[8][2]){
                    red[8][2] = true;
                    button91.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;

            case R.id.button93:
                if(turn.equals("blue") && !red[8][4] && !blue[8][4]){
                    blue[8][4] = true;
                    button93.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[8][4] && !red[8][4]){
                    red[8][4] = true;
                    button93.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;

            case R.id.button95:
                if(turn.equals("blue") && !red[8][6] && !blue[8][6]){
                    blue[8][6] = true;
                    button95.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[8][6] && !red[8][6]){
                    red[8][6] = true;
                    button95.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;

            case R.id.button97:
                if(turn.equals("blue") && !red[8][8] && !blue[8][8]){
                    blue[8][8] = true;
                    button97.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[8][8] && !red[8][8]){
                    red[8][8] = true;
                    button97.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;

            case R.id.button99:
                if(turn.equals("blue") && !red[8][10] && !blue[8][10]){
                    blue[8][10] = true;
                    button99.setBackgroundColor(Color.BLUE);
                }
                else invalid_move = true;
                break;

            case R.id.button101:
                if(turn.equals("blue") && !red[9][1] && !blue[9][1]){
                    blue[9][1] = true;
                    button101.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[9][1] && !red[9][1]){
                    red[9][1] = true;
                    button101.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;

            case R.id.button103:
                if(turn.equals("blue") && !red[9][3] && !blue[9][3]){
                    blue[9][3] = true;
                    button103.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[9][3] && !red[9][3]){
                    red[9][3] = true;
                    button103.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;

            case R.id.button105:
                if(turn.equals("blue") && !red[9][5] && !blue[9][5]){
                    blue[9][5] = true;
                    button105.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[9][5] && !red[9][5]){
                    red[9][5] = true;
                    button105.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;

            case R.id.button107:
                if(turn.equals("blue") && !red[9][7] && !blue[9][7]){
                    blue[9][7] = true;
                    button107.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[9][7] && !red[9][7]){
                    red[9][7] = true;
                    button107.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;

            case R.id.button109:
                if(turn.equals("blue") && !red[9][9] && !blue[9][9]){
                    blue[9][9] = true;
                    button109.setBackgroundColor(Color.BLUE);
                }
                else if(turn.equals("red") && !blue[9][9] && !red[9][9]){
                    red[9][9] = true;
                    button109.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;

            case R.id.button113:
                if(turn.equals("red") && !blue[10][2] && !red[10][2]){
                    red[10][2] = true;
                    button113.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;

            case R.id.button115:
                if(turn.equals("red") && !blue[10][4] && !red[10][4]){
                    red[10][4] = true;
                    button115.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;

            case R.id.button117:
                if(turn.equals("red") && !blue[10][6] && !red[10][6]){
                    red[10][6] = true;
                    button117.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;

            case R.id.button119:
                if(turn.equals("red") && !blue[10][8] && !red[10][8]){
                    red[10][8] = true;
                    button119.setBackgroundColor(Color.RED);
                }
                else invalid_move = true;
                break;
            default:
                invalid_move = true;
                break;
        }
        if(!invalid_move){
        if(turn.equals("blue")){
            turn = "red";
            turnTextbox.setText("Red's turn");
            turnTextbox.setTextColor(Color.RED);
            if(cpu == 1){
                makeCPUMove();
                turn = "blue";
                turnTextbox.setText("Blue's turn");
                turnTextbox.setTextColor(Color.BLUE);
                }
        }
        else {turn = "blue";
         turnTextbox.setText("Blue's turn");
         turnTextbox.setTextColor(Color.BLUE);
        }
        VictoryBlue();
        VictoryRed();
        }

    }
}
