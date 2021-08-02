package br.com.projeto.contratados.rest.model.response;

import br.com.projeto.contratados.domain.entity.user.Perfil;
import br.com.projeto.contratados.domain.entity.usuario.StatusUsuario;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
public class UsuarioResponse {
    private final Long id;

    private final String nome;
    private final String email;

    private final Date dataNascimento;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final Date dataNascimentoFormatado;
    private final String celular;
    private final String telefone;

    private final StatusUsuario status;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDateTime dataCriacaoPerfil;

    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enable;

    private final List<FormacaoResponse> formacao;
    private final List<ExperienciaResponse> experiencia;
    private final List<SolicitacaoResponse> solicitacao;

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String numero;

    private final String linkCurriculo;

    private final Perfil perfil;

    public UsuarioResponse(Usuario usuario){
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.dataNascimento = usuario.getDataNascimento();
        this.celular = usuario.getCelular();
        this.telefone = usuario.getTelefone();

        this.status = usuario.getStatus();
        this.dataCriacaoPerfil = usuario.getDataCriacaoPerfil();
        this.dataNascimentoFormatado = usuario.getDataNascimento();
        this.accountNonExpired = usuario.isAccountNonExpired();
        this.accountNonLocked = usuario.isAccountNonLocked();
        this.credentialsNonExpired = usuario.isCredentialsNonExpired();
        this.enable = usuario.isEnabled();

        this.formacao = FormacaoResponse.converterList(usuario.getFormacao());
        this.experiencia = ExperienciaResponse.converterList(usuario.getExperiencia());
        this.solicitacao = SolicitacaoResponse.converterList(usuario.getSolicitacao());

        if (usuario.getEndereco() != null) {
            this.cep = usuario.getEndereco().getCep();
            this.logradouro = usuario.getEndereco().getLogradouro();
            this.complemento = usuario.getEndereco().getComplemento();
            this.bairro = usuario.getEndereco().getBairro();
            this.localidade = usuario.getEndereco().getLocalidade();
            this.uf = usuario.getEndereco().getUf();
            this.numero = usuario.getEndereco().getNumero().toString();
        }

        this.linkCurriculo = usuario.getLinkCurriculo();
        this.perfil = usuario.getPerfil();

    }

    public static Page<UsuarioResponse> converter(Page<Usuario> usuario){ return usuario.map(UsuarioResponse::new); }
}
