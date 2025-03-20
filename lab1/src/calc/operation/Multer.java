package calc.operation;

public class Multer
{
    private int mult;

    public Multer()
    {
        mult = 1;
    }

    public Multer(int a)
    {
        this.mult = a;
    }

    public void mult(int b)
    {
        mult *= b;
    }

    public int getMult()
    {
        return mult;
    }
}
