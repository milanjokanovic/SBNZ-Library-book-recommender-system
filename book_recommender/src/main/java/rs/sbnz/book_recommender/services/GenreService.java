package rs.sbnz.book_recommender.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.sbnz.book_recommender.model.Genre;
import rs.sbnz.book_recommender.repositories.GenreRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GenreService {
    @Autowired
    GenreRepository genreRepository;

    public Genre findById(int id) throws ResourceNotFoundException
    {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found for this id :: " + id));
    }

    public Genre findByName(String name) throws ResourceNotFoundException
    {
        Genre genre = genreRepository.findByName(name);
        if(genre == null)
            return new Genre();
        else
            return genre;
    }

    public List<Genre> findAllGenres()
    {
        return genreRepository.findAll();
    }

    public Genre addGenre(Genre genre)
    {
        /*Genre nameValidator = genreRepository.findByName(genre.getName());
        Map< Genre, Boolean > response = new HashMap< >();

        if(nameValidator == null)
            response.put(null, Boolean.TRUE);*/

        genreRepository.save(genre);
        return genre;
    }

    public Genre updateGenre(int genreId, Genre genreDetails) throws ResourceNotFoundException {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found for this id :: " + genreId));

        genre.setName(genreDetails.getName());
        genre.setSystemGrade(genreDetails.getSystemGrade());

        final Genre updatedGenre = genreRepository.save(genre);

        return updatedGenre;
    }

    public Map<String, Boolean> deleteById(int genreId) throws ResourceNotFoundException
    {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found for this id :: " + genreId));

        genreRepository.delete(genre);
        Map< String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
