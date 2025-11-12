package pe.com.user.administrator.infrastructure.out.repository.user.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import pe.com.user.administrator.application.port.out.LoginRepositoryPort;
import pe.com.user.administrator.application.service.TokenProviderService;
import pe.com.user.administrator.domain.model.Login;
import pe.com.user.administrator.infrastructure.exception.InactiveUserException;
import pe.com.user.administrator.infrastructure.exception.InvalidCredentiasException;
import pe.com.user.administrator.infrastructure.exception.InvalidPasswordException;
import pe.com.user.administrator.infrastructure.mapper.LoginMapper;
import pe.com.user.administrator.infrastructure.out.repository.user.JpaUserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class LoginPersistenAdapter implements LoginRepositoryPort {

    private final JpaUserRepository userRepository;
    private final TokenProviderService tokenProviderService;

    @Value("${app.user.validation.password}")
    String validationPassword;

    @Override
    @Transactional
    public Login login(Login login) {
        return Optional.ofNullable(userRepository.findByNameAndPassword(login.getUserName(), login.getPassword()))
                .map(user -> {
                    if(user.getActive() == 0){
                        throw new InactiveUserException("El usuario se encuenta inactivo");
                    }

                    Pattern passwordRegex = Pattern.compile(validationPassword);
                    Matcher matcher = passwordRegex.matcher(login.getPassword());

                    if(!matcher.matches()){
                        throw new InvalidPasswordException("El password no cumple con ");
                    }

                    String token = tokenProviderService.generateToken(user.getName());

                    user.setLastLogin(LocalDateTime.now());
                    user.setToken(token);
                    return LoginMapper.toDomain(user);
                })
                .orElseThrow(() -> new InvalidCredentiasException("Usuario y/o Contraseña Invalida"));

    }
}
