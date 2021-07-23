package br.com.projeto.contratados.rest.model.response;

import br.com.projeto.contratados.domain.entity.usuario.Formacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FormacaoResponse {

    private final Long id;
    private final String descricao;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private final Date inicioFormatado;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final Date terminoFormatado;

    private final Date inicio;
    private final Date termino;


    public FormacaoResponse(Formacao formacao) {
        this.id = formacao.getId();
        this.descricao = formacao.getDescricao();

        this.inicioFormatado = formacao.getInicio();
        this.terminoFormatado = formacao.getTermino();

        this.inicio = formacao.getInicio();
        this.termino = formacao.getTermino();
    }
    public static Page<FormacaoResponse> converter(Page<Formacao> formacaos) {
        return formacaos.map(FormacaoResponse::new);
    }

    public static List<FormacaoResponse> converterList(List<Formacao> formacaos){
        return formacaos.stream().map(FormacaoResponse::new).collect(Collectors.toList());
    }
}
