package com.twu.biblioteca.entity;

public class Movie implements Comparable<Movie>, IHaveID{
    private Integer id;
    private String name;
    private Integer year;
    private String director;
    private Integer rating;

    public Movie() {}

    public Movie(Integer id, String name, Integer year, String director, Integer rating) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    @Override
    public int compareTo(Movie movie) {
        return id - movie.id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
