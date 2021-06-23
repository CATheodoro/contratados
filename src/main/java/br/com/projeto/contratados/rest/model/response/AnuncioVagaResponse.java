package br.com.projeto.contratados.rest.model.response;

import br.com.projeto.contratados.domain.entity.empresa.AnuncioVaga;
import br.com.projeto.contratados.domain.entity.solicitacao.Solicitacao;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AnuncioVagaResponse {
    private final Integer id;
    private final List<SetorCargoResponse> setorCargoResponses;
    private final List<SolicitacaoResponse> solicitacaos;
    private EnderecoResponse endereco;

    private final Time cargaHoraria;
    private final String requisitos;
    private final Float salario;
    private final boolean statusAnuncio;
    private final LocalDateTime dataPostagem;


    public AnuncioVagaResponse(AnuncioVaga anuncioVaga){
        this.id = anuncioVaga.getId();
        this.setorCargoResponses = SetorCargoResponse.converterList(anuncioVaga.getSetorCargo());
        this.solicitacaos = SolicitacaoResponse.converterList(anuncioVaga.getSolicitacao());

        if (anuncioVaga.getEndereco() != null) {
            this.endereco = EnderecoResponse.builder()
                    .cep(anuncioVaga.getEndereco().getCep())
                    .logradouro(anuncioVaga.getEndereco().getLogradouro())
                    .complemento(anuncioVaga.getEndereco().getComplemento())
                    .bairro(anuncioVaga.getEndereco().getBairro())
                    .localidade(anuncioVaga.getEndereco().getLocalidade())
                    .uf(anuncioVaga.getEndereco().getUf())
                    .build();
        }

        this.cargaHoraria = anuncioVaga.getCargaHoraria();
        this.requisitos = anuncioVaga.getRequisitos();
        this.salario = anuncioVaga.getSalario();
        this.statusAnuncio = anuncioVaga.isStatusAnuncio();
        this.dataPostagem = anuncioVaga.getDataPostagem();

    }

    public static Page<AnuncioVagaResponse> converter(Page<AnuncioVaga> anuncioVagas){
        return anuncioVagas.map(AnuncioVagaResponse::new);
    }

    public static List<AnuncioVagaResponse> converterList(List<AnuncioVaga> anuncioVaga) {
        return anuncioVaga.stream().map(AnuncioVagaResponse::new).collect(Collectors.toList());
    }
}
