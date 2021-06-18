package rs.sbnz.book_recommender.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class User extends AbsUser{
    @ManyToOne
    private Book favoriteBook;

    @ManyToOne
    private Author favoriteAuthor;

    @Column
    private Date lastActive;

    @Column String age;

    @Column
    private int blockedScoringFunction;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Score> bookScore;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_read_books",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> readBooks;

    public User(){}

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Set<Book> getReadBooks() {
        return readBooks;
    }

    public void setReadBooks(Set<Book> readBooks) {
        this.readBooks = readBooks;
    }



    public Date getLastActive() {
        return lastActive;
    }

    public void setLastActive(Date lastActive) {
        this.lastActive = lastActive;
    }


    public Book getFavoriteBook() {
        return favoriteBook;
    }

    public void setFavoriteBook(Book favoriteBook) {
        this.favoriteBook = favoriteBook;
    }

    public Author getFavoriteAuthor() {
        return favoriteAuthor;
    }

    public void setFavoriteAuthor(Author favoriteAuthor) {
        this.favoriteAuthor = favoriteAuthor;
    }

    public Set<Score> getBookScore() {
        return bookScore;
    }

    public void setBookScore(Set<Score> bookScore) {
        this.bookScore = bookScore;
    }

    public int getBlockedScoringFunction() {
        return blockedScoringFunction;
    }

    public void setBlockedScoringFunction(int blockedScoringFunction) {
        this.blockedScoringFunction = blockedScoringFunction;
    }
}
