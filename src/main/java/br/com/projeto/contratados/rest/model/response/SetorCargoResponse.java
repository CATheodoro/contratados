package br.com.projeto.contratados.rest.model.response;

import br.com.projeto.contratados.domain.entity.empresa.SetorCargo;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class SetorCargoResponse {
    private final Integer id;
    private final String setor;
    private final String cargo;

    public SetorCargoResponse(SetorCargo setorCargo){
        this.id = setorCargo.getId();
        this.setor = setorCargo.getSetor();
        this.cargo = setorCargo.getCargo();
    }

    public static Page<SetorCargoResponse> converter(Page<SetorCargo> setorCargos) {
        return setorCargos.map(SetorCargoResponse::new);
    }
}
