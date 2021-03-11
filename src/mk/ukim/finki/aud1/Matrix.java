package mk.ukim.finki.aud1;

public class Matrix {
    public static final double pi = 3.14;
    public static double sumOfMatrix(double [] [] matrix){
        double sum = 0.0;
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                sum += matrix[i][j];
            }
        }
        return sum;
    }
    public static double averageOfMatrix(double [] [] matrix){
        return sumOfMatrix(matrix)/(matrix.length * matrix[0].length);
    }

    public static void main(String[] args) {
        int a = 0;
        double b = 2.3;
        boolean c = true;
        byte d = 5;
        double [] [] matrix = {{1, 2, 3}, {0, 0, 0}};
        for (int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                System.out.print(matrix[i][j]+"  ");
            }
            System.out.println();
        }
        System.out.println(averageOfMatrix(matrix));
        System.out.println(Math.pow(2, 10));
        System.out.println();
    }

}
