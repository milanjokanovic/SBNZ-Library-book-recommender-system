package rs.sbnz.book_recommender;

import org.junit.Assert;
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
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import rs.sbnz.book_recommender.config.EventSessionConfig;
import rs.sbnz.book_recommender.model.*;
import rs.sbnz.book_recommender.model.facts.SubjectUser;
import rs.sbnz.book_recommender.repositories.AuthorRepository;
import rs.sbnz.book_recommender.repositories.BookRepository;
import rs.sbnz.book_recommender.repositories.GenreRepository;
import rs.sbnz.book_recommender.repositories.UserRepository;
import rs.sbnz.book_recommender.services.SystemGradeService;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test-user_rec.properties")
@Transactional
public class UserRecTest {
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

    private List<Book> bestBooksForUser = new ArrayList<>();

    private KieSession session;

    @Before
    public void setup(){
        KieServices ks = KieServices.Factory.get();
        KieBaseConfiguration kieBaseConfiguration = KieServices.Factory.get().newKieBaseConfiguration();
        kieBaseConfiguration.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kieContainer.newKieBase(kieBaseConfiguration);//config.getBase();//kieContainer.newKieBase(kieBaseConfiguration);

        session = kieBase.newKieSession();

        List<Book> allBooks = bookRepository.findAll();

        User user = userRepository.findById(101);

        SubjectUser subjectUser = new SubjectUser();
        subjectUser.setUser(user);

        List<User> users = userRepository.findAll();

        List<Author> readAuthors = new ArrayList<>();

        List<Score> userScores = new ArrayList<>();

        List<Genre> userGenres = new ArrayList<>();

        for(Book b : user.getReadBooks())
            if(!readAuthors.contains(b.getAuthor()))
                readAuthors.add(b.getAuthor());

        for(Book b : user.getReadBooks())
            for(Score s : b.getScore())
                if(s.getUser().getId() == user.getId())
                    userScores.add(s);

        for(Book b : user.getReadBooks())
            for(Genre g : b.getGenres())
                if(!userGenres.contains(g))
                    userGenres.add(g);

        session.insert(subjectUser);
        for(Book b: allBooks)
            session.insert(b);
        for(Author a: readAuthors)
            session.insert(a);
        /*for(Score s : userScores)
            session.insert(s);*/
        for(Genre g : userGenres)
            session.insert(g);
        for(User u : users)
        {
            if(u.getId() == 101)
                continue;
            session.insert(u);
        }
        session.insert(bestBooksForUser);
    }

    @Test
    public void AuthorTest(){

        session.getAgenda().getAgendaGroup("Close").setFocus();
        session.getAgenda().getAgendaGroup("SeriesHandler").setFocus();
        session.getAgenda().getAgendaGroup("FindBest").setFocus();
        session.getAgenda().getAgendaGroup("AuthorScore").setFocus();
        session.getAgenda().getAgendaGroup("ResetUserRecScores").setFocus();
        session.fireUntilHalt();

        for(Book b : bestBooksForUser)
            System.out.println(b.getUserRecommendedScore());

        assertEquals(0.0, bestBooksForUser.get(0).getUserRecommendedScore(), 0.0001);
        assertEquals(0.0, bestBooksForUser.get(1).getUserRecommendedScore(), 0.0001);
        assertEquals(0.0, bestBooksForUser.get(2).getUserRecommendedScore(), 0.0001);
        assertEquals(0.08, bestBooksForUser.get(3).getUserRecommendedScore(), 0.0001);
        assertEquals(0.18, bestBooksForUser.get(4).getUserRecommendedScore(), 0.0001);
    }

    @Test
    public void GenreTest(){

        session.getAgenda().getAgendaGroup("Close").setFocus();
        session.getAgenda().getAgendaGroup("SeriesHandler").setFocus();
        session.getAgenda().getAgendaGroup("FindBest").setFocus();
        session.getAgenda().getAgendaGroup("GenreScore").setFocus();
        session.getAgenda().getAgendaGroup("ResetUserRecScores").setFocus();
        session.fireUntilHalt();

        for(Book b : bestBooksForUser)
            System.out.println(b.getUserRecommendedScore());

        assertEquals(1.2, bestBooksForUser.get(0).getUserRecommendedScore(), 0.001);
        assertEquals(1.2, bestBooksForUser.get(1).getUserRecommendedScore(), 0.001);
        assertEquals(1.2, bestBooksForUser.get(2).getUserRecommendedScore(), 0.001);
        assertEquals(0.5, bestBooksForUser.get(3).getUserRecommendedScore(), 0.001);
        assertEquals(0.5, bestBooksForUser.get(4).getUserRecommendedScore(), 0.001);
    }

    @Test
    public void AdditionalTest(){

        session.getAgenda().getAgendaGroup("Close").setFocus();
        //session.getAgenda().getAgendaGroup("SeriesHandler").setFocus();
        session.getAgenda().getAgendaGroup("FindBest").setFocus();
        session.getAgenda().getAgendaGroup("AdditionalEvaluation").setFocus();
        session.getAgenda().getAgendaGroup("ResetUserRecScores").setFocus();
        session.fireUntilHalt();

        for(Book b : bestBooksForUser)
            System.out.println(b.getTitle() + "---" + b.getUserRecommendedScore());

        assertEquals(0.00, bestBooksForUser.get(0).getUserRecommendedScore(), 0.0001);
        assertEquals(0.00, bestBooksForUser.get(1).getUserRecommendedScore(), 0.0001);
        assertEquals(0.02, bestBooksForUser.get(2).getUserRecommendedScore(), 0.0001);
        assertEquals(0.01, bestBooksForUser.get(3).getUserRecommendedScore(), 0.0001);
        assertEquals(0.03, bestBooksForUser.get(4).getUserRecommendedScore(), 0.0001);
    }

    @Test
    public void OtherUserTest(){

        session.getAgenda().getAgendaGroup("Close").setFocus();
        //session.getAgenda().getAgendaGroup("SeriesHandler").setFocus();
        session.getAgenda().getAgendaGroup("FindBest").setFocus();
        session.getAgenda().getAgendaGroup("OtherUserScore").setFocus();
        session.getAgenda().getAgendaGroup("ResetUserRecScores").setFocus();
        session.fireUntilHalt();

        for(Book b : bestBooksForUser)
            System.out.println(b.getTitle() + "---" + b.getUserRecommendedScore());

        assertEquals(0.00, bestBooksForUser.get(0).getUserRecommendedScore(), 0.0001);
        assertEquals(0.00, bestBooksForUser.get(1).getUserRecommendedScore(), 0.0001);
        assertEquals(0.00, bestBooksForUser.get(2).getUserRecommendedScore(), 0.0001);
        assertEquals(0.00, bestBooksForUser.get(3).getUserRecommendedScore(), 0.0001);
        assertEquals(0.01, bestBooksForUser.get(4).getUserRecommendedScore(), 0.0001);
    }

    @Test
    public void FindBestTest(){

        session.getAgenda().getAgendaGroup("Close").setFocus();
        //session.getAgenda().getAgendaGroup("SeriesHandler").setFocus();
        session.getAgenda().getAgendaGroup("FindBest").setFocus();
        session.getAgenda().getAgendaGroup("OtherUserScore").setFocus();
        session.getAgenda().getAgendaGroup("AdditionalEvaluation").setFocus();
        session.getAgenda().getAgendaGroup("AuthorScore").setFocus();
        session.getAgenda().getAgendaGroup("GenreScore").setFocus();
        session.getAgenda().getAgendaGroup("ResetUserRecScores").setFocus();
        session.fireUntilHalt();

        for(Book b : bestBooksForUser)
            System.out.println(b.getTitle() + "---" + b.getUserRecommendedScore());

        assertEquals("The Republic of Thieves", bestBooksForUser.get(0).getTitle());
        assertEquals("The Last Olympian", bestBooksForUser.get(1).getTitle());
        assertEquals("The Red Winter", bestBooksForUser.get(2).getTitle());
        assertEquals("The silver chair", bestBooksForUser.get(3).getTitle());
        assertEquals("Harry Potter and the deathly hallows", bestBooksForUser.get(4).getTitle());
    }

    @Test
    public void SeriesHandlerTest(){

        session.getAgenda().getAgendaGroup("Close").setFocus();
        session.getAgenda().getAgendaGroup("SeriesHandler").setFocus();
        session.getAgenda().getAgendaGroup("FindBest").setFocus();
        session.getAgenda().getAgendaGroup("OtherUserScore").setFocus();
        session.getAgenda().getAgendaGroup("AdditionalEvaluation").setFocus();
        session.getAgenda().getAgendaGroup("AuthorScore").setFocus();
        session.getAgenda().getAgendaGroup("GenreScore").setFocus();
        session.getAgenda().getAgendaGroup("ResetUserRecScores").setFocus();
        session.fireUntilHalt();

        for(Book b : bestBooksForUser)
            System.out.println(b.getTitle() + "---" + b.getUserRecommendedScore());

        assertEquals("The Lies of Locke Lamora", bestBooksForUser.get(0).getTitle());
        assertEquals("The Lightning Thief", bestBooksForUser.get(1).getTitle());
        assertEquals("The hound of Rowan", bestBooksForUser.get(2).getTitle());
        assertEquals("Prince Caspian", bestBooksForUser.get(3).getTitle());
        assertEquals("Harry Potter and the goblet of fire", bestBooksForUser.get(4).getTitle());
    }
}
