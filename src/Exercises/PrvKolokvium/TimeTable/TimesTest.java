package Exercises.PrvKolokvium.TimeTable;


import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class TimeTable{
    private ArrayList<LocalTime> times;
    public TimeTable() {
        times = new ArrayList<> ();
    }

    public void readTimes(InputStream inputStream) throws UnsupportedFormatException, InvalidTimeException {
        Scanner scanner = new Scanner (inputStream);
        while(scanner.hasNextLine ()) {
            String line = scanner.nextLine ();
            String[] parts = line.replace ('.', ':').split (" ");
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].contains ("-")) throw new UnsupportedFormatException (parts[i]);
                int HH = Integer.parseInt (parts[i].split (":")[0]);
                int mm = Integer.parseInt (parts[i].split (":")[1]);
                if (HH > 23 || mm > 59) throw new InvalidTimeException ();
                times.add (LocalTime.of (HH, mm));
            }
        }
    }

    public void writeTimes(OutputStream outputStream, TimeFormat format){
        PrintWriter pw = new PrintWriter (outputStream);
        Collections.sort (times);
        times.stream ().forEach (t -> {

            if(format == TimeFormat.FORMAT_24)
                System.out.println (t);
            else{
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern ("H:mm");
                if(t.getHour () == 0)
                    //if(t.isAfter (LocalTime.of (23, 59)) && t.isBefore (LocalTime.of (1, 0))){
                    //System.out.printf ("%d:%.02d AM", 12, t.getMinute ());
                    System.out.println (dateTimeFormatter.format (t.plusHours (12)) + " AM");
                else if(t.getHour () >= 1 && t.getHour () <= 11)
                    //else if(t.isAfter (LocalTime.of (0, 59)) && t.isBefore (LocalTime.of (12, 0))){
                    //System.out.printf ("%d:%.02d AM", t.getHour (), t.getMinute ());
                    System.out.println (dateTimeFormatter.format (t) + " PM");
                else if(t.getHour () == 12 )
                    System.out.println (dateTimeFormatter.format (t)+ " PM");
                    //else if(t.isAfter (LocalTime.of (11, 59)) && t.isBefore (LocalTime.of (13, 0))){
                    //System.out.printf ("%d:%.02d PM", t.getHour (), t.getMinute ());

                else{
                    System.out.println (dateTimeFormatter.format (t.minusHours (12)) + " PM");
                    //System.out.printf ("%d:%.02d PM", t.getHour () - 12, t.getMinute ());
                }
            }
        });
    }
}

class InvalidTimeException extends Exception{

}

class UnsupportedFormatException extends Exception{
    private String s;

    public UnsupportedFormatException(String s){
        this.s = s;
    }

    public String getMessage(){
        return s;
    }
}

public class TimesTest {

    public static void main(String[] args) {
        TimeTable timeTable = new TimeTable();
        try {
            timeTable.readTimes(System.in);
        } catch (UnsupportedFormatException e) {
            System.out.println("UnsupportedFormatException: " + e.getMessage());
        } catch (InvalidTimeException e) {
            System.out.println("InvalidTimeException: " + e.getMessage());
        }
        System.out.println("24 HOUR FORMAT");
        timeTable.writeTimes(System.out, TimeFormat.FORMAT_24);
        System.out.println("AM/PM FORMAT");
        timeTable.writeTimes(System.out, TimeFormat.FORMAT_AMPM);
    }

}

enum TimeFormat {
    FORMAT_24, FORMAT_AMPM
}
