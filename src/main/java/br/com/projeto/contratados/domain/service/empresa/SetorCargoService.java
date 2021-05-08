package br.com.projeto.contratados.domain.service.empresa;

import br.com.projeto.contratados.config.exception.AnuncioVagaNaoEncontrado;
import br.com.projeto.contratados.config.exception.SetorCargoNaoEncontrado;
import br.com.projeto.contratados.domain.entity.empresa.AnuncioVaga;
import br.com.projeto.contratados.domain.entity.empresa.SetorCargo;
import br.com.projeto.contratados.domain.repository.empresa.AnuncioVagaRepository;
import br.com.projeto.contratados.domain.repository.empresa.SetorCargoRepository;
import br.com.projeto.contratados.rest.model.request.empresa.setor_cargo.SetorCargoAtualizarRequest;
import br.com.projeto.contratados.rest.model.request.empresa.setor_cargo.SetorCargoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SetorCargoService {

    @Autowired
    private SetorCargoRepository setorCargoRepository;

    @Autowired
    private AnuncioVagaRepository anuncioVagaRepository;

    public SetorCargo cadastrar(SetorCargoRequest form) {

        SetorCargo setorCargo = form.converter();

        Optional<AnuncioVaga> optional = anuncioVagaRepository.findById(setorCargo.getAnuncioVaga().getId());
        if (optional.isEmpty())
            throw new AnuncioVagaNaoEncontrado("Anúncio vaga não encontrado, não foi possível cadastrar um setor e um cargo");

        return setorCargoRepository.save(setorCargo);
    }

    public Page<SetorCargo> listar(Pageable paginacao) {
        return setorCargoRepository.findAll(paginacao);
    }


    public SetorCargo atualizar(Integer id, SetorCargoAtualizarRequest form) {
        Optional<SetorCargo> optional = setorCargoRepository.findById(id);
        if (optional.isEmpty())
            throw new SetorCargoNaoEncontrado("Setor Cargo não encontrado, ele não pode ser atualizado");

        SetorCargo setorCargo = form.converter(id, setorCargoRepository);
        return setorCargoRepository.save(setorCargo);
    }

    public SetorCargo deletar(Integer id) {
        Optional<SetorCargo> optional = setorCargoRepository.findById(id);
        if (optional.isEmpty())
            throw new SetorCargoNaoEncontrado("Setor Cargo não encontrado, ele não pode ser deletado");

        SetorCargo setorCargo = setorCargoRepository.getOne(id);
        setorCargoRepository.deleteById(id);
        return setorCargo;
    }
}
