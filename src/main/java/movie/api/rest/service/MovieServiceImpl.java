package movie.api.rest.service;

import movie.api.rest.model.Movie;
import movie.api.rest.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl( MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> findByCategory(String category) throws DataAccessException {
        return  movieRepository.findAll()
                .stream()
                .filter(movie -> movie.getCategory().equals(category))
                .map(movieRepository::save)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Movie> findByTitle(String title) throws DataAccessException {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<Movie> findByYear(Integer year) throws DataAccessException {
        return  movieRepository.findAll()
                .stream()
                .filter(movie -> movie.getYear().equals(year))
                .map(movieRepository::save)
                .collect(Collectors.toList());
    }

    @Override
    public Movie saveMovie(Movie movie) throws DataAccessException {
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Long id) throws DataAccessException {
        movieRepository.deleteById(id);
    }

    @Override
    public Iterable<Movie> findAll() {
        return movieRepository.findAll();
    }
}
