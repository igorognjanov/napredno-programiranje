package labs.l01.zadaca3;

import java.util.Scanner;

public class ArrayReader {

    public static IntegerArray readIntegerArray(){
        int [] niza;

        Scanner in = new Scanner (System.in);
        int n = in.nextInt ();
        niza = new int [n];
        for(int i=0; i<n; i++){
            niza[i] = in.nextInt ();
        }
        IntegerArray ia = new IntegerArray (niza);
        return ia;
    }

    public static void main(String[] args) {
        String str = new String ("Igor");
        String str2 = new String ("Igor");
        System.out.println (str.equals (str2));
        System.out.println (str == str2);
        Double d = 1.23;
        Double d1 = 1.23;
        System.out.println (d1.equals (d));
    }


}
