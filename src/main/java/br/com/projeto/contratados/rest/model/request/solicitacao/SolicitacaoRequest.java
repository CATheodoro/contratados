package br.com.projeto.contratados.rest.model.request.solicitacao;

import br.com.projeto.contratados.domain.entity.solicitacao.Solicitacao;
import br.com.projeto.contratados.domain.entity.empresa.AnuncioVaga;
import br.com.projeto.contratados.domain.entity.solicitacao.SolicitacaoEmpresaStatus;
import br.com.projeto.contratados.domain.entity.solicitacao.SolicitacaoUsuarioStatus;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class SolicitacaoRequest {

    @NotNull
    private Long anuncioVagaId;

    public Solicitacao converter(Usuario usuario, AnuncioVaga anuncioVaga) {

        return Solicitacao.builder()
                .usuario(usuario)
                .anuncioVaga(anuncioVaga)
                .solicitacaoEmpresaStatus(SolicitacaoEmpresaStatus.PENDENTE)
                .solicitacaoUsuarioStatus(SolicitacaoUsuarioStatus.PENDENTE)
                .dataCriacaoSolicitacao(LocalDateTime.now())
                .build();
    }
}
