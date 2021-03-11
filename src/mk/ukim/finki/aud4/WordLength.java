package mk.ukim.finki.aud4;

import java.io.*;
import java.util.Scanner;
import java.util.function.Consumer;

class FileConsumer implements Consumer<String>{
    int wordsCounter, charachtersCounter, linesCounter;
    @Override
    public void accept(String s){
        String[] words = s.split ("\\s+");
        wordsCounter += words.length;
        linesCounter++;
        for(int i=0; i<words.length; i++){
            charachtersCounter += words[i].length ();
        }
    }

    @Override
    public String toString(){
        return"Lines: " + linesCounter +"\nWords: "+ wordsCounter+"\nCharachters: " + charachtersCounter;
    }
}

class Counter{
    int linesCounter, wordsCounter, charachterCounter;

    public Counter(int linesCounter, int wordsCounter, int charachterCounter) {
        this.linesCounter = linesCounter;
        this.wordsCounter = wordsCounter;
        this.charachterCounter = charachterCounter;
    }

    public Counter add(Counter counter){
        return new Counter (this.linesCounter + counter.linesCounter,
                this.wordsCounter + counter.wordsCounter,
        this.charachterCounter + counter.charachterCounter);
    }

    public Counter(String line){
        String[] words = line.split ("\\s+");
        this.linesCounter=1;
        wordsCounter += words.length;
        for(int i=0; i<words.length; i++){
            charachterCounter += words[i].length ();
        }
    }

    @Override
    public String toString(){
        return"Lines: " + linesCounter +"\nWords: "+ wordsCounter+"\nCharachters: " + charachterCounter;
    }


}

public class WordLength {

    public static void calculateWithBufferedReaderAndMapReduce(InputStream input){
        BufferedReader bufferedReader = new BufferedReader (new InputStreamReader (input));
        System.out.println (bufferedReader.lines ().map (line -> new Counter (line))
                .reduce(
                        new Counter (0,0,0), (a, b) -> a.add(b)
        ));

    }

    public static void calculateWithBufferedReaderAndConsumer(InputStream input){
        BufferedReader bufferedReader = new BufferedReader (new InputStreamReader (input));
        FileConsumer fileConsumer = new FileConsumer ();
        bufferedReader.lines ().forEach (fileConsumer::accept);
        System.out.println (fileConsumer);
    }


    public static void calculateWithBufferedReader(InputStream input) throws IOException {
        int linesCounter=0;
        int wordsCounter=0;
        int charachtersCounter=0;
        BufferedReader reader = new BufferedReader(new InputStreamReader (input));
        String line;
        while ((line = reader.readLine ()) != null){
            String[] words = line.split ("\\s+");
            wordsCounter += words.length;
            for(int i=0; i<words.length; i++){
                charachtersCounter += words[i].length ();
            }
            linesCounter++;
        }
        System.out.printf ("Lines: %d\nWords: %d\nCharachters: %d", linesCounter, wordsCounter, charachtersCounter);
    }

    public static void calculateWithScanner(InputStream input){
        int linesCounter=0;
        int wordsCounter=0;
        int charachtersCounter=0;
        Scanner scanner = new Scanner(input);
        while(scanner.hasNextLine ()){
            linesCounter++;
            String line = scanner.nextLine ();
            String[] words = line.split ("\\s+");
            wordsCounter += words.length;
            for(int i=0; i<words.length; i++){
                charachtersCounter += words[i].length ();
            }
        }
        System.out.printf ("Lines: %d\nWords: %d\nCharachters: %d", linesCounter, wordsCounter, charachtersCounter);
    }


    public static void main(String[] args) {
        File file = new File ("C:\\Users\\Igor\\IdeaProjects\\NP2020\\src\\mk\\ukim\\finki\\aud4\\Dat");
        try {
            calculateWithBufferedReaderAndMapReduce (new FileInputStream (file));
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
