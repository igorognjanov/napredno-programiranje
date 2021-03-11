package mk.ukim.finki.aud4.Grades;

import java.io.*;
import java.security.PublicKey;
import java.util.*;

class Student implements Comparable<Student>{
    private String firstName;
    private String lastName;
    private int exam1, exam2, exam3;

    public Student(String firstName, String lastName, int exam1, int exam2, int exam3) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.exam1 = exam1;
        this.exam2 = exam2;
        this.exam3 = exam3;
    }
    public static Student getStudentFrom(String line){
        String[] parts = line.split (":");
        return new Student (
                parts[1],
                parts[0],
                Integer.parseInt (parts[2]),
                Integer.parseInt (parts[3]),
                Integer.parseInt (parts[4]));

    }


    public double getTotalPoints(){
        return exam1* 0.25 + exam2 * 0.3 + exam3 * 0.45;
    }
    public char getGrade(){
        double totalPoints = getTotalPoints ();
        if(totalPoints >= 90) return 'A';
        if(totalPoints >= 80) return 'B';
        if(totalPoints >= 70) return 'C';
        if(totalPoints >= 60) return 'D';
        if(totalPoints >= 50) return 'E';
        return 'E';
    }

    public String studentWithGrade() {
        return String.format ("%s %s %c", lastName, firstName, getGrade ());
    }

    @Override
    public int compareTo(Student s){
        return Character.compare (getGrade (), s.getGrade ());
    }

    @Override
    public String toString(){
        return String.format ("%s %s %d %d %d %.2f %c", lastName, firstName, exam1, exam2, exam3, getTotalPoints (), getGrade ());
    }

}


class Course{
    List<Student> students;


    public Course(){
        students = new ArrayList<> ();
    }

    public void readStudents(InputStream input){
        Scanner scanner = new Scanner (input);
        while(scanner.hasNextLine ()){
            String line = scanner.nextLine ();
            students.add (Student.getStudentFrom (line));
        }
    }

    public void printSortedStudents(OutputStream outputStream){
        PrintWriter printWriter = new PrintWriter (outputStream);
        Collections.sort (students);
        for(Student s : students){
            printWriter.println(s.studentWithGrade ());
        }
        printWriter.flush ();
    }

    public void printDetailedReport(OutputStream outputStream){
        PrintWriter printWriter = new PrintWriter (outputStream);
        for(Student s : students)
            printWriter.println (s.toString ());
        printWriter.flush ();
    }

    public void printGradeDistribution(OutputStream outputStream){
        PrintWriter printWriter = new PrintWriter (outputStream);
        int niza[] = new int[6];
        for(Student s : students){
            niza[s.getGrade () - 'A']++;

        }

        for(int i=0; i<6; i++){
            printWriter.printf ("%c: %d\n", i + 'A', niza[i]);
        }
        printWriter.flush ();
    }
}

public class GradesTest {
    public static void main(String[] args) {
        Course course = new Course ();
        File file = new File ("C:\\Users\\Igor\\IdeaProjects\\NP2020\\src\\mk\\ukim\\finki\\aud4\\Grades\\Dat");
        File outputFile = new File ("C:\\Users\\Igor\\IdeaProjects\\NP2020\\src\\mk\\ukim\\finki\\aud4\\Grades\\OutputFile");
        try {
            course.readStudents (new FileInputStream (file));
            course.printSortedStudents (System.out);
            course.printGradeDistribution (System.out);
            course.printDetailedReport (new FileOutputStream (outputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        }
    }

}
