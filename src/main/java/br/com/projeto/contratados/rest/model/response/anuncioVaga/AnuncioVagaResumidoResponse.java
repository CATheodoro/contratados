package br.com.projeto.contratados.rest.model.response.anuncioVaga;

import br.com.projeto.contratados.domain.entity.empresa.AnuncioVaga;
import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import br.com.projeto.contratados.rest.model.response.SetorCargoResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AnuncioVagaResumidoResponse {
    private final Long id;
    private List<SetorCargoResponse> setorCargoResponses;
    private final String titulo;

    private final boolean statusAnuncio;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDateTime dataPostagem;

    private final Long empresaId;
    private final String nomeEmpresa;

    private String localidade;
    private String uf;


    public AnuncioVagaResumidoResponse(AnuncioVaga anuncioVaga){
        this.id = anuncioVaga.getId();
        if(anuncioVaga.getSetorCargo() != null)
            this.setorCargoResponses = SetorCargoResponse.converterList(anuncioVaga.getSetorCargo());

        if (anuncioVaga.getEndereco() != null) {
            this.localidade = anuncioVaga.getEndereco().getLocalidade();
            this.uf = anuncioVaga.getEndereco().getUf();
        }
        this.titulo = anuncioVaga.getTitulo();
        this.statusAnuncio = anuncioVaga.isStatusAnuncio();
        this.dataPostagem = anuncioVaga.getDataPostagem();

        this.empresaId = anuncioVaga.getEmpresa().getId();
        this.nomeEmpresa = anuncioVaga.getEmpresa().getNome();
    }

    public static Page<AnuncioVagaResumidoResponse> converter(Page<AnuncioVaga> anuncioVagas){
        return anuncioVagas.map(AnuncioVagaResumidoResponse::new);
    }
}
