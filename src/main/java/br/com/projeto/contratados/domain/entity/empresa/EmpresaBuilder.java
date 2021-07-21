package br.com.projeto.contratados.domain.entity.empresa;

import br.com.projeto.contratados.domain.entity.user.Perfil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class EmpresaBuilder {
    private List<AnuncioVaga> anuncioVaga = new ArrayList<>();
    private String descricao;
    private String cnpj;
    private Date dataFundacao;
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

    private EmpresaBuilder() {
    }

    public static EmpresaBuilder builder() {
        return new EmpresaBuilder();
    }

    public EmpresaBuilder anuncioVaga(List<AnuncioVaga> anuncioVaga) {
        this.anuncioVaga = anuncioVaga;
        return this;
    }

    public EmpresaBuilder descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public EmpresaBuilder cnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public EmpresaBuilder dataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
        return this;
    }

    public EmpresaBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public EmpresaBuilder email(String email) {
        this.email = email;
        return this;
    }

    public EmpresaBuilder password(String password) {
        this.password = password;
        return this;
    }

    public EmpresaBuilder perfil(Perfil perfil) {
        this.perfil = perfil;
        return this;
    }

    public EmpresaBuilder accountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
        return this;
    }

    public EmpresaBuilder accountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    public EmpresaBuilder credentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
        return this;
    }

    public EmpresaBuilder enable(boolean enable) {
        this.enable = enable;
        return this;
    }

    public EmpresaBuilder dataCriacaoPerfil(LocalDateTime dataCriacaoPerfil) {
        this.dataCriacaoPerfil = dataCriacaoPerfil;
        return this;
    }

    public EmpresaBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public EmpresaBuilder celular(String celular) {
        this.celular = celular;
        return this;
    }

    public EmpresaBuilder telefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public Empresa build() {
        Empresa empresa = new Empresa();
        empresa.setAnuncioVaga(anuncioVaga);
        empresa.setDescricao(descricao);
        empresa.setCnpj(cnpj);
        empresa.setDataFundacao(dataFundacao);
        empresa.setId(id);
        empresa.setEmail(email);
        empresa.setPassword(password);
        empresa.setPerfil(perfil);
        empresa.setAccountNonExpired(accountNonExpired);
        empresa.setAccountNonLocked(accountNonLocked);
        empresa.setCredentialsNonExpired(credentialsNonExpired);
        empresa.setEnable(enable);
        empresa.setDataCriacaoPerfil(dataCriacaoPerfil);
        empresa.setNome(nome);
        empresa.setCelular(celular);
        empresa.setTelefone(telefone);
        return empresa;
    }
}
