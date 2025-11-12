package pe.com.user.administrator.infrastructure.mapper;

import pe.com.user.administrator.domain.model.Login;
import pe.com.user.administrator.infrastructure.in.web.controller.dto.login.LoginRequest;
import pe.com.user.administrator.infrastructure.in.web.controller.dto.login.LoginResponse;
import pe.com.user.administrator.infrastructure.out.persistence.user.UserJpaEntity;

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
                .build();
    }

    public static LoginResponse toResponse(Login login) {
        return LoginResponse.builder()
                .userName(login.getUserName())
                .token(login.getToken())
                .build();
    }
}
