package br.com.projeto.contratados.config.exception;

import org.springframework.security.core.AuthenticationException;

public class AltenticacaoInvalida extends RuntimeException {
    public AltenticacaoInvalida(AuthenticationException e) { super(e);
    }
}
