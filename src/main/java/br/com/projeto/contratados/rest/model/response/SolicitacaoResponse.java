package br.com.projeto.contratados.rest.model.response;

import br.com.projeto.contratados.domain.entity.solicitacao.Solicitacao;
import br.com.projeto.contratados.domain.entity.solicitacao.SolicitacaoEmpresaStatus;
import br.com.projeto.contratados.domain.entity.solicitacao.SolicitacaoUsuarioStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SolicitacaoResponse {
    private final Integer id;
    private final String usuario;

    private final Integer anuncioVagaId;
    private final SolicitacaoEmpresaStatus solicitacaoEmpresaStatus;
    private final SolicitacaoUsuarioStatus solicitacaoUsuarioStatus;
    private final Time horaEntrevista;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final Date dataEntrevista;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDateTime dataCriacaoSolicitacao;

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private Integer numero;

    public SolicitacaoResponse(Solicitacao solicitacao){
        this.id = solicitacao.getId();
        this.usuario = solicitacao.getUsuario().getNome();
        this.anuncioVagaId = solicitacao.getAnuncioVaga().getId();

        this.solicitacaoEmpresaStatus = solicitacao.getSolicitacaoEmpresaStatus();
        this.solicitacaoUsuarioStatus = solicitacao.getSolicitacaoUsuarioStatus();
        this.horaEntrevista = solicitacao.getHoraEntrevista();
        this.dataEntrevista = solicitacao.getDataEntrevista();
        this.dataCriacaoSolicitacao = solicitacao.getDataCriacaoSolicitacao();

        if (solicitacao.getEndereco() != null) {
            this.cep = solicitacao.getEndereco().getCep();
            this.logradouro = solicitacao.getEndereco().getLogradouro();
            this.complemento = solicitacao.getEndereco().getComplemento();
            this.bairro = solicitacao.getEndereco().getBairro();
            this.localidade = solicitacao.getEndereco().getLocalidade();
            this.uf = solicitacao.getEndereco().getUf();
            this.numero = solicitacao.getEndereco().getNumero();
        }
    }

    public static Page<SolicitacaoResponse> converter(Page<Solicitacao> solicitacao){
        return solicitacao.map(SolicitacaoResponse::new);
    }

    public static List<SolicitacaoResponse> converterList(List<Solicitacao> solicitacao) {
        return solicitacao.stream().map(SolicitacaoResponse::new).collect(Collectors.toList());
    }
}
