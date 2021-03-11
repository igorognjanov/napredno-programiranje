package Exercises.VtorKolovium.NajdobriFilmovi;

import java.util.*;
import java.util.stream.Collectors;

class Movie{
    private String title;
    private int[] ratings;

    public Movie(String title, int[] ratings){
        this.title = title;
        this.ratings = ratings;
    }

    public int getNumberOfRatings(){
        return ratings.length;
    }

    public double averageRating(){
        int count = 0;
        for(int i=0; i<ratings.length; i++){
            count += ratings[i];
        }
        return count /(double) ratings.length;
    }

    public String getTitle() {
        return title;
    }

    public double ratingCoefficient(){
        return averageRating () * (double) getNumberOfRatings ();
    }

    @Override
    public String toString() {
            return String.format ("%s (%.2f) of %d ratings", title, averageRating (), ratings.length);
        //return title + " (" + averageRating () + ") of " +ratings.length+" ratings";
     }
}

class MoviesList{
    private List<Movie> movies;

    public MoviesList(){
        movies = new ArrayList<> ();
    }

    public void addMovie(String title, int[] ratings){
        movies.add (new Movie (title, ratings));
    }

    public int getMaxNumberOfRating(){
        return movies.stream ()
                .max (Comparator.comparing (Movie::getNumberOfRatings))
                .get ().getNumberOfRatings ();
    }

    public List<Movie> top10ByAvgRating(){
        return movies.stream ()
                .sorted ((a, b) -> {
                    if(a.averageRating () == b.averageRating ()){
                        return a.getTitle ().compareTo (b.getTitle ());
                    }
                    return Double.compare (b.averageRating (), a.averageRating ());
                })
                .limit (10).collect (Collectors.toList ());
    }

    public List<Movie> top10ByRatingCoef(){
        return movies.stream ()
                .sorted ((a, b) -> {
                    if(b.ratingCoefficient ()/getMaxNumberOfRating () == a.ratingCoefficient ()/getMaxNumberOfRating ())
                        return a.getTitle ().compareTo (b.getTitle ());
                    return
                        Double.compare (b.ratingCoefficient ()/getMaxNumberOfRating ()
                                , a.ratingCoefficient ()/getMaxNumberOfRating ());
                })
                .limit (10)
                .collect (Collectors.toList ());
    }

}

public class MoviesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MoviesList moviesList = new MoviesList();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            int x = scanner.nextInt();
            int[] ratings = new int[x];
            for (int j = 0; j < x; ++j) {
                ratings[j] = scanner.nextInt();
            }
            scanner.nextLine();
            moviesList.addMovie(title, ratings);
        }
        scanner.close();
        List<Movie> movies = moviesList.top10ByAvgRating();
        System.out.println("=== TOP 10 BY AVERAGE RATING ===");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
        movies = moviesList.top10ByRatingCoef();
        System.out.println("=== TOP 10 BY RATING COEFFICIENT ===");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
}

// vashiot kod ovde