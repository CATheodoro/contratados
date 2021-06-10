package br.com.projeto.contratados.domain.service.empresa;


import br.com.projeto.contratados.config.exception.excecoes.AnuncioVagaNaoEncontradoException;
import br.com.projeto.contratados.config.exception.excecoes.EmpresaNaoEncontradaException;
import br.com.projeto.contratados.domain.entity.empresa.AnuncioVaga;
import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import br.com.projeto.contratados.domain.repository.empresa.AnuncioVagaRepository;
import br.com.projeto.contratados.domain.repository.empresa.EmpresaRepository;
import br.com.projeto.contratados.rest.model.request.empresa.anuncio_vaga.AnuncioVagaAtualizarRequest;
import br.com.projeto.contratados.rest.model.request.empresa.anuncio_vaga.AnuncioVagaAtualizarStatusRequest;
import br.com.projeto.contratados.rest.model.request.empresa.anuncio_vaga.AnuncioVagaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class AnuncioVagaService {
    @Autowired
    private AnuncioVagaRepository anuncioVagaRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    public AnuncioVaga cadastrar(AnuncioVagaRequest form) throws IOException {

        AnuncioVaga anuncioVaga = form.converter();

        Optional<Empresa> optional = empresaRepository.findById(anuncioVaga.getEmpresa().getId());
        if (optional.isEmpty())
            throw new EmpresaNaoEncontradaException("Empresa não encontrada, não foi possível criar o anúncio");

        return anuncioVagaRepository.save(anuncioVaga);
    }

    public Page<AnuncioVaga> listar(Pageable paginacao) {
        return anuncioVagaRepository.findAll(paginacao);
    }

    public AnuncioVaga atualizar(Integer id, AnuncioVagaAtualizarRequest form) throws IOException {

        Optional<AnuncioVaga> optional = anuncioVagaRepository.findById(id);
        if (optional.isEmpty())
            throw new AnuncioVagaNaoEncontradoException("Anúncio de Vagas não encontrado, não pode ser atualizado");

        AnuncioVaga anuncioVaga = form.converter(id, anuncioVagaRepository);
        return anuncioVagaRepository.save(anuncioVaga);
    }

    public AnuncioVaga atualizarStatus(Integer id, AnuncioVagaAtualizarStatusRequest form) {
        Optional<AnuncioVaga> optional = anuncioVagaRepository.findById(id);
        if (optional.isEmpty())
            throw new AnuncioVagaNaoEncontradoException("Anúncio de Vagas não encontrado, seu status não pode ser alterado");

        AnuncioVaga anuncioVaga = form.converter(id, anuncioVagaRepository);
        return anuncioVagaRepository.save(anuncioVaga);
    }
}
