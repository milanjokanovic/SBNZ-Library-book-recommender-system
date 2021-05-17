package rs.sbnz.book_recommender.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.sbnz.book_recommender.dto.GenreDTO;
import rs.sbnz.book_recommender.helper.GenreMapper;
import rs.sbnz.book_recommender.model.Genre;
import rs.sbnz.book_recommender.services.GenreService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    GenreService genreService;

    private GenreMapper genreMapper = new GenreMapper();

    @GetMapping("/id/{id}")
    public ResponseEntity<GenreDTO> findByGenreId(@PathVariable int id)
    {
        try {
            GenreDTO genre = genreMapper.toDto(genreService.findById(id));

            return new ResponseEntity<>(genre, HttpStatus.OK);
        } catch (Exception e) {
            //e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<GenreDTO> findByGenreId(@PathVariable String name)
    {
        try {
            GenreDTO genre = genreMapper.toDto(genreService.findByName(name));
            if(genre.getId() == 0)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(genre, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            //e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> findAllGenres()
    {
        List<GenreDTO> genres = genreMapper.toDtoList(genreService.findAllGenres());
        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @PostMapping
    public GenreDTO addGenre(@Valid @RequestBody GenreDTO genreDTO)
    {
        Genre genre = genreMapper.toEntity(genreDTO);
        return genreMapper.toDto(genreService.addGenre(genre));
    }

    @DeleteMapping("/{id}")
    public Map< String, Boolean > deleteGenre(@PathVariable(value = "id") Integer genreId)
    {
        return genreService.deleteById(genreId);
    }

    @PutMapping("{id}")
    public ResponseEntity <GenreDTO> updateGenre(@PathVariable(value = "id") Integer genreId, @Valid @RequestBody GenreDTO genreDetails)
    {
        Genre genre = genreMapper.toEntity(genreDetails);
        return ResponseEntity.ok(genreMapper.toDto(genreService.updateGenre(genreId, genre)));
    }
}
