package movie.api.rest.service;

import movie.api.rest.model.Movie;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MovieService {

    Movie saveMovie(Movie movie) throws DataAccessException;

    void deleteMovie(Long id) throws DataAccessException;

    Iterable<Movie> findAll();

    List<Movie> findByCategory(String title) throws DataAccessException;

    Optional<Movie> findByTitle(String category) throws DataAccessException;

    List<Movie> findByYear(Integer year) throws DataAccessException;

}
