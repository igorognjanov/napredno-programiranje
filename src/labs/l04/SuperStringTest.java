package labs.l04;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

class SuperString{
    private LinkedList<String> strings;
    private LinkedList<String> lastAdded;

    public SuperString(){
        strings = new LinkedList<String> ();
        lastAdded = new LinkedList<> ();

    }
    public void append(String s){
        strings.addLast (s);
        lastAdded.addLast (s);
    }
    public void insert(String s){
        strings.addFirst (s);
        lastAdded.addLast (s);
    }
    public boolean contains(String s){
        String joined = strings.stream ().collect(Collectors.joining());
        return joined.contains (s);
    }
    public void reverse(){
        for(int i=0; i<strings.size (); i++){
            strings.add (0, new StringBuilder (strings.remove (i)).reverse ().toString ());
        }
        lastAdded = lastAdded.stream ().map (s -> new StringBuilder (s).reverse ().toString ())
                .collect (Collectors.toCollection (LinkedList::new));
    }
    @Override
    public String toString(){
        return strings.stream ().collect(Collectors.joining());
    }
    public void removeLast(int k) {
        int i = 0;
        while(i<k){
            strings.remove (lastAdded.removeLast());
            i++;
        }
    }
}


public class SuperStringTest {
    public static void main(String[] args) {
        String[] str = {"igor", "ognjanov"};
        SuperString superString = new SuperString ();
        superString.append (str[0]);
        superString.append (str[1]);
        superString.append ("kral3");
        superString.append ("kral4");
        superString.append ("kral5");
        superString.append ("kral6");
        superString.append ("kral7");
        //System.out.println (superString.contains ("rog"));
        superString.reverse ();
        superString.removeLast (4);
        superString.insert ("10");
        superString.insert ("20");
        superString.insert ("30");
        //superString.reverse ();
        superString.removeLast (3);
        System.out.println (superString);
    }
/*
    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (  k == 0 ) {
            SuperString s = new SuperString();
            while ( true ) {
                int command = jin.nextInt();
                if ( command == 0 ) {//append(String s)
                    s.append(jin.next());
                }
                if ( command == 1 ) {//insert(String s)
                    s.insert(jin.next());
                }
                if ( command == 2 ) {//contains(String s)
                    System.out.println(s.contains(jin.next()));
                }
                if ( command == 3 ) {//reverse()
                    s.reverse();
                }
                if ( command == 4 ) {//toString()
                    System.out.println(s);
                }
                if ( command == 5 ) {//removeLast(int k)
                    s.removeLast(jin.nextInt());
                }
                if ( command == 6 ) {//end
                    break;
                }
            }
        }
    }*/

}
