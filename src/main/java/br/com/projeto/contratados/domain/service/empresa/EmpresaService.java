package br.com.projeto.contratados.domain.service.empresa;

import br.com.projeto.contratados.config.exception.excecoes.EmailJaCadastradoException;
import br.com.projeto.contratados.config.exception.excecoes.EmpresaNaoEncontradaException;
import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import br.com.projeto.contratados.domain.repository.empresa.EmpresaRepository;
import br.com.projeto.contratados.rest.model.request.empresa.empresa.AtualizarEmailEmpresaRequest;
import br.com.projeto.contratados.rest.model.request.empresa.empresa.AtualizarSenhaEmpresaRequest;
import br.com.projeto.contratados.rest.model.request.empresa.empresa.AtualizarEmpresaRequest;
import br.com.projeto.contratados.rest.model.request.empresa.empresa.EmpresaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    public Empresa cadastrar(EmpresaRequest form) {
        Empresa empresa = form.converter();
        if(empresaRepository.existsByUserEmail(empresa.getUser().getEmail()))
            throw new EmailJaCadastradoException("E-mail já cadastrado");

        return empresaRepository.save(empresa);
    }

    public Page<Empresa> listar(String nomeFantasia, Pageable paginacao) {
        if (nomeFantasia == null)
            return empresaRepository.findAll(paginacao);

        return empresaRepository.findByNomeFantasia(nomeFantasia, paginacao);
    }

    public Empresa atualizar(Integer id, AtualizarEmpresaRequest form) {
        Optional<Empresa> optional = empresaRepository.findById(id);
        if(optional.isEmpty())
            throw new EmpresaNaoEncontradaException("Empresa não encontrada, não pode ser atualizada");

        Empresa empresa = form.atualizacaoEmpresaForm(id, empresaRepository);
        return empresaRepository.save(empresa);
    }

    public Empresa atualizarEmail(Integer id, AtualizarEmailEmpresaRequest form) {
        Optional<Empresa> optional = empresaRepository.findById(id);
        if(optional.isEmpty())
            throw new EmpresaNaoEncontradaException("Empresa não encontrada, E-mail não pode ser atualizada");

        Empresa empresa = form.atualizarSenhaEmpresaRequest(id, empresaRepository);

        if (empresaRepository.existsByUserEmail(empresa.getUser().getEmail()))
            throw new EmailJaCadastradoException("E-mail já cadastrado");

        return empresaRepository.save(empresa);
    }

    public Empresa atualizarSenha(Integer id, AtualizarSenhaEmpresaRequest form) {
        Optional<Empresa> optional = empresaRepository.findById(id);
        if(optional.isEmpty())
            throw new EmpresaNaoEncontradaException("Empresa não encontrada, senha não pode ser atualizada");

        Empresa empresa = form.atualizarSenhaEmpresaRequest(id, empresaRepository);
        return empresaRepository.save(empresa);
    }
}
