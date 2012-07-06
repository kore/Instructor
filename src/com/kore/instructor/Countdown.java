package com.kore.instructor;

import java.util.ArrayList;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;

public class Countdown
{
    protected TextView countdown;

    protected TextView status;

    protected TextToSpeech talker;

    protected long runTime;

    protected long interval;

    protected ArrayList<Instruction> instructions;

    protected Handler handler;

    protected static final int MSG = 1;

    public Countdown(TextView countdown, TextView status, TextToSpeech talker)
    {
        this.countdown = countdown;
        this.status    = status;
        this.talker    = talker;
        this.interval  = 10;
    }

    public void onTick(long remaining)
    {
        countdown.setText(
            String.format(
                "%02d:%02d.%02d",
                remaining / 1000 / 60 % 60,
                remaining / 1000 % 60,
                remaining / 10 % 100
            )
        );
    }

    public void start(ArrayList<Instruction> instructions)
    {
        this.instructions = instructions;
        this.nextInstruction();
    }

    public void stop()
    {
        if (this.handler != null)
        {
            this.handler.removeMessages(MSG);
            this.instructions = null;
        }
    }

    protected void nextInstruction()
    {
        if (this.instructions.size() <= 0)
        {
            return;
        }

        Instruction instruction = (Instruction) this.instructions.remove(0);
        this.startInstruction(instruction);
    }

    protected synchronized final Countdown startInstruction(Instruction instruction)
    {
        this.runTime = (long) instruction.seconds * 1000;
        this.status.setText(instruction.hint);
        this.talker.speak(instruction.message, TextToSpeech.QUEUE_FLUSH, null);

        if (this.runTime <= 0)
        {
            this.nextInstruction();
            return this;
        }

        final long stopTime = SystemClock.elapsedRealtime() + this.runTime;
        this.handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                synchronized (Countdown.this)
                {
                    final long timeLeft = stopTime - SystemClock.elapsedRealtime();

                    if (timeLeft <= 0)
                    {
                        nextInstruction();
                    }
                    else if (timeLeft < interval)
                    {
                        sendMessageDelayed(obtainMessage(MSG), timeLeft);
                    }
                    else
                    {
                        long lastTickStart = SystemClock.elapsedRealtime();
                        onTick(timeLeft);

                        long delay = lastTickStart + interval - SystemClock.elapsedRealtime();

                        // special case: user's onTick took more than interval to
                        // complete, skip to next interval
                        while (delay < 0) delay += interval;

                        sendMessageDelayed(this.obtainMessage(MSG), delay);
                    }
                }
            }
        };

        handler.sendMessage(handler.obtainMessage(MSG));

        return this;
    }
}

