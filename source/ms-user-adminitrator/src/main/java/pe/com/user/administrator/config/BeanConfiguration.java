package pe.com.user.administrator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.com.user.administrator.application.port.out.LoginRepositoryPort;
import pe.com.user.administrator.application.port.out.TokenProviderPort;
import pe.com.user.administrator.application.port.out.UserRepositoryPort;
import pe.com.user.administrator.application.service.LoginService;
import pe.com.user.administrator.application.service.TokenProviderService;
import pe.com.user.administrator.application.service.UserService;
import pe.com.user.administrator.infrastructure.out.repository.user.JpaPhoneRepository;
import pe.com.user.administrator.infrastructure.out.repository.user.JpaUserRepository;
import pe.com.user.administrator.infrastructure.out.repository.user.adapter.LoginPersistenAdapter;
import pe.com.user.administrator.infrastructure.out.repository.user.adapter.TokenProviderAdapter;
import pe.com.user.administrator.infrastructure.out.repository.user.adapter.UserPersistenAdapter;

@Configuration
public class BeanConfiguration {

    /*
    * UserServives and Port
    * */
    @Bean
    public UserRepositoryPort userRepositoryPort(JpaUserRepository jpaUserRepository, JpaPhoneRepository jpaPhoneRepository){
        return new UserPersistenAdapter(jpaUserRepository, jpaPhoneRepository);
    }

    @Bean
    public UserService userService(UserRepositoryPort userRepositoryPort){
        return new UserService(userRepositoryPort);
    }

    /*
     * LoginServives and Port
     * */
    @Bean
    public LoginRepositoryPort loginRepositoryPort(JpaUserRepository jpaUserRepository, TokenProviderService tokenProviderService){
        return new LoginPersistenAdapter(jpaUserRepository, tokenProviderService);
    }

    @Bean
    public LoginService loginService(LoginRepositoryPort loginRepositoryPort){
        return new LoginService(loginRepositoryPort);
    }

    /*
     * TokenProviderServives and Port
     * */
    @Bean
    public TokenProviderPort tokenProviderPort(){
        return new TokenProviderAdapter();
    }

    @Bean
    public TokenProviderService tokenProviderService(TokenProviderPort tokenProviderPort){
        return new TokenProviderService(tokenProviderPort);
    }
}
