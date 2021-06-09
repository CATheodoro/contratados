package br.com.projeto.contratados.rest.model.response;

import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class EmpresaResponse {
    private final Integer id;
    private final String email;
    // private String senha;
    private final String nomeFantasia;
    private final String descricao;
    private final Integer celular;
    private final Integer telefone;
    private final String cnpj;
    //    private img fotoPerfil;
    private final Date dataFundacao;
    private final LocalDateTime dataCriacaoPerfil;

    public EmpresaResponse(Empresa empresa) {
        this.id = empresa.getId();
        this.email = empresa.getUser().getEmail();
        this.nomeFantasia = empresa.getNomeFantasia();
        this.descricao = empresa.getDescricao();
        this.celular = empresa.getCelular();
        this.telefone = empresa.getTelefone();
        this.cnpj = empresa.getCnpj();
        this.dataFundacao = empresa.getDataFundacao();
        this.dataCriacaoPerfil = empresa.getDataCriacaoPerfil();
    }


    public static Page<EmpresaResponse> converterEmpresaDto(Page<Empresa> empresa) { return empresa.map(EmpresaResponse::new); }
}
