package mk.ukim.finki.np.juni.Movies;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class Movie {
    String title;
    String director;
    String genre;
    float rating;

    public Movie(String title, String director, String genre, float rating) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %s) %.2f",
                this.title,
                this.director,
                this.genre,
                this.rating);
    }
}

class MoviesCollection {
    List<Movie> movieList;
    final Comparator<Movie> titleRatingComparator = Comparator.comparing(Movie::getTitle)
            .thenComparing(Movie::getRating);
    final Comparator<Movie> ratingComparator = Comparator.comparing(Movie::getRating);
    final Comparator<Movie> directorNameComparator = Comparator.comparing(Movie::getDirector);

    public MoviesCollection() {
        movieList = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        movieList.add(movie);
    }

    public void printByGenre(String genre) {
        movieList.stream()
                .filter(i -> i.getGenre().equals(genre))
                .sorted(titleRatingComparator)
                .forEach(i -> System.out.println(i));
    }

    public List<Movie> getTopRatedN(int printN) {
        return movieList.stream()
                .sorted(ratingComparator.reversed())
                .limit(printN)
                .collect(Collectors.toList());
    }

    private int countMovies(String directorName) {
        return (int)movieList.stream()
                .filter(i -> i.getDirector().equals(directorName))
                .mapToInt(k -> Integer.parseInt(String.valueOf(k.getRating())))
                .count();
    }

    public Map<String, Integer> getCountByDirector(int n) {
        Map<String, Long> temp =  movieList.stream()
                .sorted(directorNameComparator)
                .collect(Collectors.groupingBy(
                        i -> i.getDirector(),
                        Collectors.counting()
                ));



//        Map<String, Integer> finalTemp = movieList.stream()
//                .sorted(directorNameComparator)
//                .collect(Collectors.groupingBy(
//                        i-> i.getDirector(),
//                        Collectors.summingInt()
//                ));

        Map<String, Integer> finalMap = new TreeMap<>();



//        Set<String> local1 = temp.keySet();
//        Collection<Long> local2 = temp.values();


        //дава ерор на мудл кога претворам од сет во стринг
        List<String> directorNames = temp.keySet().stream().toList();
        List<Long> directorNumbers = temp.values().stream().toList();
        List<Integer> directorNumbersFinal = new ArrayList<>();



        for(int i=0; i<directorNumbers.size(); i++) {
            directorNumbersFinal.add(Integer.parseInt(String.valueOf(directorNumbers.get(i))));
        }


        for(int i=0; i<directorNumbersFinal.size(); i++) {
            finalMap.put(directorNames.get(i),directorNumbersFinal.get(i));
        }

        return finalMap;

    }
    /*
    public Map<String, Integer> getCountByDirector(int n){
        Map<String,Integer> nova = new TreeMap<>();
        List<String> list = new ArrayList<>(directedBy.keySet());
        list.stream().limit(n).forEach(i->{
            nova.put(i,directedBy.get(i).getCount());
        });
//        for(int i=0;i<n;++i){
//            nova.put(list.get(i),directedBy.get(list.get(i)).getCount());
//        }
        return nova;
    }*/
}

public class MoviesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int printN = scanner.nextInt();
        scanner.nextLine();
        MoviesCollection moviesCollection = new MoviesCollection();
        Set<String> genres = fillCollection(scanner, moviesCollection);
        System.out.println("=== PRINT BY GENRE ===");
        for (String genre : genres) {
            System.out.println("GENRE: " + genre);
            moviesCollection.printByGenre(genre);
        }
        System.out.println("=== TOP N BY RATING ===");
        printList(moviesCollection.getTopRatedN(printN));

        System.out.println("=== COUNT BY DIRECTOR ===");
        printMap(moviesCollection.getCountByDirector(n));

    }

    static void printMap(Map<String,Integer> countByDirector) {
        countByDirector.entrySet().stream()
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }

    static void printList(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    static TreeSet<String> fillCollection(Scanner scanner,
                                          MoviesCollection collection) {
        TreeSet<String> categories = new TreeSet<String>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            Movie movie = new Movie(parts[0], parts[1], parts[2], Float.parseFloat(parts[3]));
            collection.addMovie(movie);
            categories.add(parts[2]);
        }
        return categories;
    }
}