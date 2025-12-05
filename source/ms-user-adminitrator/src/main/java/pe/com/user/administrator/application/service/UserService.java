package pe.com.user.administrator.application.service;

import lombok.RequiredArgsConstructor;
import pe.com.user.administrator.application.port.in.CreateUserUseCase;
import pe.com.user.administrator.application.port.out.UserRepositoryPort;
import pe.com.user.administrator.domain.model.User;
import pe.com.user.administrator.infrastructure.exception.ExisteUserException;

@RequiredArgsConstructor
public class UserService implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User execute(User user) {
        if(userRepositoryPort.existsUser(user.getEmail())){
            throw new ExisteUserException("El correo ya registrado");
        }

        User saved = userRepositoryPort.save(user);

        return saved;
    }
}
