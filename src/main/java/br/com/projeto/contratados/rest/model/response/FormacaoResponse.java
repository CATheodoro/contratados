package br.com.projeto.contratados.rest.model.response;

import br.com.projeto.contratados.domain.entity.usuario.Formacao;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.Date;
@Getter
public class FormacaoResponse {

    private final Integer id;
    private final String descricao;
    private final Date inicio;
    private final Date termino;


    public FormacaoResponse(Formacao formacao) {
        this.id = formacao.getId();
        this.descricao = formacao.getDescricao();
        this.inicio = formacao.getInicio();
        this.termino = formacao.getTermino();
    }
    public static Page<FormacaoResponse> converterFormacaoDto(Page<Formacao> formacao) {
        return formacao.map(FormacaoResponse::new);
    }
}
