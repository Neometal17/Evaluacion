package pe.com.user.administrator.infrastructure.adapter.out;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;
import pe.com.user.administrator.domain.model.Login;
import pe.com.user.administrator.infrastructure.adapter.out.h2.persistence.UserJpaEntity;
import pe.com.user.administrator.infrastructure.adapter.out.h2.repository.JpaUserRepository;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@Import(LoginPersistenAdapter.class)
public class LoginPersistenAdapterTest {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Autowired
    private LoginPersistenAdapter loginPersistenAdapter;

    @Test
    void shouldReturnLogin(){
        UserJpaEntity userEntity = new UserJpaEntity();
        userEntity.setName("user1");
        userEntity.setPassword("password1");
        userEntity.setActive(1);
        userEntity.setEmail("nada@algo.com");

        jpaUserRepository.save(userEntity);

        Login login = loginPersistenAdapter.login("user1", "password1");

        assertNotNull(login);
        assertEquals("user1", login.getUserName());
        assertEquals(1, login.getActive());
    }
}
