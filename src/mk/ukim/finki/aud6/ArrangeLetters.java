package mk.ukim.finki.aud6;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrangeLetters {
    public static String arrange(String str){
        String[] parts = str.split (" ");

//        for(int i=0; i<parts.length; i++){
//            char[] p = parts[i].toCharArray ();
//            Arrays.sort (p);
//            parts[i] = new String (p);
//        }
//        return Arrays.stream (parts).sorted ().collect (Collectors.joining(" "));



//        return IntStream.range (0, parts.length)
//                .mapToObj (i ->
//                {
//                    char[] p = parts[i].toCharArray ();
//                    Arrays.sort (p);
//                    return new String (p);
//                }).sorted ().collect (Collectors.joining(" "));

        return Arrays.stream (parts).map (part ->
        {
            char[] c = part.toCharArray ();
            Arrays.sort (c);
            return new String(c);
        }).sorted ().collect (Collectors.joining (" "));



    }


    public static void main(String[] args) {
        System.out.println (arrange ("kO pSk sO"));
    }


}

