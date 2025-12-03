package pe.com.user.administrator.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import pe.com.user.administrator.infrastructure.adapter.in.dto.user.UserRequest;
import pe.com.user.administrator.infrastructure.adapter.out.h2.mapper.UserMapper;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
@Tag(name = "User", description = "Para obtener los usuario")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Registrar Usuario", description = "Para registrar los usuarios")
    public ResponseEntity<?> registerNewUser(@Validated @RequestBody UserRequest user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUserUseCase(UserMapper.toDomain(user)));
    }
}
