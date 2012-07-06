package com.kore.instructor.Visitor;

import java.util.ArrayList;

import com.kore.instructor.*;

public class English implements VisitorI
{
    protected ArrayList<Instruction> instructions;

    public English()
    {
        this.instructions = new ArrayList<Instruction>();
    }

    public ArrayList<Instruction> getInstructions()
    {
        return this.instructions;
    }

    public void startTraing(Training training)
    {
        this.instructions.add(new Instruction("Prepare", "Traing will start in 10 seconds - prepare!", 10));
    }

    public void endTraing(Training training)
    {
        this.instructions.add(new Instruction("Finished", "Done for today", 0));
    }

    public void startPracticeIteration(PracticeIteration iteration)
    {
    }

    public void endPracticeIteration(PracticeIteration iteration)
    {
    }

    public void startPractice(Practice practice, int number)
    {
        this.instructions.add(new Instruction("Practice", "Start practice " + number, 0));
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
        this.instructions.add(new Instruction("Unit " + number, "Start unit " + number, unit.time));
        this.instructions.add(new Instruction("Pause", "Pause for " + unit.pause + " seconds", unit.pause));
    }

    public void endUnit(Unit unit, int number)
    {
    }
}
