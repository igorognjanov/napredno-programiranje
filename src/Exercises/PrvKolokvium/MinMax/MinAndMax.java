package Exercises.PrvKolokvium.MinMax;

import java.util.Scanner;

class MinMax<T extends Comparable<T>>{
    private T t1;
    private T t2;
    private int processed;

    public MinMax(){
        processed = 0;
    }
    public void update(T element){
        if(t1 == null && t2 == null) {
            t1 = element;
            processed++;
        }
        else if(t2 == null){
            if(t1.compareTo (element) >0){
                t2 = element;
                processed++;
            }
            else{
                t2 = t1;
                t1 = element;
                processed++;
            }
        }
        else{
            if(element.compareTo (t1) < 0){
                t1 = element;
                processed++;
            }
            else if(element.compareTo (t2) > 0){
                t2 = element;
                processed++;
            }

        }
    }

    public String toString(){
        return t1.toString () + " " + t2.toString () + " " + processed;
    }

}



public class MinAndMax {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        MinMax<String> strings = new MinMax<String>();
        for(int i = 0; i < n; ++i) {
            String s = scanner.next();
            strings.update(s);
        }
        System.out.println(strings);
        MinMax<Integer> ints = new MinMax<Integer>();
        for(int i = 0; i < n; ++i) {
            int x = scanner.nextInt();
            ints.update(x);
        }
        System.out.println(ints);
    }
}
