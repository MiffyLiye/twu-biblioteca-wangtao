package com.twu.biblioteca.controller.movie;

import com.twu.biblioteca.controller.BaseController;
import com.twu.biblioteca.service.MovieService;

public class MoviesCheckoutController extends BaseController {
    public MoviesCheckoutController() {
        super();
    }

    public MoviesCheckoutController(MovieService movieService) {
        this();
        this.movieService = movieService;
    }

    @Override
    public void runView() {
        if (path.size() == 2) {
            movieCheckoutView();
        }
        else if (path.size() == 3 && path.get(2).equals("successful")) {
            movieCheckoutSuccessfulView();
        }
        else if (path.size() == 3 && path.get(2).equals("unsuccessful")) {
            movieCheckoutUnsuccessfulView();
        }
    }

    private MovieService movieService;

    @Override
    protected void initRequestedPath() {
        requestedPath.add("welcome");
        requestedPath.add("checkout movies");
    }

    private void movieCheckoutView() {
        System.out.print("Checkout Movies" + NewLine);
        System.out.print("Press B to go back. Input the movie ID to checkout." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                try {
                    Integer id = Integer.parseInt(cmd);
                    String number = (String) session.get("user library number");
                    if (number != null && movieService.checkout(id, number)) {
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

    private void movieCheckoutSuccessfulView() {
        System.out.print("Thank you! Enjoy the movie." + NewLine);
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

    private void movieCheckoutUnsuccessfulView() {
        if (session.get("user library number") == null) {
            System.out.print("Please login." + NewLine);
        }
        else {
            System.out.print("That movie is not available." + NewLine);
        }
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
