package movie.api.rest.MovieTests;

import movie.api.rest.model.Movie;
import movie.api.rest.repository.MovieRepository;
import movie.api.rest.service.MovieServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Movie Service Test")
class MovieServiceTest {
    @Autowired
    private MovieServiceImpl movieService;
    @Mock
    private MovieRepository movieRepository;
    private final ArrayList<Movie> movieArrayList = new ArrayList<>();
    private Movie movie;

    @BeforeEach
    void setup() {

        MockitoAnnotations.initMocks(this);
        movieService = new MovieServiceImpl(movieRepository);
        movie = Movie.builder().title("unforgiven").category("western").description("blablabla").year(1998).rating(10).build();
    }

    @DisplayName("Testing the save method from MovieService")
    @Test
    public void saveMovie() {

        when(movieService.saveMovie(movie)).thenReturn(movie);
        Assertions.assertEquals(movie, movieService.saveMovie(movie));
    }

    @Test
    public void findAll() {

        when(movieService.findAll()).thenReturn(movieArrayList);
        Assertions.assertEquals(movie, movieService.findAll());
    }
}