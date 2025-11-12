package pe.com.user.administrator.application.port.in;

import pe.com.user.administrator.domain.model.Login;

public interface SignInUserCase {
    Login signIn(Login login);
}
