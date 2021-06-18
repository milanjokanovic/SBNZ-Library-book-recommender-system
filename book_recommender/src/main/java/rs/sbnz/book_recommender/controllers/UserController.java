package rs.sbnz.book_recommender.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.sbnz.book_recommender.dto.BookDTO;
import rs.sbnz.book_recommender.dto.FavoriteDTO;
import rs.sbnz.book_recommender.dto.UserDTO;
import rs.sbnz.book_recommender.dto.mapper.BookMapper;
import rs.sbnz.book_recommender.dto.mapper.UserMapper;
import rs.sbnz.book_recommender.model.Book;
import rs.sbnz.book_recommender.model.Score;
import rs.sbnz.book_recommender.model.User;
import rs.sbnz.book_recommender.repositories.UserRepository;
import rs.sbnz.book_recommender.services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private UserMapper userMapper = new UserMapper();

    private BookMapper bookMapper = new BookMapper();

    @GetMapping("/read/{id}")
    public ResponseEntity<List<BookDTO>> getUserReadBook(@PathVariable Integer id) {
        List<Book> books = userService.findUserRead(id);
        List<BookDTO> bookDTOS = bookMapper.toDtoList(books);
        for(Book book : books){
            for (BookDTO dto : bookDTOS){
                if(dto.getId() == book.getId()){
                    dto.setUserScore(getUserScore(book, id));
                    dto.setFavored(userService.isFavoriteBook(book, id));
                }
            }
        }
        return ResponseEntity.ok(bookDTOS);
    }

    private int getUserScore(Book book, int userId){
        int score = 0;
        for(Score s: book.getScore()){
            if(s.getUser().getId() == userId){
                score = s.getValue();
                return score;
            }
        }
        return score;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(userMapper.toDtoList(users));
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody UserDTO dto) {
        try {
            userService.addUser(userMapper.toEntity(dto));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/favorite/book")
    public ResponseEntity<Void> setUserFavoriteBook(@RequestBody FavoriteDTO dto) {
        try {
            userService.setFavoriteBook(dto.getId(), dto.getUserId());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/favorite/author")
    public ResponseEntity<Void> setUserFavoriteAuthor(@RequestBody FavoriteDTO dto) {
        try {
            userService.setFavoriteAuthor(dto.getId(), dto.getUserId());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        try {
            if(userService.deleteById(id))
                return new ResponseEntity<>(HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
