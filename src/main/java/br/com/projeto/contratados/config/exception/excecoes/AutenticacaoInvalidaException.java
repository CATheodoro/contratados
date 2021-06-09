package br.com.projeto.contratados.config.exception.excecoes;

import org.springframework.security.core.AuthenticationException;

public class AutenticacaoInvalidaException extends RuntimeException {
    public AutenticacaoInvalidaException(AuthenticationException e) { super(e);
    }
}
