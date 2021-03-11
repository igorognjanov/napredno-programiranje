package mk.ukim.finki.aud5;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class MathGenericsTest {
    public static String statistics(List<? extends Number> numbers){ // vraka min, max, sum, avg, std, count
        String str="";

        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        double sum = 0;
        double avg;
        double count = 0;

        for(Number n: numbers){
            if(min > n.doubleValue ()){
                min = n.doubleValue ();
            }
            if(max < n.doubleValue ())
                max = n.doubleValue ();
            sum += n.doubleValue ();
            count++;
        }
        DoubleSummaryStatistics doubleSummaryStatistics = new DoubleSummaryStatistics ();
        numbers.stream ().forEach (n -> doubleSummaryStatistics.accept (n.doubleValue ()));
        System.out.printf("Minimum: %.2f\n maximum: %.2f\n zbir: %.2f\n prosek: %.2f\n broj na elementi: %d\n",
                doubleSummaryStatistics.getMin (),doubleSummaryStatistics.getMax (), doubleSummaryStatistics.getSum (),
                doubleSummaryStatistics.getAverage (), doubleSummaryStatistics.getCount ());
        avg = sum / count;
        return String.format ("Minimum: %.2f\n maximum: %.2f\n zbir: %.2f\n prosek: %.2f\n broj na elementi: %.2f\n",
                min, max, sum, avg, count);

    }

    public static void main(String[] args) {
        List<Number> list = new ArrayList<> ();
        for(int i=1; i<20; i+=2){
            list.add (i);
        }
        System.out.println (statistics (list));
    }
}
