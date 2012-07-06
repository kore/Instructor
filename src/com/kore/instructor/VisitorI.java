package com.kore.instructor;

import java.util.ArrayList;

public interface VisitorI
{
    public ArrayList<Instruction> getInstructions();

    public void startTraing(Training training);

    public void endTraing(Training training);

    public void startPracticeIteration(PracticeIteration iteration);

    public void endPracticeIteration(PracticeIteration iteration);

    public void startPractice(Practice practice, int number);

    public void endPractice(Practice practice, int number);

    public void startUnitIteration(UnitIteration iteration);

    public void endUnitIteration(UnitIteration iteration);

    public void startUnit(Unit unit, int number);

    public void endUnit(Unit unit, int number);
}
