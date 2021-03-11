package mk.ukim.finki.aud7.Maps;

import java.util.*;

public class MapsExample {
    public static void main(String[] args) {
        Map<String, String> hashMap = new HashMap<> ();

        hashMap.put ("igoasdasdasr", "kral");
        hashMap.put ("ognjanov", "12345678910");
        hashMap.put ("Asus", "1234567891");
        hashMap.put ("ognjanov", "900099009");

        System.out.println (hashMap);

        Map<String, String> linkedHashlMap = new LinkedHashMap<> ();

        linkedHashlMap.put ("Igor", "FinkiUkimMK");
        linkedHashlMap.put ("Ognjanov", "123234");
        linkedHashlMap.put ("Igor", "asdasd");
        linkedHashlMap.put ("a", "ASDASD");
        System.out.println (linkedHashlMap);

        Map<String, String> treeMap = new TreeMap<> (Comparator.comparing (String::length).thenComparing (t -> t));


        treeMap.put ("Igor", "FinkiUkimMK");
        treeMap.put ("Ognjanov", "123234");
        treeMap.put ("Igor", "asdasd");
        treeMap.put ("a", "ASDASD");

        System.out.println (treeMap);
    }
}
