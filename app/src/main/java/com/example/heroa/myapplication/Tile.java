package com.example.heroa.myapplication;
enum State {PLAYERONE, PLAYERTWO, EMPTY};
public class Tile
{
    State state;
    boolean checked;

    void SetState(State newState)
    {
        state = newState;
    }

    State GetState()
    {
        return state;
    }

    void SetChecked(boolean newChecked)
    {
        checked = newChecked;
    }

    boolean GetChecked()
    {
        return checked;
    }
}

