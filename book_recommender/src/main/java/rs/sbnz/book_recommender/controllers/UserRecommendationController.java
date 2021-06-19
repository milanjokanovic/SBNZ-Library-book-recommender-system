package rs.sbnz.book_recommender.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.sbnz.book_recommender.dto.AuthorDTO;
import rs.sbnz.book_recommender.dto.BookDTO;
import rs.sbnz.book_recommender.dto.mapper.BookMapper;
import rs.sbnz.book_recommender.services.UserRecommendationService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/userrec")
public class UserRecommendationController {
    @Autowired
    UserRecommendationService userRecommendationService;

    private BookMapper bookMapper = new BookMapper();

    @GetMapping("/rec/{id}")
    public List<BookDTO> findUserRecBooks(@PathVariable Integer id)
    {
        return bookMapper.toDtoList(userRecommendationService.prepareData(id));
    }
}
