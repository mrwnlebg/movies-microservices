package com.example.moviecatalog.services;

import com.example.moviecatalog.models.CatalogItems;
import com.example.moviecatalog.models.Movie;
import com.example.moviecatalog.models.Rating;
import com.example.moviecatalog.models.UserRatings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoServices {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getCatalogItemsFallback")
    public CatalogItems getCatalogItems(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movies-information/movies/" + rating.getMovieId(), Movie.class);
        return new CatalogItems(movie.getTitle(), movie.getOverview(), rating.getRate());
    }

    public CatalogItems getCatalogItemsFallback(Rating rating) {
        return new CatalogItems("0", "no name", rating.getRate());
    }
}
