package br.com.projeto.contratados.domain.service.empresa;

import br.com.projeto.contratados.config.exception.excecoes.EmailJaCadastradoException;
import br.com.projeto.contratados.config.exception.excecoes.EmpresaNaoEncontradaException;
import br.com.projeto.contratados.config.exception.excecoes.SenhaIncorretaException;
import br.com.projeto.contratados.config.security.TokenService;
import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import br.com.projeto.contratados.domain.repository.empresa.EmpresaRepository;
import br.com.projeto.contratados.domain.repository.user.UserRepository;
import br.com.projeto.contratados.rest.model.request.empresa.empresa.AtualizarEmailEmpresaRequest;
import br.com.projeto.contratados.rest.model.request.empresa.empresa.AtualizarEmpresaRequest;
import br.com.projeto.contratados.rest.model.request.empresa.empresa.AtualizarSenhaEmpresaRequest;
import br.com.projeto.contratados.rest.model.request.empresa.empresa.EmpresaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    private Long getIdEmpresa(){
        return tokenService.getAuthenticatedEmpresa();
    }

    public Empresa cadastrar(EmpresaRequest form) {
        var empresa = form.converter();
        if (userRepository.existsByEmail(empresa.getEmail()))
            throw new EmailJaCadastradoException("E-mail já cadastrado");

        return empresaRepository.save(empresa);
    }

    public Page<Empresa> listar(String nome, Pageable paginacao) {
        if (nome == null)
            return empresaRepository.findAll(paginacao);

        return empresaRepository.findByNome(nome, paginacao);
    }

    public Empresa perfilEmpresa(Long id) {
        if(id == null || id == 0) {
            Optional<Empresa> optional = empresaRepository.findById(getIdEmpresa());
            if (optional.isEmpty())
                throw new EmpresaNaoEncontradaException("Ocorreu algum erro, Não foi possivel acessar seu perfil");
            return optional.get();
        }
        Optional<Empresa> optional = empresaRepository.findById(id);
        if (optional.isEmpty())
            throw new EmpresaNaoEncontradaException("Ocorreu algum erro, Não foi possivel acessar o perfil da empresa");
        return  optional.get();
    }

    public Empresa atualizar(Long id, AtualizarEmpresaRequest form) {
        Optional<Empresa> optional = empresaRepository.findById(getIdEmpresa());
        if (optional.isEmpty())
            throw new EmpresaNaoEncontradaException("Empresa não encontrada, não pode ser atualizada");

        var empresa = form.atualizacaoEmpresaForm(optional.get());
        return empresaRepository.save(empresa);
    }

    public Empresa atualizarEmail(Long id, AtualizarEmailEmpresaRequest form) {
        Optional<Empresa> optional = empresaRepository.findById(getIdEmpresa());
        if (optional.isEmpty())
            throw new EmpresaNaoEncontradaException("Empresa não encontrada, E-mail não pode ser atualizada");

        if(!new BCryptPasswordEncoder().matches(form.getOldPassword(), optional.get().getPassword()))
            throw new SenhaIncorretaException("Senha Incorreta");


        var empresa = form.atualizarSenhaEmpresaRequest(optional.get());

        if (userRepository.existsByEmail(empresa.getEmail()))
            throw new EmailJaCadastradoException("E-mail já cadastrado");

        return empresaRepository.save(empresa);
    }


    public Empresa atualizarSenha(Long id, AtualizarSenhaEmpresaRequest form) {
        Optional<Empresa> optional = empresaRepository.findById(getIdEmpresa());
        if (optional.isEmpty())
            throw new EmpresaNaoEncontradaException("Empresa não encontrada, senha não pode ser atualizada");

        if(!new BCryptPasswordEncoder().matches(form.getOldPassword(), optional.get().getPassword()))
            throw new SenhaIncorretaException("Senha Incorreta");

        var empresa = form.atualizarSenhaEmpresaRequest(optional.get());
        return empresaRepository.save(empresa);
    }

}
