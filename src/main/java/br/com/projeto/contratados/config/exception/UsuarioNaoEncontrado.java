package br.com.projeto.contratados.config.exception;

public class UsuarioNaoEncontrado extends RuntimeException {
    public UsuarioNaoEncontrado(String s) {
        super(s);
    }
}
