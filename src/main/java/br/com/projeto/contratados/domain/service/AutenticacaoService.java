package br.com.projeto.contratados.domain.service;

import br.com.projeto.contratados.config.exception.AltenticacaoInvalidaException;
import br.com.projeto.contratados.config.security.TokenService;
import br.com.projeto.contratados.rest.model.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public String autenticar(LoginRequest form) {

        UsernamePasswordAuthenticationToken dadosLogin = form.converter();

        try {
            Authentication authentication = authenticationManager.authenticate(dadosLogin);
            return tokenService.gerarToken(authentication);

        } catch (AuthenticationException e){
           throw new AltenticacaoInvalidaException(e);
        }

    }
}
