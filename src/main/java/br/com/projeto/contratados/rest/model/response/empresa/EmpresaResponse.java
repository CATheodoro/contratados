package br.com.projeto.contratados.rest.model.response.empresa;

import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import br.com.projeto.contratados.rest.model.response.anuncioVaga.AnuncioVagaResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
public class EmpresaResponse {
    private final Long id;
    private final String email;
    private final String nome;
    private final String descricao;
    private final String celular;
    private final String telefone;
    private final String cnpj;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final Date dataFundacao;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDateTime dataCriacaoPerfil;
    private final List<AnuncioVagaResponse> anuncioVagaResponses;

    public EmpresaResponse(Empresa empresa) {
        this.id = empresa.getId();
        this.email = empresa.getEmail();
        this.nome = empresa.getNome();
        this.descricao = empresa.getDescricao();
        this.celular = empresa.getCelular();
        this.telefone = empresa.getTelefone();
        this.cnpj = empresa.getCnpj();
        this.dataFundacao = empresa.getDataFundacao();
        this.dataCriacaoPerfil = empresa.getDataCriacaoPerfil();
        this.anuncioVagaResponses = AnuncioVagaResponse.converterList(empresa.getAnuncioVaga());
    }

    public static Page<EmpresaResponse> converterEmpresaDto(Page<Empresa> empresa) { return empresa.map(EmpresaResponse::new); }
}
