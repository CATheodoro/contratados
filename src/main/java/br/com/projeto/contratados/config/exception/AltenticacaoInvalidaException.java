package br.com.projeto.contratados.config.exception;

import org.springframework.security.core.AuthenticationException;

public class AltenticacaoInvalidaException extends RuntimeException {
    public AltenticacaoInvalidaException(AuthenticationException e) { super(e);
    }
}
