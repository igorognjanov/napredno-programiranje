package Exercises.VtorKolovium.UnikatniIminja;


import java.util.*;
import java.util.stream.Collectors;

class Names{

    Map<String, Integer> numberOfNames;
    Map<String, Integer> charactersInName;

    public Names() {
        numberOfNames = new TreeMap<> ();
        charactersInName = new HashMap<> ();
    }

    public void addName(String name){
        numberOfNames.putIfAbsent (name, 0);
        numberOfNames.put (name, numberOfNames.get (name) + 1);


        charactersInName.putIfAbsent (name, getUniqueCharachters (name));
    }

    public int getUniqueCharachters(String name){
        HashSet<Character> set = new HashSet<> ();
        for(int i=0; i<name.length (); i++){
            set.add (Character.toLowerCase (name.charAt (i)));
        }
        return set.size ();
    }

    public void printN(int n){
        numberOfNames.keySet ().stream ()
                .filter (name -> numberOfNames.get (name) >= n)
                .forEach (name -> System.out.println (name + " (" +
                        numberOfNames.get (name) + ") "
                        + charactersInName.get (name)));
    }

    public String findName(int len, int x){
        List<String> list = numberOfNames.keySet ().stream ()
                .filter (name -> name.length () < len)
                .collect(Collectors.toList());
        return list.get (x % list.size ());
    }
}


public class NamesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        Names names = new Names();
        for (int i = 0; i < n; ++i) {
            String name = scanner.nextLine();
            names.addName(name);
        }
        n = scanner.nextInt();
        System.out.printf("===== PRINT NAMES APPEARING AT LEAST %d TIMES =====\n", n);
        names.printN(n);
        System.out.println("===== FIND NAME =====");
        int len = scanner.nextInt();
        int index = scanner.nextInt();
        System.out.println(names.findName(len, index));
        scanner.close();

    }
}

// vashiot kod ovde