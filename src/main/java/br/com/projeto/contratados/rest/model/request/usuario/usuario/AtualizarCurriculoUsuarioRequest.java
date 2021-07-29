package br.com.projeto.contratados.rest.model.request.usuario.usuario;

import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AtualizarCurriculoUsuarioRequest {

    @NotNull @NotEmpty
    private String linkCurriculo;

    public Usuario atualizarCurriculoUsuarioRequest(Usuario usuario){
        usuario.setLinkCurriculo(this.linkCurriculo);

        return usuario;
    }
}
