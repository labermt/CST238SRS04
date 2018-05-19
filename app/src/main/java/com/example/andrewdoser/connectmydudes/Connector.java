package com.example.andrewdoser.connectmydudes;

import android.content.Context;
import android.widget.Button;

public class Connector {

    public Button butt;

    public boolean butUp = false;
    public boolean butDown = false;
    public boolean butLeft = false;
    public boolean butRight = false;


    public Connector(Context c) {
        butt = new Button(c);
    }
}
