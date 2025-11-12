package pe.com.user.administrator.infrastructure.out.repository.user.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pe.com.user.administrator.application.port.out.UserRepositoryPort;
import pe.com.user.administrator.domain.model.Phone;
import pe.com.user.administrator.domain.model.User;
import pe.com.user.administrator.infrastructure.exception.ExisteUserException;
import pe.com.user.administrator.infrastructure.mapper.PhoneMapper;
import pe.com.user.administrator.infrastructure.out.persistence.user.PhoneJpaEntity;
import pe.com.user.administrator.infrastructure.out.persistence.user.UserJpaEntity;
import pe.com.user.administrator.infrastructure.mapper.UserMapper;
import pe.com.user.administrator.infrastructure.out.repository.user.JpaPhoneRepository;
import pe.com.user.administrator.infrastructure.out.repository.user.JpaUserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserPersistenAdapter implements UserRepositoryPort {

    private final JpaUserRepository userRepository;
    private final JpaPhoneRepository phoneRepository;

    @Override
    @Transactional
    public User save(User user) {
        Optional.ofNullable(userRepository.findByEmail(user.getEmail()))
                .ifPresent(u -> {
                    throw new ExisteUserException("El correo ya registrado");
                });

        UserJpaEntity entity = UserMapper.toEntity(user);

        LocalDateTime dateCurrent = LocalDateTime.now();
        entity.setCreate(dateCurrent);
        entity.setLastLogin(dateCurrent);
        entity.setModified(dateCurrent);
        entity.setToken("");
        entity.setActive(1);

        User useModel = UserMapper.toDomain(userRepository.saveAndFlush(entity));

        List<PhoneJpaEntity> listPhoneEntity = PhoneMapper.toEntity(user.getPhones(), user.getId());

        List<Phone> phoneModels = PhoneMapper.toDomain(phoneRepository.saveAll(listPhoneEntity));

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
