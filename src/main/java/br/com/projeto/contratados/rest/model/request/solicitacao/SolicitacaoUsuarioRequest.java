package br.com.projeto.contratados.rest.model.request.solicitacao;

import br.com.projeto.contratados.domain.entity.solicitacao.Solicitacao;
import br.com.projeto.contratados.domain.entity.solicitacao.SolicitacaoUsuarioStatus;
import br.com.projeto.contratados.domain.repository.SolicitacaoRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SolicitacaoUsuarioRequest {

    @NotNull
    private SolicitacaoUsuarioStatus solicitacaoUsuarioStatus;

    public Solicitacao solicitacaoUsuarioRequest(Solicitacao solicitacao){
        solicitacao.setSolicitacaoUsuarioStatus(this.solicitacaoUsuarioStatus);

        return solicitacao;
    }
}
