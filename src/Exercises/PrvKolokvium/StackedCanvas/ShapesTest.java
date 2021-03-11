package Exercises.PrvKolokvium.StackedCanvas;

import java.util.ArrayList;
import java.util.Scanner;

enum Color{
    RED, GREEN, BLUE
}

interface Scalable{
    void scale(float scaleFactor);
}
interface Stackable{
    float weight();
}

abstract class Shape implements Scalable, Stackable{
    protected String id;
    protected Color color;
    protected char type;

    public Shape(String id, Color color) {
        this.id = id;
        this.color = color;
    }

    abstract public void scale(float scaleFactor);

    abstract public float weight();
}

class Rectangle extends Shape{
    private float width;
    private float height;

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Rectangle(String id, Color color, float width, float height) {
        super (id, color);
        this.width = width;
        this.height = height;
        this.type = 'R';
    }

    @Override
    public float weight() {
        return width * height;
    }

    public void scale(float scaleFactor){
        width *= scaleFactor;
        height *= scaleFactor;
    }
}

class Circle extends Shape{
    private float radius;

    public float getRadius() {
        return radius;
    }

    public Circle(String id, Color color, float radius) {
        super (id, color);
        this.type = 'C';
        this.radius = radius;
    }

    @Override
    public float weight() {
        return (float)Math.PI*radius*radius;
    }

    public void scale(float scaleFactor){
        radius *= scaleFactor;
    }
}


class Canvas{
    private ArrayList<Shape> shapes;

    public Canvas() {
        shapes = new ArrayList<> ();
    }

    public void add(String id, Color color, float radius){
        int i;
        Circle circle = new Circle (id, color, radius);
        for(i=0; i<shapes.size () ;i ++){
            if(shapes.get (i).weight () <= circle.weight ())
                break;
        }
        shapes.add (i, circle);
    }

    public void add(String id, Color color, float width, float height){
        Rectangle rectangle = new Rectangle (id, color, width, height);
        int i;
        for(i=0; i<shapes.size () ;i ++){
            if(shapes.get (i).weight () <= rectangle.weight ())
                break;
        }
        shapes.add (i, rectangle);
    }

    public void scale(String id, float scaleFactor){
        Shape shape=null;
        for(int i=0; i<shapes.size (); i++){
            if(shapes.get (i).id.equals (id)) {
                shape = shapes.remove (i);
                break;
            }
        }
        if(shape != null)
            shape.scale (scaleFactor);
        add(shape);
    }

    public void add(Shape shape){
        if(shape.type == 'R'){
            Rectangle rectangle = (Rectangle) shape;
            add (rectangle.id, rectangle.color, rectangle.getWidth (), rectangle.getHeight ());
        }
        else{
            Circle circle = (Circle) shape;
            add (circle.id, circle.color, circle.getRadius ());
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder ();
        shapes.stream ().forEach (shape -> {
            sb.append (String.format ("%c:%-5s", shape.type, shape.id));
            if(shape.color == Color.BLUE) sb.append (String.format ("%-10s", "BLUE"));
            if(shape.color == Color.GREEN) sb.append (String.format ("%-10s", "GREED"));
            if(shape.color == Color.RED) sb.append (String.format ("%-10s", "RED"));
            sb.append (String.format ("%10.2f\n", shape.weight ()));
        });
        return sb.toString ();

    }

}

public class ShapesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Canvas canvas = new Canvas();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            int type = Integer.parseInt(parts[0]);
            String id = parts[1];
            if (type == 1) {
                Color color = Color.valueOf(parts[2]);
                float radius = Float.parseFloat(parts[3]);
                canvas.add(id, color, radius);
            } else if (type == 2) {
                Color color = Color.valueOf(parts[2]);
                float width = Float.parseFloat(parts[3]);
                float height = Float.parseFloat(parts[4]);
                canvas.add(id, color, width, height);
            } else if (type == 3) {
                float scaleFactor = Float.parseFloat(parts[2]);
                System.out.println("ORIGNAL:");
                System.out.print(canvas);
                canvas.scale(id, scaleFactor);
                System.out.printf("AFTER SCALING: %s %.2f\n", id, scaleFactor);
                System.out.print(canvas);
            }

        }
    }
}