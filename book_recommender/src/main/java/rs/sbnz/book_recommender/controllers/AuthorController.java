package rs.sbnz.book_recommender.controllers;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rs.sbnz.book_recommender.dto.AuthorDTO;
import rs.sbnz.book_recommender.dto.BookDTO;
import rs.sbnz.book_recommender.dto.mapper.BookMapper;
import rs.sbnz.book_recommender.helper.AuthorMapper;
import rs.sbnz.book_recommender.model.Author;
import rs.sbnz.book_recommender.model.Book;
import rs.sbnz.book_recommender.model.Server;
import rs.sbnz.book_recommender.services.AuthorService;
import org.springframework.web.bind.annotation.*;
import rs.sbnz.book_recommender.services.SystemGradeService;
import rs.sbnz.book_recommender.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @Autowired
    KieContainer kieContainer;

    @Autowired
    UserService userService;

    @Autowired
    private SystemGradeService systemGradeService;

    private AuthorMapper authorMapper = new AuthorMapper();

    private BookMapper bookMapper = new BookMapper();

    @GetMapping("/id/{id}")
    public ResponseEntity<AuthorDTO> findByAuthorId(@PathVariable int id)
    {
        try {
            AuthorDTO author = authorMapper.toDto(authorService.findById(id));

            return new ResponseEntity<>(author, HttpStatus.OK);
        } catch (Exception e) {
            //e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<AuthorDTO> findByAuthorId(@PathVariable String name)
    {
        try {
            AuthorDTO author = authorMapper.toDto(authorService.findByName(name));
            if(author.getId() == 0)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(author, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            //e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> findAllAuthors()
    {
        List<AuthorDTO> authors = authorMapper.toDtoList(authorService.findAllAuthors());
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("/userview/{id}")
    public ResponseEntity<List<AuthorDTO>> findAllAuthorsUserView(@PathVariable(value = "id") Integer userId)
    {
        List<Author> authors = authorService.findAllAuthors();
        List<AuthorDTO> authorsDTO = authorMapper.toDtoList(authors);
        for(Author author : authors){
            for (AuthorDTO dto: authorsDTO){
                if(dto.getId() == author.getId()){
                    dto.setFavored(userService.isFavoriteAuthor(author, userId));
                }
            }
        }
        return new ResponseEntity<>(authorsDTO, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Void> addAuthor(@Valid @RequestBody AuthorDTO authorDTO)
    {
        Author author = authorMapper.toEntity(authorDTO);
        //return authorMapper.toDto(authorService.addAuthor(author));
        try {
            authorService.addAuthor(author);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Map< String, Boolean > deleteAuthor(@PathVariable(value = "id") Integer authorId)
    {
        return authorService.deleteById(authorId);
    }

    @PutMapping("{id}")
    public ResponseEntity <AuthorDTO> updateAuthor(@PathVariable(value = "id") Integer authorId, @Valid @RequestBody AuthorDTO authorDetails)
    {
        Author author = authorMapper.toEntity(authorDetails);
        return ResponseEntity.ok(authorMapper.toDto(authorService.updateAuthor(authorId, author)));
    }

    @GetMapping("/testpravila")
    public void RuleTest()
    {
        /*KieServices kieServices = KieServices.Factory.get();

        KieContainer kContainer = kieServices.getKieClasspathContainer();
        */
        //LOG.info("Creating kieBase");
        KieBase kieBase = kieContainer.getKieBase();

        //LOG.info("There should be rules: ");
        /*for ( KiePackage kp : kieBase.getKiePackages() ) {
            for (Rule rule : kp.getRules()) {
                //LOG.info("kp " + kp + " rule " + rule.getName());
            }
        }*/

        //LOG.info("Creating kieSession");
        KieSession session = kieBase.newKieSession();

        //LOG.info("Now running data");

        Server s1 = new Server("rhel7",2,1024,2048);
        session.insert(s1);
        session.fireAllRules();
    }

    @GetMapping("/test/systemgrade")
    public ResponseEntity<List<BookDTO>> RuleTestGrade()
    {
        //systemGradeService.fireEvent();
        List<Book> books = systemGradeService.fireEvent();
        return ResponseEntity.ok(bookMapper.toDtoList(books));
    }

    @GetMapping("/test/scoring")
    public String ScoringTest()
    {
        //systemGradeService.getGrade();
        return "Ok";
    }
}
