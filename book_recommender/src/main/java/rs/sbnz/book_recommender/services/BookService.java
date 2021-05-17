package rs.sbnz.book_recommender.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.sbnz.book_recommender.model.Book;
import rs.sbnz.book_recommender.model.User;
import rs.sbnz.book_recommender.repositories.BookRepository;
import rs.sbnz.book_recommender.repositories.UserRepository;

import java.util.List;

@Service
public class BookService {

    /*@Autowired
    UserRepository userRepository;*/

    @Autowired
    BookRepository bookRepository;


    public List<Book> findAll()
    {
        return bookRepository.findAll();
    }

    public Book addBook(Book book)
    {
        /*User activeUser = userRepository.findById(user.getId())
                .orElse(null);*/

        bookRepository.save(book);

        return book;
    }

    public Boolean deleteById(int bookId) //throws ResourceNotFoundException
    {
        Book book = bookRepository.findById(bookId)
                .orElse(null);
        bookRepository.delete(book);
        /*Map< String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);*/
        return true;
    }

    public Book updateBook(int bookId, Book book) // throws ResourceNotFoundException {
    {
        Book foundBook = bookRepository.findById(bookId)
                .orElse(null); //Throw(() -> new ResourceNotFoundException("Employee not found for this id :: " + userId));

        foundBook.setGenres(book.getGenres());
        foundBook.setTitle(book.getTitle());
        foundBook.setPageNum(book.getPageNum());
        foundBook.setBrPregleda(book.getBrPregleda());
        foundBook.setBasedOnRealEvent(book.getBasedOnRealEvent());

        final Book updatedBook = bookRepository.save(foundBook);

        return updatedBook;
    }
}
