package br.com.projeto.contratados.rest.model.request.empresa.setor_cargo;

import br.com.projeto.contratados.domain.entity.empresa.SetorCargo;
import br.com.projeto.contratados.domain.repository.empresa.SetorCargoRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SetorCargoAtualizarRequest {
    private String setor;
    private String cargo;

    public SetorCargo converter(SetorCargo setorCargo) {

        if (this.setor != null && !this.setor.isEmpty())
            setorCargo.setSetor(this.setor);
        if (this.cargo != null && !this.cargo.isEmpty())
            setorCargo.setCargo(this.cargo);

        return setorCargo;
    }
}
