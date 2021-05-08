package br.com.projeto.contratados.config.exception;

public class EmailJaCadastrado extends RuntimeException {
    public EmailJaCadastrado(String s) {
        super(s);
    }
}
