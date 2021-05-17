package rs.sbnz.book_recommender.dto;

import rs.sbnz.book_recommender.model.Genre;

public class GenreDTO {
    private int id;
    private String name;
    private double systemGrade;

    public GenreDTO() {}

    public GenreDTO(int id, String name, double systemGrade) {
        this.id = id;
        this.name = name;
        this.systemGrade = systemGrade;
    }

    public GenreDTO(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
        this.systemGrade = genre.getSystemGrade();
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
