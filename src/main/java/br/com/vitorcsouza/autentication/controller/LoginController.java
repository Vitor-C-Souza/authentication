package br.com.vitorcsouza.autentication.controller;


import br.com.vitorcsouza.autentication.dto.LoginDto;
import br.com.vitorcsouza.autentication.model.Login;
import br.com.vitorcsouza.autentication.security.DadosTokenJWT;
import br.com.vitorcsouza.autentication.security.TokenService;
import br.com.vitorcsouza.autentication.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService service;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/cadastro")
    public ResponseEntity<LoginDto> cadastro(@RequestBody @Valid LoginDto dto, UriComponentsBuilder uriComponentsBuilder) {
        dto.setSenha(BCrypt.hashpw(dto.getSenha(), BCrypt.gensalt()));
        LoginDto login = service.cadastro(dto);
        URI adress = uriComponentsBuilder.path("cadastro/{id}").buildAndExpand(login.getId()).toUri();

        return ResponseEntity.created(adress).body(login);
    }

    @PostMapping
    public ResponseEntity efetuarLogin (@RequestBody @Valid LoginDto dto){

        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsuario(), dto.getSenha());
        var authetication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.GerarToken((Login) authetication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
