package pe.com.user.administrator.infrastructure.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.user.administrator.application.service.UserService;
import pe.com.user.administrator.infrastructure.mapper.UserMapper;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerNewUser(@Validated @RequestBody UserDTO user){
        return ResponseEntity.ok().body(userService.createUserUseCase(UserMapper.toDomain(user)));
    }
}
