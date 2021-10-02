package br.com.projeto.contratados.rest.model.request.usuario.usuario;

import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import br.com.projeto.contratados.domain.repository.usuario.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AtualizarSenhaUsuarioRequest {
    @Size(min = 6, max = 50)
    @NotNull @NotEmpty
    private String oldPassword;

    @Size(min = 6, max = 50)
    @NotNull @NotEmpty
    private String password;

    public Usuario atualizarSenhaUsuario(Usuario usuario){
        usuario.setPassword(new BCryptPasswordEncoder().encode(this.password));
        return usuario;
    }
}
