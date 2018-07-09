package br.com.events.auth;

import br.com.events.auth.bean.Login;
import br.com.events.auth.bean.Token;
import br.com.events.auth.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description = "Serviço de Autenticação")
@RestController
@RequestMapping(value = "/auth")
@CrossOrigin
public class AuthResource {

    private final AuthService authService;

    @Autowired
    public AuthResource(AuthService authService) {
        this.authService = authService;
    }

    @ApiOperation(value = "Autentica Usuário", httpMethod = "POST", response = Token.class)
    @PostMapping()
    public Token auth(@RequestBody Login login) {
        return authService.auth(login.getUsername(), login.getPassword());
    }

}