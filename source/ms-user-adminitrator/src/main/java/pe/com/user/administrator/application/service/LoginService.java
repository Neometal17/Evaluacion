package pe.com.user.administrator.application.service;

import lombok.RequiredArgsConstructor;
import pe.com.user.administrator.application.port.in.SignInUserCase;
import pe.com.user.administrator.application.port.out.LoginRepositoryPort;
import pe.com.user.administrator.application.port.out.TokenProviderPort;
import pe.com.user.administrator.config.PasswordValidator;
import pe.com.user.administrator.domain.model.Login;
import pe.com.user.administrator.infrastructure.exception.InactiveUserException;
import pe.com.user.administrator.infrastructure.exception.InvalidPasswordException;

@RequiredArgsConstructor
public class LoginService implements SignInUserCase {

    private final LoginRepositoryPort loginRepositoryPort;
    private final TokenProviderPort tokenProviderPort;
    private final PasswordValidator passwordValidator;

    @Override
    public Login execute(Login login) {
        Login result = loginRepositoryPort.login(login.getUserName(), login.getPassword());

        if(result.getActive() == 0){
            throw new InactiveUserException("El usuario se encuenta inactivo");
        }

        if(!passwordValidator.isValidPassword(login.getPassword())){
            throw new InvalidPasswordException("El password no cumple con los criterios");
        }

        String token = tokenProviderPort.generateToken(result.getUserName());

        result.setToken(token);

        return result;
    }


}
