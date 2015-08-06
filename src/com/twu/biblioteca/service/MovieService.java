package com.twu.biblioteca.service;

import com.twu.biblioteca.item.Movie;

import java.util.List;

/**
 * Created by MiffyLiye on 05/08/2015.
 */
public class MovieService extends ItemService<Movie> {
    public MovieService() {
        super();
        List<Movie> available_movies = getAvailableItems();
        available_movies.add(new Movie(1, "The Story of Tao Part One", 2015, "Wang Tao", 10));
        available_movies.add(new Movie(2, "The Story of Tao Part Two", 2015, "Wang Tao", null));
        setAvailableItems(available_movies);

        List<Movie> checkouted_movies = getCheckoutedItems();
        checkouted_movies.add(new Movie(3, "The Story of Tao Part Three", 2015, "Wang Tao", null));
        setCheckoutedItems(checkouted_movies);
    }
}
