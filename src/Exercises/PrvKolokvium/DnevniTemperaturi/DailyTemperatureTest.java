package Exercises.PrvKolokvium.DnevniTemperaturi;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * I partial exam 2016
 */

class DailyTemperature implements Comparable<DailyTemperature>{
    private int day;
    private ArrayList<Double> celsiusMeasurements;
    private ArrayList<Double> fahrenheitMeasurements;

    public DailyTemperature(String line){
        fahrenheitMeasurements = new ArrayList<> ();
        celsiusMeasurements = new ArrayList<> ();
        String[] parts = line.split ("\\s+");
        day = Integer.parseInt (parts[0]);
        for(int i=1; i< parts.length; i++){
            if(parts[i].charAt (parts[i].length ()-1) == 'C'){
                celsiusMeasurements.add (Double.parseDouble (parts[i].substring (0, parts[i].length ()-1)));
                fahrenheitMeasurements.add(convertToFahrenheit (Double.parseDouble (parts[i].substring (0, parts[i].length ()-1))));
            }
            else {
                celsiusMeasurements.add (convertToCelsius (Double.parseDouble (parts[i].substring (0, parts[i].length ()-1))));
                fahrenheitMeasurements.add (Double.parseDouble (parts[i].substring (0, parts[i].length ()-1)));
            }
        }
    }

    public int compareTo(DailyTemperature d){
        return Integer.compare (this.day, d.day);
    }

    private static double convertToFahrenheit(double n){
        return n*9.00/5.00+32.00;
    }
    private static double convertToCelsius(double n){
        return (n-32.00)*5/9;
    }

    public String print(char scale){
        DoubleSummaryStatistics d = new DoubleSummaryStatistics ();
        if(scale == 'C'){
            celsiusMeasurements.stream ().forEach (s -> d.accept (s));
        }
        else{
            fahrenheitMeasurements.stream ().forEach (s -> d.accept (s));
        }
        return String.format ("%3d: Count: %3d Min: %6.2f%c Max: %6.2f%c Avg: %6.2f%c",
                day,
                d.getCount (),
                d.getMin (),
                scale,
                d.getMax (),
                scale,
                d.getAverage (),
                scale
        );


    }
}


class DailyTemperatures{
    private ArrayList<DailyTemperature> days;

    public DailyTemperatures(){
        days = new ArrayList<> ();
    }

    public void readTemperatures(InputStream inputStream){
        Scanner scanner = new Scanner (inputStream);
        while (scanner.hasNextLine ()){
            days.add (new DailyTemperature (scanner.nextLine ()));
        }
    }

    void writeDailyStats(OutputStream outputStream, char scale){
        PrintWriter pw = new PrintWriter (outputStream);
        Collections.sort (days);
        days.stream ().forEach (d -> pw.println (d.print (scale)));
        pw.flush ();
    }
}


public class DailyTemperatureTest {
    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        dailyTemperatures.readTemperatures(System.in);
        System.out.println("=== Daily temperatures in Celsius (C) ===");
        dailyTemperatures.writeDailyStats(System.out, 'C');
        System.out.println("=== Daily temperatures in Fahrenheit (F) ===");
        dailyTemperatures.writeDailyStats(System.out, 'F');


    }
}

// Vashiot kod ovde