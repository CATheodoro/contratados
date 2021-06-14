package br.com.projeto.contratados.rest.model.request.usuario.formacao;

import br.com.projeto.contratados.domain.entity.usuario.Formacao;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Builder
public class FormacaoRequest {

    @NotEmpty @NotNull
    private String descricao;
    @NotNull
    private Date inicio;
    @NotNull
    private Date termino;



    public Formacao converte(Usuario usuario){
        return Formacao.builder()
                .descricao(this.descricao)
                .inicio(this.inicio)
                .termino(this.termino)
                .usuario(usuario)
                .build();
    }
}
