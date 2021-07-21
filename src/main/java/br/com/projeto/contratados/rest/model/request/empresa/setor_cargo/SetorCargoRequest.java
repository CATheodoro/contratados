package br.com.projeto.contratados.rest.model.request.empresa.setor_cargo;

import br.com.projeto.contratados.domain.entity.empresa.AnuncioVaga;
import br.com.projeto.contratados.domain.entity.empresa.SetorCargo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class SetorCargoRequest {

    @NotNull
    private AnuncioVaga anuncioVaga;
    private String setor;
    @NotNull @NotEmpty
    private String cargo;


    public SetorCargo converter(){
        return SetorCargo.builder()
                .anuncioVaga(this.anuncioVaga)
                .setor(this.setor)
                .cargo(this.cargo)
                .build();
    }
}
