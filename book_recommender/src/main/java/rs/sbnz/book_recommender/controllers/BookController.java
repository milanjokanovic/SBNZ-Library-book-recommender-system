package rs.sbnz.book_recommender.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.sbnz.book_recommender.dto.BookDTO;
import rs.sbnz.book_recommender.dto.GenreDTO;
import rs.sbnz.book_recommender.dto.UserDTO;
import rs.sbnz.book_recommender.dto.mapper.BookMapper;
import rs.sbnz.book_recommender.model.Book;
import rs.sbnz.book_recommender.model.Genre;
import rs.sbnz.book_recommender.services.BookService;
import rs.sbnz.book_recommender.services.SystemGradeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    private BookMapper bookMapper = new BookMapper();

    @Autowired
    private SystemGradeService systemGradeService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks() {
        List<Book> books = bookService.findAll();
        return ResponseEntity.ok(bookMapper.toDtoList(books));
    }

    @PostMapping
    public ResponseEntity<Void> addBook(@RequestBody BookDTO dto) {
        try {
            bookService.addBook(bookMapper.toEntity(dto));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        try {
            if(bookService.deleteById(id))
                return new ResponseEntity<>(HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity <BookDTO> updateGenre(@PathVariable(value = "id") Integer bookId, @Valid @RequestBody BookDTO dto)
    {
        Book book = bookMapper.toEntity(dto);
        try {
            return ResponseEntity.ok(bookMapper.toDto(bookService.updateBook(bookId, book)));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/top/systemgrade")
    public ResponseEntity<List<BookDTO>> topBooks()
    {
        //systemGradeService.fireEvent();
        List<Book> books = systemGradeService.fireEvent();
        return ResponseEntity.ok(bookMapper.toDtoList(books));
    }
}
