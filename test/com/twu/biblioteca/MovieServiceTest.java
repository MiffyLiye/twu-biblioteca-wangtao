package com.twu.biblioteca;

import com.twu.biblioteca.item.Movie;
import com.twu.biblioteca.service.MovieService;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MiffyLiye on 05/08/2015.
 */
public class MovieServiceTest {
    private MovieService movieService;
    private String NewLine;

    @Before
    public void SetUp() {
        NewLine = System.getProperty("line.separator");

        movieService = new MovieService();
        List<Movie> available_movies = new LinkedList<Movie>();
        available_movies.add(new Movie(1, "The Story of Tao Part One", 2015, "Wang Tao", 10));
        available_movies.add(new Movie(2, "The Story of Tao Part Two", 2015, "Wang Tao", null));
        movieService.setAvailableItems(available_movies);
        List<Movie> checkouted_movies = new LinkedList<Movie>();
        checkouted_movies.add(new Movie(3, "The Story of Tao Part Three", 2015, "Wang Tao", null));
        movieService.setCheckoutedItems(checkouted_movies);
    }

    @Test
    public void should_get_all_available_movies() throws Exception {
        assertEquals(2, movieService.getAvailableItems().size());
    }

    @Test
    public void should_find_available_movie_by_id() {
        assertNotNull(movieService.findAvailableItemById(1));
    }

    @Test
    public void should_not_found_if_cannot_find_movie_by_id() {
        assertNull(movieService.findAvailableItemById(0));
    }

    @Test
    public void should_successful_checkout_movie_if_it_is_available() {
        boolean actual = movieService.checkout(1);
        assertEquals(true, actual);
    }

    @Test
    public void should_not_list_checkouted_movie_on_movies_list() {
        movieService.checkout(1);

        assertEquals(1, movieService.getAvailableItems().size());
    }

    @Test
    public void should_unsuccessful_checkout_movie_if_it_is_unavailable() {
        movieService.checkout(1);
        assertEquals(false, movieService.checkout(1));

        assertEquals(false, movieService.checkout(-1));
    }

    @Test
    public void should_successful_return_movie_if_it_is_checkouted() {
        movieService.checkout(1);
        assertTrue(movieService.checkin(1));

        assertEquals(2, movieService.getAvailableItems().size());
    }

    @Test
    public void should_unsuccessful_return_movie_if_it_is_not_checkouted() {
        assertFalse(movieService.checkin(1));
        assertFalse(movieService.checkin(-1));
    }
}