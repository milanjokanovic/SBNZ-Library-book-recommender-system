package rs.sbnz.book_recommender.services;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import rs.sbnz.book_recommender.helper.user_recommendation.BooksHelper;
import rs.sbnz.book_recommender.model.*;
import rs.sbnz.book_recommender.repositories.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRecommendationService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    KieContainer kieContainer;

    public void prepareData() {
        List<Book> allBooks = bookRepository.findAll();
        
        User user = userRepository.findById(1).orElseThrow(() -> new ResourceNotFoundException("User not found"));

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
        KieServices ks = KieServices.Factory.get();
        /*KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("rs.sbnz", "drools-spring-kjar", "0.0.1-SNAPSHOT"));*/

        KieBaseConfiguration kieBaseConfiguration = KieServices.Factory.get().newKieBaseConfiguration();
        kieBaseConfiguration.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kieContainer.newKieBase(kieBaseConfiguration);//config.getBase();//kieContainer.newKieBase(kieBaseConfiguration);

        KieSession session = kieBase.newKieSession();

        //LOG.info("Creating kieSession");
        session.insert(user);
        for(Book b: allBooks)
            session.insert(b);
        for(Author a: readAuthors)
            session.insert(a);
        /*for(Score s : userScores)
            session.insert(s);*/
        for(Genre g : userGenres)
            session.insert(g);
        //LOG.info("Now running data");
        //session.getAgenda().getAgendaGroup("UserAgeFilter").setFocus();
        //session.getAgenda().getAgendaGroup("AuthorScore").setFocus();
        session.getAgenda().getAgendaGroup("Close").setFocus();
        session.getAgenda().getAgendaGroup("AdditionalEvaluation").setFocus();
        //session.getAgenda().getAgendaGroup("ResetUserRecScores");
        //session.getAgenda().getAgendaGroup("GenreScore").setFocus();
        session.fireUntilHalt();//AuthorScore

        System.out.println("dd");

    }
}
