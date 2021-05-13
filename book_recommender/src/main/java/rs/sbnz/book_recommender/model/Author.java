package rs.sbnz.book_recommender.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Author {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private double systemGrade;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "author")
    private Set<Book> books;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "favoriteAuthor")
    private Set<User> favoredByUsers;


    public Author(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSystemGrade() {
        return systemGrade;
    }

    public void setSystemGrade(double systemGrade) {
        this.systemGrade = systemGrade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Set<User> getFavoredByUsers() {
        return favoredByUsers;
    }

    public void setFavoredByUsers(Set<User> favoredByUsers) {
        this.favoredByUsers = favoredByUsers;
    }
}
