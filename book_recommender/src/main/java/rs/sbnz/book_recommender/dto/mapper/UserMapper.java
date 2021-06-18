package rs.sbnz.book_recommender.dto.mapper;

import rs.sbnz.book_recommender.dto.UserDTO;
import rs.sbnz.book_recommender.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper implements MapperInterface<User, UserDTO>{

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setUsername(dto.getUsername());
        return user;
    }

    @Override
    public List<User> toEntityList(List<UserDTO> dtoList) {

        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public UserDTO toDto(User entity) {
        return new UserDTO(entity.getId(), entity.getUsername(), entity.getEmail(), entity.getPassword(), entity.getAge());
    }

    @Override
    public List<UserDTO> toDtoList(List<User> entityList) {

        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
