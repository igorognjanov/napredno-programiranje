package mk.ukim.finki.aud4;

import java.io.*;
import java.util.Comparator;

class Person implements Comparable<Person>{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String line){
        String[] words = line.split ("\\s+");
        this.age = Integer.parseInt (words[1]);
        this.name = words[0];
    }



    @Override
    public String toString() {
        return "Person: " + name + "\n" +
                "Age: " + age + "\n";
    }

    @Override
    public int compareTo(Person that) {
        if(this.age == that.age) return 0;
        if(this.age < (that.age)) return -1;
        return 1;
    }
}

public class OldestPerson {

    public static Person getOldestPerson(InputStream input){
        BufferedReader bufferedReader = new BufferedReader (new InputStreamReader (input));
        Person oldestPerson = bufferedReader.lines ()
                .map (line -> new Person (line)).max (Comparator.naturalOrder ()).get ();

        return oldestPerson;
    }

    public static void main(String[] args) {
        File file = new File ("C:\\Users\\Igor\\IdeaProjects\\NP2020\\src\\mk\\ukim\\finki\\aud4\\Dat");
        try {
            System.out.println (getOldestPerson (new FileInputStream (file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        }
    }
}
