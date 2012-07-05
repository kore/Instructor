package com.kore.instructor;

public class Practice
{
    protected UnitIteration iteration;

    public Practice(UnitIteration iteration)
    {
        this.iteration = iteration;
    }

    public void accept(VisitorI visitor, int number)
    {
        visitor.startPractice(this, number);
        this.iteration.accept(visitor);
        visitor.endPractice(this, number);
    }
}
