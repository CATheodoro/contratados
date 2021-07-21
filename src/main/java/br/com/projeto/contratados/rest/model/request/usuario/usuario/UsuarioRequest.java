package br.com.projeto.contratados.rest.model.request.usuario.usuario;

import br.com.projeto.contratados.domain.entity.Endereco;
import br.com.projeto.contratados.domain.entity.user.Perfil;
import br.com.projeto.contratados.domain.entity.usuario.StatusUsuario;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import br.com.projeto.contratados.domain.entity.usuario.UsuarioBuilder;
import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@Builder
public class UsuarioRequest {

    private String enderecoCep;

    //Dados Usuario
    @NotNull @NotEmpty @Email
    private String email;
    @NotNull @NotEmpty
    private String senha;
    @NotNull @NotEmpty
    private String nome;

    private Date dataNascimento;

    private String celular;
    private String telefone;

    //private Image fotoPerfil;
    //private Image curriculo;

    private StatusUsuario status;

    private String complemento;

    private Integer numero;

    public Usuario converter() throws IOException {
        Endereco endereco = null;
        if (enderecoCep != null) {
            var viaCEPClient = new ViaCEPClient();
            var viaCEPEndereco = viaCEPClient.getEndereco(enderecoCep);

            endereco = Endereco.builder()
                    .cep(viaCEPEndereco.getCep())
                    .logradouro(viaCEPEndereco.getLogradouro())
                    .complemento(this.complemento)
                    .bairro(viaCEPEndereco.getBairro())
                    .localidade(viaCEPEndereco.getLocalidade())
                    .uf(viaCEPEndereco.getUf())
                    .ibge(viaCEPEndereco.getIbge())
                    .numero(this.numero)
                    .build();
        }


        return UsuarioBuilder.builder()
                .email(this.email)
                .password(new BCryptPasswordEncoder().encode(this.senha))
                .perfil(Perfil.USUARIO)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enable(true)
                .endereco(endereco)
                .nome(this.nome)
                .dataNascimento(this.dataNascimento)
                .celular(this.celular)
                .telefone(this.telefone)
                .status(StatusUsuario.DISPONIVEL)
                .dataCriacaoPerfil(LocalDateTime.now())
                .build();
    }

}
