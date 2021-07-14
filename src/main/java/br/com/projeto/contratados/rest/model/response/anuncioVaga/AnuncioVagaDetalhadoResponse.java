package br.com.projeto.contratados.rest.model.response.anuncioVaga;

import br.com.projeto.contratados.domain.entity.empresa.AnuncioVaga;
import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import br.com.projeto.contratados.rest.model.response.EnderecoResponse;
import br.com.projeto.contratados.rest.model.response.SetorCargoResponse;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AnuncioVagaDetalhadoResponse {
    private final Integer id;
    private List<SetorCargoResponse> setorCargoResponses;
    private EnderecoResponse endereco;

    private final String requisitos;
    private final boolean statusAnuncio;
    private final LocalDateTime dataPostagem;

    private final String nomeEmpresa;
    private Integer celular;
    private Integer telefone;

    public AnuncioVagaDetalhadoResponse(AnuncioVaga anuncioVaga, Empresa empresa) {
        this.id = anuncioVaga.getId();
        if (anuncioVaga.getSetorCargo() != null)
            this.setorCargoResponses = SetorCargoResponse.converterList(anuncioVaga.getSetorCargo());

        if (anuncioVaga.getEndereco() != null) {
            this.endereco = EnderecoResponse.builder()
                    .localidade(anuncioVaga.getEndereco().getLocalidade())
                    .uf(anuncioVaga.getEndereco().getUf())
                    .build();
        }

        this.requisitos = anuncioVaga.getRequisitos();
        this.statusAnuncio = anuncioVaga.isStatusAnuncio();
        this.dataPostagem = anuncioVaga.getDataPostagem();

        this.nomeEmpresa = empresa.getNomeFantasia();
        this.celular = empresa.getCelular();
        this.telefone = empresa.getTelefone();
    }
}
