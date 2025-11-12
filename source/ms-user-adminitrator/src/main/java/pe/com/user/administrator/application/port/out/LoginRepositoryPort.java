package pe.com.user.administrator.application.port.out;

import pe.com.user.administrator.domain.model.Login;

public interface LoginRepositoryPort {
    Login login(Login login);
}
