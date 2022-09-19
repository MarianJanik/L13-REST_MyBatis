package cz.marianjanik.l13restmybatis;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
public class MainController {

    @GetMapping (path = "/movie/test1")
    public String test1() {
        return "Pozdrav z Vendryně!";
    }

    @GetMapping (path = "/movie/test2")
    public Movie test2() {
        MainService mainService = new MainService();
        return mainService.getMovieForTest();
    }

   @GetMapping (path = "/movie/test3")
    public Movie test3() throws SQLException, IOException {
        MainService mainService = new MainService();
        return mainService.getMovieByIdSql(1);
    }

    @GetMapping (path = "/movie/id/{id}")
    public Movie getMovieBaId(@PathVariable int id) throws IOException {
        MainService mainService = new MainService();
        return mainService.getMovieByIdSql(id);
    }

    @GetMapping (path = "/movie/all")
    public List<Movie> getAllMovie() throws IOException {
        MainService mainService = new MainService();
        return mainService.getAllMovieSql();
    }

    @PostMapping(path = "/movie/save")
    public String addMovie(@RequestBody Movie movie) throws IOException {
        MainService mainService = new MainService();
        mainService.setMovieSql(movie);
        return "Zapsáno";
    }

    @PutMapping(path = "/movie/update")
    public String updateMovie(@RequestBody Movie updateMovie) throws IOException {
        MainService mainService = new MainService();
        if (mainService.getMovieByIdSql(updateMovie.getId()) == null) {
             return "Film ze zadaným 'id'=" + updateMovie.getId() + " nebyl nalezen.";
        }
        mainService.updateMovieSql(updateMovie);
        return "Film byl opraven.";
    }

    @DeleteMapping(path = "/movie/id/{idMovie}")
    public String deleteMovieById(@PathVariable int idMovie) throws IOException {
        MainService mainService = new MainService();
        mainService.deleteMovieByIdSql(idMovie);
        return "Film č." + idMovie + " byl smazán.";
    }

    @DeleteMapping(path = "/movie/name/")
    public String deleteMovieByName(@RequestParam String name) throws IOException {
        MainService mainService = new MainService();
        mainService.deleteMovieByNameSql(name);
        return "Film " + name + " byl smazán.";
    }


}
