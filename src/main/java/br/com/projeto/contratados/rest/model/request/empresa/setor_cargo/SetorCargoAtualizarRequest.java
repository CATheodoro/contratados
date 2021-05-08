package br.com.projeto.contratados.rest.model.request.empresa.setor_cargo;

import br.com.projeto.contratados.domain.entity.empresa.SetorCargo;
import br.com.projeto.contratados.domain.repository.empresa.SetorCargoRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class SetorCargoAtualizarRequest {
    private String setor;
    private String cargo;

    public SetorCargo converter(Integer id, SetorCargoRepository setorCargoRepository) {
        SetorCargo setorCargo = setorCargoRepository.getOne(id);

        if (this.setor != null && !this.setor.isEmpty())
            setorCargo.setSetor(this.setor);
        if (this.cargo != null && !this.cargo.isEmpty())
        setorCargo.setCargo(this.cargo);

        return setorCargo;
    }
}
