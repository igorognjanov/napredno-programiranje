package mk.ukim.finki.aud4.LongestPalindrome;

import javax.sound.sampled.Line;
import java.io.*;
import java.util.Comparator;
import java.util.function.Predicate;

public class PalindromeTester {

    public static void longestPalindrome(InputStream inputStream){
        BufferedReader bf = new BufferedReader (new InputStreamReader (inputStream));
        System.out.println (bf.lines ()
                .map (String::toLowerCase)
                .filter (a -> isPalindrome (a))
                .max (Comparator.naturalOrder ()).map (a -> a.length ())

        );
    }

    public static boolean isPalindrome(String s){
        StringBuilder reverse = new StringBuilder ();
        reverse.append (s);
        reverse.reverse ();
        return s.equals (reverse.toString ());
    }

    public static void main(String[] args) {
        File file = new File ("C:\\Users\\Igor\\IdeaProjects\\NP2020\\src\\mk\\ukim\\finki\\aud4\\LongestPalindrome\\Words");
        try {
            longestPalindrome (new FileInputStream (file));
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        }
    }
}
