package mk.ukim.finki.aud4.FunkciskiInterfejsi;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ArrayListTest {

    public static void main(String[] args) {
        Predicate<Integer> integerPredicate = integer -> integer > 5;

        List<String> stringList = new ArrayList<> (200);
        stringList.add ("Igor");
        stringList.add (1, "Ognjanov");
        stringList.add (1, "kral");
        List<String> otherList = new ArrayList<> (10);
        otherList.addAll (stringList);
        otherList.add("SFDF");

        otherList.stream ()
                .map (string -> string + "kral")
                .filter (string -> string.length () < 5)
                .forEach (string -> System.out.println (string.length ()));

        System.out.println (stringList);
        System.out.println (otherList);
    }
}
