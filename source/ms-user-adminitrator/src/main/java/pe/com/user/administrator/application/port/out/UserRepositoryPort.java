package pe.com.user.administrator.application.port.out;

import pe.com.user.administrator.domain.model.User;

import java.util.List;

public interface UserRepositoryPort {
    User save(User user);
    List<User> findAll();
}
