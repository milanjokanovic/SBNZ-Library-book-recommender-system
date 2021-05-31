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

    //private Date date = new Date();

    private List<Book> bestBooksList;

    public List<Book> fireEvent(){
        //config.eventSession().insert(date);
        config.eventSession().fireUntilHalt();

        return bestBooksList;
    }


    public void testCall(){
        System.out.println("Calling me");

        KieServices ks = KieServices.Factory.get();
        /*KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("rs.sbnz", "drools-spring-kjar", "0.0.1-SNAPSHOT"));*/

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

    public void getGrade(KieSession session){
        //RuleData data = new RuleData();

        BookLengthTypeData shortData = new BookLengthTypeData(0, 100, LengthType.SHORT);
        BookLengthTypeData mediumData = new BookLengthTypeData(100, 500, LengthType.MEDIUM);
        BookLengthTypeData longData = new BookLengthTypeData(500, Integer.MAX_VALUE, LengthType.LONG);

        SeriesData seriesData = new SeriesData();
        GeneralBookData bookData = new GeneralBookData();


        Score s = new Score();
        s.setId(1);
        s.setValue(2);

        Score s1 = new Score();
        s1.setId(2);
        s1.setValue(1);

        Score s2 = new Score();
        s2.setId(3);
        s2.setValue(1);

        Set<Score> score1 = new HashSet<Score>();

        score1.add(s1);
        score1.add(s2);
        score1.add(s);

        Book book = new Book();
        book.setId(1);
        book.setPageNum(900);
        book.setTitle("Nova");
        book.setBrPregleda(2);
        book.setScore(score1);
        book.setFavoredByUsers(new HashSet<User>());

        book.setGenres(new HashSet<Genre>());


        Score s21 = new Score();
        s21.setId(4);
        s21.setValue(4);


        Book book2 = new Book();
        book2.setId(2);
        book2.setPageNum(295);
        book2.setTitle("Nova2");
        book2.setFavoredByUsers(new HashSet<User>());

        book2.setGenres(new HashSet<Genre>());

        book2.setScore(new HashSet<Score>());


        book2.addScore(s21);
        book2.addPregled();
        book2.addPregled();
        book2.setSeriesNumber(1);

        Score s31 = new Score();
        s31.setId(5);
        s31.setValue(2);

        Book book3 = new Book();
        book3.setId(3);
        book3.setPageNum(95);
        book3.setTitle("Nova3");

        book3.setFavoredByUsers(new HashSet<User>());

        book3.setGenres(new HashSet<Genre>());

        book3.setScore(new HashSet<Score>());

        book3.addScore(s31);
        book3.addPregled();

        Score s41 = new Score();
        s41.setId(6);
        s41.setValue(5);

        Book book4 = new Book();
        book4.setId(4);
        book4.setPageNum(919);
        book4.setTitle("Nova4");

        book4.setFavoredByUsers(new HashSet<User>());

        book4.setGenres(new HashSet<Genre>());

        book4.setScore(new HashSet<Score>());

        book4.addScore(s41);
        book4.setBrPregleda(1);

        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -3);
        //System.out.println(cal.getTime().before(date));


        Author a = new Author();

        a.setBooks(new HashSet<>());

        a.setFavoredByUsers(new HashSet<>());

        Author a1 = new Author();

        a1.setBooks(new HashSet<>());

        a1.setFavoredByUsers(new HashSet<>());

        User u = new User();
        u.setFavoriteBook(book);
        u.setId(5);
        u.setLastActive(cal.getTime());
        User u1 = new User();
        u1.setFavoriteBook(book);
        u1.setLastActive(date);
        u1.setId(1);

        User u2 = new User();
        u2.setId(2);
        u2.setFavoriteBook(book);
        u2.setLastActive(date);

        User u3 = new User();
        u3.setFavoriteBook(book2);
        u3.setLastActive(cal.getTime());
        u3.setId(3);
        User u4 = new User();
        u4.setFavoriteBook(book2);
        u4.setLastActive(date);
        u4.setId(4);

        a1.getFavoredByUsers().add(u4);
        a1.getFavoredByUsers().add(u3);

        a.getFavoredByUsers().add(u1);
        a.getFavoredByUsers().add(u2);

        a.getBooks().add(book4);
        a.getBooks().add(book3);
        a.getBooks().add(book2);

        a1.getBooks().add(book);


        book.getFavoredByUsers().add(u1);
        book.getFavoredByUsers().add(u);
        book.getFavoredByUsers().add(u3);

        book2.getFavoredByUsers().add(u4);
        book2.getFavoredByUsers().add(u2);

        Genre g = new Genre();
        g.setName("asd");
        g.setId(1);

        Genre g1 = new Genre();
        g1.setName("neki");
        g1.setId(2);

        List<Genre> sets = new ArrayList<>();

        sets.add(g);


        Set<Genre> bookGen = new HashSet<>();

        bookGen.add(g);


        book4.setGenres(bookGen);

        List<Genre> genres = new ArrayList<>();

        genres.add(g);
        genres.add(g1);


        book2.getGenres().add(g);
        book2.getGenres().add(g1);

        session.insert(shortData);
        session.insert(mediumData);
        session.insert(longData);
        session.insert(book);
        session.insert(book2);
        session.insert(book3);
        session.insert(book4);
        session.insert(seriesData);

        session.insert(sets);


        for(Genre gen : genres) {
            session.insert(gen);
        }

        //kSession.insert(g);
        //kSession.insert(g1);

        session.insert(u);
        session.insert(u1);
        session.insert(u2);
        session.insert(u3);
        session.insert(u4);
        session.insert(bookData);
        session.insert(a);
        session.insert(a1);


        session.insert(4); // ove je broj user-a u bazi
        //kSession.insert(types);


        session.getAgenda().getAgendaGroup("Level7").setFocus();
        session.getAgenda().getAgendaGroup("Level6").setFocus();
        session.getAgenda().getAgendaGroup("Level5").setFocus();
        session.getAgenda().getAgendaGroup("Level4").setFocus();
        session.getAgenda().getAgendaGroup("Level3").setFocus();
        session.getAgenda().getAgendaGroup("Level2").setFocus();
        session.getAgenda().getAgendaGroup("Level1").setFocus();
        session.getAgenda().getAgendaGroup("Level0").setFocus();

        session.fireAllRules();

        System.out.println("Type:" + shortData.getType() + " Grade:" + shortData.getGrade() + " Value:" + shortData.getValue());
        System.out.println("Type:" + mediumData.getType() + " Grade:" + mediumData.getGrade() + " Value:" + mediumData.getValue());
        System.out.println("Type:" + longData.getType() + " Grade:" + longData.getGrade() + " Value:" + longData.getValue());
        System.out.println("Score:" + bookData.getAvgBookScore() / bookData.getBookCount() + " Views:" + bookData.getAvgBookViewNumber() / bookData.getBookCount());
        System.out.println("Score:" + seriesData.getGrade() + " Value:" + seriesData.getValue());


        System.out.println("Book: " + book.getTitle() + " Score:" + book.getSystemGrade());
        System.out.println("Book: " + book2.getTitle() + " Score:" + book2.getSystemGrade());
        System.out.println("Book: " + book3.getTitle() + " Score:" + book3.getSystemGrade());
        System.out.println("Book: " + book4.getTitle() + " Score:" + book4.getSystemGrade());

        System.out.println("Genre Score:" + g.getSystemGrade());
        System.out.println("Genre Score:" + g1.getSystemGrade());

        System.out.println("Author Score:" + a.getSystemGrade());
        System.out.println("Author Score:" + a1.getSystemGrade());
    }

    public  void getSystemGrade(){
        /*KieBaseConfiguration kieBaseConfiguration = KieServices.Factory.get().newKieBaseConfiguration();
        kieBaseConfiguration.setOption(EventProcessingOption.CLOUD);*/

        KieServices ks = KieServices.Factory.get();
        /*KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("rs.sbnz", "drools-spring-kjar", "0.0.1-SNAPSHOT"));*/

        KieBaseConfiguration kieBaseConfiguration = KieServices.Factory.get().newKieBaseConfiguration();
        kieBaseConfiguration.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kieContainer.newKieBase(kieBaseConfiguration);//config.getBase();//kieContainer.newKieBase(kieBaseConfiguration);

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
        session.getAgenda().getAgendaGroup("Level1").setFocus();
        session.getAgenda().getAgendaGroup("Level0").setFocus();

        session.fireUntilHalt();

        for(Book book : books){
            System.out.println("Book: " + book.getTitle() + " Score:" + book.getSystemGrade());
        }

        //userRepository.save();

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
        /*date = new Date();
        config.eventSession().insert(date);*/
    }
}
