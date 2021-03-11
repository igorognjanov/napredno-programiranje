package mk.ukim.finki.aud5;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;

interface Drawable{
    void draw();
}



public class Box<T extends Drawable> {
    private ArrayList<T> elements;

    public Box() {
        this.elements = new ArrayList<> ();
    }

    public void add(T el){
        this.elements.add (el);
    }

    public boolean isEmpty(){
        return elements.size () == 0;
    }

    public void drawItem(){
        if (isEmpty ()) return;
        Random r = new Random ();
        System.out.println (r.nextInt (50));
        //elements.remove (new Random ().nextInt (elements.size ())).draw ();
    }


    public static void main(String[] args) {
        Box<Drawable> box = new Box<> ();
        Drawable dr = new Drawable () {
            @Override
            public void draw() {
                System.out.println (new Random ().nextInt (50));
            }
        };
        for(int i=0; i<100; i++)
            box.add (()-> System.out.println (dr));//() -> System.out.println (new Random().nextInt (50)));
        for(int i=0; i<50; i++)
            box.drawItem ();
    }
}
