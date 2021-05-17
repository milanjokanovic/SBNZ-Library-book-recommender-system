package rs.sbnz.book_recommender.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.sbnz.book_recommender.dto.BookDTO;
import rs.sbnz.book_recommender.dto.ScoreDTO;
import rs.sbnz.book_recommender.dto.mapper.ScoreMapper;
import rs.sbnz.book_recommender.model.Book;
import rs.sbnz.book_recommender.model.Score;
import rs.sbnz.book_recommender.services.ScoreService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/scores")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    private ScoreMapper scoreMapper = new ScoreMapper();

    @GetMapping
    public ResponseEntity<List<ScoreDTO>> getScores() {
        List<Score> scores = scoreService.findAll();
        return ResponseEntity.ok(scoreMapper.toDtoList(scores));
    }

    @PostMapping
    public ResponseEntity<Void> addScore(@RequestBody ScoreDTO dto) {
        try {
            scoreService.addScore(scoreMapper.toEntity(dto));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScore(@PathVariable Integer id) {
        try {
            if(scoreService.deleteById(id))
                return new ResponseEntity<>(HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
