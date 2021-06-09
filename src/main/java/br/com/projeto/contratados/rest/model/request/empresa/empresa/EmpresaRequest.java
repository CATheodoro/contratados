package br.com.projeto.contratados.rest.model.request.empresa.empresa;

import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import br.com.projeto.contratados.domain.entity.user.Perfil;
import br.com.projeto.contratados.domain.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
public class EmpresaRequest {

    @NotNull @NotEmpty @Email
    private String email;
    @NotNull @NotEmpty
    private String senha;
    @NotNull @NotEmpty
    private String nomeFantasia;

    private String descricao;
    private Integer celular;
    private Integer telefone;
    private String cnpj;
    //    private img fotoPerfil;
    private Date dataFundacao;


    public Empresa converter() {

        User user = User.builder()
                .email(this.email)
                .password(new BCryptPasswordEncoder().encode(this.senha))
                .perfil(Perfil.EMPRESA)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enable(true)
                .build();

        return Empresa.builder()
                .user(user)
                .nomeFantasia(this.nomeFantasia)
                .descricao(this.descricao)
                .celular(this.celular)
                .telefone(this.telefone)
                .cnpj(this.cnpj)
                .dataFundacao(this.dataFundacao)
                .dataCriacaoPerfil(LocalDateTime.now())
                .build();
    }
}
