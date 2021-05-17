package rs.sbnz.book_recommender.dto.mapper;

import rs.sbnz.book_recommender.dto.UserDTO;
import rs.sbnz.book_recommender.model.User;

import java.util.List;

public class UserMapper implements MapperInterface<User, UserDTO>{

    @Override
    public User toEntity(UserDTO dto) {
        return null;
    }

    @Override
    public List<User> toEntityList(List<UserDTO> dtoList) {
        return null;
    }

    @Override
    public UserDTO toDto(User entity) {
        return null;
    }

    @Override
    public List<UserDTO> toDtoList(List<User> entityList) {
        return null;
    }
}
