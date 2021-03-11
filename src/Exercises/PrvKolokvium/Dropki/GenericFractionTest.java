package Exercises.PrvKolokvium.Dropki;

import java.util.Scanner;

public class GenericFractionTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double n1 = scanner.nextDouble();
        double d1 = scanner.nextDouble();
        float n2 = scanner.nextFloat();
        float d2 = scanner.nextFloat();
        int n3 = scanner.nextInt();
        int d3 = scanner.nextInt();
        try {
            GenericFraction<Double, Double> gfDouble = new GenericFraction<Double, Double>(n1, d1);
            GenericFraction<Float, Float> gfFloat = new GenericFraction<Float, Float>(n2, d2);
            GenericFraction<Integer, Integer> gfInt = new GenericFraction<Integer, Integer>(n3, d3);
            System.out.printf("%.2f\n", gfDouble.toDouble());
            System.out.println(gfDouble.add(gfFloat));
            System.out.println(gfInt.add(gfFloat));
            System.out.println(gfDouble.add(gfInt));
            gfInt = new GenericFraction<Integer, Integer>(n3, 0);
        } catch(ZeroDenominatorException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }

}

// вашиот код овде
class GenericFraction<T extends Number, U extends Number>{
    private T numerator;
    private U denumerator;

    public GenericFraction(T numerator, U denumerator) throws ZeroDenominatorException{
        if(denumerator.equals (0)) throw new ZeroDenominatorException();
        this.denumerator = denumerator;
        this.numerator = numerator;
    }

    public GenericFraction<Double, Double> add(GenericFraction<? extends Number, ? extends Number> gf){
        try {
            double num1 = numerator.doubleValue () * gf.denumerator.doubleValue ();
            double num2 = gf.numerator.doubleValue () * denumerator.doubleValue ();
            double den = gf.denumerator.doubleValue () * denumerator.doubleValue ();
            return new GenericFraction<Double, Double>
                    (num1 + num2, den).divide ();
        } catch (ZeroDenominatorException e) {
            System.out.println (e.getMessage ());
        }
        return null;
    }

    public double toDouble(){
        return numerator.doubleValue () /  denumerator.doubleValue ();
    }

    public String toString(){
        return String.format ("%.2f / %.2f", numerator.doubleValue (), denumerator.doubleValue ());
    }

    public GenericFraction<Double, Double> divide(){
        for(int i=(int)Math.min (numerator.doubleValue (), denumerator.doubleValue ()); i>=2; i--){
            if(numerator.doubleValue () % i == 0 && denumerator.doubleValue () % i == 0){
                try {
                    return new GenericFraction<> (numerator.doubleValue ()/i, denumerator.doubleValue ()/i);
                } catch (ZeroDenominatorException e) {
                    e.printStackTrace ();
                }
                break;
            }
        }
        try {
            return new GenericFraction<> (numerator.doubleValue (), denumerator.doubleValue ());
        } catch (ZeroDenominatorException e) {
            e.printStackTrace ();
        }
        return null;
    }

}

class ZeroDenominatorException extends Exception{

    public String getMessage(){
        return "Denominator cannot be zero";
    }
}
