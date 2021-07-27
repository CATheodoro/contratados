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
    private final Long id;
    private final Long usuarioId;
    private final String nomeUsuario;

    private final SolicitacaoEmpresaStatus solicitacaoEmpresaStatus;
    private final SolicitacaoUsuarioStatus solicitacaoUsuarioStatus;
    private final Time horaEntrevista;

    private final Date dataEntrevista;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDateTime dataCriacaoSolicitacao;
    private final String descricao;

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String numero;

    private final Long anuncioVagaId;
    private final Long empresaId;
    private final String titulo;
    private List<SetorCargoResponse> setorCargoResponses;
    private final boolean statusAnuncio;
    private final String nomeEmpresa;


    public SolicitacaoResponse(Solicitacao solicitacao){
        this.id = solicitacao.getId();
        this.usuarioId = solicitacao.getUsuario().getId();
        this.nomeUsuario = solicitacao.getUsuario().getNome();

        this.solicitacaoEmpresaStatus = solicitacao.getSolicitacaoEmpresaStatus();
        this.solicitacaoUsuarioStatus = solicitacao.getSolicitacaoUsuarioStatus();
        this.horaEntrevista = solicitacao.getHoraEntrevista();
        this.dataEntrevista = solicitacao.getDataEntrevista();
        this.dataCriacaoSolicitacao = solicitacao.getDataCriacaoSolicitacao();

        this.descricao = solicitacao.getDescricao();

        if (solicitacao.getEndereco() != null) {
            this.cep = solicitacao.getEndereco().getCep();
            this.logradouro = solicitacao.getEndereco().getLogradouro();
            this.complemento = solicitacao.getEndereco().getComplemento();
            this.bairro = solicitacao.getEndereco().getBairro();
            this.localidade = solicitacao.getEndereco().getLocalidade();
            this.uf = solicitacao.getEndereco().getUf();
            this.numero = solicitacao.getEndereco().getNumero().toString();
        } else {
            this.uf = solicitacao.getAnuncioVaga().getEndereco().getUf();
            this.localidade = solicitacao.getAnuncioVaga().getEndereco().getLocalidade();
        }
        this.anuncioVagaId = solicitacao.getAnuncioVaga().getId();
        this.empresaId = solicitacao.getAnuncioVaga().getEmpresa().getId();
        this.titulo = solicitacao.getAnuncioVaga().getTitulo();
        this.setorCargoResponses = SetorCargoResponse.converterList(solicitacao.getAnuncioVaga().getSetorCargo());
        this.statusAnuncio = solicitacao.getAnuncioVaga().isStatusAnuncio();
        this.nomeEmpresa = solicitacao.getAnuncioVaga().getEmpresa().getNome();

    }

    public static Page<SolicitacaoResponse> converter(Page<Solicitacao> solicitacao){
        return solicitacao.map(SolicitacaoResponse::new);
    }

    public static List<SolicitacaoResponse> converterList(List<Solicitacao> solicitacao) {
        return solicitacao.stream().map(SolicitacaoResponse::new).collect(Collectors.toList());
    }
}
