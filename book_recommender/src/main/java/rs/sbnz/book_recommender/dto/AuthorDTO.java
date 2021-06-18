package rs.sbnz.book_recommender.dto;

import rs.sbnz.book_recommender.model.Author;

public class AuthorDTO {
    private int id;
    private String name;
    private double systemGrade;
    private boolean favored;

    public AuthorDTO() {}

    public AuthorDTO(int id, String name, double systemGrade) {
        this.id = id;
        this.name = name;
        this.systemGrade = systemGrade;
    }

    public AuthorDTO(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.systemGrade = author.getSystemGrade();
    }

    public boolean isFavored() {
        return favored;
    }

    public void setFavored(boolean favored) {
        this.favored = favored;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
