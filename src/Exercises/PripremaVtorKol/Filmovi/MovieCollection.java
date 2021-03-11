package Exercises.PripremaVtorKol.Filmovi;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

class Movie implements Comparable<Movie>{
    private String title;
    private String director;
    private String genre;
    private float rating;

    public Movie(String title, String director, String genre, float rating) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.rating = rating;
    }

    public int compareTo(Movie that){
        if(this.title.toLowerCase ().equals (that.title.toLowerCase ())){
            return Float.compare (this.rating, that.rating);
        }
        return this.title.toLowerCase ().compareTo (that.title.toLowerCase ());
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    public float getRating() {
        return rating;
    }
}
public class MovieCollection{
    private Map<String, Set<Movie>> moviesByGenre;
    private List<Movie> movies;
    private Map<String, Integer> numberOfMoviesByDirector;


    public void addMovie(Movie movie){
        movies.add (movie);

        moviesByGenre.putIfAbsent (movie.getGenre (), new TreeSet<Movie>());
        moviesByGenre.get (movie.getGenre ()).add (movie);

        numberOfMoviesByDirector.putIfAbsent (movie.getDirector (), 0);
        numberOfMoviesByDirector.put(movie.getDirector (), numberOfMoviesByDirector.get (movie.getDirector ()) +1);
    }

    public void printByGenre(String genre){

    }

    public List<Movie> getTopRatedN(int n){
        return movies;
    }

    public Map<String, Integer> getCountByDirector(String director){
        return numberOfMoviesByDirector;
    }
}
