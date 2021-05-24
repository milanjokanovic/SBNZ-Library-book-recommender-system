package rs.sbnz.book_recommender.services;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import rs.sbnz.book_recommender.helper.user_recommendation.BooksHelper;
import rs.sbnz.book_recommender.model.Book;
import rs.sbnz.book_recommender.model.Server;
import rs.sbnz.book_recommender.model.User;
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



        KieBase kieBase = kieContainer.getKieBase();



        //LOG.info("Creating kieSession");
        KieSession session = kieBase.newKieSession();
        session.insert(user);
        for(Book b: allBooks)
            session.insert(b);
        //LOG.info("Now running data");

        session.fireAllRules();

        System.out.println("dd");

    }
}
