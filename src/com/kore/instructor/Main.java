package com.kore.instructor;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;

import com.kore.instructor.Visitor.*;

public class Main extends Activity implements OnInitListener
{
    protected TextToSpeech talker;

    protected Countdown countdown;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    protected void run(Training training)
    {
        this.talker      = new TextToSpeech(this, this);
        VisitorI visitor = new English();
        this.countdown   = new Countdown(
            (TextView) findViewById(R.id.countdown),
            (TextView) findViewById(R.id.status),
            this.talker
        );

        findViewById(R.id.button_steps).setVisibility(View.GONE);
        findViewById(R.id.button_interval).setVisibility(View.GONE);
        findViewById(R.id.button_superset).setVisibility(View.GONE);
        findViewById(R.id.button_circuit).setVisibility(View.GONE);
        findViewById(R.id.button_intense).setVisibility(View.GONE);

        findViewById(R.id.button_cancel).setVisibility(View.VISIBLE);

        training.accept(visitor);
        countdown.start(visitor.getInstructions());
    }

    public void onInit(int status)
    {
        // Do nothing
    }


    public void onDestroy()
    {
        if (this.talker != null)
        {
            this.talker.stop();
            this.talker.shutdown();
        }

        super.onDestroy();
    }

    public void cancelTraining(View view)
    {
        if (this.talker != null)
        {
            this.talker.stop();
        }

        if (this.countdown != null)
        {
            this.countdown.stop();
        }

        findViewById(R.id.button_steps).setVisibility(View.VISIBLE);
        findViewById(R.id.button_interval).setVisibility(View.VISIBLE);
        findViewById(R.id.button_superset).setVisibility(View.VISIBLE);
        findViewById(R.id.button_circuit).setVisibility(View.VISIBLE);
        findViewById(R.id.button_intense).setVisibility(View.VISIBLE);

        findViewById(R.id.button_cancel).setVisibility(View.GONE);
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
