package com.kore.instructor;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.os.Bundle;
import android.os.CountDownTimer;

public class MyFirstActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void startCountdown(View view)
    {
       final TextView countdown = (TextView) findViewById(R.id.countdown);

       new CountDownTimer(5000, 100) {
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
                countdown.setText("00:00:00.00");
            }
       }.start();
    }
}
