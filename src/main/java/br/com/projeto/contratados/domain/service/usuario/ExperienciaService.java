package br.com.projeto.contratados.domain.service.usuario;

import br.com.projeto.contratados.config.exception.excecoes.ExperienciaNaoEncontradaException;
import br.com.projeto.contratados.config.exception.excecoes.UsuarioNaoEncontradoException;
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

    public Experiencia cadastrar(ExperienciaRequest form) {

        var experiencia = form.converte();

        Optional<Usuario> optional = usuarioRepository.findById(experiencia.getUsuario().getId());
        if (optional.isEmpty())
            throw new UsuarioNaoEncontradoException("Usuário não encontrado, não foi possível adicinar uma formação");

        return experienciaRepository.save(experiencia);
    }

    public Page<Experiencia> listar(String descricao, Pageable paginacao) {

        if (descricao == null)
            return experienciaRepository.findAll(paginacao);

        return experienciaRepository.findByDescricao(descricao, paginacao);
    }

    public Experiencia atualizar(Integer id, AtualizacaoExperienciaRequest form) {

        Optional<Experiencia> optional = experienciaRepository.findById(id);
        if (optional.isEmpty())
            throw new ExperienciaNaoEncontradaException("Experiência não encontrada, não pode ser atualizada");

        var experiencia = form.atualizacaoExperienciaForm(id, experienciaRepository);
        return experienciaRepository.save(experiencia);
    }

    public Experiencia deletar(Integer id) {
        Optional<Experiencia> optional = experienciaRepository.findById(id);
        if(optional.isEmpty())
            throw new ExperienciaNaoEncontradaException("Experiência não encontrada, não pode ser deletada");

        var experiencia = experienciaRepository.getOne(id);
        experienciaRepository.deleteById(id);
        return experiencia;

    }
}
