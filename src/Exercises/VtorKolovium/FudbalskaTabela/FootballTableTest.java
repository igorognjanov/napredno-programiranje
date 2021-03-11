package Exercises.VtorKolovium.FudbalskaTabela;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Partial exam II 2016/2017
 */

class Team implements Comparable<Team>{
    String name;
    private int points;
    private int wins;
    private int draws;
    private int played;
    private int goalsScored;
    private int goalsReceived;

    public Team(String name){
        this.name = name;
        points = wins = draws = played = goalsReceived = goalsScored = 0;
    }

    private void updatePoints(){
        points = 3 * wins + draws;
    }

    public void increasePlayed(int thisGoals, int otherGoals){
        played++;
        if(thisGoals > otherGoals){
            wins ++;
        }
        if(thisGoals == otherGoals)
            draws ++;
        goalsScored += thisGoals;
        goalsReceived += otherGoals;
        updatePoints ();
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getPlayed() {
        return played;
    }

    public int goalDifference(){
        return goalsScored - goalsReceived;
    }

    @Override
    public int compareTo(Team o) {
        if(this.points == o.points){
            if(this.goalDifference () == o.goalDifference ()){
                return o.name.compareTo (this.name);
            }
            return Integer.compare (this.goalDifference (), o.goalDifference ());
        }
        return Integer.compare (this.points, o.points);
    }
}

class Counter{
    int i;

    public Counter(){
        i=1;
    }

    public void increment(){
        i++;
    }
    public int getI(){
        return i;
    }
}

class FootballTable{
    HashMap<String, Team> tabela;
    //(a, b) -> tabela.get (b).compareTo (tabela.get (a))
    public FootballTable(){
        tabela = new HashMap<String, Team> ();
    }

    public void addGame(String homeTeam, String awayTeam, int homeGoals, int awayGoals){
        tabela.putIfAbsent (homeTeam, new Team (homeTeam));
        tabela.putIfAbsent (awayTeam, new Team (awayTeam));
        tabela.get (homeTeam).increasePlayed (homeGoals, awayGoals);
        tabela.get (awayTeam).increasePlayed (awayGoals, homeGoals);
    }

    public void printTable(){
        Counter i=new Counter ();
        tabela.keySet ().stream ()
                .sorted ((a, b) ->tabela.get (b).compareTo (tabela.get (a)))
                .forEach (team ->{
                    Team d = tabela.get (team);
                    System.out.printf("%2d. %-15s%5s%5s%5s%5s%5s\n",
                        i.getI (),
                        d.getName (),
                        d.getPlayed (),
                        d.getWins (),
                        d.getDraws (),
                        d.getPlayed () - d.getWins () - d.getDraws (),
                        d.getPoints ());
                    i.increment ();
                });
    }

}

public class FootballTableTest {
    public static void main(String[] args) throws IOException {
        FootballTable table = new FootballTable();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.lines()
                .map(line -> line.split(";"))
                .forEach(parts -> table.addGame(parts[0], parts[1],
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3])));
        reader.close();
        System.out.println("=== TABLE ===");
        System.out.printf("%-19s%5s%5s%5s%5s%5s\n", "Team", "P", "W", "D", "L", "PTS");
        table.printTable();
    }
}

// Your code here

