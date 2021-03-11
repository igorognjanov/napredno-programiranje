package mk.ukim.finki.aud3.ApstraktniKlasi;

public class LambdaExpression {
    public static void main(String[] args) {
        Interface addition = (a, b) -> a+b;
        System.out.println (addition.operation (10, 330));
    }
}
