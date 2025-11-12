package pe.com.user.administrator.infrastructure.in.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.user.administrator.application.service.LoginService;
import pe.com.user.administrator.infrastructure.in.web.controller.dto.login.LoginRequest;
import pe.com.user.administrator.infrastructure.mapper.LoginMapper;
import pe.com.user.administrator.infrastructure.in.web.controller.dto.login.LoginResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest login){
        return ResponseEntity.ok().body(LoginMapper.toResponse(loginService.signIn(LoginMapper.toDomain(login))));
    }
}
