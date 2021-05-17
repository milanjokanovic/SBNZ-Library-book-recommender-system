package rs.sbnz.book_recommender.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.sbnz.book_recommender.dto.AuthorDTO;
import rs.sbnz.book_recommender.helper.AuthorMapper;
import rs.sbnz.book_recommender.model.Author;
import rs.sbnz.book_recommender.services.AuthorService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    private AuthorMapper authorMapper = new AuthorMapper();

    @GetMapping("/id/{id}")
    public ResponseEntity<AuthorDTO> findByAuthorId(@PathVariable int id)
    {
        try {
            AuthorDTO author = authorMapper.toDto(authorService.findById(id));

            return new ResponseEntity<>(author, HttpStatus.OK);
        } catch (Exception e) {
            //e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<AuthorDTO> findByAuthorId(@PathVariable String name)
    {
        try {
            AuthorDTO author = authorMapper.toDto(authorService.findByName(name));
            if(author.getId() == 0)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(author, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            //e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> findAllAuthors()
    {
        List<AuthorDTO> authors = authorMapper.toDtoList(authorService.findAllAuthors());
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @PostMapping
    public AuthorDTO addAuthor(@Valid @RequestBody AuthorDTO authorDTO)
    {
        Author author = authorMapper.toEntity(authorDTO);
        return authorMapper.toDto(authorService.addAuthor(author));
    }

    @DeleteMapping("/{id}")
    public Map< String, Boolean > deleteAuthor(@PathVariable(value = "id") Integer authorId)
    {
        return authorService.deleteById(authorId);
    }

    @PutMapping("{id}")
    public ResponseEntity <AuthorDTO> updateAuthor(@PathVariable(value = "id") Integer authorId, @Valid @RequestBody AuthorDTO authorDetails)
    {
        Author author = authorMapper.toEntity(authorDetails);
        return ResponseEntity.ok(authorMapper.toDto(authorService.updateAuthor(authorId, author)));
    }
}
