package labs.l02.zadaca1;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MatrixReader {

    public static DoubleMatrix read(InputStream is) throws InsufficientElementsException {
        double [] niza;
        Scanner in = new Scanner (is);
        int rows = (in.nextInt ());
        int cols = (in.nextInt ());
        niza = new double[rows * cols];
        for(int i=0; i<rows*cols; i++){
            niza[i] = Double.parseDouble (in.next ());
        }
        return new DoubleMatrix (niza, rows, cols);
    }
}
