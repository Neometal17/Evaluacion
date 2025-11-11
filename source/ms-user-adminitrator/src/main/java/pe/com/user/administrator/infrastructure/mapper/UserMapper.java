package pe.com.user.administrator.infrastructure.mapper;

import pe.com.user.administrator.domain.model.User;
import pe.com.user.administrator.infrastructure.adapter.in.web.UserDTO;
import pe.com.user.administrator.infrastructure.adapter.out.persistence.UserJpaEntity;

public class UserMapper {
    public static UserJpaEntity toEntity(User user){
        UserJpaEntity entity = new UserJpaEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setPassword(user.getPassword());
        entity.setEmail(user.getEmail());
        return entity;
    }

    public static User toDomain(UserJpaEntity entity){
        return User.builder()
                .id(entity.getId())
                .name(entity.getName())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .build();
    }

    public static User toDomain(UserDTO dto){
        return User.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
    }
}
