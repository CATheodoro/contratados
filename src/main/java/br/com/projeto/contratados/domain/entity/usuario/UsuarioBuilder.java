package br.com.projeto.contratados.domain.entity.usuario;

import br.com.projeto.contratados.domain.entity.Endereco;
import br.com.projeto.contratados.domain.entity.solicitacao.Solicitacao;
import br.com.projeto.contratados.domain.entity.user.Perfil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class UsuarioBuilder {
    private Long id;
    private String email;
    private String password;
    private Perfil perfil;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enable;
    private LocalDateTime dataCriacaoPerfil;
    private String nome;
    private String celular;
    private String telefone;
    private List<Formacao> formacao = new ArrayList<>();
    private List<Experiencia> experiencia = new ArrayList<>();
    private List<Solicitacao> solicitacao = new ArrayList<>();
    private Endereco endereco;
    private Date dataNascimento;
    private StatusUsuario status;
    private String linkCurriculo;

    private UsuarioBuilder() {
    }

    public static UsuarioBuilder builder() {
        return new UsuarioBuilder();
    }

    public UsuarioBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public UsuarioBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UsuarioBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UsuarioBuilder perfil(Perfil perfil) {
        this.perfil = perfil;
        return this;
    }

    public UsuarioBuilder accountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
        return this;
    }

    public UsuarioBuilder accountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    public UsuarioBuilder credentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
        return this;
    }

    public UsuarioBuilder enable(boolean enable) {
        this.enable = enable;
        return this;
    }

    public UsuarioBuilder dataCriacaoPerfil(LocalDateTime dataCriacaoPerfil) {
        this.dataCriacaoPerfil = dataCriacaoPerfil;
        return this;
    }

    public UsuarioBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public UsuarioBuilder celular(String celular) {
        this.celular = celular;
        return this;
    }

    public UsuarioBuilder telefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public UsuarioBuilder formacao(List<Formacao> formacao) {
        this.formacao = formacao;
        return this;
    }

    public UsuarioBuilder experiencia(List<Experiencia> experiencia) {
        this.experiencia = experiencia;
        return this;
    }

    public UsuarioBuilder solicitacao(List<Solicitacao> solicitacao) {
        this.solicitacao = solicitacao;
        return this;
    }

    public UsuarioBuilder endereco(Endereco endereco) {
        this.endereco = endereco;
        return this;
    }

    public UsuarioBuilder dataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public UsuarioBuilder status(StatusUsuario status) {
        this.status = status;
        return this;
    }

    public UsuarioBuilder linkCurriculo(String linkCurriculo) {
        this.linkCurriculo = linkCurriculo;
        return this;
    }

    public Usuario build() {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setPerfil(perfil);
        usuario.setAccountNonExpired(accountNonExpired);
        usuario.setAccountNonLocked(accountNonLocked);
        usuario.setCredentialsNonExpired(credentialsNonExpired);
        usuario.setEnable(enable);
        usuario.setDataCriacaoPerfil(dataCriacaoPerfil);
        usuario.setNome(nome);
        usuario.setCelular(celular);
        usuario.setTelefone(telefone);
        usuario.setFormacao(formacao);
        usuario.setExperiencia(experiencia);
        usuario.setSolicitacao(solicitacao);
        usuario.setEndereco(endereco);
        usuario.setDataNascimento(dataNascimento);
        usuario.setStatus(status);
        usuario.setLinkCurriculo(linkCurriculo);
        return usuario;
    }
}
