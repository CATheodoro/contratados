package br.com.projeto.contratados.rest.model.response;

import br.com.projeto.contratados.domain.entity.usuario.Experiencia;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.Date;

@Getter
public class ExperienciaResponse {
    private final Integer id;
    private final String descricao;
    private final Date inicio;
    private final Date termino;

    //private Usuario usuario;

    public ExperienciaResponse(Experiencia experiencia) {
        this.id = experiencia.getId();
        this.descricao = experiencia.getDescricao();
        this.inicio = experiencia.getInicio();
        this.termino = experiencia.getTermino();
    }

    public static Page<ExperienciaResponse> converterExperienciaDto(Page<Experiencia> experiencia) {
        return experiencia.map(ExperienciaResponse::new);
    }
}
