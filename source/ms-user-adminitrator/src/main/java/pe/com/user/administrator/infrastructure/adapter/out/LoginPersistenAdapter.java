package pe.com.user.administrator.infrastructure.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pe.com.user.administrator.application.port.out.LoginRepositoryPort;
import pe.com.user.administrator.domain.model.Login;
import pe.com.user.administrator.infrastructure.exception.InvalidCredentiasException;
import pe.com.user.administrator.infrastructure.adapter.out.h2.mapper.LoginMapper;
import pe.com.user.administrator.infrastructure.adapter.out.h2.repository.JpaUserRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class LoginPersistenAdapter implements LoginRepositoryPort {

    private final JpaUserRepository userRepository;

    @Override
    @Transactional
    public Login login(String userName, String password) {
        return Optional.ofNullable(userRepository.findByNameAndPassword(userName, password))
                .map(LoginMapper::toDomain)
                .orElseThrow(() -> new InvalidCredentiasException("Usuario y/o Contraseña Invalida"));
    }
}
