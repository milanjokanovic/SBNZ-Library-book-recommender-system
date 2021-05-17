package rs.sbnz.book_recommender.dto;

public class ScoreDTO {
    private int id;
    private String userMail;
    private String bookTitle;
    private int value;

    public ScoreDTO(int id, String userMail, String bookTitle, int value) {
        this.id = id;
        this.userMail = userMail;
        this.bookTitle = bookTitle;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
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
