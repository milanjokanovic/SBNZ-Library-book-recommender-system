package rs.sbnz.book_recommender.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.sbnz.book_recommender.dto.UserDTO;
import rs.sbnz.book_recommender.dto.mapper.UserMapper;
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
