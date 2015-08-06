package com.twu.biblioteca;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by MiffyLiye on 05/08/2015.
 */
public class MovieService {
    private String NewLine;
    private List<Movie> available_movies;
    private List<Movie> checkouted_movies;

    public MovieService() {
        NewLine = System.getProperty("line.separator");
        available_movies = new LinkedList<Movie>();
        checkouted_movies = new LinkedList<Movie>();
        available_movies.add(new Movie(1, "The Story of Tao Part One", 2015, "Wang Tao", 10));
        available_movies.add(new Movie(2, "The Story of Tao Part Two", 2015, "Wang Tao", null));
        setAvailableMovies(available_movies);
        List<Movie> checkouted_movies = new LinkedList<Movie>();
        checkouted_movies.add(new Movie(3, "The Story of Tao Part Three", 2015, "Wang Tao", null));
        setCheckoutedMovies(checkouted_movies);
    }


    public List<Movie> getAvailableMovies() {
        return available_movies;
    }

    public void setAvailableMovies(List<Movie> movies) {
        this.available_movies = movies;
    }

    public List<Movie> getCheckoutedMovies() {
        return checkouted_movies;
    }

    public void setCheckoutedMovies(List<Movie> movies) {
        this.checkouted_movies = movies;
    }

    public Movie findAvailableMovieById(Integer id) {
        for (Movie movie : available_movies) {
            if (movie.getId().equals(id)) {
                return movie;
            }
        }
        return null;
    }

    private Movie findCheckoutedMovieById(Integer id) {
        for (Movie movie : checkouted_movies) {
            if (movie.getId().equals(id)) {
                return movie;
            }
        }
        return null;
    }

    public boolean checkout(Integer id) {
        Movie movie = findAvailableMovieById(id);
        if (movie == null) {
            return false;
        }
        else {
            Movie checkouted = findCheckoutedMovieById(id);
            if (checkouted == null) {
                available_movies.remove(movie);
                checkouted_movies.add(movie);
                return true;
            }
            else {
                return false;
            }
        }
    }

    public boolean checkin(Integer id) {
        Movie movie = findCheckoutedMovieById(id);
        if (movie == null) {
            return false;
        }
        else {
            checkouted_movies.remove(movie);
            available_movies.add(movie);
            Collections.sort(available_movies);
            return true;
        }
    }
}
