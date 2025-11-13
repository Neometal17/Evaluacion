package pe.com.user.administrator.infrastructure.in.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.user.administrator.application.service.UserService;
import pe.com.user.administrator.infrastructure.in.web.controller.dto.user.UserRequest;
import pe.com.user.administrator.infrastructure.mapper.UserMapper;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerNewUser(@Validated @RequestBody UserRequest user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUserUseCase(UserMapper.toDomain(user)));
    }
}
