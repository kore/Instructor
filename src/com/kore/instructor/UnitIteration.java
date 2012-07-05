package com.kore.instructor;

public class UnitIteration
{
    protected Unit unit;

    protected int count;

    public UnitIteration(Unit unit, int count)
    {
        this.unit  = unit;
        this.count = count;
    }

    public void accept(VisitorI visitor)
    {
        int i = 0;

        visitor.startUnitIteration(this);
        for (i = 0; i < count; ++i)
        {
            this.unit.accept(visitor, i + 1);
        }
        visitor.endUnitIteration(this);
    }
}
