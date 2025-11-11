package pe.com.user.administrator.application.service;

import lombok.RequiredArgsConstructor;
import pe.com.user.administrator.application.port.in.CreateUserUseCase;
import pe.com.user.administrator.application.port.out.UserRepositoryPort;
import pe.com.user.administrator.domain.model.User;

@RequiredArgsConstructor
public class UserService implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User createUserUseCase(User user) {
        return userRepositoryPort.save(user);
    }
}
