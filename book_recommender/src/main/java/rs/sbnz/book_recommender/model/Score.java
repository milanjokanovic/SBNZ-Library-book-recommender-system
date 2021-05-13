package rs.sbnz.book_recommender.model;

import javax.persistence.*;

@Entity
public class Score {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private int value;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    public Score(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
