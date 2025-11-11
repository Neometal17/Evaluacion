package pe.com.user.administrator.infrastructure.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import pe.com.user.administrator.application.port.out.UserRepositoryPort;
import pe.com.user.administrator.domain.model.User;
import pe.com.user.administrator.infrastructure.mapper.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserPersistenAdapter implements UserRepositoryPort {

    private final JpaUserRepository userRepository;

    @Override
    public User save(User user) {
        UserJpaEntity entity = UserMapper.toEntity(user);
        return UserMapper.toDomain(userRepository.save(entity));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDomain)
                .collect(Collectors.toList());
    }
}
