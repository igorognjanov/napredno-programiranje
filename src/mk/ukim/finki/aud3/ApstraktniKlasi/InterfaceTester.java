package mk.ukim.finki.aud3.ApstraktniKlasi;

public class InterfaceTester {

    public static void main(String[] args) {

        Interface adition = new Interface () {
            @Override
            public double operation(int a, int b) {
                return a + b;
            }
        };

        System.out.println (adition.operation (10, 20));
    }
}
