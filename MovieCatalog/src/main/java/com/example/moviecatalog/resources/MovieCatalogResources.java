package com.example.moviecatalog.resources;

import com.example.moviecatalog.models.CatalogItems;
import com.example.moviecatalog.models.Movie;
import com.example.moviecatalog.models.Rating;
import com.example.moviecatalog.models.UserRatings;
import com.example.moviecatalog.services.MovieInfoServices;
import com.example.moviecatalog.services.UserRatingsServices;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResources {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MovieInfoServices movieInfoServices;

    @Autowired
    private UserRatingsServices userRatingsServices;

    @RequestMapping("/{userId}")
    public List<CatalogItems> getCatalogItemsByUserId(@PathVariable String userId) {
        UserRatings ratings = userRatingsServices.getUserRatings(userId);
        return ratings.getUserRating().stream()
                .map(rating -> movieInfoServices.getCatalogItems(rating))
                .collect(Collectors.toList());
    }
}
