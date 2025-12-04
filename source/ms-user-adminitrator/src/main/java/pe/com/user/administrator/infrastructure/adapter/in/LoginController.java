package pe.com.user.administrator.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.user.administrator.application.service.LoginService;
import pe.com.user.administrator.infrastructure.adapter.in.dto.login.LoginRequest;
import pe.com.user.administrator.infrastructure.adapter.out.h2.mapper.LoginMapper;
import pe.com.user.administrator.infrastructure.adapter.in.dto.login.LoginResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/login")
@Tag(name = "Login", description = "Para el logeo")
public class LoginController {

    private final LoginService loginService;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Login", description = "Para validar inicio de session")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest login){
        return ResponseEntity.ok().body(LoginMapper.toResponse(loginService.execute(LoginMapper.toDomain(login))));
    }
}
