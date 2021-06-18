package rs.sbnz.book_recommender.dto;

public class ScoreDTO {
    private int id;
    private int userId;
    private String bookTitle;
    private int value;

    public ScoreDTO() {}

    public ScoreDTO(int id, int userId, String bookTitle, int value) {
        this.id = id;
        this.userId = userId;
        this.bookTitle = bookTitle;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userMail) {
        this.userId = userMail;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
