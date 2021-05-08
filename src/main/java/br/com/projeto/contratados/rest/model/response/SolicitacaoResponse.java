package br.com.projeto.contratados.rest.model.response;

import br.com.projeto.contratados.domain.entity.solicitacao.Solicitacao;
import br.com.projeto.contratados.domain.entity.solicitacao.SolicitacaoEmpresaStatus;
import br.com.projeto.contratados.domain.entity.solicitacao.SolicitacaoUsuarioStatus;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

@Getter
public class SolicitacaoResponse {
    private final Integer id;
//    private Usuario usuario;
//    private AnuncioVaga anuncioVaga;
    private EnderecoResponse endereco;
    private final SolicitacaoEmpresaStatus solicitacaoEmpresaStatus;
    private final SolicitacaoUsuarioStatus solicitacaoUsuarioStatus;
    private final Time horaEntrevista;
    private final Date dataEntrevista;
    private final LocalDateTime dataCriacaoSolicitacao;

    public SolicitacaoResponse(Solicitacao solicitacao){
        this.id = solicitacao.getId();
//        this.usuario = solicitacao.getUsuario();
//        this.anuncioVaga = solicitacao.getAnuncioVaga();

        if (solicitacao.getEndereco() != null) {
            this.endereco = EnderecoResponse.builder()
                    .cep(solicitacao.getEndereco().getCep())
                    .logradouro(solicitacao.getEndereco().getLogradouro())
                    .complemento(solicitacao.getEndereco().getComplemento())
                    .bairro(solicitacao.getEndereco().getBairro())
                    .localidade(solicitacao.getEndereco().getLocalidade())
                    .uf(solicitacao.getEndereco().getUf())
                    .build();
        }
        this.solicitacaoEmpresaStatus = solicitacao.getSolicitacaoEmpresaStatus();
        this.solicitacaoUsuarioStatus = solicitacao.getSolicitacaoUsuarioStatus();
        this.horaEntrevista = solicitacao.getHoraEntrevista();
        this.dataEntrevista = solicitacao.getDataEntrevista();
        this.dataCriacaoSolicitacao = solicitacao.getDataCriacaoSolicitacao();
    }

    public static Page<SolicitacaoResponse> converter(Page<Solicitacao> solicitacao){
        return solicitacao.map(SolicitacaoResponse::new);
    }
}
