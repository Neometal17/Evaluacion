package pe.com.user.administrator.infrastructure.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pe.com.user.administrator.application.port.out.UserRepositoryPort;
import pe.com.user.administrator.domain.model.Phone;
import pe.com.user.administrator.domain.model.User;
import pe.com.user.administrator.infrastructure.adapter.out.h2.mapper.PhoneMapper;
import pe.com.user.administrator.infrastructure.adapter.out.h2.persistence.PhoneJpaEntity;
import pe.com.user.administrator.infrastructure.adapter.out.h2.persistence.UserJpaEntity;
import pe.com.user.administrator.infrastructure.adapter.out.h2.mapper.UserMapper;
import pe.com.user.administrator.infrastructure.adapter.out.h2.repository.JpaPhoneRepository;
import pe.com.user.administrator.infrastructure.adapter.out.h2.repository.JpaUserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserPersistenAdapter implements UserRepositoryPort {

    private final JpaUserRepository userRepository;
    private final JpaPhoneRepository phoneRepository;

    @Override
    public boolean existsUser(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    @Transactional
    public User save(User user) {

        UserJpaEntity entity = UserMapper.toEntity(user);

        UserJpaEntity saveUserEntity = userRepository.saveAndFlush(entity);

        List<PhoneJpaEntity> listPhoneEntity = PhoneMapper.toEntity(user.getPhones(), saveUserEntity.getId());

        List<Phone> phoneModels = PhoneMapper.toDomain(phoneRepository.saveAll(listPhoneEntity));

        User useModel = UserMapper.toDomain(saveUserEntity);
        useModel.setPhones(phoneModels);

        return useModel;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDomain)
                .collect(Collectors.toList());
    }
}
