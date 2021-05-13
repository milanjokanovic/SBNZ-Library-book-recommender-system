package rs.sbnz.book_recommender.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private int id;
    @Column
    private int pageNum;
    @Column
    private String title;
    @Column
    private int brPregleda;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "book")
    private Set<Score> score;

    @Column
    private double systemGrade;

    @ManyToOne
    private Author author;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "favoriteBook")
    private Set<User> favoredByUsers;
    // genre

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "readBooks")
    private Set<User> users;

    public Book() {
    }

    /*public double getAvgGrade() {
        double sum = 0;
        for(int i = 0; i < this.score.size(); i++) {
            sum += this.score.get(i);
        }
        if(sum ==0)
            return 0;
        return sum / this.score.size();
    }*/

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<User> getFavoredByUsers() {
        return favoredByUsers;
    }

    public void setFavoredByUsers(Set<User> favoredByUsers) {
        this.favoredByUsers = favoredByUsers;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Score> getScore() {
        return score;
    }

    public void setScore(Set<Score> score) {
        this.score = score;
    }

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

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBrPregleda() {
        return brPregleda;
    }

    public void setBrPregleda(int brPregleda) {
        this.brPregleda = brPregleda;
    }

    public void addPregled() {
        this.brPregleda += 1;
    }
}
