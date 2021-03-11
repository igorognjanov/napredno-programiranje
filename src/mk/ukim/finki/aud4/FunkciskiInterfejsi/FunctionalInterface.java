package mk.ukim.finki.aud4.FunkciskiInterfejsi;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterface {
    public static void main(String[] args) {
        Consumer<Integer> integerConsumer = integer -> {
            integer *= integer;
            System.out.println (integer);
        };
        integerConsumer.accept (20);
        Supplier<Integer> integerSupplier = () -> {
            Random random = new Random ();
            return Math.abs (random.nextInt () % 100 + 1);
        };
        System.out.println (integerSupplier.get ());
        Predicate<Integer> integerPredicate = integer -> integer < 10;
        Function<Integer, Double> integerDoubleFunction = new Function<Integer, Double> () {
            @Override
            public Double apply(Integer integer) {
                return (double) integer + 0.5;
            }
        };
        System.out.println (integerDoubleFunction.apply (20));
        System.out.println (integerPredicate.test (20));
    }

}
