package com.kore.instructor.Visitor;

import java.util.ArrayList;
import android.content.res.Resources;

import com.kore.instructor.*;

public class Basic implements VisitorI
{
    protected ArrayList<Instruction> instructions;

    protected Resources resources;

    public Basic(Resources resources)
    {
        this.instructions = new ArrayList<Instruction>();
        this.resources    = resources;
    }

    public ArrayList<Instruction> getInstructions()
    {
        return this.instructions;
    }

    public void startTraing(Training training)
    {
        this.instructions.add(
            new Instruction(
                this.resources.getString(R.string.prepare),
                this.resources.getQuantityString(R.plurals.prepare_long, 10, 10),
                10
            )
        );
    }

    public void endTraing(Training training)
    {
        this.instructions.add(
            new Instruction(
                this.resources.getString(R.string.finished),
                this.resources.getString(R.string.finished_long),
                0
            )
        );
    }

    public void startPracticeIteration(PracticeIteration iteration)
    {
    }

    public void endPracticeIteration(PracticeIteration iteration)
    {
    }

    public void startPractice(Practice practice, int number)
    {
        this.instructions.add(
            new Instruction(
                this.resources.getString(R.string.practice),
                this.resources.getQuantityString(R.plurals.practice_long, number, number),
                0
            )
        );
    }

    public void endPractice(Practice practice, int number)
    {
    }

    public void startUnitIteration(UnitIteration iteration)
    {
    }

    public void endUnitIteration(UnitIteration iteration)
    {
    }

    public void startUnit(Unit unit, int number)
    {
        this.instructions.add(
            new Instruction(
                this.resources.getQuantityString(R.plurals.unit, number, number),
                this.resources.getQuantityString(R.plurals.unit_long, number, number),
                unit.time
            )
        );
    }

    public void endUnit(Unit unit, int number)
    {
        if (unit.pause > 0)
        {
            this.instructions.add(
                new Instruction(
                    this.resources.getString(R.string.pause),
                    this.resources.getQuantityString(R.plurals.pause_long, (int) unit.pause, (int) unit.pause),
                    unit.pause
                )
            );
        }
    }
}
