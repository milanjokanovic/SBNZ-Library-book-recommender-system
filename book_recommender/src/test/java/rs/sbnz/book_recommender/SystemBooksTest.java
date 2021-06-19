package rs.sbnz.book_recommender;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import rs.sbnz.book_recommender.config.EventSessionConfig;
import rs.sbnz.book_recommender.model.Author;
import rs.sbnz.book_recommender.model.Book;
import rs.sbnz.book_recommender.model.Genre;
import rs.sbnz.book_recommender.model.User;
import rs.sbnz.book_recommender.model.enums.LengthType;
import rs.sbnz.book_recommender.model.facts.BookLengthTypeData;
import rs.sbnz.book_recommender.model.facts.GeneralBookData;
import rs.sbnz.book_recommender.model.facts.SeriesData;
import rs.sbnz.book_recommender.repositories.AuthorRepository;
import rs.sbnz.book_recommender.repositories.BookRepository;
import rs.sbnz.book_recommender.repositories.GenreRepository;
import rs.sbnz.book_recommender.repositories.UserRepository;
import rs.sbnz.book_recommender.services.SystemGradeService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test-system_book.properties")
@Transactional
public class SystemBooksTest {
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

    @Autowired
    private SystemGradeService service;

    private KieSession session;

    @Before
    public void setup(){
        KieServices ks = KieServices.Factory.get();
        KieBaseConfiguration kieBaseConfiguration = KieServices.Factory.get().newKieBaseConfiguration();
        kieBaseConfiguration.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kieContainer.newKieBase(kieBaseConfiguration);//config.getBase();//kieContainer.newKieBase(kieBaseConfiguration);

        session = kieBase.newKieSession();
    }

    @Test
    public void okCEPEventTest(){
        double grade0 = bookRepository.findAll().get(0).getSystemGrade();
        service.fireEvent();

        double grade1 = bookRepository.findAll().get(0).getSystemGrade();
        service.fireEvent();
        double grade2 = bookRepository.findAll().get(0).getSystemGrade();
        service.fireEvent();
        double grade3 = bookRepository.findAll().get(0).getSystemGrade();

        //assertEquals(grade1, grade2);
        //assertEquals(grade1, grade2);

        assertTrue(grade1 == grade2);
        assertTrue(grade1 == grade3);
        assertTrue(grade2 == grade3);

        assertTrue(grade0 != grade1);
    }

    @Test
    public void blockUserTest(){
        //KieSession session = config.userAlarmsSession();
        User user = userRepository.findAll().get(0);
        for(int i = 0; i < 21; i++){

            assertEquals(user.getBlockedScoringFunction(), 0);
            session.getAgenda().getAgendaGroup("UserAlarms").setFocus();

            session.insert(user);

            session.fireUntilHalt();
        }
        assertEquals(user.getBlockedScoringFunction(), 1);
    }

    @Test
    public void okGradeTest() throws Exception{


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

        for(Book book : books){
            System.out.println("Book: " + book.getTitle() + " Score:" + book.getSystemGrade());
        }


        assertEquals(3, bestBooks.size());
        assertTrue(shortData.getGrade() == longData.getGrade());
        assertTrue(shortData.getGrade() < mediumData.getGrade());

        assertTrue(0. == shortData.getValue());
        assertTrue(0. == longData.getValue());
        assertTrue(0.5 == mediumData.getValue());

        assertTrue(0.5 == seriesData.getValue());


        for (Book book : books){
            assertTrue(0 != book.getSystemGrade());
        }

        for (Genre genre : genres){
            if(genre.getName().equals("Fantasy1") || genre.getName().equals("Adventure1") || genre.getName().equals("Mystery1"))
                assertTrue(0 != genre.getSystemGrade());
            else
                assertTrue(0 == genre.getSystemGrade());
        }

        for (Author author : authors){
            assertTrue(0 != author.getSystemGrade());
        }

    }
}
