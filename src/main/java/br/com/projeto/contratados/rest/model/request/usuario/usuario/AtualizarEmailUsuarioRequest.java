package br.com.projeto.contratados.rest.model.request.usuario.usuario;

import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import br.com.projeto.contratados.domain.repository.usuario.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AtualizarEmailUsuarioRequest {

    @NotNull @NotEmpty
    private String oldPassword;
    @NotNull @NotEmpty @Email
    private String email;

    public Usuario atualizarEmailUsuario(Usuario usuario){
        usuario.setEmail(this.email);

        return usuario;
    }
}
