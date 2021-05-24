package rs.sbnz.book_recommender.model;

import rs.sbnz.book_recommender.model.enums.TargetAudience;
import org.kie.api.definition.type.PropertyReactive;

import javax.persistence.*;
import java.util.Set;

@PropertyReactive
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
    @Column
    private String series;
    @Column
    private int seriesNumber;
    @Column
    private String targetAudience;
    @Column
    private Boolean basedOnRealEvent;
    @Column
    private Boolean nobelPrize;
    /*@Column
    private Boolean neustadtInternationalLiteratureAward;
    @Column
    private Boolean internationalBotevPrize;*/
    @Column
    private int yearOfPublishing;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "book")
    private Set<Score> score;

    @Column
    private double systemGrade;

    @Column
    private int userRecommendedScore;

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

    public double avgGrade() {
        double sum = 0;
        for(Score s : this.score) {
            sum += s.getValue();
        }
        if(sum ==0)
            return 0;
        return sum / this.score.size();
    }


    public int getUserRecommendedScore() {
        return userRecommendedScore;
    }

    public void setUserRecommendedScore(int userRecommendedScore) {
        this.userRecommendedScore = userRecommendedScore;
    }

    public void addScore(Score score) {
        this.score.add(score);
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(int seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public Boolean getBasedOnRealEvent() {
        return basedOnRealEvent;
    }

    public void setBasedOnRealEvent(Boolean basedOnRealEvent) {
        this.basedOnRealEvent = basedOnRealEvent;
    }

    public Boolean getNobelPrize() {
        return nobelPrize;
    }

    public void setNobelPrize(Boolean nobelPrize) {
        this.nobelPrize = nobelPrize;
    }

    /*public Boolean getNeustadtInternationalLiteratureAward() {
        return neustadtInternationalLiteratureAward;
    }

    public void setNeustadtInternationalLiteratureAward(Boolean neustadtInternationalLiteratureAward) {
        this.neustadtInternationalLiteratureAward = neustadtInternationalLiteratureAward;
    }

    public Boolean getInternationalBotevPrize() {
        return internationalBotevPrize;
    }

    public void setInternationalBotevPrize(Boolean internationalBotevPrize) {
        this.internationalBotevPrize = internationalBotevPrize;
    }*/

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

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
