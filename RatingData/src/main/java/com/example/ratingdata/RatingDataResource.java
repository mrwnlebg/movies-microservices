package com.example.ratingdata;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingDataResource {

    @RequestMapping("/{movieId}")
    public Rating getRatingDataByMovieId(@PathVariable String movieId) {
        return new Rating(movieId, 5);
    }

    @RequestMapping("/users/{userId}")
    public UserRatings getRatingDataByUserId(@PathVariable String userId) {

         List<Rating> ratings = Arrays.asList(
                 new Rating("100", 5),
                 new Rating("200", 3)
         );
         UserRatings userRatings = new UserRatings();
         userRatings.setUserRating(ratings);
         return userRatings;
    }
}
