package mk.ukim.finki.aud7.Sets;

import java.util.*;
import java.util.function.Function;

public class SetsExapmle {
    public static void main(String[] args) {
        Set<String> hashSet = new HashSet<String> ();
        hashSet.add ("Igor");
        hashSet.add ("Ognjanov");
        hashSet.add ("Igor");
        hashSet.add ("Finki");
        hashSet.add ("a");
        hashSet.add ("z");

        System.out.println (hashSet);


        Set<String> linkedHashSet = new LinkedHashSet<> ();
        linkedHashSet.add ("Igor");
        linkedHashSet.add ("Ognjanov");
        linkedHashSet.add ("Igor");
        linkedHashSet.add ("FINKI UKIM MK");
        linkedHashSet.add ("a");
        linkedHashSet.add ("z");

        System.out.println (linkedHashSet);


        TreeSet<String> treeSet = new TreeSet<String>(Comparator.comparing (String::length)
        .thenComparing (Function.identity ()));
        treeSet.add ("Igot");
        treeSet.add ("Ognjanov");
        treeSet.add ("a");
        treeSet.add ("z");
        treeSet.add ("A");
        treeSet.add ("Igor");

        System.out.println (treeSet);
    }
}
