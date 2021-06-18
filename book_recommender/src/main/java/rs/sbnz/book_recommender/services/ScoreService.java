package rs.sbnz.book_recommender.services;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.sbnz.book_recommender.config.EventSessionConfig;
import rs.sbnz.book_recommender.model.Author;
import rs.sbnz.book_recommender.model.Book;
import rs.sbnz.book_recommender.model.Score;
import rs.sbnz.book_recommender.model.User;
import rs.sbnz.book_recommender.repositories.AuthorRepository;
import rs.sbnz.book_recommender.repositories.BookRepository;
import rs.sbnz.book_recommender.repositories.ScoreRepository;
import rs.sbnz.book_recommender.repositories.UserRepository;

import java.util.List;

@Service
public class ScoreService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    private EventSessionConfig config;


    public List<Score> findAll()
    {
        return scoreRepository.findAll();
    }

    public Score addScore(Score score) throws Exception {
        Book book = bookRepository.findByTitle(score.getBook().getTitle());

        if(book == null){
            throw new Exception();
        }

        User user = userRepository.findByEmail(score.getUser().getEmail());

        if(user == null){
            throw new Exception();
        }


        KieSession session = config.userAlarmsSession();
        //User user = userRepository.findAll().get(0);
        session.getAgenda().getAgendaGroup("UserAlarms").setFocus();

        session.insert(user);

        session.fireUntilHalt();

        System.out.println("Blocked: " + user.getBlockedScoringFunction());
        userRepository.save(user);
        if(user.getBlockedScoringFunction() == 1){
            throw new Exception();
        }

        score.setBook(book);
        score.setUser(user);



        Score oldScore = scoreRepository.findByUserAndBook(user, book); //TODO check if it works make
        // a method that just returns this and check if it works
        // TODO make a method that return user scores

        if(oldScore != null){
            oldScore.setValue(score.getValue());
            return updateScore(oldScore.getId(), oldScore);
        }




        scoreRepository.save(score);

        return score;
    }

    public Boolean deleteById(int scoreId) //throws ResourceNotFoundException
    {
        Score score = scoreRepository.findById(scoreId)
                .orElse(null);
        if(score == null)
            return false;
        scoreRepository.delete(score);
        /*Map< String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);*/
        return true;
    }

    public Score updateScore(int scoreId, Score score) // throws ResourceNotFoundException {
    {
        Score foundScore = scoreRepository.findById(scoreId)
                .orElse(null); //Throw(() -> new ResourceNotFoundException("Employee not found for this id :: " + userId));

        if(foundScore == null)
            return null;

        foundScore.setValue(score.getValue());

        final Score updatedScore = scoreRepository.save(foundScore);

        return updatedScore;
    }
}
