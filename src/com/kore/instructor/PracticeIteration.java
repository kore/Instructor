package com.kore.instructor;

public class PracticeIteration
{
    protected Practice practice;

    protected int count;

    public PracticeIteration(Practice practice, int count)
    {
        this.practice = practice;
        this.count    = count;
    }

    public void accept(VisitorI visitor)
    {
        int i = 0;

        visitor.startPracticeIteration(this);
        for (i = 0; i < count; ++i)
        {
            this.practice.accept(visitor, i + 1);
        }
        visitor.endPracticeIteration(this);
    }
}
