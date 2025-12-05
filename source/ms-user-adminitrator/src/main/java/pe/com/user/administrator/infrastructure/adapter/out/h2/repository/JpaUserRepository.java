package pe.com.user.administrator.infrastructure.adapter.out.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.user.administrator.infrastructure.adapter.out.h2.persistence.UserJpaEntity;

import java.util.Optional;


public interface JpaUserRepository extends JpaRepository<UserJpaEntity, Long> {
    UserJpaEntity findByNameAndPassword(String name, String password);
    Optional<UserJpaEntity> findByEmail(String email);
}