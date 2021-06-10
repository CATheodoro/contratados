package br.com.projeto.contratados.domain.service.usuario;

import br.com.projeto.contratados.config.exception.excecoes.FormacaoNaoEncontradaException;
import br.com.projeto.contratados.config.exception.excecoes.UsuarioNaoEncontradoException;
import br.com.projeto.contratados.domain.entity.usuario.Formacao;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import br.com.projeto.contratados.domain.repository.usuario.FormacaoRepository;
import br.com.projeto.contratados.domain.repository.usuario.UsuarioRepository;
import br.com.projeto.contratados.rest.model.request.usuario.formacao.AtualizacaoFormcaoRequest;
import br.com.projeto.contratados.rest.model.request.usuario.formacao.FormacaoRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Formacao cadastrar(FormacaoRequest form) {
        var formacao = form.converte();

        Optional<Usuario> optional = usuarioRepository.findById(formacao.getUsuario().getId());
        if (optional.isEmpty())
            throw new UsuarioNaoEncontradoException("Usuário não encontrado, não foi possível adicinar uma formação");


        return formacaoRepository.save(formacao);

    }

    public Page<Formacao> listar(String descricao, Pageable paginacao) {

        if (descricao == null)
            return formacaoRepository.findAll(paginacao);

        return formacaoRepository.findByDescricao(descricao, paginacao);
    }


    public Formacao atualizar(Integer id, AtualizacaoFormcaoRequest form) {

        Optional<Formacao> optional = formacaoRepository.findById(id);
        if (optional.isEmpty())
            throw new FormacaoNaoEncontradaException("Formação não encontrada");

        var formacao = form.atualizacaoFormacaoForm(id, formacaoRepository);
        return formacaoRepository.save(formacao);
    }

    public Formacao deletar(Integer id) {

        Optional<Formacao> optional = formacaoRepository.findById(id);
        if (optional.isEmpty())
            throw new FormacaoNaoEncontradaException("Formação não encontrada");

        var formacao = formacaoRepository.getOne(id);
        formacaoRepository.deleteById(id);
        return formacao;
    }
}
