package br.com.projeto.contratados.domain.service.empresa;


import br.com.projeto.contratados.config.exception.excecoes.AnuncioVagaNaoEncontradoException;
import br.com.projeto.contratados.config.exception.excecoes.EmpresaNaoEncontradaException;
import br.com.projeto.contratados.config.exception.excecoes.UsuarioNaoEncontradoException;
import br.com.projeto.contratados.config.security.TokenService;
import br.com.projeto.contratados.domain.entity.empresa.AnuncioVaga;
import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import br.com.projeto.contratados.domain.repository.empresa.AnuncioVagaRepository;
import br.com.projeto.contratados.domain.repository.empresa.EmpresaRepository;
import br.com.projeto.contratados.domain.repository.usuario.UsuarioRepository;
import br.com.projeto.contratados.rest.model.request.empresa.anuncio_vaga.AnuncioVagaAtualizarRequest;
import br.com.projeto.contratados.rest.model.request.empresa.anuncio_vaga.AnuncioVagaAtualizarStatusRequest;
import br.com.projeto.contratados.rest.model.request.empresa.anuncio_vaga.AnuncioVagaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AnuncioVagaService {
    @Autowired
    private AnuncioVagaRepository anuncioVagaRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Long getIdUsuario(){
        return tokenService.getAuthenticatedUsuario();
    }

    private Long getIdEmpresa(){
        return tokenService.getAuthenticatedEmpresa();
    }

    private Long getIdEmpresaSemValidacao() {
        return tokenService.getAuthenticatedEmpresaSemValidacao();
    }

    public AnuncioVaga cadastrar(AnuncioVagaRequest form) throws IOException {
        Optional<Empresa> optional = empresaRepository.findById(getIdEmpresa());
        if (optional.isEmpty())
            throw new EmpresaNaoEncontradaException("Empresa não encontrada, não foi possível criar o anúncio");

        var anuncioVaga = form.converter(optional.get());

        return anuncioVagaRepository.save(anuncioVaga);
    }

    public Page<AnuncioVaga> listar(Pageable paginacao) {
        return anuncioVagaRepository.findByEmpresaId(getIdEmpresa(), paginacao);
    }

    public Page<AnuncioVaga> listarResumida(Pageable paginacao, String localidade, String cargo) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(getIdEmpresaSemValidacao());
        if(empresaOptional.isPresent()) {
            if(localidade != "" && localidade != null && cargo !="" && cargo != null)
                return anuncioVagaRepository.findAllByEmpresaIdAndEnderecoLocalidadeAndSetorCargoCargo(getIdEmpresaSemValidacao(), localidade, cargo, paginacao);

            if(localidade !="" && localidade != null)
                return anuncioVagaRepository.findAllByEmpresaIdAndEnderecoLocalidade(getIdEmpresaSemValidacao(), localidade, paginacao);

            if(cargo !="" && cargo != null)
                return anuncioVagaRepository.findAllByEmpresaIdAndSetorCargoCargo(getIdEmpresaSemValidacao(), cargo, paginacao);

            return anuncioVagaRepository.findAllByEmpresaId(getIdEmpresaSemValidacao(), paginacao);
        }

        if(localidade != "" && localidade != null && cargo !="" && cargo != null)
            return anuncioVagaRepository.findAllByEnderecoLocalidadeAndSetorCargoCargo(localidade, cargo, paginacao);

        if(localidade !="" && localidade != null)
            return anuncioVagaRepository.findAllByEnderecoLocalidade(localidade, paginacao);

        if(cargo !="" && cargo != null)
            return anuncioVagaRepository.findAllBySetorCargoCargo(cargo, paginacao);

        return anuncioVagaRepository.findAll(paginacao);
    }

    public AnuncioVaga detalhado(Long id) {
        Optional<AnuncioVaga> optional = anuncioVagaRepository.findById(id);
        if (optional.isEmpty())
            throw new AnuncioVagaNaoEncontradoException("Anúncio de Vagas não encontrado");
        return optional.get();
    }

    public AnuncioVaga atualizar(Long id, AnuncioVagaAtualizarRequest form) throws IOException {

        Optional<AnuncioVaga> optional = anuncioVagaRepository.findById(id);
        if (optional.isEmpty())
            throw new AnuncioVagaNaoEncontradoException("Anúncio de Vagas não encontrado, não pode ser atualizado");

        if (!optional.get().getEmpresa().getId().equals(getIdEmpresa()))
            throw new EmpresaNaoEncontradaException("Empresa não encontrado, o anúncio de vaga não pode ser atualizado");

        var anuncioVaga = form.converter(optional.get());
        return anuncioVagaRepository.save(anuncioVaga);
    }

    public AnuncioVaga atualizarStatus(Long id, AnuncioVagaAtualizarStatusRequest form) {
        Optional<AnuncioVaga> optional = anuncioVagaRepository.findById(id);
        if (optional.isEmpty())
            throw new AnuncioVagaNaoEncontradoException("Anúncio de Vagas não encontrado, seu status não pode ser alterado");

        if (!optional.get().getEmpresa().getId().equals(getIdEmpresa()))
            throw new EmpresaNaoEncontradaException("Empresa não encontrado, o status do anuúncio de vaga não pode atualizado");

        var anuncioVaga = form.converter(id, anuncioVagaRepository);
        return anuncioVagaRepository.save(anuncioVaga);
    }

}
