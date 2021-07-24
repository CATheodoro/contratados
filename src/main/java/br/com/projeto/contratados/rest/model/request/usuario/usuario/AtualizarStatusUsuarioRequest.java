package br.com.projeto.contratados.rest.model.request.usuario.usuario;

import br.com.projeto.contratados.domain.entity.usuario.StatusUsuario;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AtualizarStatusUsuarioRequest {

    private StatusUsuario status;

    public Usuario atualizarStatusUsuario(Usuario usuario){
        usuario.setStatus(this.status);

        return usuario;
    }
}
