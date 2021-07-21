package br.com.projeto.contratados.domain.service.usuario;

import br.com.projeto.contratados.config.exception.excecoes.ExperienciaNaoEncontradaException;
import br.com.projeto.contratados.config.exception.excecoes.UsuarioNaoEncontradoException;
import br.com.projeto.contratados.config.security.TokenService;
import br.com.projeto.contratados.domain.entity.usuario.Experiencia;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import br.com.projeto.contratados.domain.repository.usuario.ExperienciaRepository;
import br.com.projeto.contratados.domain.repository.usuario.UsuarioRepository;
import br.com.projeto.contratados.rest.model.request.usuario.experiencia.AtualizacaoExperienciaRequest;
import br.com.projeto.contratados.rest.model.request.usuario.experiencia.ExperienciaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExperienciaService {
    @Autowired
    private ExperienciaRepository experienciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    private Long getIdUsuario(){
        return tokenService.getAuthenticatedUsuario();
    }

    public Experiencia cadastrar(ExperienciaRequest form) {

        Optional<Usuario> optional = usuarioRepository.findById(getIdUsuario());
        if (optional.isEmpty())
            throw new UsuarioNaoEncontradoException("Usuário não encontrado, não foi possível adicinar uma formação");

        var experiencia = form.converte(optional.get());

        return experienciaRepository.save(experiencia);
    }

    public Page<Experiencia> listar(String descricao, Pageable paginacao) {

        if (descricao == null)
            return experienciaRepository.findByUsuarioId(getIdUsuario(), paginacao);

        return experienciaRepository.findByDescricaoAndUsuarioId(descricao, getIdUsuario(), paginacao);
    }

    public Experiencia atualizar(Long id, AtualizacaoExperienciaRequest form) {

        Optional<Experiencia> optional = experienciaRepository.findById(id);
        if (optional.isEmpty())
            throw new ExperienciaNaoEncontradaException("Experiência não encontrada, não pode ser atualizada");

        if (!optional.get().getUsuario().getId().equals(getIdUsuario()))
            throw new UsuarioNaoEncontradoException("Usuário não encontrado, a experiência não pode ser atualizada");

        var experiencia = form.atualizacaoExperienciaForm(optional.get());
        return experienciaRepository.save(experiencia);
    }

    public Experiencia deletar(Long id) {
        Optional<Experiencia> optional = experienciaRepository.findById(id);
        if(optional.isEmpty())
            throw new ExperienciaNaoEncontradaException("Experiência não encontrada, não pode ser deletada");

        if (!optional.get().getUsuario().getId().equals(getIdUsuario()))
            throw new UsuarioNaoEncontradoException("Usuário não encontrado, a experiência não pode ser atualizada");

        var experiencia = experienciaRepository.getOne(id);
        experienciaRepository.deleteById(id);
        return experiencia;

    }
}
