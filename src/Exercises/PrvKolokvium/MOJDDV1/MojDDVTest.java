package Exercises.PrvKolokvium.MOJDDV1;
import java.io.*;
import java.util.ArrayList;

class Item {
    private int cena;
    private char tipDanok;

    public Item(int cena, char tipDanok) {
        this.cena = cena;
        this.tipDanok = tipDanok;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public char getTipDanok() {
        return tipDanok;
    }

    public void setTipDanok(char tipDanok) {
        this.tipDanok = tipDanok;
    }
}

class FiskalnaSmetka {
    private String id;
    private ArrayList<Item> items;

    public FiskalnaSmetka(String id) {
        this.id = id;
        items = new ArrayList<> ();
    }

    public void dodajSmetka(Item s){
        items.add (s);
    }

    public int vkupnoSuma(){
        int sum = 0;
        for(int i=0; i< items.size (); i++){
            sum += items.get (i).getCena ();
        }
        return sum;
    }

    public double danokZaVrakjanje(){
        double sum = 0.0;
        for(int i = 0; i<items.size (); i++){
            Item s = items.get (i);
            if(s.getTipDanok () == 'A') sum += s.getCena () * 0.18*0.15;
            else if(s.getTipDanok () == 'B') sum+=  s.getCena () * 0.05 * 0.15;
        }
        return sum;
    }

    @Override
    public String toString(){
        return String.format("%s %d %.2f", id, (int)vkupnoSuma (), danokZaVrakjanje ());
    }


}

class AmountNotAllowedException extends Exception{
    private int cena;

    public AmountNotAllowedException(int cena) {
        this.cena = cena;
    }

    public void print(){
        System.out.println ("Receipt with amount " + cena + " is not allowed to be scanned");
    }


}

class MojDDV{
    private ArrayList<FiskalnaSmetka> smetki;

    public MojDDV(){
        smetki = new ArrayList<> ();
    }

    public void readRecords(InputStream inputStream) throws IOException {
        String line;
        BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(inputStream));
        while((line = bufferedReader.readLine ()) != null){
            String[] parts = line.split ("\\s+");
            FiskalnaSmetka temp = new FiskalnaSmetka (parts[0]);
            for(int i=1; i<parts.length; i+=2){
                //System.out.println(parts[i] + " " + parts[i+1]);
                temp.dodajSmetka (new Item (Integer.parseInt (parts[i]), parts[i+1].charAt (0)));
            }
            try{
                dodaj(temp);
            }
            catch(AmountNotAllowedException e){
                e.print();
            }
        }
    }

    public void dodaj(FiskalnaSmetka s) throws AmountNotAllowedException{
        if(s.vkupnoSuma() > 30000) throw new AmountNotAllowedException(s.vkupnoSuma());
        smetki.add(s);
    }

    public void printTaxReturns(OutputStream outputStream){
        PrintWriter pw = new PrintWriter (outputStream);
        for(int i=0; i<smetki.size(); i++){
            pw.println(smetki.get(i).toString());
        }
        pw.flush ();
    }
}

public class MojDDVTest {

    public static void main(String[] args) {

        MojDDV mojDDV = new MojDDV ();

        System.out.println ("===READING RECORDS FROM INPUT STREAM===");
        try {
            mojDDV.readRecords (System.in);
        } catch (IOException e) {
            e.printStackTrace ();
        }

        System.out.println ("===PRINTING TAX RETURNS RECORDS TO OUTPUT STREAM ===");
        mojDDV.printTaxReturns (System.out);

    }
}