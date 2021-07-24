package br.com.projeto.contratados.rest.model.response;

import br.com.projeto.contratados.domain.entity.user.Perfil;
import br.com.projeto.contratados.domain.entity.user.User;
import lombok.Getter;

@Getter
public class TokenResponse {
    private final String id;
    private final String email;
    private final Perfil perfil;

    private final String token;
    private final String tipo;

    public TokenResponse(User user, String token, String tipo) {
        this.id = user.getId().toString();
        this.email = user.getEmail();
        this.perfil = user.getPerfil();

        this.token = token;
        this.tipo = tipo;
    }
}
