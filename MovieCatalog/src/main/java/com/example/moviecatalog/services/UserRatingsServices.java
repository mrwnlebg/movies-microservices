package com.example.moviecatalog.services;

import com.example.moviecatalog.models.Rating;
import com.example.moviecatalog.models.UserRatings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
@Service
public class UserRatingsServices {

    @Autowired
    RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "getUserRatingsFallback")
    public UserRatings getUserRatings(String userId) {
        UserRatings ratings = restTemplate
                .getForObject("http://movies-ratings/ratings/users/" + userId, UserRatings.class);
        return ratings;
    }
    public UserRatings getUserRatingsFallback(String userId) {
        return new UserRatings(Arrays.asList(new Rating("0",0)));
    }
}
