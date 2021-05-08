package br.com.projeto.contratados.rest.model.response;

import lombok.Getter;

@Getter
public class TokenResponse {
    private final String token;
    private final String tipo;

    public TokenResponse(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }
}
