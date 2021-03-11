package labs.Collections.GrupaAnagrami;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.security.cert.CollectionCertStoreParameters;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Anagrams {

    public static void main(String[] args) {
        findAll(System.in);
    }


    public static void findAll(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader (new InputStreamReader (inputStream));
        Comparator<List<Character>> comparator = (left, right) -> {
            StringBuilder sbl = new StringBuilder ();
            for (Character value : left) sbl.append (value);
            StringBuilder sbr = new StringBuilder ();
            for (Character character : right) sbr.append (character);
            return sbl.toString ().compareTo (sbr.toString ());
        };




        LinkedHashMap<List<Character>, TreeSet<String>> map = new LinkedHashMap<> ();


        bufferedReader.lines ().forEach (line -> {
            line = line.trim ();
            List<Character> chars = new ArrayList<> ();
            for(int i=0; i<line.length (); i++){
                chars.add (line.charAt (i));
            }

            chars = chars.stream ().sorted ().collect (Collectors.toList ());

            map.putIfAbsent (chars, new TreeSet<> ());
            map.get (chars).add (line);
        });

        map.values ().stream ()
                .filter (key -> key.size () >= 5)
                .forEach (set -> {
                    List<String> list = new ArrayList<> (set);
                    for(int i=0; i<list.size (); i++){
                        if(i != list.size ()-1)
                            System.out.print (list.get (i) + " ");
                        else System.out.println (list.get (i));
                    }
                });

    }




}
