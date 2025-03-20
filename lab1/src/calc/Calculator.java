package calc;

import calc.operation.*;

public class Calculator
{
    public int sum(int... a)
    {
        Adder adder = new Adder();

        for(int i:a)
        {
            adder.add(i);
        }

        return adder.getSum();
    }

    public int subtract(int... a)
    {
        Subtracter subtract = new Subtracter();

        for(int i:a)
        {
            subtract.min(i);
        }

        return subtract.getSubtracter();
    }

    public int mult(int... a)
    {
        Multer multer = new Multer();

        for(int i:a)
        {
            multer.mult(i);
        }

        return multer.getMult();
    }

    public float div(float a, float b)
    {
        Diver diver = new Diver();

        diver.div(a, b);

        return diver.getDiv();
    }
}
