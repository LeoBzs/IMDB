package movie.api.rest.MovieTests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import movie.api.rest.model.Movie;
import movie.api.rest.service.MovieServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Movie Controller Test")
public class MovieControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MovieServiceImpl movieService;

    Movie movie = Movie.builder().title("unforgiven").category("western").description("blablabla").year(1998).rating(10).build();

    @Test
    @DisplayName("Movie post req controller test")
    public void CreateMovie() throws Exception {

        when(movieService.saveMovie(any(Movie.class))).thenReturn(movie);

        ObjectMapper mapper = new ObjectMapper();
        String usuarioJSON = mapper.writeValueAsString(movie);

        mockMvc.perform(MockMvcRequestBuilders.post("/movie/").content(usuarioJSON)
                .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.content().json(usuarioJSON));
    }

    @Test
    @DisplayName("GET req test from the movie controller")
    public void returnsIfSuccess() throws Exception {

        given(movieService.findAll().equals(movie));

        ObjectMapper mapper = new ObjectMapper();
        String movieJSON = mapper.writeValueAsString(movie);

        mockMvc.perform(get("/movie/all" + movie.getTitle()).content(movieJSON)
                .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().json(movieJSON));

    }

}
