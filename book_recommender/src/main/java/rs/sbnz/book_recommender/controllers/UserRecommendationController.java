package rs.sbnz.book_recommender.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.sbnz.book_recommender.dto.AuthorDTO;
import rs.sbnz.book_recommender.services.UserRecommendationService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/userrec")
public class UserRecommendationController {
    @Autowired
    UserRecommendationService userRecommendationService;

    @GetMapping
    public void findAllAuthors()
    {
        userRecommendationService.prepareData();
    }
}
