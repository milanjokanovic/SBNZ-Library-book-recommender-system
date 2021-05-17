package rs.sbnz.book_recommender.dto;

import java.util.List;

public class BookDTO {
    private int id;
    private int pageNum;
    private int viewNumber;
    private String title;
    private String series;
    private int seriesNumber;
    private String targetAudience;
    private Boolean basedOnRealEvent;
    private Boolean nobelPrize;
    private int yearOfPublishing;
    private String authorName;
    private List<String> genres;
    private double avgScore;

    public BookDTO(int id, int pageNum, int viewNumber, String title, String series,
                   int seriesNumber, String targetAudience, Boolean basedOnRealEvent, Boolean nobelPrize,
                   int yearOfPublishing, String authorName, List<String> genres, double avgScore) {
        this.id = id;
        this.pageNum = pageNum;
        this.viewNumber = viewNumber;
        this.title = title;
        this.series = series;
        this.seriesNumber = seriesNumber;
        this.targetAudience = targetAudience;
        this.basedOnRealEvent = basedOnRealEvent;
        this.nobelPrize = nobelPrize;
        this.yearOfPublishing = yearOfPublishing;
        this.authorName = authorName;
        this.genres = genres;
        this.avgScore = avgScore;
    }

    public int getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(int viewNumber) {
        this.viewNumber = viewNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }
}
