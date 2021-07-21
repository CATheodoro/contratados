package br.com.projeto.contratados.rest.model.response.anuncioVaga;

import br.com.projeto.contratados.domain.entity.empresa.AnuncioVaga;
import br.com.projeto.contratados.rest.model.response.SetorCargoResponse;
import br.com.projeto.contratados.rest.model.response.SolicitacaoResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AnuncioVagaResponse {
    private final Long id;
    private List<SetorCargoResponse> setorCargoResponses;
    private List<SolicitacaoResponse> solicitacaos;

    private final Time cargaHoraria;
    private final String requisitos;
    private final Float salario;
    private final boolean statusAnuncio;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDateTime dataPostagem;

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private Integer numero;


    public AnuncioVagaResponse(AnuncioVaga anuncioVaga){
        this.id = anuncioVaga.getId();
        if(anuncioVaga.getSetorCargo() != null)
            this.setorCargoResponses = SetorCargoResponse.converterList(anuncioVaga.getSetorCargo());
        if(anuncioVaga.getSolicitacao() !=null)
            this.solicitacaos = SolicitacaoResponse.converterList(anuncioVaga.getSolicitacao());

        this.cargaHoraria = anuncioVaga.getCargaHoraria();
        this.requisitos = anuncioVaga.getRequisitos();
        this.salario = anuncioVaga.getSalario();
        this.statusAnuncio = anuncioVaga.isStatusAnuncio();
        this.dataPostagem = anuncioVaga.getDataPostagem();

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

    public static Page<AnuncioVagaResponse> converter(Page<AnuncioVaga> anuncioVagas){
        return anuncioVagas.map(AnuncioVagaResponse::new);
    }

    public static List<AnuncioVagaResponse> converterList(List<AnuncioVaga> anuncioVaga) {
        return anuncioVaga.stream().map(AnuncioVagaResponse::new).collect(Collectors.toList());
    }
}
