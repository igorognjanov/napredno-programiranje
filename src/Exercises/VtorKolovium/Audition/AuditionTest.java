package Exercises.VtorKolovium.Audition;


import java.util.*;

class Participant implements Comparable<Participant>{
    private String city;
    private String code;
    private String name;
    private int age;

    public Participant(String city, String code, String name, int age) {
        this.city = city;
        this.code = code;
        this.name = name;
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public String getCode() {
        return code;
    }

    public String toString(){
        return String.format ("%s %s %d", code, name, age);
    }

    public boolean equals(Participant p){
        return this.code.equals (p.code);
    }

    @Override
    public int compareTo(Participant o) {
        if(this.name.equals (o.name))
            if(this.age == o.age)
                return this.code.compareTo (o.code);
            else
                return Integer.compare (this.age, o.age);
        else
            return this.name.compareTo (o.name);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Participant that = (Participant) o;
        return code.equals (that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash (code);
    }
}

class Audition{
    HashMap<String, HashSet<Participant>> map;

    public Audition(){
        map = new HashMap<> ();
    }

    public void addParticipant(String city, String code, String name, int age){
        Participant participant = new Participant (city, code, name, age);

        map.computeIfAbsent (participant.getCity (), set -> new HashSet<> ()).add (participant);


//        if(map.containsKey (participant.getCity ())){
//            map.get (participant.getCity ()).add (participant);
//        }
//        else {
//            HashSet<Participant> participantTreeSet = new HashSet<Participant> ();
//            participantTreeSet.add (participant);
//            map.put (participant.getCity (), participantTreeSet);
//        }
    }

    public void listByCity(String city){
        map.get (city).stream ().sorted ().forEach (System.out::println);
    }


}

public class AuditionTest {
    public static void main(String[] args) {
        Audition audition = new Audition();
        List<String> cities = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length > 1) {
                audition.addParticipant(parts[0], parts[1], parts[2],
                        Integer.parseInt(parts[3]));
            } else {
                cities.add(line);
            }
        }
        for (String city : cities) {
            System.out.printf("+++++ %s +++++\n", city);
            audition.listByCity(city);
        }
        scanner.close();
    }
}
