package com.kore.instructor;

public class Instruction
{
    public String hint;

    public String message;

    public double seconds;

    public Instruction(String hint, String message, double seconds)
    {
        this.hint    = hint;
        this.message = message;
        this.seconds = seconds;
    }
}

