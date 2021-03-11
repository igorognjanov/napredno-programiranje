package labs.l02.zadaca1;

public class InsufficientElementsException extends Exception {
    public InsufficientElementsException(){
        System.out.println ("Insufficient number of elements");
    }
}
