package movie.api.rest.controller;

import java.util.List;
import java.util.Optional;

import movie.api.rest.model.Movie;
import movie.api.rest.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieServiceImpl movieService;

    @Autowired
    public MovieController(MovieServiceImpl movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(movieService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/title/")
    public ResponseEntity<Movie> getMovieByTitle(@RequestParam String title) {
        Optional<Movie> movieData = movieService.findByTitle(title);
        return movieData
                .map(movie -> new ResponseEntity<>(movie, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/genre/")
    public ResponseEntity<List<Movie>> getMovieByCategory(@RequestParam String category) {
        return new ResponseEntity<>(movieService.findByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/year/")
    public ResponseEntity<List<Movie>> getMovieByYear(@RequestParam Integer year) {
        return new ResponseEntity<>(movieService.findByYear(year), HttpStatus.OK);
    }

    @PutMapping("/title/")
    public ResponseEntity<Movie> updateMovie(@RequestParam String title, @RequestBody Movie movie) {
        Optional<Movie> movieData = movieService.findByTitle(title);
        if (movieData.isPresent()) {
            Movie _movie = movieData.get();
            _movie.setTitle(movie.getTitle());
            _movie.setDescription(movie.getDescription());
            _movie.setCategory(movie.getCategory());
            _movie.setRating(movie.getRating());
            return new ResponseEntity<>(movieService.saveMovie(movie), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie, BindingResult bindingResult) {

        Movie saveMovie = movieService.saveMovie(movie);

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(movie, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(saveMovie, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteMovie(@PathVariable("id") long id) {
        try {
            movieService.deleteMovie(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
