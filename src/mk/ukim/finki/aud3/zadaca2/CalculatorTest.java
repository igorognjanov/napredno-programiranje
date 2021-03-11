package mk.ukim.finki.aud3.zadaca2;

import java.util.Scanner;

public class CalculatorTest {
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        while(true){
            Calculator calculator = new Calculator ();

            while(true){
                String line = input.nextLine ();
                String [] strings = line.split ("\\s+");
                char operation = strings[0].charAt (0);
                if(operation == 'r'){
                    System.out.println (calculator.getResult ());
                    break;
                }

                double value = Double.parseDouble (strings[1]);
                try {
                    System.out.println (calculator.execute (operation, value));
                }
                catch (UknownOperationException u){
                    System.out.println (u.getMessage ());
                }
            }

            System.out.println ("Y/N");
            String yn = input.next ();
            if(Character.toLowerCase (yn.charAt (0)) == 'n'){
                System.out.println ("End f program");
                break;
            }
        }


    }
}
