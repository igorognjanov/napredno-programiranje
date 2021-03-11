package mk.ukim.finki.aud7.Names;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class Name{
    private String name;
    private int count;

    public Name (String line){
        String[] parts = line.split (" ");
        this.name = parts[0];
        this.count = Integer.parseInt (parts[1]);
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}

public class UnisexNames {

    public static HashMap<String, Integer> getMapFromFile(String filename){
        HashMap<String, Integer> hashMap = new HashMap<> ();
        try {
            BufferedReader bufferedReader = new BufferedReader (new FileReader (filename));
            bufferedReader.lines ().map(Name::new).forEach (name -> hashMap.put (name.getName (), name.getCount ()));
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        }
        return hashMap;
    }

    public static HashMap<String, Integer> getUnisexNames(HashMap<String, Integer> boynames, HashMap<String, Integer> girlnames){
        HashMap<String, Integer> result = new HashMap<> ();
        boynames.forEach ((name, count) -> {
            if(girlnames.containsKey (name))
                result.put (name, count);
        });
        return result;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> boys = getMapFromFile ("C:\\Users\\Igor\\IdeaProjects\\NP2020\\src\\mk\\ukim\\finki\\aud7\\Names\\boynames");
        HashMap<String, Integer> girls = getMapFromFile ("C:\\Users\\Igor\\IdeaProjects\\NP2020\\src\\mk\\ukim\\finki\\aud7\\Names\\girlnames");

        System.out.println (getUnisexNames (boys, girls).size ());
    }
}
