package com.example.movieinfo;

import com.example.movieinfo.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MoviesInfoResources {
    @Autowired
    RestTemplate restTemplate;

    @Value("${api.key}")
    private String apiKey;


    @RequestMapping("/{movieId}")
    public Movie getMovieById(@PathVariable String movieId) {
        Movie movie = restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+apiKey,
                Movie.class);

        return new Movie(movieId, movie.getTitle(), movie.getOverview());
    }
}
