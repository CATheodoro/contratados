package br.com.projeto.contratados.rest.model.request.solicitacao;

import br.com.projeto.contratados.domain.entity.Endereco;
import br.com.projeto.contratados.domain.entity.solicitacao.Solicitacao;
import br.com.projeto.contratados.domain.entity.solicitacao.SolicitacaoEmpresaStatus;
import br.com.projeto.contratados.domain.repository.SolicitacaoRepository;
import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
public class SolicitacaoEmpresaStatusRequest {

    @NotNull
    private SolicitacaoEmpresaStatus solicitacaoEmpresaStatus;

    public Solicitacao solicitacaoEmpresaRequest(Solicitacao solicitacao) {
        solicitacao.setSolicitacaoEmpresaStatus(this.solicitacaoEmpresaStatus);

        return solicitacao;
    }

}
