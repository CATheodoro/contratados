package br.com.projeto.contratados.domain.entity.empresa;

import br.com.projeto.contratados.domain.entity.user.Perfil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class EmpresaBuilder {
    private List<AnuncioVaga> anuncioVaga = new ArrayList<>();
    private String nomeFantasia;
    private String descricao;
    private Integer celular;
    private Integer telefone;
    private String cnpj;
    private Date dataFundacao;
    private LocalDateTime dataCriacaoPerfil;
    private Integer id;
    private String email;
    private String password;
    private Perfil perfil;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enable;

    private EmpresaBuilder() {
    }

    public static EmpresaBuilder builder() {
        return new EmpresaBuilder();
    }

    public EmpresaBuilder anuncioVaga(List<AnuncioVaga> anuncioVaga) {
        this.anuncioVaga = anuncioVaga;
        return this;
    }

    public EmpresaBuilder nomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
        return this;
    }

    public EmpresaBuilder descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public EmpresaBuilder celular(Integer celular) {
        this.celular = celular;
        return this;
    }

    public EmpresaBuilder telefone(Integer telefone) {
        this.telefone = telefone;
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

    public EmpresaBuilder dataCriacaoPerfil(LocalDateTime dataCriacaoPerfil) {
        this.dataCriacaoPerfil = dataCriacaoPerfil;
        return this;
    }

    public EmpresaBuilder id(Integer id) {
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

    public Empresa build() {
        Empresa empresa = new Empresa();
        empresa.setAnuncioVaga(anuncioVaga);
        empresa.setNomeFantasia(nomeFantasia);
        empresa.setDescricao(descricao);
        empresa.setCelular(celular);
        empresa.setTelefone(telefone);
        empresa.setCnpj(cnpj);
        empresa.setDataFundacao(dataFundacao);
        empresa.setDataCriacaoPerfil(dataCriacaoPerfil);
        empresa.setId(id);
        empresa.setEmail(email);
        empresa.setPassword(password);
        empresa.setPerfil(perfil);
        empresa.setAccountNonExpired(accountNonExpired);
        empresa.setAccountNonLocked(accountNonLocked);
        empresa.setCredentialsNonExpired(credentialsNonExpired);
        empresa.setEnable(enable);
        return empresa;
    }
}
