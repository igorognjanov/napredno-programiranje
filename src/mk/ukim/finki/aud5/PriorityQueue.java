package mk.ukim.finki.aud5;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.Collectors;

class Node<T extends Comparable<T>>{
    T element;
    int priority;

    public Node(T element, int priority) {
        this.element = element;
        this.priority = priority;
    }

    public int compareTo(Node<T> n){
        return Integer.compare (this.priority, n.priority);
    }
}

public class PriorityQueue <T extends Comparable<T>>{
    private List<Node<T>> elements;

    public PriorityQueue(){
        elements = new ArrayList<> ();
    }

    public boolean isEmpty(){
        return elements.size () == 0;
    }

    public void add(T item, int priority){
        Node<T> node = new Node<> (item, priority);
        if(isEmpty ()) {
            elements.add (node);
        }
        else{
            int i;
            for(i=0; i<elements.size (); i++){
                if(elements.get (i).compareTo (node) >= 0){
                    break;
                }
            }
            elements.add (i, node);
        }
    }

    public T remove(){
        if(isEmpty ()) throw new NoSuchElementException();
        return elements.remove (elements.size ()-1).element;
    }

    public static void main(String[] args) {
        PriorityQueue<String> stringPriorityQueue = new PriorityQueue<> ();
        stringPriorityQueue.add ("10", 10);
        stringPriorityQueue.add ("12", 12);
        stringPriorityQueue.add ("13", 13);
        stringPriorityQueue.add ("2", 2);
        stringPriorityQueue.add ("1334", 1334);
        stringPriorityQueue.add ("-12", -12);
        System.out.println (stringPriorityQueue.remove ());
        System.out.println (stringPriorityQueue.remove ());
        System.out.println (stringPriorityQueue.remove ());
        System.out.println (stringPriorityQueue.remove ());
        System.out.println (stringPriorityQueue.remove ());
        System.out.println (stringPriorityQueue.remove ());
    }
}
