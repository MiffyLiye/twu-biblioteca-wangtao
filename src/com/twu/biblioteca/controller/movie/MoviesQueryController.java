package com.twu.biblioteca.controller.movie;

import com.twu.biblioteca.controller.BaseController;
import com.twu.biblioteca.item.Movie;
import com.twu.biblioteca.service.MovieService;

import java.util.Collections;
import java.util.List;

public class MoviesQueryController extends BaseController {
    private MovieService movieService;
    private Integer movie_details_id;

    @Override
    protected void init_requested_path() {
        requested_path.add("welcome");
        requested_path.add("query movies");
    }

    public MoviesQueryController() {
        super();
    }

    public MoviesQueryController(MovieService movieService) {
        this();
        this.movieService = movieService;
    }

    @Override
    public void runView() {
        if (path.size() == 2) {
            moviesListView();
        }
        else if (path.size() >= 3 && path.get(2).equals("movie details")) {
            movieDetailsView();
        }
    }

    private void moviesListView() {
        List<Movie> movieList = movieService.getAvailableItems();
        Collections.sort(movieList);
        for (Movie movie : movieList) {
            System.out.print("ID: " + movie.getId().toString() + "\t"
                    + "Name: " + movie.getName() + "\t"
                    + "Year: " + movie.getYear().toString() + NewLine);
        }
        System.out.print("Press B to go back. Input movie ID to see details." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                try {
                    movie_details_id = Integer.parseInt(cmd);
                    path.add("movie details");
                    break;
                }
                catch (NumberFormatException ex) {
                    System.out.print("Select a valid option!" + NewLine);
                }
            }
        }
    }

    private void movieDetailsView() {
        Movie movie = movieService.findAvailableItemById(movie_details_id);
        System.out.print("ID: " + movie.getId().toString() + NewLine
                + "Name: " + movie.getName() + NewLine
                + "Year: " + movie.getYear().toString() + NewLine
                + "Director: " + movie.getDirector() + NewLine
                + "Rating: " + (movie.getRating() != null ? movie.getRating().toString() : "Unrated")  + NewLine);
        System.out.print("Press B to go back." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                System.out.print("Select a valid option!" + NewLine);
            }
        }
    }


}
