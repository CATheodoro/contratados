package br.com.projeto.contratados.domain.service.empresa;

import br.com.projeto.contratados.config.exception.excecoes.AnuncioVagaNaoEncontradoException;
import br.com.projeto.contratados.config.exception.excecoes.EmpresaNaoEncontradaException;
import br.com.projeto.contratados.config.exception.excecoes.SetorCargoNaoEncontradoException;
import br.com.projeto.contratados.config.security.TokenService;
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

    @Autowired
    private TokenService tokenService;

    private Long getIdEmpresa(){
        return tokenService.getAuthenticatedEmpresa();
    }

    public SetorCargo cadastrar(SetorCargoRequest form) {

        Optional<AnuncioVaga> optional = anuncioVagaRepository.findById(form.getAnuncioVagaId());
        if (optional.isEmpty())
            throw new AnuncioVagaNaoEncontradoException("Anúncio vaga não encontrado, não foi possível cadastrar um setor e um cargo");

        if (!optional.get().getEmpresa().getId().equals(getIdEmpresa()))
            throw new EmpresaNaoEncontradaException("Empresa não encontrada, não foi possível cadastrar um setor e um cargo");

        var setorCargo = form.converter(optional.get());

        return setorCargoRepository.save(setorCargo);
    }

    public Page<SetorCargo> listar(Pageable paginacao) {
        return setorCargoRepository.findAll(paginacao);
    }


    public SetorCargo atualizar(Long id, SetorCargoAtualizarRequest form) {
        Optional<SetorCargo> optional = setorCargoRepository.findById(id);
        if (optional.isEmpty())
            throw new SetorCargoNaoEncontradoException("Setor Cargo não encontrado, ele não pode ser atualizado");

        if (!optional.get().getAnuncioVaga().getEmpresa().getId().equals(getIdEmpresa()))
            throw new EmpresaNaoEncontradaException("Empresa não encontrada, não foi possível atualizar os setores e os cargos");

        var setorCargo = form.converter(optional.get());
        return setorCargoRepository.save(setorCargo);
    }

    public SetorCargo deletar(Long id) {
        Optional<SetorCargo> optional = setorCargoRepository.findById(id);
        if (optional.isEmpty())
            throw new SetorCargoNaoEncontradoException("Setor Cargo não encontrado, ele não pode ser deletado");

        if (!optional.get().getAnuncioVaga().getEmpresa().getId().equals(getIdEmpresa()))
            throw new EmpresaNaoEncontradaException("Empresa não encontrada, não foi possível deletar o setor e o cargo");

        var setorCargo = setorCargoRepository.getOne(id);
        setorCargoRepository.deleteById(id);
        return setorCargo;
    }
}
