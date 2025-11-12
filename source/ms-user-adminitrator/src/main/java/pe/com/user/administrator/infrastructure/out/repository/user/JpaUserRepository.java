package pe.com.user.administrator.infrastructure.out.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.user.administrator.infrastructure.out.persistence.user.UserJpaEntity;


public interface JpaUserRepository extends JpaRepository<UserJpaEntity, Long> {
    UserJpaEntity findByNameAndPassword(String name, String password);
    UserJpaEntity findByEmail(String email);
}