package mk.ukim.finki.np.vezhbi.ispit;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//IMA NEKADE GRESHKA

class Movie {
    private String title;
    private List<Integer> ratings;

    public Movie(String title, int [] ratings) {
        this.title = title;
        this.ratings = IntStream.range(0, ratings.length)
                .mapToObj(i -> ratings[i])
                .collect(Collectors.toList());
    }

    public String getTitle() {
        return title;
    }

    public double averageRating() {
        return this.ratings.stream()
                .mapToInt(i -> i)
                .sum()*1.0/this.ratings.size();
    }

    public int getRatingsLength(){
        return this.ratings.size();
    }

    public double ratingCoeficient() {
        return this.averageRating() * this.getRatingsLength() / MoviesList.maxRating;
    }

    public List<Integer> getRatings() {
        return ratings;
    }
    @Override
    public String toString() {
        return String.format("%s (%.2f) %d",
                this.title,
                this.averageRating(),
                this.getRatingsLength());
    }
}

class MoviesList {
    private List<Movie> movieList;
    private Comparator<Movie> comparator = Comparator
            .comparing(Movie::averageRating)
            .thenComparing(Movie::getTitle);
    static int maxRating = -1;
    private Comparator<Movie> coeficientComparator = Comparator
            .comparing(Movie::ratingCoeficient);

    public MoviesList() {
        this.movieList = new ArrayList<>();
    }

    public void addMovie(String title, int[] ratings) {
        this.movieList.add(new Movie(title,ratings));
    }

    public List<Movie> top10ByAvgRating() {
        return this.movieList.stream()
                .sorted(comparator.reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public void maxRatings() {
        maxRating = this.movieList.stream()
                .mapToInt(i -> i.getRatings().size())
                .max()
                .getAsInt();
    }

    public List<Movie> top10ByRatingCoef() {
        return this.movieList.stream()
                .sorted(coeficientComparator)
                .limit(10)
                .collect(Collectors.toList());
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