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

    private List<Book> allBooks;

    private KieSession session;

    @Before
    public void setup(){
        KieServices ks = KieServices.Factory.get();
        KieBaseConfiguration kieBaseConfiguration = KieServices.Factory.get().newKieBaseConfiguration();
        kieBaseConfiguration.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kieContainer.newKieBase(kieBaseConfiguration);//config.getBase();//kieContainer.newKieBase(kieBaseConfiguration);

        session = kieBase.newKieSession();

        allBooks = bookRepository.findAll();

        User user = userRepository.findById(101);

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

        session.insert(user);
        for(Book b: allBooks)
            session.insert(b);
        for(Author a: readAuthors)
            session.insert(a);
        /*for(Score s : userScores)
            session.insert(s);*/
        for(Genre g : userGenres)
            session.insert(g);
    }

    @Test
    public void AuthorTest(){

        session.getAgenda().getAgendaGroup("Close").setFocus();
        session.getAgenda().getAgendaGroup("AuthorScore").setFocus();
        session.getAgenda().getAgendaGroup("ResetUserRecScores");
        session.fireUntilHalt();

        for(Book b : allBooks)
            System.out.println(b.getUserRecommendedScore());

        assertEquals(0.12, allBooks.get(0).getUserRecommendedScore(), 0.0001);
        assertEquals(0.12, allBooks.get(1).getUserRecommendedScore(), 0.0001);
        assertEquals(0.08, allBooks.get(2).getUserRecommendedScore(), 0.0001);
        assertEquals(0.0, allBooks.get(3).getUserRecommendedScore(), 0.0001);
    }

    @Test
    public void GenreTest(){

        session.getAgenda().getAgendaGroup("Close").setFocus();
        session.getAgenda().getAgendaGroup("GenreScore").setFocus();
        session.getAgenda().getAgendaGroup("ResetUserRecScores");
        session.fireUntilHalt();

        for(Book b : allBooks)
            System.out.println(b.getUserRecommendedScore());

        assertEquals(1.2, allBooks.get(0).getUserRecommendedScore(), 0.001);
        assertEquals(0.0, allBooks.get(1).getUserRecommendedScore(), 0.001);
        assertEquals(1.2, allBooks.get(2).getUserRecommendedScore(), 0.001);
        assertEquals(0.6, allBooks.get(3).getUserRecommendedScore(), 0.001);
    }

    @Test
    public void AdditionalTest(){

        session.getAgenda().getAgendaGroup("Close").setFocus();
        session.getAgenda().getAgendaGroup("AdditionalEvaluation").setFocus();
        session.getAgenda().getAgendaGroup("ResetUserRecScores");
        session.fireUntilHalt();

        for(Book b : allBooks)
            System.out.println(b.getUserRecommendedScore());

        assertEquals(0.02, allBooks.get(0).getUserRecommendedScore(), 0.0001);
        assertEquals(0.01, allBooks.get(1).getUserRecommendedScore(), 0.0001);
        assertEquals(0.02, allBooks.get(2).getUserRecommendedScore(), 0.0001);
        assertEquals(0.02, allBooks.get(3).getUserRecommendedScore(), 0.0001);
    }
}
