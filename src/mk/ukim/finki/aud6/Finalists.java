package mk.ukim.finki.aud6;

import java.util.*;

public class Finalists {

    public static List<Integer> pick(int finalists, int awards){
        List<Integer> list = new ArrayList<> ();
        List<Integer> list1 = new ArrayList<> ();
        Random random = new Random ();

        while (list.size () != awards){
            int n = random.nextInt (finalists) + 1;
            if(!list.contains (n)) {
                list.add (n);
                System.out.println (n);
            }
        }

        return list;
    }

    public static void main(String[] args) {
        pick (5, 3);
    }

}
