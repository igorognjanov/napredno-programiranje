package labs.l01.zadaca3;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
public final class IntegerArray {
    private int [] IntArray;
    public IntegerArray(int [] niza){
        IntArray = new int [niza.length];
        for(int i=0; i<niza.length; i++){
            IntArray[i] = niza[i];
        }
    }

    InputStream is = new InputStream () {
        @Override
        public int read() throws IOException {
            return 0;
        }
    };

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o.getClass () != this.getClass ()) return false;
        IntegerArray i = (IntegerArray) o;
        if(Arrays.equals (this.IntArray, i.IntArray)) return true;
        return false;
    }

    public int length(){
        return IntArray.length;
    }

    public int getElementAt(int i){
        return IntArray[i];
    }

    public int sum(){
        int zbir=0;
        for(int i=0; i<IntArray.length; i++){
            zbir += IntArray[i];
        }
        return zbir;
    }

    public double average(){
        return (double)sum()/(double)IntArray.length;
    }

    public IntegerArray getSorted(){
        Arrays.sort (IntArray);
        IntegerArray ia = new IntegerArray (IntArray);
        return ia;
    }

    public IntegerArray concat(IntegerArray ia){
        int [] temp = new int [this.IntArray.length + ia.length ()];
        for(int i=0; i<this.length (); i++){
            temp[i] = this.IntArray[i];
        }
        int j=0;
        for(int i=this.IntArray.length; i< this.length () + ia.length (); i++){
            temp[i] = ia.getElementAt (j);
            j++;
        }
        IntegerArray t = new IntegerArray (temp);
        return t;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder ("[");
        for(int i=0; i<IntArray.length; i++) {
            s.append (this.getElementAt (i));
            if(i != IntArray.length-1) s.append (", ");
        }
        s.append ("]");
        return s.toString ();
    }

    public static void main(String[] args) {
        int [] niza = {10, 20, 30, 40, 50, 60, 70};
        int [] niza2 = {80, 90, 100, 110, 120, 130};

        IntegerArray ia1 = new IntegerArray (niza);
        IntegerArray ia2 = new IntegerArray (niza2);

        System.out.println (ia1);
        System.out.println (ia2);

        System.out.println (ia1.concat (ia2));

    }
}
