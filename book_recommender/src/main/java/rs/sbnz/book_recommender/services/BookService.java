package rs.sbnz.book_recommender.services;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.sbnz.book_recommender.config.EventSessionConfig;
import rs.sbnz.book_recommender.model.Author;
import rs.sbnz.book_recommender.model.Book;
import rs.sbnz.book_recommender.model.Genre;
import rs.sbnz.book_recommender.model.User;
import rs.sbnz.book_recommender.model.facts.PopularData;
import rs.sbnz.book_recommender.repositories.AuthorRepository;
import rs.sbnz.book_recommender.repositories.BookRepository;
import rs.sbnz.book_recommender.repositories.GenreRepository;
import rs.sbnz.book_recommender.repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    GenreRepository genreRepository;

    @Autowired
    private EventSessionConfig config;

    public List<Book> findAll()
    {
        return bookRepository.findAll();
    }


    private void readBookRules(User user, Book book, String ruleGroup){
        KieSession session = config.userReadSession();
        //User user = userRepository.findAll().get(0);
        //System.out.println("Reading events");

        session.getAgenda().getAgendaGroup("ClearMemory").setFocus();
        session.getAgenda().getAgendaGroup(ruleGroup).setFocus();

        session.insert(user);
        session.insert(book);
        session.fireUntilHalt();


    }

    public void testIspis(){
        KieSession session = config.userReadSession();

        List<Book> books = bookRepository.findAll();
        List<PopularData> popularData = new ArrayList<>();
        for(Book book : books){
            PopularData data = new PopularData();
            data.setBookId(book.getId());

            popularData.add(data);
        }

        for (PopularData data : popularData){

            //System.out.println("Usao za " + data.getBookId());

            session.getAgenda().getAgendaGroup("ClearPopular").setFocus();
            session.getAgenda().getAgendaGroup("NewPopular").setFocus();
            session.getAgenda().getAgendaGroup("Popular").setFocus();
            session.getAgenda().getAgendaGroup("PopularBook").setFocus();
            session.insert(data);
            session.fireUntilHalt();

            if(data.getPopularFactor() != 0){
                System.out.println("Normal za " + data.getBookId());
                System.out.println("Popular factor: " + data.getPopularFactor());
            }

            if(data.getNewPopularFactor() != 0){
                System.out.println("New za " + data.getBookId());
                System.out.println("New factor: " + data.getNewPopularFactor());
            }
        }
    }

    public void readBook(int userId, int bookId){
        Book book = bookRepository.findById(bookId).orElse(null);
        User user = userRepository.findById(userId);
        if(user.getReadBooks() != null){
            int ind = 0;
            for(Book b: user.getReadBooks()){
                if(b.getId() == bookId){
                    break;
                }
                ind++;
            }
            if(ind == user.getReadBooks().size()){
                user.getReadBooks().add(book);
                book.setBrPregleda(book.getBrPregleda() + 1);
                readBookRules(user, book, "BookFirstTimeRead");
                //System.out.println("Prvi put citam");
            }

        }
        else{
            user.setReadBooks(new HashSet<>());
            user.getReadBooks().add(book);
            book.setBrPregleda(book.getBrPregleda() + 1);

            //System.out.println("Prazan user read");
            readBookRules(user, book, "BookFirstTimeRead");
        }
        readBookRules(user, book, "BookRead");
        //testIspis(book);
        userRepository.save(user);
        bookRepository.save(book);
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
        book.setAuthor(author);
        List<String> names = new ArrayList<>();
        for(Genre gen : book.getGenres()){
            names.add(gen.getName());
        }
        Set<Genre> genreList = genreRepository.findByGenreNames(names);

        book.setGenres(genreList);
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
