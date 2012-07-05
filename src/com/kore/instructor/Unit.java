package com.kore.instructor;

public class Unit
{
    public double time;

    public double pause;

    public Unit(double time)
    {
        this.time = time;
    }

    public Unit(double time, double pause)
    {
        this.time  = time;
        this.pause = pause;
    }

    public void accept(VisitorI visitor, int number)
    {
        visitor.startUnit(this, number);
        visitor.endUnit(this, number);
    }
}
