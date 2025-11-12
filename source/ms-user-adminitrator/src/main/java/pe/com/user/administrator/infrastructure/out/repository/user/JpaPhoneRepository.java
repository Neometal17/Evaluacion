package pe.com.user.administrator.infrastructure.out.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.user.administrator.infrastructure.out.persistence.user.PhoneJpaEntity;

public interface JpaPhoneRepository extends JpaRepository<PhoneJpaEntity, Long> {
}
