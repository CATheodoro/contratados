package br.com.projeto.contratados.domain.service.usuario;

import br.com.projeto.contratados.config.exception.excecoes.EmailJaCadastradoException;
import br.com.projeto.contratados.config.exception.excecoes.UsuarioNaoEncontradoException;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import br.com.projeto.contratados.domain.repository.usuario.UsuarioRepository;
import br.com.projeto.contratados.rest.model.request.usuario.usuario.AtualizacaoUsuarioRequest;
import br.com.projeto.contratados.rest.model.request.usuario.usuario.AtualizarEmailUsuarioRequest;
import br.com.projeto.contratados.rest.model.request.usuario.usuario.AtualizarSenhaUsuarioRequest;
import br.com.projeto.contratados.rest.model.request.usuario.usuario.UsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    public Usuario cadastrar(UsuarioRequest request) throws IOException {
        Usuario usuario = request.converter();

        if (usuarioRepository.existsByUserEmail(usuario.getUser().getEmail()))
            throw new EmailJaCadastradoException("Email já cadastrado");


        return usuarioRepository.save(usuario);
    }


    public Page<Usuario> listar(String nome, Pageable paginacao) {
        if (nome == null)
            return usuarioRepository.findAll(paginacao);

        return usuarioRepository.findByNome(nome, paginacao);

    }

    public Usuario atualizar(Integer id, AtualizacaoUsuarioRequest form) throws IOException {

        Optional<Usuario> optional = usuarioRepository.findById(id);

        if(optional.isEmpty())
            throw new UsuarioNaoEncontradoException("Usuario não encontrado");


        Usuario usuario = form.atualizacaoUsuarioForm(id, usuarioRepository);

        return usuarioRepository.save(usuario);

    }

    public Usuario atualizarSenha(Integer id, AtualizarSenhaUsuarioRequest form) {

        Optional<Usuario> optional = usuarioRepository.findById(id);

        if(optional.isEmpty())
            throw new UsuarioNaoEncontradoException("Usuario não encontrado, Senha não alterada");

        Usuario usuario = form.atualizarSenhaUsuario(id, usuarioRepository);
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarEmail(Integer id, AtualizarEmailUsuarioRequest form) {
        Optional<Usuario> optional = usuarioRepository.findById(id);

        if(optional.isEmpty())
            throw new UsuarioNaoEncontradoException("Usuario não encontrado, E-mail não alterada");


        Usuario usuario = form.atualizarEmailUsuario(id, usuarioRepository);

        if (usuarioRepository.existsByUserEmail(usuario.getUser().getEmail()))
            throw new EmailJaCadastradoException("Email já cadastrado");


        return usuarioRepository.save(usuario);
    }
}
