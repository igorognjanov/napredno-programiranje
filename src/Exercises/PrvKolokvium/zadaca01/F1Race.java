package Exercises.PrvKolokvium.zadaca01;

import java.io.*;
import java.util.*;

class F1Test {

    public static void main(String[] args) {
        F1Race f1Race = new F1Race();
        try {
            f1Race.readResults(System.in);
        } catch (IOException e) {
            e.printStackTrace ();
        }
        f1Race.printSorted(System.out);
    }

}
class F1Racer implements Comparable<F1Racer>{
    private String name;
    private String lap1;
    private String lap2;
    private String lap3;

    public F1Racer(String line){
        //System.out.println (line);
        String[] parts = line.split ("\\s+");
        for (int i=0; i<4; i++)
            System.out.println (parts[i]);
        this.name = parts[0];
        this.lap1 = parts[1];
        this.lap2 = parts[2];
        this.lap3 = parts[3];
    }

    private static String getSmaller(String lap1, String lap2){
        String [] parts1 = lap1.split (":");
        String[] parts2 = lap2.split (":");
        Date date = new Date ();
        for(int i=0; i<3; i++){
            if(Integer.parseInt (parts1[i]) > Integer.parseInt (parts2[i])) return lap2;
            if(Integer.parseInt (parts1[i]) < Integer.parseInt (parts2[i])) return lap1;
        }
        return "";
    }

    public String getBestLap(){
        return getSmaller (getSmaller (lap1, lap2), lap3);
    }

    @Override
    public int compareTo(F1Racer o) {
        if(getSmaller (this.getBestLap (), o.getBestLap ()).equals (this.getBestLap ())) return -1;
        else return 1;
    }

    @Override
    public String toString(){
        return String.format ("%-10s%10s", name, getBestLap ());
    }
}
class F1Race {
    // vashiot kod ovde
    List<F1Racer> racerList;
    public F1Race(){
        racerList = new ArrayList<> (10);
    }

    void readResults(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader (new InputStreamReader (inputStream));
        String line;
        while((line = br.readLine ()) != null){
            racerList.add (new F1Racer (line));
        }
    }

    void printSorted(OutputStream outputStream){
        PrintWriter pw = new PrintWriter (outputStream);
        Collections.sort (racerList, Comparator.naturalOrder ());
        for(int i=0; i<racerList.size (); i++){
            pw.print (i + ". " + racerList.get (i).toString ());
        }
        pw.flush ();
    }



}