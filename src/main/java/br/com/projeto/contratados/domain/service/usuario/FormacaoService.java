package br.com.projeto.contratados.domain.service.usuario;

import br.com.projeto.contratados.config.exception.excecoes.FormacaoNaoEncontradaException;
import br.com.projeto.contratados.config.exception.excecoes.UsuarioNaoEncontradoException;
import br.com.projeto.contratados.config.security.TokenService;
import br.com.projeto.contratados.domain.entity.usuario.Formacao;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import br.com.projeto.contratados.domain.repository.usuario.FormacaoRepository;
import br.com.projeto.contratados.domain.repository.usuario.UsuarioRepository;
import br.com.projeto.contratados.rest.model.request.usuario.formacao.AtualizacaoFormcaoRequest;
import br.com.projeto.contratados.rest.model.request.usuario.formacao.FormacaoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FormacaoService {

    @Autowired
    private FormacaoRepository formacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    private Long getIdUsuario(){

        return tokenService.getAuthenticatedUsuario();
    }

    public Formacao cadastrar(FormacaoRequest form) {

        Optional<Usuario> optional = usuarioRepository.findById(getIdUsuario());
        if (optional.isEmpty())
            throw new UsuarioNaoEncontradoException("Usuário não encontrado, não foi possível adicinar uma formação");

        var formacao = form.converte(optional.get());


        return formacaoRepository.save(formacao);

    }

    public Page<Formacao> listar(String descricao, Pageable paginacao) {

        if (descricao == null)
            return formacaoRepository.findByUsuarioId(getIdUsuario(), paginacao);

        return formacaoRepository.findByDescricaoAndUsuarioId(descricao, getIdUsuario(), paginacao);
    }


    public Formacao atualizar(Long id, AtualizacaoFormcaoRequest form) {

        Optional<Formacao> optional = formacaoRepository.findById(id);
        if (optional.isEmpty())
            throw new FormacaoNaoEncontradaException("Formação não encontrada");

        if (!optional.get().getUsuario().getId().equals(getIdUsuario()))
            throw new UsuarioNaoEncontradoException("Usuário não encontrado, a formação não pode ser atualizada");

        var formacao = form.atualizacaoFormacaoForm(optional.get());
        return formacaoRepository.save(formacao);
    }

    public Formacao deletar(Long id) {

        Optional<Formacao> optional = formacaoRepository.findById(id);
        if (optional.isEmpty())
            throw new FormacaoNaoEncontradaException("Formação não encontrada");

        if (!optional.get().getUsuario().getId().equals(getIdUsuario()))
            throw new UsuarioNaoEncontradoException("Usuário não encontrado, a formação não pode ser deletada");

        var formacao = formacaoRepository.getOne(id);
        formacaoRepository.deleteById(id);
        return formacao;
    }
}
