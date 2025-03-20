package calc.operation;

public class Subtracter
{
    private int minus;

    public Subtracter()
    {
        minus = 0;
    }

    public Subtracter(int a)
    {
        this.minus = a;
    }

    public void min(int b)
    {
        minus -= b;
    }

    public int getSubtracter()
    {
        return minus;
    }
}
