package br.com.projeto.contratados.rest.model.response.anuncioVaga;

import br.com.projeto.contratados.domain.entity.empresa.AnuncioVaga;
import br.com.projeto.contratados.rest.model.response.SetorCargoResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AnuncioVagaDetalhadoResponse {
    private final Integer id;
    private List<SetorCargoResponse> setorCargoResponses;

    private final String requisitos;
    private final boolean statusAnuncio;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDateTime dataPostagem;

    private final Integer empresaId;
    private final String nomeEmpresa;
    private final String email;
    private final Integer celular;
    private final Integer telefone;

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private Integer numero;



    public AnuncioVagaDetalhadoResponse(AnuncioVaga anuncioVaga) {
        this.id = anuncioVaga.getId();
        if (anuncioVaga.getSetorCargo() != null)
            this.setorCargoResponses = SetorCargoResponse.converterList(anuncioVaga.getSetorCargo());

        this.requisitos = anuncioVaga.getRequisitos();
        this.statusAnuncio = anuncioVaga.isStatusAnuncio();
        this.dataPostagem = anuncioVaga.getDataPostagem();

        this.empresaId = anuncioVaga.getEmpresa().getId();
        this.nomeEmpresa = anuncioVaga.getEmpresa().getNomeFantasia();
        this.email = anuncioVaga.getEmpresa().getEmail();
        this.celular = anuncioVaga.getEmpresa().getCelular();
        this.telefone = anuncioVaga.getEmpresa().getTelefone();

        if (anuncioVaga.getEndereco() != null) {
            this.cep = anuncioVaga.getEndereco().getCep();
            this.logradouro = anuncioVaga.getEndereco().getLogradouro();
            this.complemento = anuncioVaga.getEndereco().getComplemento();
            this.bairro = anuncioVaga.getEndereco().getBairro();
            this.localidade = anuncioVaga.getEndereco().getLocalidade();
            this.uf = anuncioVaga.getEndereco().getUf();
            this.numero = anuncioVaga.getEndereco().getNumero();
        }

    }
}
