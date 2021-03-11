package labs.l02.zadaca1;

import java.io.ByteArrayInputStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.io.InputStream;




public class    DoubleMatrixTester {


    public static class DoubleMatrix {
        private final double [][] matrix;
        private final int rows;
        private final int columns;

        public DoubleMatrix(double [] mat, int a, int b) throws InsufficientElementsException {
            matrix = new double[a][b];
            int k=0;
            if(mat.length < a * b){
                throw new InsufficientElementsException ();
            }
            else if(mat.length > a * b) {
                k = mat.length - a * b;
            }
            rows = a;
            columns = b;
            for(int i=0; i<a; i++){
                for(int j=0; j<b; j++){
                    matrix[i][j] = mat[k];
                    k++;
                }
            }
        }

        public String getDimensions(){
            String str = "[" + rows + " x " + columns + "]";
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
            double max = matrix[row-1][0];
            for(int i=1; i<columns; i++){
                if(matrix[row-1][i] > max){
                    max = matrix[row-1][i];
                }
            }
            return max;
        }

        public double maxElementAtColumn(int col) throws InvalidColumnNumberException {
            if(col<1 || col > columns) throw new InvalidColumnNumberException ();
            double max = matrix[0][col-1];
            for(int i=0; i<rows; i++){
                if(matrix[i][col-1] > max){
                    max = matrix[i][col-1];
                }
            }
            return max;
        }

        public double sum(){
            double Sum = 0.0;
            for(int i=0; i<rows; i++){
                for(int j=0; j<columns; j++) {
                    Sum += matrix[i][j];
                }
            }
            return Sum;
        }

        public double[] toSortedArray(){
            double [] newMatrix = new double[rows * columns];
            int k=0;
            for(int i=0; i<rows; i++){
                for(int j=0; j<columns; j++) {
                    newMatrix[k] = matrix[i][j];
                    k++;
                }
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
            for(int i=0; i<rows; i++){
                for(int j=0; j<columns; j++){
                    str += df.format(matrix[i][j]);
                    if(j != columns-1)
                        str += "\t";
                }
                if(i != rows-1)
                    str += "\n";
            }
            return str;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass () != o.getClass ()) return false;
            DoubleMatrix that = (DoubleMatrix) o;
            if(this.rows () != that.rows() || this.columns () != that.columns ()) return false;
            for(int i=0; i<rows; i++){
                for(int j=0; j<columns; j++){
                    if(this.matrix[i][j] != that.matrix[i][j])
                        return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            int result = Objects.hash (rows, columns);
            result = 31 * result + Arrays.hashCode (matrix);
            return result;
        }    }



    public static class MatrixReader {

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


    public static class InvalidColumnNumberException extends Exception{
        public InvalidColumnNumberException(){
        }

        public String getMessage(){
            return "Invalid column number";
        }
    }

    public static class InvalidRowNumberException extends Exception{
        public InvalidRowNumberException(){
        }
        public String getMessage(){
            return "Invalid row number";
        }
    }

    public static class InsufficientElementsException extends Exception {
        public InsufficientElementsException(){
        }

        public String getMessage(){
            return "Insufficient number of elements";
        }
    }







    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int tests = scanner.nextInt();
        DoubleMatrix fm = null;

        double[] info = null;

        DecimalFormat format = new DecimalFormat("0.00");

        for (int t = 0; t < tests; t++) {

            String operation = scanner.next();

            switch (operation) {
                case "READ": {
                    int N = scanner.nextInt();
                    int R = scanner.nextInt();
                    int C = scanner.nextInt();

                    double[] f = new double[N];

                    for (int i = 0; i < f.length; i++)
                        f[i] = scanner.nextDouble();

                    try {
                        fm = new DoubleMatrix(f, R, C);
                        info = Arrays.copyOf(f, f.length);

                    } catch (InsufficientElementsException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                    }

                    break;
                }

                case "INPUT_TEST": {
                    int R = scanner.nextInt();
                    int C = scanner.nextInt();

                    StringBuilder sb = new StringBuilder();

                    sb.append(R + " " + C + "\n");

                    scanner.nextLine();

                    for (int i = 0; i < R; i++)
                        sb.append(scanner.nextLine() + "\n");

                    fm = MatrixReader.read(new ByteArrayInputStream(sb
                            .toString().getBytes()));

                    info = new double[R * C];
                    Scanner tempScanner = new Scanner(new ByteArrayInputStream(sb
                            .toString().getBytes()));
                    tempScanner.nextDouble();
                    tempScanner.nextDouble();
                    for (int z = 0; z < R * C; z++) {
                        info[z] = tempScanner.nextDouble();
                    }

                    tempScanner.close();

                    break;
                }

                case "PRINT": {
                    System.out.println(fm.toString());
                    break;
                }

                case "DIMENSION": {
                    System.out.println("Dimensions: " + fm.getDimensions());
                    break;
                }

                case "COUNT_ROWS": {
                    System.out.println("Rows: " + fm.rows());
                    break;
                }

                case "COUNT_COLUMNS": {
                    System.out.println("Columns: " + fm.columns());
                    break;
                }

                case "MAX_IN_ROW": {
                    int row = scanner.nextInt();
                    try {
                        System.out.println("Max in row: "
                                + format.format(fm.maxElementAtRow(row)));
                    } catch (InvalidRowNumberException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                    }
                    break;
                }

                case "MAX_IN_COLUMN": {
                    int col = scanner.nextInt();
                    try {
                        System.out.println("Max in column: "
                                + format.format(fm.maxElementAtColumn(col)));
                    } catch (InvalidColumnNumberException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                    }
                    break;
                }

                case "SUM": {
                    System.out.println("Sum: " + format.format(fm.sum()));
                    break;
                }

                case "CHECK_EQUALS": {
                    int val = scanner.nextInt();

                    int maxOps = val % 7;

                    for (int z = 0; z < maxOps; z++) {
                        double work[] = Arrays.copyOf(info, info.length);

                        int e1 = (31 * z + 7 * val + 3 * maxOps) % info.length;
                        int e2 = (17 * z + 3 * val + 7 * maxOps) % info.length;

                        if (e1 > e2) {
                            double temp = work[e1];
                            work[e1] = work[e2];
                            work[e2] = temp;
                        }

                        DoubleMatrix f1 = fm;
                        DoubleMatrix f2 = new DoubleMatrix(work, fm.rows(),
                                fm.columns());
                        System.out
                                .println("Equals check 1: "
                                        + f1.equals(f2)
                                        + " "
                                        + f2.equals(f1)
                                        + " "
                                        + (f1.hashCode() == f2.hashCode()&&f1
                                        .equals(f2)));
                    }

                    if (maxOps % 2 == 0) {
                        DoubleMatrix f1 = fm;
                        DoubleMatrix f2 = new DoubleMatrix(new double[]{3.0, 5.0,
                                7.5}, 1, 1);

                        System.out
                                .println("Equals check 2: "
                                        + f1.equals(f2)
                                        + " "
                                        + f2.equals(f1)
                                        + " "
                                        + (f1.hashCode() == f2.hashCode()&&
                                        f1
                                                .equals(f2)));
                    }

                    break;
                }

                case "SORTED_ARRAY": {
                    double[] arr = fm.toSortedArray();

                    String arrayString = "[";

                    if (arr.length > 0)
                        arrayString += format.format(arr[0]) + "";

                    for (int i = 1; i < arr.length; i++)
                        arrayString += ", " + format.format(arr[i]);

                    arrayString += "]";

                    System.out.println("Sorted array: " + arrayString);
                    break;
                }

            }

        }

        scanner.close();
    }
}
