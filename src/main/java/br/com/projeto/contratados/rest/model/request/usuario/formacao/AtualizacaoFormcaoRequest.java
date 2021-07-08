package br.com.projeto.contratados.rest.model.request.usuario.formacao;

import br.com.projeto.contratados.domain.entity.usuario.Formacao;
import br.com.projeto.contratados.domain.repository.usuario.FormacaoRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class AtualizacaoFormcaoRequest {

    private String descricao;
    private Date inicio;
    private Date termino;

    //private Usuario usuario;
    public Formacao atualizacaoFormacaoForm(Formacao formacao) {
        if (this.descricao != null && !this.descricao.isEmpty())
            formacao.setDescricao(this.descricao);
        if (this.inicio != null)
            formacao.setInicio(this.inicio);
        if (this.termino != null)
            formacao.setTermino(this.termino);

        return formacao;
    }
}
