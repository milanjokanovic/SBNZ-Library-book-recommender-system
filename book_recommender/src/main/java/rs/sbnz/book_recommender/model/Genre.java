package rs.sbnz.book_recommender.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Genre {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String name;

    @Column
    private double systemGrade;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "genres")
    private Set<Book> books;

    public Genre(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSystemGrade() {
        return systemGrade;
    }

    public void setSystemGrade(double systemGrade) {
        this.systemGrade = systemGrade;
    }
}
