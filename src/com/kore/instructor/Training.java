package com.kore.instructor;

public class Training
{
    protected PracticeIteration iteration;

    public Training(PracticeIteration iteration)
    {
        this.iteration = iteration;
    }

    public void accept(VisitorI visitor)
    {
        visitor.startTraing(this);
        this.iteration.accept(visitor);
        visitor.endTraing(this);
    }
}
