package Exercises.VtorKolovium.Components;



import labs.l02.zadaca1.InvalidRowNumberException;

import java.util.*;


class Component implements Comparable<Component>{
    String color;
    int weight;
    Set<Component> components;

    public Component(String color, int weight) {
        this.color = color;
        this.weight = weight;
        components = new TreeSet<> ();
    }

    void addComponent(Component component){
        components.add (component);
    }

    public int compareTo(Component that){
        if(this.weight == that.weight){
            return this.color.compareTo (that.color);
        }
        return Integer.compare (this.weight, that.weight);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder ();
        sb.append (weight + ":" + color + "\n");
        for(Component component : components){
            sb.append (component.toString ());
        }
        return sb.toString ();
    }
}

class Window{
    String name;
    Map<Integer, Component> components;

    public Window(String name) {
        this.name = name;
        components = new TreeMap<> ();
    }

    void addComponent(int position, Component component) throws InvalidPositionException {
        if(components.containsKey (position))
            throw new InvalidPositionException (position);
        else components.put (position, component);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder ("WINDOW " + name);
        components.values ().stream ().forEach (component -> sb.append (component.toString ()));
        return sb.toString ();
    }
}

class InvalidPositionException extends Exception{
    int pos;

    public InvalidPositionException(int pos){
        this.pos = pos;
    }

    public String getMessage(){
        return "Invalid position " + pos + ", already taken!";
    }
}

public class ComponentTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Window window = new Window(name);
        Component prev = null;
        while (true) {
            try {
                int what = scanner.nextInt();
                scanner.nextLine();
                if (what == 0) {
                    int position = scanner.nextInt();
                    window.addComponent(position, prev);
                } else if (what == 1) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev = component;
                } else if (what == 2) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                    prev = component;
                } else if (what == 3) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                } else if(what == 4) {
                    break;
                }

            } catch (InvalidPositionException e) {
                System.out.println(e.getMessage());
            }
            scanner.nextLine();
        }

        System.out.println("=== ORIGINAL WINDOW ===");
        System.out.println(window);
        int weight = scanner.nextInt();
        scanner.nextLine();
        String color = scanner.nextLine();
//        window.changeColor(weight, color);
//        System.out.println(String.format("=== CHANGED COLOR (%d, %s) ===", weight, color));
//        System.out.println(window);
//        int pos1 = scanner.nextInt();
//        int pos2 = scanner.nextInt();
//        System.out.println(String.format("=== SWITCHED COMPONENTS %d <-> %d ===", pos1, pos2));
//        window.swichComponents(pos1, pos2);
//        System.out.println(window);
    }
}

// вашиот код овде