package br.com.projeto.contratados.rest.model.request.usuario.usuario;

import br.com.projeto.contratados.domain.entity.*;
import br.com.projeto.contratados.domain.entity.usuario.StatusUsuario;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;
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


    public Usuario converter() throws IOException {
        Endereco endereco = null;
        if (enderecoCep != null) {
            ViaCEPClient viaCEPClient = new ViaCEPClient();
            ViaCEPEndereco viaCEPEndereco = viaCEPClient.getEndereco(enderecoCep);

            endereco = Endereco.builder()
                    .cep(viaCEPEndereco.getCep())
                    .logradouro(viaCEPEndereco.getLogradouro())
                    .complemento(viaCEPEndereco.getComplemento())
                    .bairro(viaCEPEndereco.getBairro())
                    .localidade(viaCEPEndereco.getLocalidade())
                    .uf(viaCEPEndereco.getUf())
                    .ibge(viaCEPEndereco.getIbge())
                    .build();
        }
        return Usuario.builder()
                .endereco(endereco)
                .email(email)
                .senha(new BCryptPasswordEncoder().encode(this.senha))
                .nome(nome)
                .dataNascimento(dataNascimento)
                .celular(celular)
                .telefone(telefone)
                .status(status)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enable(true)
                .dataCriacaoPerfil(LocalDateTime.now())
                .build();
    }
}
