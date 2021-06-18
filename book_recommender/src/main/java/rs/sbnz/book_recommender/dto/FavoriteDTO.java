package rs.sbnz.book_recommender.dto;

public class FavoriteDTO {
    private int id;
    private int userId;

    public FavoriteDTO(){}

    public FavoriteDTO(int id, int userId) {
        this.id = id;
        this.userId = userId;
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

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
