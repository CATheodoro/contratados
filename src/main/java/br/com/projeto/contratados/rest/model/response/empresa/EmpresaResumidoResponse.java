package br.com.projeto.contratados.rest.model.response.empresa;

import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import br.com.projeto.contratados.rest.model.response.anuncioVaga.AnuncioVagaResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
public class EmpresaResumidoResponse {
    private final Integer id;
    private final String email;
    private final String nomeFantasia;
    private final String descricao;
    private final Integer celular;
    private final Integer telefone;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final Date dataFundacao;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDateTime dataCriacaoPerfil;

    public EmpresaResumidoResponse(Empresa empresa) {
        this.id = empresa.getId();
        this.email = empresa.getEmail();
        this.nomeFantasia = empresa.getNomeFantasia();
        this.descricao = empresa.getDescricao();
        this.celular = empresa.getCelular();
        this.telefone = empresa.getTelefone();
        this.dataFundacao = empresa.getDataFundacao();
        this.dataCriacaoPerfil = empresa.getDataCriacaoPerfil();
    }
}
