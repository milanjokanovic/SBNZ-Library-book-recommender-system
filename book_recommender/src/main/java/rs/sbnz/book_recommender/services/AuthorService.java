package rs.sbnz.book_recommender.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import rs.sbnz.book_recommender.model.Author;
import rs.sbnz.book_recommender.repositories.AuthorRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public Author findById(int id) throws ResourceNotFoundException
    {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found for this id :: " + id));
    }

    public Author findByName(String name) throws ResourceNotFoundException
    {
        Author author = authorRepository.findByName(name);
        if(author == null)
            return new Author();
        else
            return author;
    }

    public List<Author> findAllAuthors()
    {
        return authorRepository.findAll();
    }

    public Author addAuthor(Author author)
    {
        /*Author nameValidator = authorRepository.findByName(author.getName());
        Map< Author, Boolean > response = new HashMap< >();

        if(nameValidator == null)
            response.put(null, Boolean.TRUE);*/

        authorRepository.save(author);
        return author;
    }

    public Author updateAuthor(int authorId, Author authorDetails) throws ResourceNotFoundException {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found for this id :: " + authorId));

        author.setName(authorDetails.getName());
        author.setSystemGrade(authorDetails.getSystemGrade());

        final Author updatedAuthor = authorRepository.save(author);

        return updatedAuthor;
    }

    public Map<String, Boolean> deleteById(int authorId) throws ResourceNotFoundException
    {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found for this id :: " + authorId));

        authorRepository.delete(author);
        Map< String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
