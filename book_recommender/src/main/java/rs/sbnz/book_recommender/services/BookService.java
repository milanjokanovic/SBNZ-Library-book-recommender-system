package rs.sbnz.book_recommender.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.sbnz.book_recommender.model.Author;
import rs.sbnz.book_recommender.model.Book;
import rs.sbnz.book_recommender.model.Genre;
import rs.sbnz.book_recommender.model.User;
import rs.sbnz.book_recommender.repositories.AuthorRepository;
import rs.sbnz.book_recommender.repositories.BookRepository;
import rs.sbnz.book_recommender.repositories.GenreRepository;
import rs.sbnz.book_recommender.repositories.UserRepository;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    GenreRepository genreRepository;

    public List<Book> findAll()
    {
        return bookRepository.findAll();
    }

    public Book addBook(Book book) throws Exception {
        /*User activeUser = userRepository.findById(user.getId())
                .orElse(null);*/
        Book oldBook = bookRepository.findByTitle(book.getTitle());

        if(oldBook != null){
            throw new Exception();
        }

        Author author = authorRepository.findByName(book.getAuthor().getName());

        if(author == null){
            throw new Exception();
        }

        List<Genre> genres = genreRepository.findAll();

        

        bookRepository.save(book);

        return book;
    }

    public Boolean deleteById(int bookId) //throws ResourceNotFoundException
    {
        Book book = bookRepository.findById(bookId)
                .orElse(null);

        if(book == null){
            return false;
        }
        bookRepository.delete(book);
        /*Map< String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);*/
        return true;
    }

    public Book updateBook(int bookId, Book book) throws Exception // throws ResourceNotFoundException {
    {
        Book foundBook = bookRepository.findById(bookId)
                .orElse(null); //Throw(() -> new ResourceNotFoundException("Employee not found for this id :: " + userId));

        if(foundBook == null){
            throw new Exception();
        }

        //foundBook.setGenres(book.getGenres());
        //foundBook.setTitle(book.getTitle());
        foundBook.setPageNum(book.getPageNum());
        foundBook.setBrPregleda(book.getBrPregleda());

        foundBook.setSeries(book.getSeries());
        foundBook.setSeriesNumber(book.getSeriesNumber());

        foundBook.setTargetAudience(book.getTargetAudience());
        foundBook.setYearOfPublishing(book.getYearOfPublishing());
        foundBook.setBasedOnRealEvent(book.getBasedOnRealEvent());
        foundBook.setNobelPrize(book.getNobelPrize());

        final Book updatedBook = bookRepository.save(foundBook);

        return updatedBook;
    }
}
