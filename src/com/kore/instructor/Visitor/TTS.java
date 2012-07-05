package com.kore.instructor.Visitor;

import android.widget.TextView;
import android.os.CountDownTimer;
import com.kore.instructor.*;

public class TTS implements VisitorI
{
    protected TextView countdown;

    protected TextView status;

    public TTS(TextView countdown, TextView status)
    {
        this.countdown = countdown;
        this.status    = status;
    }

    protected void start(String hint, String message, double seconds)
    {
        final TextView countdown = this.countdown;

        status.setText(hint);
        new CountDownTimer((int) seconds * 1000, 100)
        {
            public void onTick(long remaining)
            {
                countdown.setText(
                    String.format(
                        "%02d:%02d:%02d.%02d",
                        remaining / 1000 / 60 / 60 % 24,
                        remaining / 1000 / 60 % 60,
                        remaining / 1000 % 60,
                        remaining / 10 % 100
                    )
                );
            }

            public void onFinish()
            {
                
            }
        }.start();

        // We need to sync somehow with timer end
    }

    public void startTraing(Training training)
    {
        this.start("Prepare", "Traing will start in 10 seconds - prepare!", 10);
    }

    public void endTraing(Training training)
    {
        this.start("Finished", "Done for today", 0);
    }

    public void startPracticeIteration(PracticeIteration iteration)
    {
    }

    public void endPracticeIteration(PracticeIteration iteration)
    {
    }

    public void startPractice(Practice practice, int number)
    {
        this.start("Practice", "Start practice " + number, 0);
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
        this.start("Training", "Start unit " + number, unit.time);
        this.start("Pause", "Pause for " + number + " seconds", unit.pause);
    }

    public void endUnit(Unit unit, int number)
    {
    }
}
