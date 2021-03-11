package Exercises.PrvKolokvium.Zadaca3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

class Archive{
    protected int id;
    protected Date dateArchived;

    public Archive(int id) {
        this.id = id;
    }

    public void setDateArchived(Date dateArchived) {
        this.dateArchived = dateArchived;
    }
}

class LockedArchive extends Archive{
    private Date dateToOpen;

    public LockedArchive(int id, Date dateToOpen) {
        super (id);
        this.dateToOpen = dateToOpen;
    }

    public Date getDateToOpen() {
        return dateToOpen;
    }
}

class SpecialArchive extends Archive{
    private int maxOpen;
    private int timesOpened;

    public SpecialArchive(int id, int maxOpen) {
        super (id);
        this.maxOpen = maxOpen;
    }

    public int getMaxOpen() {
        return maxOpen;
    }

    public int getTimesOpened() {
        return timesOpened;
    }

    public void opened(){
        timesOpened++;
    }
}

class ArchiveStore{
    private ArrayList<Archive> archives;
    private String log="";
    private DateFormat format;

    public ArchiveStore(){
        archives = new ArrayList<> ();
        format = new SimpleDateFormat ("dd.MM.yyyy");
        format.setTimeZone (TimeZone.getTimeZone ("UTC"));
    }

    public void archiveItem(Archive item, Date date){
        item.setDateArchived (date);
        archives.add (item);
        log += "Item " + item.id + " archived at " + format.format (item.dateArchived) + "\n";
    }

    public void openItem(int id, Date date) throws NonExistingItemException {
        int ind=-1;
        for(int i=0; i<archives.size (); i++){
            if(archives.get (i).id == id){
                ind = i;
            }
        }
        if(ind == -1) throw new NonExistingItemException(id);
        open(archives.get(ind), date);
    }

    public void open(Archive item, Date date){
        if(item instanceof LockedArchive){
            LockedArchive la = (LockedArchive) item;
            if(date.before (la.getDateToOpen ())){
                log += "Item " + la.id +" cannot be opened before " + la.getDateToOpen () + "\n";
                return;
            }
        }
        else if(item instanceof SpecialArchive){
            SpecialArchive sa = (SpecialArchive) item;
            if(sa.getMaxOpen () - sa.getTimesOpened () == 0){
                log += "Item " + sa.id + " cannot be opened more than "+ sa.getMaxOpen ()+" times" + "\n";
                return ;
            }
            else{
                sa.opened();
            }
        }
        log += "Item " + item.id +" opened at " + date + "\n";
    }

    public String getLog(){
        return log;
    }


}

class NonExistingItemException extends Exception{
    private int id;

    public NonExistingItemException(int n){
        id = n;
    }

    public String getMessage(){
        return String.format("Item with id %d doesn't exist", id);
    }
}


public class ArchiveStoreTest {
    public static void main(String[] args) {
        ArchiveStore store = new ArchiveStore();
        Date date = new Date(113, 10, 7);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        int n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        int i;
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            long days = scanner.nextLong();
            Date dateToOpen = new Date(date.getTime() + (days * 24 * 60
                    * 60 * 1000));
            LockedArchive lockedArchive = new LockedArchive(id, dateToOpen);
            store.archiveItem(lockedArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            int maxOpen = scanner.nextInt();
            SpecialArchive specialArchive = new SpecialArchive(id, maxOpen);
            store.archiveItem(specialArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        while(scanner.hasNext()) {
            int open = scanner.nextInt();
            try {
                store.openItem(open, date);
            } catch(NonExistingItemException e) {
                //  System.out.println(e.getMessage());
            }
        }
        System.out.println(store.getLog());
    }
}

// вашиот код овде


