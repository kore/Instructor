package com.kore.instructor;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.kore.instructor.Visitor.*;

public class Main extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    protected void run(Training training)
    {
        VisitorI visitor = new TTS();
        Countdown countdown = new Countdown(
            (TextView) findViewById(R.id.countdown),
            (TextView) findViewById(R.id.status)
        );

        training.accept(visitor);
        countdown.start(visitor.getInstructions());
    }

    public void startSteps(View view)
    {
        run(
            new Training(
                new PracticeIteration(
                    new Practice(
                        new UnitIteration(
                            new Unit( 7.5 * 60 ),
                            1
                        )
                    ),
                    4
                )
            )
        );
    }

    public void startInterval(View view)
    {
        run(
            new Training(
                new PracticeIteration(
                    new Practice(
                        new UnitIteration(
                            new Unit( 3 * 60 ),
                            3
                        )
                    ),
                    4
                )
            )
        );
    }

    public void startSuperset(View view)
    {
        run(
            new Training(
                new PracticeIteration(
                    new Practice(
                        new UnitIteration(
                            new Unit( 4 * 60 ),
                            1
                        )
                    ),
                    6
                )
            )
        );
    }

    public void startCircuit(View view)
    {
        run(
            new Training(
                new PracticeIteration(
                    new Practice(
                        new UnitIteration(
                            new Unit( 20 * 60 ),
                            1
                        )
                    ),
                    1
                )
            )
        );
    }

    public void startIntense(View view)
    {
        run(
            new Training(
                new PracticeIteration(
                    new Practice(
                        new UnitIteration(
                            new Unit( 20, 10 ),
                            8
                        )
                    ),
                    3
                )
            )
        );
    }
}
