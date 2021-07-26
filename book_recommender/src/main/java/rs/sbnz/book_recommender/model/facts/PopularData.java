package rs.sbnz.book_recommender.model.facts;

public class PopularData {
    private int bookId;
    private double popularFactor;
    private double newPopularFactor;

    public PopularData(){}

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public double getPopularFactor() {
        return popularFactor;
    }

    public void setPopularFactor(double popularFactor) {
        this.popularFactor = popularFactor;
    }

    public double getNewPopularFactor() {
        return newPopularFactor;
    }

    public void setNewPopularFactor(double newPopularFactor) {
        this.newPopularFactor = newPopularFactor;
    }
}
