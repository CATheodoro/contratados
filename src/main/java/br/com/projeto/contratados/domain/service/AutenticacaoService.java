package br.com.projeto.contratados.domain.service;


import br.com.projeto.contratados.config.exception.excecoes.AutenticacaoInvalidaException;
import br.com.projeto.contratados.config.security.TokenService;
import br.com.projeto.contratados.domain.entity.user.User;
import br.com.projeto.contratados.rest.model.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
            var authentication = authenticationManager.authenticate(dadosLogin);
            return tokenService.gerarToken(authentication);

        } catch (AuthenticationException e){
           throw new AutenticacaoInvalidaException(e);
        }

    }

    public User getUser(LoginRequest form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        try {
            var authentication = authenticationManager.authenticate(dadosLogin);
            return tokenService.getUser(authentication);

        } catch (AuthenticationException e){
            throw new AutenticacaoInvalidaException(e);
        }
    }
}
