package rs.sbnz.book_recommender.model.facts;

import rs.sbnz.book_recommender.model.enums.LengthType;

public class BookLengthTypeData {
    private int minLength;
    private int maxLength;
    private double grade;
    private LengthType type;
    private double value;

    public BookLengthTypeData() {

    }

    public BookLengthTypeData(int min, int max, LengthType type) {
        this.maxLength = max;
        this.minLength = min;
        this.type = type;
        this.grade = 0;
        this.value = 0;
    }

    public void increaseGrade(double grade) {
        this.grade += grade;
    }

    public void resetGrade() {
        grade = 0;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public LengthType getType() {
        return type;
    }

    public void setType(LengthType type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
