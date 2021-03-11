package Exercises.Ispitni.LaboratoriskiVezbi;


import java.util.*;
import java.util.stream.Collectors;

class Student implements Comparable<Student>{
    private String index;
    List<Integer> poeni;

    public Student(String index, List<Integer> poeni) {
        this.index = index;
        this.poeni = poeni;
    }

    public String getIndex() {
        return index;
    }

    public List<Integer> getPoeni() {
        return poeni;
    }

    public double getAverage(){
        int counter = 0;
        for(Integer i : poeni){
            counter += i;
        }
        return counter / 10.0;
    }

    public String toString(){
        String ret = index;
        if(hasMoreThan2Asbences ()){
            ret += " NO ";
        }
        else ret += " YES ";
        return ret + String.format ("%.2f", getAverage ());
    }

    public boolean hasMoreThan2Asbences(){
        return poeni.size () < 8;
    }

    public int compareTo(Student that){
        if(this.getAverage () == that.getAverage ()){
            return this.index.compareTo (that.index);
        }
        return Double.compare (this.getAverage (), that.getAverage ());
    }
}

class LabExercises{

    List<Student> students;
    Map<Integer, HashSet<Student>> studentsByYear;

    public LabExercises() {
        this.students = new ArrayList<> ();
        studentsByYear = new HashMap<> ();
    }

    public void addStudent(Student student){
        students.add (student);
        String str = student.getIndex().substring (0, 2);

        if(!student.hasMoreThan2Asbences ()) {
            int year = 20 - (Integer.parseInt (str));
            studentsByYear.putIfAbsent (year, new HashSet<> ());
            studentsByYear.get (year).add (student);
        }
    }

    public void printByAveragePoints(boolean ascending, int n){
        if(ascending) {
            students.stream ()
                    .sorted ()
                    .limit (n)
                    .forEach (System.out::println);
        }
        else {
            students.stream ()
                    .sorted (Comparator.reverseOrder ())
                    .limit (n)
                    .forEach (System.out::println);
        }
    }

    public List<Student> failedStudents(){
        return students.stream ()
                .filter (Student::hasMoreThan2Asbences)
                .sorted (Comparator.comparing (Student::getIndex).thenComparing (Student::getAverage))
                .collect(Collectors.toList());
    }

    public Map<Integer,Double> getStatisticsByYear(){
        Map<Integer, Double> averagesByYear = new HashMap<> ();
        for(int year : studentsByYear.keySet ()){
            double count = 0.0;
            for(Student student : studentsByYear.get (year)){
                count += student.getAverage ();
            }
            count /= studentsByYear.get (year).size ();
            averagesByYear.put (year, count);
        }
        return averagesByYear;
    }

}

public class LabExercisesTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LabExercises labExercises = new LabExercises();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] parts = input.split("\\s+");
            String index = parts[0];
            List<Integer> points = Arrays.stream(parts).skip(1)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());

            labExercises.addStudent(new Student(index, points));
        }

        System.out.println("===printByAveragePoints (ascending)===");
        labExercises.printByAveragePoints(true, 100);
        System.out.println("===printByAveragePoints (descending)===");
        labExercises.printByAveragePoints(false, 100);
        System.out.println("===failed students===");
        labExercises.failedStudents().forEach(System.out::println);
        System.out.println("===statistics by year");
        labExercises.getStatisticsByYear().entrySet().stream()
                .map(entry -> String.format("%d : %.2f", entry.getKey(), entry.getValue()))
                .forEach(System.out::println);

    }
}