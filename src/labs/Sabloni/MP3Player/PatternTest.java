package labs.Sabloni.MP3Player;

import java.util.ArrayList;
import java.util.List;

class Song{
    private String title;
    private String artist;

    public Song(String name, String singer) {
        this.title = name;
        this.artist = singer;
    }


    @Override
    public String toString() {
        return "Song{" +
                "title=" + title +
                ", artist=" + artist +
                '}';
    }
}

class MP3Player{
    private List<Song> songList;
    private int currentSong;
    private int stopped;

    private static final int PLAYING = 0;
    private static final int STOPPED = 1;
    private static final int ALREADY_STOPPED = 2;

    public MP3Player(List<Song> songs) {
        this.songList = songs;
        currentSong = 0;
        stopped = 1;
    }

    public void pressPlay(){
        if(stopped == STOPPED || stopped == ALREADY_STOPPED){
            System.out.println ("Song " + currentSong + " is playing");
            stopped = PLAYING;
        }
        else{
            System.out.println ("Song is already playing");
        }
    }

    public void printCurrentSong(){
        System.out.println (songList.get (currentSong));
    }

    public void pressStop(){
        if(stopped == PLAYING){
            System.out.println ("Song " + currentSong+ " is paused");
            stopped = STOPPED;
        }
        else if (stopped == STOPPED){
            System.out.println ("Songs are stopped");
            currentSong = 0;
            stopped = ALREADY_STOPPED;
        }
        else System.out.println ("Songs are already stopped");
    }

    public void pressFWD(){
        stopped = STOPPED;
        currentSong = (currentSong+1) % songList.size ();
        System.out.println ("Forward...");
    }

    public void pressREW(){
        stopped = STOPPED;
        currentSong--;
        if(currentSong == -1){
            currentSong = songList.size ()-1;
        }
        System.out.println ("Reward...");

    }


    @Override
    public String toString() {
        return "MP3Player{" +
                "currentSong = " + currentSong +
                ", songList = " + songList +
                '}';
    }
}




public class PatternTest {
    public static void main(String args[]) {
        List<Song> listSongs = new ArrayList<Song>();
        listSongs.add(new Song("first-title", "first-artist"));
        listSongs.add(new Song("second-title", "second-artist"));
        listSongs.add(new Song("third-title", "third-artist"));
        listSongs.add(new Song("fourth-title", "fourth-artist"));
        listSongs.add(new Song("fifth-title", "fifth-artist"));
        MP3Player player = new MP3Player(listSongs);


        System.out.println(player.toString());
        System.out.println("First test");


        player.pressPlay();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
        System.out.println("Second test");


        player.pressStop();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
        System.out.println("Third test");


        player.pressFWD();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
    }
}

//Vasiot kod ovde