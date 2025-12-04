package pe.com.user.administrator.application.port.in;

import pe.com.user.administrator.domain.model.User;

public interface CreateUserUseCase {
    User execute(User user);
}
