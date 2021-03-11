package Exercises.VtorKolovium.KalendarNaNastani;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Event{
    private String name;
    private String location;
    private Date date;


    public Event(String name, String location, Date date) {
        this.name = name;
        this.location = location;
        this.date = date;
    }





}


class EventCalendar{
    private int year;

    private Map<String, Event> eventsOnDay;
    private Map<Integer, Integer> numberOfEventsInMonth;

    public EventCalendar(int year) {
        this.year = year;
    }

    public void addEvent(String name, String location, Date date) throws WrongDateException{
        if(date.getYear () + 1900 != year)
            throw new WrongDateException (date);


        numberOfEventsInMonth.putIfAbsent (date.getMonth (), 0);
        numberOfEventsInMonth.put(date.getMonth (), numberOfEventsInMonth.get (date.getMonth ()) + 1);
    }
}

class WrongDateException extends Exception{
    Date date;

    public WrongDateException(Date date) {
        this.date = date;
    }

    public String getMessage(){
        return "Wrong date: " + date.toString ();
    }
}


public class EventCalendarTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int year = scanner.nextInt();
        scanner.nextLine();
        EventCalendar eventCalendar = new EventCalendar(year);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            String name = parts[0];
            String location = parts[1];
            Date date = df.parse(parts[2]);
            System.out.println (date.getYear ());
            try {
                eventCalendar.addEvent(name, location, date);
            } catch (WrongDateException e) {
                System.out.println(e.getMessage());
            }
        }
        Date date = df.parse(scanner.nextLine());
//        eventCalendar.listEvents(date);
//        eventCalendar.listByMonth();
    }
}

// vashiot kod ovde