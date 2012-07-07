package com.kore.instructor;

import java.util.ArrayList;
import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.graphics.Typeface;
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

        this.countdown = (Countdown) getLastNonConfigurationInstance();

        this.talker = new TextToSpeech(this, this);

        TextView countdown = (TextView) findViewById(R.id.countdown);
        Typeface font = Typeface.createFromAsset(getAssets(), "lcdmn.ttf");
        countdown.setTypeface(font);

        if (this.countdown != null)
        {
            this.countdown.setAll(
                countdown,
                (TextView) findViewById(R.id.status),
                this.talker
            );
        }

        this.updateViewState(this.countdown != null);
    }

    protected void run(Training training)
    {
        VisitorI visitor = new Basic(getResources());
        this.countdown = new Countdown(this.getAnnouncements());

        this.countdown.setAll(
            (TextView) findViewById(R.id.countdown),
            (TextView) findViewById(R.id.status),
            this.talker
        );


        training.accept(visitor);
        countdown.start(visitor.getInstructions());
        this.updateViewState(true);
    }

    protected ArrayList<Announcement> getAnnouncements()
    {
        ArrayList<Announcement> announcements = new ArrayList<Announcement>();

        announcements.add(new Announcement(
            getResources().getQuantityString(R.plurals.minutes, 5, 5),
            5 * 60 * 1000
        ));
        announcements.add(new Announcement(
            getResources().getQuantityString(R.plurals.minutes, 2, 2),
            2 * 60 * 1000
        ));
        announcements.add(new Announcement(
            getResources().getQuantityString(R.plurals.minutes, 1, 1),
            1 * 60 * 1000
        ));
        announcements.add(new Announcement(
            getResources().getQuantityString(R.plurals.seconds, 30, 30),
            30 * 1000
        ));
        announcements.add(new Announcement(
            getResources().getQuantityString(R.plurals.seconds, 10, 10),
            10 * 1000
        ));

        return announcements;
    }

    protected void updateViewState(boolean running)
    {
        if (running)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        else
        {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }

        findViewById(R.id.button_steps).setVisibility(running ? View.GONE : View.VISIBLE);
        findViewById(R.id.button_interval).setVisibility(running ? View.GONE : View.VISIBLE);
        findViewById(R.id.button_superset).setVisibility(running ? View.GONE : View.VISIBLE);
        findViewById(R.id.button_circuit).setVisibility(running ? View.GONE : View.VISIBLE);
        findViewById(R.id.button_intense).setVisibility(running ? View.GONE : View.VISIBLE);

        findViewById(R.id.button_cancel).setVisibility(running ? View.VISIBLE : View.GONE);
    }

    @Override
    public Object onRetainNonConfigurationInstance()
    {
        return countdown;
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
            this.countdown = null;
        }

        this.updateViewState(false);
    }

    public void startSteps(View view)
    {
        run(
            new Training(
                new PracticeIteration(
                    new Practice(
                        new UnitIteration(
                            new Unit( 7.5 * 60, 30 ),
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
