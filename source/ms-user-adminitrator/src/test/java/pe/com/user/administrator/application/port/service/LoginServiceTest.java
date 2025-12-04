package pe.com.user.administrator.application.port.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.com.user.administrator.application.port.out.LoginRepositoryPort;
import pe.com.user.administrator.application.port.out.TokenProviderPort;
import pe.com.user.administrator.application.service.LoginService;
import pe.com.user.administrator.config.PasswordValidator;
import pe.com.user.administrator.domain.model.Login;
import pe.com.user.administrator.infrastructure.exception.InactiveUserException;
import pe.com.user.administrator.infrastructure.exception.InvalidPasswordException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginServiceTest {

    private LoginRepositoryPort loginRepositoryPort;
    private TokenProviderPort tokenProviderPort;
    private PasswordValidator passwordValidator;
    private LoginService loginService;

    @BeforeEach
    void setUp(){
        loginRepositoryPort = mock(LoginRepositoryPort.class);
        tokenProviderPort = mock(TokenProviderPort.class);
        passwordValidator = mock(PasswordValidator.class);

        loginService = new LoginService(loginRepositoryPort, tokenProviderPort, passwordValidator);
    }

    @Test
    void loginSuccess(){
        Login login = Login.builder()
                .userName("userName1")
                .password("password$1")
                .active(1)
                .build();
        Login loginStore = Login.builder()
                .userName("userName1")
                .password("password$1")
                .active(1)
                .build();

        when(loginRepositoryPort.login("userName1", "password$1")).thenReturn(loginStore);
        when(passwordValidator.isValidPassword("password$1")).thenReturn(true);
        when(tokenProviderPort.generateToken("userName1")).thenReturn("TOKEN123");

        Login result = loginService.execute(login);

        assertEquals("TOKEN123", result.getToken());
        verify(loginRepositoryPort).login("userName1","password$1");
        verify(tokenProviderPort).generateToken("userName1");
    }

    @Test
    void shouldInactiveUserException(){
        Login login = Login.builder().userName("user1").password("password1").active(0).build();
        Login loginResponse = Login.builder().userName("user1").password("password1").active(0).build();

        when(loginRepositoryPort.login("user1", "password1")).thenReturn(loginResponse);

        assertThrows(InactiveUserException.class, () -> loginService.execute(login));
    }

    @Test
    void shouldInvalidPasswordException(){
        Login login = Login.builder().userName("user1").password("password1").active(1).build();
        Login loginResponse = Login.builder().userName("user1").password("password1").active(1).build();

        when(loginRepositoryPort.login("user1", "password1")).thenReturn(loginResponse);
        when(passwordValidator.isValidPassword("password1")).thenReturn(false);

        assertThrows(InvalidPasswordException.class, () -> loginService.execute(login));
    }
}
