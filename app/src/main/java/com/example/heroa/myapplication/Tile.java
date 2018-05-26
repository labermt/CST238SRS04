package com.example.heroa.myapplication;
enum State {PLAYERONE, PLAYERTWO, EMPTY};
public class Tile
{
    State state = State.EMPTY;
    boolean checked = false;

    Tile()
    {

    }


    public void SetState(State newState)
    {
        state = newState;
    }

    public State GetState()
    {
        return state;
    }

    public void SetChecked(boolean newChecked)
    {
        checked = newChecked;
    }

    public boolean GetChecked()
    {
        return checked;
    }
}
