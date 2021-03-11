import labs.l01.zadaca02.Account;
import labs.l01.zadaca02.Transaction;

import java.util.Arrays;
import java.util.Objects;

public class Bank {
    private String name;
    private Account[] niza;
    private double vkupnoTransferi;
    private double vkupnoProvizija;


    public static double toInt(String str){
        double zbir = 0.0;
        double [] sum = new double[str.length ()-2];
        int k=0;
        for(int i=0; i<str.length ()-1; i++){
            if(Character.isDigit (str.charAt (i))) {
                sum[k] = (int)str.charAt (i) - 48;
                k++;
            }
        }
        for (int i=0; i<sum.length; i++)System.out.println (sum[i]);
        System.out.println (sum.length);
        int j=sum.length-3;
        for(int i=0; i<sum.length;i++){
            zbir += sum[i] * Math.pow (10.0, j);
            j--;
        }

        return zbir;
    }


    public Bank(String name, Account [] niza){
        this.niza = niza;
        this.name = name;
        vkupnoProvizija = 0.0;
        vkupnoTransferi = 0.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Bank bank = (Bank) o;
        return Double.compare (bank.vkupnoTransferi, vkupnoTransferi) == 0 &&
                Double.compare (bank.vkupnoProvizija, vkupnoProvizija) == 0 &&
                Objects.equals (name, bank.name) &&
                Arrays.equals (niza, bank.niza);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash (name, vkupnoTransferi, vkupnoProvizija);
        result = 31 * result + Arrays.hashCode (niza);
        return result;
    }

    public boolean makeTransaction(Transaction t){
        int j=-1;
        int k=-1;
        for(int i=0; i<niza.length; i++){
            if(t.getFromId () == niza[i].getID())
                j = i;
            if(t.getToId () == niza[i].getID ())
                k = i;
        }
        if(j==k || j == -1 || k == -1)return false;
        double m = toInt (t.getAmount ());

        return true;
    }
}
