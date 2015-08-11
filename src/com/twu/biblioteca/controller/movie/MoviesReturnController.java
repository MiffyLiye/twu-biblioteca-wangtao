package com.twu.biblioteca.controller.movie;

import com.twu.biblioteca.controller.BaseController;
import com.twu.biblioteca.service.MovieService;

public class MoviesReturnController extends BaseController {
    public MoviesReturnController() {
        super();
    }

    public MoviesReturnController(MovieService movieService) {
        this();
        this.movieService = movieService;
    }

    @Override
    public void runView() {
        if (path.size() == 2) {
            movieReturnView();
        }
        else if (path.size() == 3 && path.get(2).equals("successful")) {
            movieReturnSuccessfulView();
        }
        else if (path.size() == 3 && path.get(2).equals("unsuccessful")) {
            movieReturnUnsuccessfulView();
        }
    }

    private MovieService movieService;

    @Override
    protected void initRequestedPath() {
        requestedPath.add("welcome");
        requestedPath.add("return movies");
    }

    private void movieReturnView() {
        System.out.print("Return Movies" + NewLine);
        System.out.print("Press B to go back. Input the movie ID to return." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                try {
                    Integer id = Integer.parseInt(cmd);
                    if (movieService.checkin(id)) {
                        path.add("successful");
                    }
                    else {
                        path.add("unsuccessful");
                    }
                    break;
                }
                catch (NumberFormatException ex) {
                    System.out.print("Select a valid option!" + NewLine);
                }
            }
        }
    }

    private void movieReturnSuccessfulView() {
        System.out.print("Thank you for returning the movie." + NewLine);
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

    private void movieReturnUnsuccessfulView() {
        System.out.print("That is not a valid movie to return." + NewLine);
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
