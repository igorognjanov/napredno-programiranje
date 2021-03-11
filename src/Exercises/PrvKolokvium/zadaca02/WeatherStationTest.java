package Exercises.PrvKolokvium.zadaca02;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class WeatherStation{
    private ArrayList<Measurement> measurements;
    private int x;

    public WeatherStation(int x){
        measurements = new ArrayList<> ();
        this.x = x;
    }

    public void addMeasurement(float temperature, float wind, float humidity, float visibility, Date date){
        Measurement temp = new Measurement (temperature, wind, humidity, visibility, date);
        for(int i=0; i<measurements.size (); i++){
            Date d = measurements.get (i).getDate ();
            if(d.toInstant ().isAfter (date.toInstant ()) && d.toInstant ().minusSeconds (150).isBefore (date.toInstant ())
                    || (d.toInstant ().isBefore (date.toInstant ()) && d.toInstant ().plusSeconds (150).isAfter (date.toInstant ())))
                // date.toInstant ().plusSeconds (150).isAfter (measurements.get (i).getDate ().toInstant ()))
                return;
        }

        for(int i=0;i<measurements.size (); i++){
            Date d = measurements.get (i).getDate ();
            if(d.toInstant ( ).isBefore (date.toInstant ().minusSeconds (24*60*60*x)))
                    {
                measurements.remove (measurements.get (i));
                System.out.println ("AFASDF");
            }
        }

        measurements.add (temp);
    }

    public int total(){return measurements.size ();}

    public void status(Date from, Date to) throws RuntimeException{
        boolean flag = true;
        Collections.sort (measurements);
        for(int i=0; i<measurements.size (); i++){
            if(measurements.get (i).getDate ().toInstant ().isBefore (to.toInstant ().plusSeconds (1)) &&
                    measurements.get (i).getDate ().toInstant ().isAfter (from.toInstant ().minusSeconds (1))){

                flag = false;
            }
            System.out.println (measurements.get (i));
        }

        if(flag){
            throw new RuntimeException();
        }
    }
}

class Measurement implements Comparable<Measurement>{
    private float temp;
    private float wind;
    private float hum;
    private float vis;
    private Date date;

    public Measurement(float temp, float wind, float hum, float vis, Date date) {
        this.temp = temp;
        this.wind = wind;
        this.hum = hum;
        this.vis = vis;
        this.date = date;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getWind() {
        return wind;
    }

    public void setWind(float wind) {
        this.wind = wind;
    }

    public float getHum() {
        return hum;
    }

    public void setHum(float hum) {
        this.hum = hum;
    }

    public float getVis() {
        return vis;
    }

    public void setVis(float vis) {
        this.vis = vis;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString(){
        return temp + " " + wind + " km/h " + hum + "% " + vis + " km " + date;
    }

    @Override
    public int compareTo(Measurement measurement){
        if(this.getDate ().toInstant ().equals (measurement.getDate ().toInstant ()))
            return 0;
        if(this.getDate ().toInstant ().isAfter (measurement.getDate ().toInstant ()))
            return 1;
        return -1;
    }

}

public class WeatherStationTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        int n = scanner.nextInt();
        scanner.nextLine();
        WeatherStation ws = new WeatherStation(n);
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("=====")) {
                break;
            }
            String[] parts = line.split(" ");
            float temp = Float.parseFloat(parts[0]);
            float wind = Float.parseFloat(parts[1]);
            float hum = Float.parseFloat(parts[2]);
            float vis = Float.parseFloat(parts[3]);
            line = scanner.nextLine();
            Date date = df.parse(line);
            ws.addMeasurement(temp, wind, hum, vis, date);
        }
        String line = scanner.nextLine();
        Date from = df.parse(line);
        line = scanner.nextLine();
        Date to = df.parse(line);
        scanner.close();
        System.out.println(ws.total());
        try {
            ws.status(from, to);
        } catch (RuntimeException e) {
            System.out.println(e);
        }

    }
}

// vashiot kod ovde