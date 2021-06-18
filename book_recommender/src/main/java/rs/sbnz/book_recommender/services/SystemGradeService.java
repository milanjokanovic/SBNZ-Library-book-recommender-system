package rs.sbnz.book_recommender.services;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.sbnz.book_recommender.config.EventSessionConfig;
import rs.sbnz.book_recommender.model.*;
import rs.sbnz.book_recommender.model.enums.LengthType;
import rs.sbnz.book_recommender.model.facts.BookLengthTypeData;
import rs.sbnz.book_recommender.model.facts.GeneralBookData;
import rs.sbnz.book_recommender.model.facts.SeriesData;
import rs.sbnz.book_recommender.model.facts.SystemGradingEvent;
import rs.sbnz.book_recommender.repositories.AuthorRepository;
import rs.sbnz.book_recommender.repositories.BookRepository;
import rs.sbnz.book_recommender.repositories.GenreRepository;
import rs.sbnz.book_recommender.repositories.UserRepository;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;


@Service
public class SystemGradeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private EventSessionConfig config;

    static final HashMap<Integer, ReentrantLock> locks = new HashMap<Integer, ReentrantLock>();

    //private Date date = new Date();

    private List<Book> bestBooksList;

    public List<Book> fireEvent(){
        //config.eventSession().insert(date);
        config.eventSession().fireUntilHalt();

        return bestBooksList;
    }


    public void listCall(){
        System.out.println("Calling me");

        KieServices ks = KieServices.Factory.get();


        KieBaseConfiguration kieBaseConfiguration = KieServices.Factory.get().newKieBaseConfiguration();
        kieBaseConfiguration.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kieContainer.newKieBase(kieBaseConfiguration);//config.getBase();//kieContainer.newKieBase(kieBaseConfiguration);

        KieSession session = kieBase.newKieSession();

        List<Book> books = bookRepository.findAll();

        for (Book book : books){
            session.insert(book);
        }

        List<Book> bestBooks = new ArrayList<>();

        session.insert(bestBooks);

        session.getAgenda().getAgendaGroup("Close").setFocus();
        session.getAgenda().getAgendaGroup("Level8").setFocus();

        session.fireUntilHalt();

        System.out.println("OLD BEST BOOKS");

        for(Book b : bestBooks){
            System.out.println("Book: " + b.getTitle() + " Score:" + b.getSystemGrade());
        }

        bestBooksList = bestBooks;
    }


    public void getGrade(){

        KieSession session = config.userAlarmsSession();
        User user = userRepository.findAll().get(0);
        session.getAgenda().getAgendaGroup("UserAlarms").setFocus();

        session.insert(user);

        session.fireUntilHalt();

        System.out.println("Blocked: " + user.getBlockedScoringFunction());
        userRepository.save(user);
    }

    public  void getSystemGrade(){

        KieServices ks = KieServices.Factory.get();


        KieBaseConfiguration kieBaseConfiguration = KieServices.Factory.get().newKieBaseConfiguration();
        kieBaseConfiguration.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kieContainer.newKieBase(kieBaseConfiguration);

        KieSession session = kieBase.newKieSession();

        List<User> users = userRepository.findAll();
        List<Book> books = bookRepository.findAll();
        List<Author> authors = authorRepository.findAll();
        List<Genre> genres = genreRepository.findAll();

        for(User user : users){
            session.insert(user);
        }

        for (Book book : books){
            session.insert(book);
        }

        for (Genre genre : genres){
            session.insert(genre);
        }

        for (Author author : authors){
            session.insert(author);
        }

        BookLengthTypeData shortData = new BookLengthTypeData(0, 100, LengthType.SHORT);
        BookLengthTypeData mediumData = new BookLengthTypeData(100, 500, LengthType.MEDIUM);
        BookLengthTypeData longData = new BookLengthTypeData(500, Integer.MAX_VALUE, LengthType.LONG);

        SeriesData seriesData = new SeriesData();
        GeneralBookData bookData = new GeneralBookData();

        List<Book> bestBooks = new ArrayList<>();


        session.insert(shortData);
        session.insert(mediumData);
        session.insert(longData);
        session.insert(seriesData);
        session.insert(bookData);
        session.insert(users.size());
        session.insert(bestBooks);

        session.getAgenda().getAgendaGroup("Close").setFocus();
        session.getAgenda().getAgendaGroup("Level8").setFocus();
        session.getAgenda().getAgendaGroup("Level7").setFocus();
        session.getAgenda().getAgendaGroup("Level6").setFocus();
        session.getAgenda().getAgendaGroup("Level5").setFocus();
        session.getAgenda().getAgendaGroup("Level4").setFocus();
        session.getAgenda().getAgendaGroup("Level3").setFocus();
        session.getAgenda().getAgendaGroup("Level2").setFocus();
        session.getAgenda().getAgendaGroup("Level1_5").setFocus();
        session.getAgenda().getAgendaGroup("Level1").setFocus();
        session.getAgenda().getAgendaGroup("Level0").setFocus();

        session.fireUntilHalt();

        /*for(Book book : books){
            System.out.println("Book: " + book.getTitle() + " Score:" + book.getSystemGrade());
        }*/



        for(User user : users){
            userRepository.save(user);
        }

        for (Book book : books){
            bookRepository.save(book);
        }

        for (Genre genre : genres){
            genreRepository.save(genre);
        }

        for (Author author : authors){
            authorRepository.save(author);
        }

        System.out.println("BEST BOOKS");

        for(Book b : bestBooks){
            System.out.println("Book: " + b.getTitle() + " Score:" + b.getSystemGrade());
        }

        bestBooksList = bestBooks;

    }
}
