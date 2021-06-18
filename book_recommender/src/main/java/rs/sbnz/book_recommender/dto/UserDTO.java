package rs.sbnz.book_recommender.dto;

public class UserDTO {
    private int id;
    private String email;
    private String password;
    private String username;
    private String age;

    public UserDTO(){}

    public UserDTO(int id, String username, String email, String password, String age) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.age = age;
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
