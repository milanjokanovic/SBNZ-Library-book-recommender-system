package rs.sbnz.book_recommender.model.facts;

public class GeneralBookData {
    private double avgBookScore;
    private double avgBookViewNumber;
    private double bookCount;


    public GeneralBookData() {}

    public void increaseDataValues(Double score, Integer viewNumber) {
        avgBookScore += score;
        avgBookViewNumber += viewNumber;
        bookCount += 1;
    }

    public double getAvgBookScore() {
        return avgBookScore;
    }
    public void setAvgBookScore(double avgBookScore) {
        this.avgBookScore = avgBookScore;
    }
    public double getAvgBookViewNumber() {
        return avgBookViewNumber;
    }
    public void setAvgBookViewNumber(double avgBookViewNumber) {
        this.avgBookViewNumber = avgBookViewNumber;
    }
    public double getBookCount() {
        return bookCount;
    }
    public void setBookCount(double bookCount) {
        this.bookCount = bookCount;
    }

}
