package pe.com.user.administrator.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class PasswordValidator {

    @Value("${app.user.validation.password}")
    String validationPassword;

    public boolean isValidPassword(String password){
        Pattern passwordRegex = Pattern.compile(validationPassword);
        Matcher matcher = passwordRegex.matcher(password);
        return matcher.matches();
    }
}
