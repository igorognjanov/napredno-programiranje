package Exercises.PrvKolokvium.Triple;

import java.util.*;

public class TripleTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        Triple<Integer> tInt = new Triple<Integer>(a, b, c);
        System.out.printf("%.2f\n", tInt.max());
        System.out.printf("%.2f\n", tInt.avarage());
        tInt.sort();
        System.out.println(tInt);
        float fa = scanner.nextFloat();
        float fb = scanner.nextFloat();
        float fc = scanner.nextFloat();
        Triple<Float> tFloat = new Triple<Float>(fa, fb, fc);
        System.out.printf("%.2f\n", tFloat.max());
        System.out.printf("%.2f\n", tFloat.avarage());
        tFloat.sort();
        System.out.println(tFloat);
        double da = scanner.nextDouble();
        double db = scanner.nextDouble();
        double dc = scanner.nextDouble();
        Triple<Double> tDouble = new Triple<Double>(da, db, dc);
        System.out.printf("%.2f\n", tDouble.max());
        System.out.printf("%.2f\n", tDouble.avarage());
        tDouble.sort();
        System.out.println(tDouble);
    }
}
// vasiot kod ovde
class Triple<T extends Number & Comparable<T>>{
    private List<T> n;

    public Triple(T n1, T n2, T n3){
        n = new ArrayList<> ();
        n.add (n1);
        n.add (n2);
        n.add (n3);
    }

    public DoubleSummaryStatistics getDDS(){
        DoubleSummaryStatistics doubleSummaryStatistics = new DoubleSummaryStatistics ();
        for(int i=0; i<3; i++)
            doubleSummaryStatistics.accept (n.get (i).doubleValue ());
        return doubleSummaryStatistics;
    }

    public double max(){
        return getDDS ().getMax ();
    }

    public double avarage(){
        return getDDS ().getAverage ();
    }

    public void sort(){
        Collections.sort (n);
    }

    public String toString(){
        return String.format ("%.2f %.2f %.2f",
                n.get (0).doubleValue (),
                n.get (1).doubleValue (),
                n.get (2).doubleValue ()
        );
    }
}


