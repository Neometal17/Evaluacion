package pe.com.user.administrator.infrastructure.adapter.out.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.user.administrator.infrastructure.adapter.out.h2.persistence.PhoneJpaEntity;

public interface JpaPhoneRepository extends JpaRepository<PhoneJpaEntity, Long> {
}
