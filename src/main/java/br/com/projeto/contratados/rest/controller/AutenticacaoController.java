package br.com.projeto.contratados.rest.controller;

import br.com.projeto.contratados.config.security.TokenService;
import br.com.projeto.contratados.domain.service.AutenticacaoService;
import br.com.projeto.contratados.rest.model.response.TokenResponse;
import br.com.projeto.contratados.rest.model.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping
    public ResponseEntity<TokenResponse> autenticar(@RequestBody @Valid LoginRequest form){

        String token = autenticacaoService.autenticar(form);
        return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
    }
}
