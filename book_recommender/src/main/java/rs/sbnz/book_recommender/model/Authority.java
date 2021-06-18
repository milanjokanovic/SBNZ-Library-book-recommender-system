package rs.sbnz.book_recommender.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

// POJO koji implementira Spring Security GrantedAuthority kojim se mogu definisati role u aplikaciji
@Entity
@Table(name="authority")
public class Authority implements GrantedAuthority {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="name")
    String name;


    public Authority() {
        super();
    }

    public Authority(String name) {
        super();
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
