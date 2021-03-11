package Exercises.VtorKolovium.Aerodromi;


import com.sun.source.tree.Tree;

import java.time.LocalTime;
import java.util.*;
import java.util.function.Function;

class Airport{
    private String name;
    private String country;
    private String code;
    private int passengers;

    private Map<String, TreeSet<Flight>> flightTo; // Map<codeTo, flight>
    private TreeSet<Flight> flights;
    private TreeSet<Flight> arrivingFlights;


    public Airport(String name, String country, String code, int passengers) {
        this.name = name;
        this.country = country;
        this.code = code;
        this.passengers = passengers;
        flightTo = new HashMap<String, TreeSet<Flight>> ();
        flights = new TreeSet<> (Comparator.comparing (Flight::getTo).thenComparing (Function.identity ()));
        arrivingFlights = new TreeSet<Flight> ();
    }

    public void addNewFlight(Flight f){
        flightTo.putIfAbsent (f.getTo(), new TreeSet<> (Comparator.comparing (Flight::getTime)));
        flightTo.get (f.getTo ()).add (f);

        flights.add (f);

    }

    public void addArrivingFlight(Flight f){
        arrivingFlights.add (f);
    }

    public void showFlightsTo(String to){
        if(flightTo.get (to) == null){
            System.out.printf("No flights from %s to %s\n", code, to);
            return;
        }
        for(Flight flight : flightTo.get (to)){;
            System.out.println (flight);
        }
    }

    public void showFlights(){
        int index = 1;
        System.out.println (this);
        for(Flight flight : flights){
            System.out.println (index + ". " + flight);
            index++;
        }
    }

    public void showArrivingFlights(){
        for(Flight f : arrivingFlights){
            System.out.println (f);
        }
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCode() {
        return code;
    }

    public int getPassengers() {
        return passengers;
    }

    public Map<String, TreeSet<Flight>> getFlightTo() {
        return flightTo;
    }

    public String toString(){
        return String.format ("%s (%s)\n%s\n%d", name, code, country, passengers);
    }
}

class Flight implements Comparable<Flight>{
    private String from;
    private String to;
    private int time;
    private int duration;

    public Flight(String from, String to, int time, int duration) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.duration = duration;
    }

    public String getTo() {
        return to;
    }



    @Override
    public String toString() {
        return String.format ("%s-%s %s-%s %s", from, to, getTime (time), getTime (time + duration),
                getDuration (duration));
    }

    public String getTime(int minutes) {
        int h = minutes / 60;
        minutes -= h * 60;
        String d = "";
        if (h < 24) {
            h %= 24;
            return String.format ("%02d:%02d", h, minutes);
        } else {
            h %= 24;
            return String.format ("%02d:%02d +1d", h, minutes);
        }
    }

    public String getDuration(int duration){
        int h=duration / 60;
        duration -= h * 60;
        return String.format ("%dh%02dm", h, duration);//h + "h" + duration + "m";
    }

    public int compareTo(Flight that){
        if(this.time == that.time){
            return Integer.compare (this.duration, that.duration);
        }
        return Integer.compare (this.time, that.time);
    }

    public int compareDestinations(Flight that){
        return this.to.compareTo (that.to);
    }

    public String getFrom() {
        return from;
    }

    public int getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }
}

class Airports{

    Map<String, Airport> codeAirport; // Map<code, airport>

    public Airports() {
        codeAirport = new HashMap<> ();
    }

    public void addAirport(String name, String country, String code, int passengers){
        codeAirport.putIfAbsent (code, new Airport (name, country, code, passengers));
    }

    public void addFlights(String from, String to, int time, int duration){
        codeAirport.get (from).addNewFlight (new Flight (from, to, time, duration));
        codeAirport.get (to).addArrivingFlight(new Flight (from, to, time, duration));
    }

    public void showFlightsFromAirport(String code){
        codeAirport.get (code).showFlights ();
    }

    public void showDirectFlightsFromTo(String from, String to){
        codeAirport.get (from).showFlightsTo (to);
    }

    public void showDirectFlightsTo(String to){
        codeAirport.get (to).showArrivingFlights ();
    }

}



public class AirportsTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Airports airports = new Airports();
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] codes = new String[n];
        for (int i = 0; i < n; ++i) {
            String al = scanner.nextLine();
            String[] parts = al.split(";");
            airports.addAirport(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
            codes[i] = parts[2];
        }
        int nn = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < nn; ++i) {
            String fl = scanner.nextLine();
            String[] parts = fl.split(";");
            airports.addFlights(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
        }
        int f = scanner.nextInt();
        int t = scanner.nextInt();
        String from = codes[f];
        String to = codes[t];
        System.out.printf("===== FLIGHTS FROM %S =====\n", from);
        airports.showFlightsFromAirport(from);
        System.out.printf("===== DIRECT FLIGHTS FROM %S TO %S =====\n", from, to);
        airports.showDirectFlightsFromTo(from, to);
        t += 5;
        t = t % n;
        to = codes[t];
        System.out.printf("===== DIRECT FLIGHTS TO %S =====\n", to);
        airports.showDirectFlightsTo(to);
    }
}

// vashiot kod ovde

