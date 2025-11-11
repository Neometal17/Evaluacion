package pe.com.user.administrator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.com.user.administrator.application.port.out.UserRepositoryPort;
import pe.com.user.administrator.application.service.UserService;
import pe.com.user.administrator.infrastructure.adapter.out.persistence.JpaUserRepository;
import pe.com.user.administrator.infrastructure.adapter.out.persistence.UserPersistenAdapter;

@Configuration
public class BeanConfiguration {

    @Bean
    public UserRepositoryPort userRepositoryPort(JpaUserRepository jpaUserRepository){
        return new UserPersistenAdapter(jpaUserRepository);
    }

    @Bean
    public UserService userService(UserRepositoryPort userRepositoryPort){
        return new UserService(userRepositoryPort);
    }
}
