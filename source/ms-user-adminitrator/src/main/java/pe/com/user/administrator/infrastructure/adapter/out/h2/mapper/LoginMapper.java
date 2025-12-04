package pe.com.user.administrator.infrastructure.adapter.out.h2.mapper;

import pe.com.user.administrator.domain.model.Login;
import pe.com.user.administrator.infrastructure.adapter.in.dto.login.LoginDto;
import pe.com.user.administrator.infrastructure.adapter.in.dto.login.LoginRequest;
import pe.com.user.administrator.infrastructure.adapter.in.dto.login.LoginResponse;
import pe.com.user.administrator.infrastructure.adapter.out.h2.persistence.UserJpaEntity;

public class LoginMapper {

    public static Login toDomain(LoginRequest loginDto){
        return Login.builder()
                .userName(loginDto.getUserName())
                .password(loginDto.getPassword())
                .build();
    }

    public static Login toDomain(UserJpaEntity entity){
        return Login.builder()
                .userName(entity.getName())
                .token(entity.getToken())
                .active(entity.getActive())
                .build();
    }

    public static LoginDto toDto(UserJpaEntity entity){
        return LoginDto.builder()
                .name(entity.getName())
                .active(entity.getActive())
                .build();
    }

    public static LoginResponse toResponse(Login login) {
        return LoginResponse.builder()
                .userName(login.getUserName())
                .token(login.getToken())
                .build();
    }
}
