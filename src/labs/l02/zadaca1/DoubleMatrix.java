package labs.l02.zadaca1;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;

public class DoubleMatrix {
    private final double [] matrix;
    private final int rows;
    private final int columns;

    public DoubleMatrix(double [] mat, int a, int b) throws InsufficientElementsException {
        matrix = new double[a*b];
        int k=0;
        if(mat.length < a * b){
            throw new InsufficientElementsException ();
        }
        else if(mat.length > a * b) {
            k = mat.length - a * b;
        }
        rows = a;
        columns = b;
        for(int i=0; i<a*b; i++){
            matrix[i] = mat[k];
            k++;
        }
    }

    public String getDimensions(){
        String str = "Dimensions: [" + rows + " x " + columns + "]";
        return str;
    }

    public int rows(){
        return rows;
    }

    public int columns(){
        return columns;
    }

    public double maxElementAtRow(int row) throws InvalidRowNumberException {
        if(row<1 || row > rows) throw new InvalidRowNumberException ();
        double max = matrix[(row-1) * columns];
        for(int i=(row-1) * columns; i<row * columns; i++){
            if(matrix[i] > max){
                max = matrix[i];
            }
        }
        return max;
    }

    public double maxElementAtColumn(int col) throws InvalidColumnNumberException {
        if(col<1 || col > columns) throw new InvalidColumnNumberException ();
        double max = matrix[(col-1) * rows];
        for(int i=(col-1) * rows; i<col * rows; i++){
            if(matrix[i] > max){
                max = matrix[i];
            }
        }
        return max;
    }

    public double sum(){
        double Sum = 0.0;
        for(int i=0; i<rows * columns; i++){
            Sum += matrix[i];
        }
        return Sum;
    }

    public double[] toSortedArray(){
        double [] newMatrix = new double[rows * columns];
        for(int i=0; i<rows * columns; i++){
            newMatrix[i] = matrix[i];
        }
        for(int i=0; i<rows*columns-1; i++){
            for(int j=0; j<rows*columns-i-1; j++){
                if(newMatrix[j] < newMatrix[j+1]){
                    double temp = newMatrix[j];
                    newMatrix[j] = newMatrix[j+1];
                    newMatrix[j+1] = temp;
                }
            }
        }
        return newMatrix;
    }

    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat ("0.00");
        String str="";
        int j=0;
        for(int i=0; i<rows*columns; i++){
            str += df.format (matrix[i]) + "\t";
            j++;
            if(j == columns) {
                str += "\n";
                j = 0;
            }
        }
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        DoubleMatrix that = (DoubleMatrix) o;
        return rows == that.rows &&
                columns == that.columns &&
                Arrays.equals (matrix, that.matrix);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash (rows, columns);
        result = 31 * result + Arrays.hashCode (matrix);
        return result;
    }

    public static void main(String[] args) {
        double[] mat = new double[40];
        for(int i=0; i<40; i++) mat[i] = i+1;
        try {
            DoubleMatrix dm = new DoubleMatrix (mat, 10 ,4);
            DoubleMatrix dmSorted = new DoubleMatrix (dm.toSortedArray (), dm.rows (), dm.columns ());
            System.out.println (dmSorted);
            System.out.println (dm.getDimensions ());
            System.out.println (dm.maxElementAtRow (1));
            System.out.println (dm.maxElementAtColumn (1));
            System.out.println (dm.sum ());
        }
        catch (InsufficientElementsException i){
            System.out.println ("Nema dovolno elementi!");
        }
        catch (InvalidRowNumberException i){
            System.out.println ("Vnesi validen red");
        }
        catch (InvalidColumnNumberException i){
            System.out.println ("Vnesi validna kolona");
        }
    }
}
