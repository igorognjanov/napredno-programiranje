package mk.ukim.finki.aud3.zadaca2;

public class Calculator {
    private double result;
    private Strategy s;

    public Calculator(){
        this.result = 0.00;
    }

    public String execute(char a, double value) throws UknownOperationException{
        if(a == '+') s = new Addition ();
        else if(a == '-') s = new Subtraction ();
        else if(a == '/') s = new Divide ();
        else if(a == '*') s = new Multiply ();
        else throw new UknownOperationException();

        result = s.operation (result, value);
        return String.format ("the new result is: %.2f", result);
    }

    public double getResult(){
        return result;
    }

    @Override
    public String toString(){
        return String.format ("result is: %.2f", result);
    }
}
