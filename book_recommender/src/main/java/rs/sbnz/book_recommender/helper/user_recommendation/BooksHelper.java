package rs.sbnz.book_recommender.helper.user_recommendation;

import rs.sbnz.book_recommender.model.Book;

public class BooksHelper {
    private Book book;
    private int gradeForUser;

    public BooksHelper() {}

    public BooksHelper(Book book, int gradeForUser) {
        this.book = book;
        this.gradeForUser = gradeForUser;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getGradeForUser() {
        return gradeForUser;
    }

    public void setGradeForUser(int gradeForUser) {
        this.gradeForUser = gradeForUser;
    }
}
