package rs.sbnz.book_recommender.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @ManyToOne
    private Book favoriteBook;

    @ManyToOne
    private Author favoriteAuthor;

    @Column
    private Date lastActive;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Score> bookScore;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_read_books",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> readBooks;

    public User(){}

    public Set<Book> getReadBooks() {
        return readBooks;
    }

    public void setReadBooks(Set<Book> readBooks) {
        this.readBooks = readBooks;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public Date getLastActive() {
        return lastActive;
    }

    public void setLastActive(Date lastActive) {
        this.lastActive = lastActive;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
