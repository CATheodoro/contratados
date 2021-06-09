package br.com.projeto.contratados.domain.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Perfil implements GrantedAuthority {

    USUARIO(1, "USUARIO"),
    EMPRESA(2, "EMPRESA");

    private Integer codigo;
    private String descricao;

    @Override
    public String getAuthority() {
        return null;
    }
}
