package rs.sbnz.book_recommender.model.facts;

public class SeriesData {
    private double grade;
    private double value;
    private double count;

    public SeriesData() {}

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void increaseGrade(double grade) {
        this.grade += grade;
        count += 1;
    }

    public double getCount() {
        return count;
    }
}
