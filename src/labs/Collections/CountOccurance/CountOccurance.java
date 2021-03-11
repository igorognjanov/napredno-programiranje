package labs.Collections.CountOccurance;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import java.util.Map.Entry;

class TermFrequency{

    TreeMap<String, Integer> countByWords;
    TreeSet<String> set;
    private int n;


    public String Trim(String word){
        return word.replaceAll ("[ ,.!]", "");
    }

    public TermFrequency(InputStream inputStream, String[] ignoreWords){
        List<String> ignore = Arrays.asList (ignoreWords);
        countByWords = new TreeMap<> ();
        n=0;
        set = new TreeSet<> (this::comp);

        BufferedReader bufferedReader = new BufferedReader (new InputStreamReader (inputStream));
        bufferedReader.lines ().map (line -> line.split ("\\s+"))
                .forEach (Word -> Arrays.stream (Word)
                        .filter (word -> !ignore
                                .contains (Trim (word.toLowerCase ())))
                        .map (this::Trim).forEach (word -> {
                            countByWords.putIfAbsent (word, 0);
                            countByWords.replace (word, countByWords.get (word) + 1);
                            set.add (word);
                        }));

    }

    public int comp(String a, String b){
        return Integer.compare (countByWords.get (a), countByWords.get (b));
    }

    public int countTotal(){
        countByWords.values ().stream().forEach (num -> {
            n+=num;
        });
        return n;
    }

    public int countDistinct(){
        return (int) countByWords.keySet ().stream ().count ();
    }

    public List<String> mostOften(int n){
        return set.stream ().limit (n).collect(Collectors.toList());
    }
}


class TermFrequencyTest {
    public static void main(String[] args) throws FileNotFoundException {
        String[] stop = new String[] { "во", "и", "се", "за", "ќе", "да", "од",
                "ги", "е", "со", "не", "тоа", "кои", "до", "го", "или", "дека",
                "што", "на", "а", "но", "кој", "ја" };
        TermFrequency tf = new TermFrequency(System.in,
                stop);
        System.out.println(tf.countTotal());
        System.out.println(tf.countDistinct());
        System.out.println(tf.mostOften(10));
    }
}
// vasiot kod ovde
