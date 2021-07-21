package br.com.projeto.contratados.rest.model.request.empresa.anuncio_vaga;

import br.com.projeto.contratados.domain.entity.empresa.AnuncioVaga;
import br.com.projeto.contratados.domain.repository.empresa.AnuncioVagaRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnuncioVagaAtualizarStatusRequest {
    private boolean statusAnuncio;

    public AnuncioVaga converter(Long id, AnuncioVagaRepository anuncioVagaRepository) {
        var anuncioVaga = anuncioVagaRepository.getOne(id);

        if (!statusAnuncio)
            anuncioVaga.setStatusAnuncio(false);

        return anuncioVaga;
    }
}
