package br.com.projeto.contratados.rest.model.response;

import br.com.projeto.contratados.domain.entity.usuario.StatusUsuario;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
public class UsuarioResponse {
    private final Integer id;

    //Dados Usuario
    private final String nome;
    private final String email;

    private final Date dataNascimento;

    private final String celular;
    private final String telefone;
    private EnderecoResponse endereco;
    private final StatusUsuario status;
    private final LocalDateTime dataCriacaoPerfil;

    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enable;

    private List<FormacaoResponse> formacao;
    private List<ExperienciaResponse> experiencia;
    private List<SolicitacaoResponse> solicitacao;

    public UsuarioResponse(Usuario usuario){
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.dataNascimento = usuario.getDataNascimento();
        this.celular = usuario.getCelular();
        this.telefone = usuario.getTelefone();

        if (usuario.getEndereco() != null) {
            this.endereco = EnderecoResponse.builder()
                    .cep(usuario.getEndereco().getCep())
                    .logradouro(usuario.getEndereco().getLogradouro())
                    .complemento(usuario.getEndereco().getComplemento())
                    .bairro(usuario.getEndereco().getBairro())
                    .localidade(usuario.getEndereco().getLocalidade())
                    .uf(usuario.getEndereco().getUf())
                    .build();
        }


        this.status = usuario.getStatus();
        this.dataCriacaoPerfil = usuario.getDataCriacaoPerfil();

        this.accountNonExpired = usuario.isAccountNonExpired();
        this.accountNonLocked = usuario.isAccountNonLocked();
        this.credentialsNonExpired = usuario.isCredentialsNonExpired();
        this.enable = usuario.isEnabled();
        //this.formacao = usuario.getFormacao();
        //this.experiencia = usuario.getExperiencia();
        //this.solicitacao = usuario.getSolicitacao();

    }

    public static Page<UsuarioResponse> converter(Page<Usuario> usuario){ return usuario.map(UsuarioResponse::new); }
}
