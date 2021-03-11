package labs.l02.zadaca3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

enum TYPE {
    POINT,
    CIRCLE
}

enum DIRECTION {
    UP,
    DOWN,
    LEFT,
    RIGHT
}


class CirclesTester {

    public static interface Movable{
        void moveUp();
        void moveDown();
        void moveLeft();
        void moveRight();
        int getCurrentXPosition();
        int getCurrentYPosition();
    }

    public static class MovablePoint implements Movable{
        private int x;
        private int y;
        private int xSpeed;
        private int ySpeed;

        public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
            this.x = x;
            this.y = y;
            this.xSpeed = xSpeed;
            this.ySpeed = ySpeed;
        }
         @Override
         public String toString(){
            String str = "Moving point with coordinates (" + x + "," + y + ")";
            return str;
         }

        public void moveUp(){
            y += ySpeed;
        }
        public void moveDown(){
            y-= ySpeed;
        }
        public void moveLeft(){
            x-= xSpeed;
        }
        public void moveRight(){ x+= xSpeed; }
        public int getCurrentXPosition(){
            return x;
        }
        public int getCurrentYPosition(){
            return y;
        }
    }

    public static class MovableCircle implements Movable{
        private int radius;
        private MovablePoint center;

        public MovableCircle(int radius, MovablePoint center) {
            this.radius = radius;
            this.center = center;
        }



        public void moveUp() { center.moveUp ();}
        public void moveDown(){
            center.moveDown ();
        }
        public void moveLeft(){
            center.moveLeft ();
        }
        public void moveRight(){
            center.moveRight ();
        }
        public int getCurrentXPosition(){
            return center.x;
        }
        public int getCurrentYPosition(){
            return center.y;
        }

        @Override
        public String toString(){
            String str = "Movable circle with center coordinates (" + center.x + "," + center.y + ") and radius " + radius;
            return str;
        }
    }

    public static class ObjectCanNotBeMovedException extends Exception{
        private int x;
        private int y;
        public ObjectCanNotBeMovedException(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public String getMessage(){
            return "Point (" + x + "," + y + ") is out of bounds";
        }
    }



    public static class MovablesCollection{
        private static int maxX;
        private static int maxY;
        Movable[] movables;

        public MovablesCollection(int x_MAX, int y_MAX){
            maxX = x_MAX;
            maxY = y_MAX;
            movables = new Movable[0];
        }

        public static void setxMax(int m){
            maxX = m;
        }
        public static void setyMax(int m){
            maxY = m;
        }


        public void addMovableObject(Movable m) throws MovableObjectNotFittableException{
            if(m.getCurrentXPosition () < 0 || m.getCurrentXPosition () > maxX ||
            m.getCurrentYPosition () < 0 || m.getCurrentYPosition () > maxY){
                throw new MovableObjectNotFittableException(m.getCurrentXPosition (), m.getCurrentYPosition ());
            }
            MovableCircle t = new MovableCircle (10, new MovablePoint (10, 10, 20, 20));
            if(m.getClass () == t.getClass ()){
                MovableCircle temp = (MovableCircle) m;
                if(temp.getCurrentXPosition () + temp.radius > maxX
                || temp.getCurrentYPosition () + temp.radius > maxY ||
                temp.getCurrentYPosition () - temp.radius < 0 ||
                temp.getCurrentXPosition () - temp.radius < 0){
                    throw new MovableObjectNotFittableException (temp.getCurrentXPosition (), temp.getCurrentYPosition (), temp.radius);
                }
            }
            Movable[] temp = new Movable[movables.length+1];
            for(int i=0; i<movables.length; i++){
                temp[i] = movables[i];
            }
            temp[movables.length] = m;
            movables = temp;

        }

        public void moveObjectsFromTypeWithDirection (TYPE type, DIRECTION direction) throws ObjectCanNotBeMovedException {
            if (type == TYPE.valueOf ("POINT")) {
                MovablePoint temp = new MovablePoint (10, 10, 20, 30);
                for (int i = 0; i < movables.length; i++) {
                    if (movables[i].getClass () == temp.getClass ()) {
                        if(direction == DIRECTION.DOWN){
                            movables[i].moveDown ();
                            if(movables[i].getCurrentYPosition () < 0){
                                int r=movables[i].getCurrentYPosition ();
                                int m =movables[i].getCurrentXPosition ();
                                movables[i].moveUp ();
                                throw new ObjectCanNotBeMovedException (m, r);
                            }
                        }
                        else if(direction == DIRECTION.UP){
                            movables[i].moveUp ();
                            if(movables[i].getCurrentYPosition () > maxY){
                                int r=movables[i].getCurrentYPosition ();
                                int m =movables[i].getCurrentXPosition ();
                                movables[i].moveDown ();
                                throw new ObjectCanNotBeMovedException (m, r);
                            }
                        }
                        else if(direction == DIRECTION.LEFT){
                            movables[i].moveLeft ();
                            if(movables[i].getCurrentYPosition () < 0){
                                int r=movables[i].getCurrentYPosition ();
                                int m =movables[i].getCurrentXPosition ();
                                movables[i].moveRight ();
                                throw new ObjectCanNotBeMovedException (m , r);
                            }
                        }
                        else {
                            movables[i].moveRight ();
                            if(movables[i].getCurrentYPosition () > maxX){
                                int r=movables[i].getCurrentYPosition ();
                                int m =movables[i].getCurrentXPosition ();
                                movables[i].moveLeft ();
                                throw new ObjectCanNotBeMovedException (m, r);
                            }
                        }

                    }
                }
            } else {
                Movable temp;//new MovableCircle (10, new MovablePoint (30, 30, 50, 50));
                for (int i = 0; i < movables.length; i++) {
                    temp = movables[i];
                    if(direction == DIRECTION.DOWN){
                        movables[i].moveDown ();
                    }
                    else if(direction == DIRECTION.UP){
                        movables[i].moveUp ();
                    }
                    else if(direction == DIRECTION.LEFT){
                        movables[i].moveLeft ();
                    }
                    else movables[i].moveRight ();

                }
            }
        }


        @Override
        public String toString(){
            String str = "Collection of movable objects with size " + movables.length + ":\n";
            for(int i=0; i<movables.length; i++){
                str+= movables[i].toString () + "\n";
            }
            return str;

        }
    }

    public static class MovableObjectNotFittableException extends Exception{
        private int x;
        private int y;
        private int radius;

        public MovableObjectNotFittableException(int x, int y, int radius){
            this.x = x;
            this.y = y;
            this.radius = radius;
        }
        public MovableObjectNotFittableException(int x, int y){
            this.x = x;
            this.y = y;
            this.radius = -1;
        }

        public String getMessage(){
            if(radius != -1)
            return "Movable circle with center ("+x+","+y + ") and radius " + radius + " can not be fitted into the collection";
            return "";
        }
    }


    public static void main(String[] args) {

        System.out.println("===COLLECTION CONSTRUCTOR AND ADD METHOD TEST===");
        MovablesCollection collection = new MovablesCollection(100, 100);
        Scanner sc = new Scanner(System.in);
        int samples = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < samples; i++) {
            String inputLine = sc.nextLine();
            String[] parts = inputLine.split(" ");

            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int xSpeed = Integer.parseInt(parts[3]);
            int ySpeed = Integer.parseInt(parts[4]);

            if (Integer.parseInt(parts[0]) == 0) { //point
                try {
                    collection.addMovableObject(new MovablePoint(x, y, xSpeed, ySpeed));
                } catch (MovableObjectNotFittableException e) {
                    System.out.println (e.getMessage ());
                }
            } else { //circle
                int radius = Integer.parseInt(parts[5]);
                try {
                    collection.addMovableObject(new MovableCircle(radius, new MovablePoint(x, y, xSpeed, ySpeed)));
                } catch (MovableObjectNotFittableException e) {
                    System.out.println (e.getMessage ());
                }
            }

        }
        System.out.println(collection.toString());

        System.out.println("MOVE POINTS TO THE LEFT");
        try {
            collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.LEFT);
        } catch (ObjectCanNotBeMovedException e) {
            e.printStackTrace ();
        }
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES DOWN");
        try {
            collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);
        } catch (ObjectCanNotBeMovedException e) {
            e.printStackTrace ();
        }
        System.out.println(collection.toString());

        System.out.println("CHANGE X_MAX AND Y_MAX");
        MovablesCollection.setxMax(90);
        MovablesCollection.setyMax(90);

        System.out.println("MOVE POINTS TO THE RIGHT");
        try {
            collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.RIGHT);
        } catch (ObjectCanNotBeMovedException e) {
            e.printStackTrace ();
        }
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES UP");
        try {
            collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.UP);
        } catch (ObjectCanNotBeMovedException e) {
            e.printStackTrace ();
        }
        System.out.println(collection.toString());

    }


}
