package pe.com.user.administrator.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaUserRepository extends JpaRepository<UserJpaEntity, Long> {

}