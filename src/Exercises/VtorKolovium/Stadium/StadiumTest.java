package Exercises.VtorKolovium.Stadium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class Sector{
    private String code;
    private int seats;
    private ArrayList<Integer> busySeats;
    private int type;

    public Sector(String code, int seats) {
        this.code = code;
        this.busySeats = new ArrayList<> ();
        this.seats = seats;
        this.type = 0;
    }

    public void buyTicket(int seat, int type) throws SeatTakenException, SeatNotAllowedException {
        if(this.busySeats.contains (seat)) throw new SeatTakenException ();
        if (this.type == 0) {
            this.type = type;
        } if (type == 0 || type == this.type) {
            if (busySeats.contains (seat)) {
                throw new SeatTakenException ();
            }
            busySeats.add (seat);
        }
        else throw new SeatNotAllowedException ();
    }

    public int freeSeats(){
        return seats - busySeats.size ();
    }


    public String getCode() {
        return code;
    }

    public int getSeats() {
        return seats;
    }

    public ArrayList<Integer> getBusySeats() {
        return busySeats;
    }

    public String toString(){
        return String.format ("%s\t%d/%d\t%.1f%%",
                this.code,
                this.seats-this.busySeats.size (),
                this.seats,
                100*(this.busySeats.size () / (double)this.seats));
    }

}

class Stadium{
    private String stadiumName;
    private ArrayList<Sector> sectors;

    public Stadium(String stadiumName) {
        this.stadiumName = stadiumName;
        sectors = new ArrayList<> ();
    }

    public void createSectors(String[] sectorNames, int[] sizes){
        for(int i=0; i<sectorNames.length; i++){
            sectors.add (new Sector (sectorNames[i], sizes[i]));
        }
    }

    public void buyTicket(String sectorName, int seat, int type) throws SeatTakenException, SeatNotAllowedException {
        for(Sector sector : sectors){
            if(sector.getCode ().equals (sectorName)){
                sector.buyTicket (seat, type);
            }
        }
    }

    public void showSectors(){
        sectors.stream ().sorted (Comparator.comparing (Sector::freeSeats).reversed ().thenComparing (Sector::getCode))
                .forEach (System.out::println);
    }
}

class SeatNotAllowedException extends Exception{

    public SeatNotAllowedException(){}
}

class SeatTakenException extends Exception{

    public SeatTakenException(){}
}


class StaduimTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] sectorNames = new String[n];
        int[] sectorSizes = new int[n];
        String name = scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            sectorNames[i] = parts[0];
            sectorSizes[i] = Integer.parseInt(parts[1]);
        }
        Stadium stadium = new Stadium(name);
        stadium.createSectors(sectorNames, sectorSizes);
        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            try {
                stadium.buyTicket(parts[0], Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]));
            } catch (SeatNotAllowedException e) {
                System.out.println("SeatNotAllowedException");
            } catch (SeatTakenException e) {
                System.out.println("SeatTakenException");
            }
        }
        stadium.showSectors();
    }
}
