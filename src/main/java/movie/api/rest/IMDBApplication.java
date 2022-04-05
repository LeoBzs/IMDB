package movie.api.rest;

import movie.api.rest.model.Movie;
import movie.api.rest.service.MovieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
@EnableSwagger2
public class IMDBApplication {

    public static void main(String[] args) {
        SpringApplication.run(IMDBApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(MovieService movieService) {
        return (args) -> {
            Movie movie = new Movie(1L,"Imperdoaveis", "Western", "Clássico do Clint Eastwood, vingança, tiroteio e todos os outros clichès. Grande filme.",1998,10);
            movieService.saveMovie(movie);

            Movie movie2 = new Movie(2L,"exorcista", "Terror", "Não envelheceu muito bem, mas é um clássico!",1996,8);
            movieService.saveMovie(movie2);

            Movie movie3 = new Movie(3L,"mib", "Ação", "Mais um Blockbuster do Will Smith",2005,6);
            movieService.saveMovie(movie3);

            Movie movie4 = new Movie(4L,"alien", "Sci-fi", "Uma mas melhores misturas Sci-fi/terror desde 'a coisa'",1979,9);
            movieService.saveMovie(movie4);
        };
    }
}
// herokuapp: https://imdb-bonaparte.herokuapp.com/
