package pe.com.user.administrator.application.service;

import lombok.RequiredArgsConstructor;
import pe.com.user.administrator.application.port.in.SignInUserCase;
import pe.com.user.administrator.application.port.out.LoginRepositoryPort;
import pe.com.user.administrator.domain.model.Login;

@RequiredArgsConstructor
public class LoginService implements SignInUserCase {

    private final LoginRepositoryPort loginRepositoryPort;

    @Override
    public Login signIn(Login login) {
        return loginRepositoryPort.login(login);
    }
}
