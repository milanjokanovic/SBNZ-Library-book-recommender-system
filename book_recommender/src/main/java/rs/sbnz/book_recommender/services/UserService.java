package rs.sbnz.book_recommender.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.sbnz.book_recommender.model.Author;
import rs.sbnz.book_recommender.model.Book;
import rs.sbnz.book_recommender.model.User;
import rs.sbnz.book_recommender.repositories.AuthorRepository;
import rs.sbnz.book_recommender.repositories.BookRepository;
import rs.sbnz.book_recommender.repositories.UserRepository;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    public List<Book> findUserRead(int id) {
        User user = userRepository.findById(id);
        Set<Book> books = user.getReadBooks();
        List<Book> readBooks = new ArrayList<>();

        for(Book b : books)
            readBooks.add(b);

        return readBooks;
    }

    public void setLastActive(int userId){
        User user = userRepository.findById(userId);
        if(user == null){
            return;
        }
        user.setLastActive(new Date());
        userRepository.save(user);
    }

    public Boolean isFavoriteBook(Book book, int userId){
        User user = userRepository.findById(userId);
        if(user.getFavoriteBook() != null){
            if(user.getFavoriteBook().getId() == book.getId())
                return true;
        }

        return false;
    }

    public Boolean isFavoriteAuthor(Author author, int userId){
        User user = userRepository.findById(userId);
        if(user.getFavoriteAuthor() != null){
            if(user.getFavoriteAuthor().getId() == author.getId())
                return true;
        }

        return false;
    }

    public User addUser(User user) throws Exception
    {
        /*User activeUser = userRepository.findById(user.getId())
                .orElse(null);*/
        User activeUser = userRepository.findByEmail(user.getEmail());
        if(activeUser != null){
            throw new Exception();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setBlockedScoringFunction(0);
        user.setLastActive(new Date());
        userRepository.save(user);

        return user;
    }

    public Boolean deleteById(int userId) throws ResourceNotFoundException
    {
        User activeUser = userRepository.findById(userId);
        if(activeUser == null)
            throw new ResourceNotFoundException("User not found for this id :: \" + userId");
        userRepository.delete(activeUser);
        /*Map< String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);*/
        return true;
    }

    public User updateUser(int userId, User user) // throws ResourceNotFoundException {
    {
        User activeUser = userRepository.findById(userId); //Throw(() -> new ResourceNotFoundException("Employee not found for this id :: " + userId));

        activeUser.setEmail(user.getEmail());
        activeUser.setPassword(user.getPassword());

        final User updatedUser = userRepository.save(activeUser);

        return updatedUser;
    }

    public void setFavoriteBook(int bookId, int userId) throws Exception{
        Book book = bookRepository.findById(bookId).orElse(null);
        if(book == null)
            throw new Exception();
        User user = userRepository.findById(userId);
        if(user == null)
            throw new Exception();
        user.setFavoriteBook(book);
        userRepository.save(user);
    }

    public void setFavoriteAuthor(int authorId, int userId) throws Exception{
        Author author = authorRepository.findById(authorId).orElse(null);
        if(author == null)
            throw new Exception();
        User user = userRepository.findById(userId);
        if(user == null)
            throw new Exception();
        user.setFavoriteAuthor(author);
        userRepository.save(user);
    }
}
