package mk.ukim.finki.aud7.BirthdayParadox;

import java.util.HashSet;
import java.util.Random;
import java.util.stream.IntStream;

public class Birthdays {




    public static boolean experiment(int n){
        HashSet<Integer> set = new HashSet<> ();
        Random random = new Random ();
        int counter = 0;

        for(int i=0; i<n; i++){
            int birthday = random.nextInt (365) + 1;
            if(set.contains (birthday)){
                counter++;
            }
            else
                set.add (birthday);
        }
        return counter>0;
    }

    public static double getProbability(int n){
//        int counter = 0;
//        for(int i=0; i<100000; i++){
//            if(experiment (n))
//                counter++;
//        }
//
//        return counter / 100000.0;
         return IntStream.range (0, 100000).filter (i -> experiment (n)).count () / 100000.0;
    }

    public static void main(String[] args) {

        for(int n = 2; n<=50; n++)
            System.out.println (String.format ("For %d people, the probability for two birtdays is about %.4f",
                    n,
                    getProbability (n)));
    }


}
