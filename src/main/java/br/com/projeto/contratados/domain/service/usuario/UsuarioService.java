package br.com.projeto.contratados.domain.service.usuario;

import br.com.projeto.contratados.config.exception.excecoes.EmailJaCadastradoException;
import br.com.projeto.contratados.config.exception.excecoes.UsuarioNaoEncontradoException;
import br.com.projeto.contratados.config.security.TokenService;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import br.com.projeto.contratados.domain.repository.user.UserRepository;
import br.com.projeto.contratados.domain.repository.usuario.UsuarioRepository;
import br.com.projeto.contratados.rest.model.request.usuario.usuario.AtualizacaoUsuarioRequest;
import br.com.projeto.contratados.rest.model.request.usuario.usuario.AtualizarEmailUsuarioRequest;
import br.com.projeto.contratados.rest.model.request.usuario.usuario.AtualizarSenhaUsuarioRequest;
import br.com.projeto.contratados.rest.model.request.usuario.usuario.UsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Value("${spring.profiles.active}")
    String profile;

    private Integer getIdUsuario(Integer id){
        if (profile.equals("test"))
            return id;

        return tokenService.getAuthenticatedUsuario();
    }

    public Usuario cadastrar(UsuarioRequest request) throws IOException {

        if (userRepository.existsByEmail(request.getEmail()))
            throw new EmailJaCadastradoException("Email já cadastrado");

        var usuario = request.converter();

        return usuarioRepository.save(usuario);
    }

    public Page<Usuario> listar(String nome, Pageable paginacao) {
        if (nome == null)
            return usuarioRepository.findAll(paginacao);
        return usuarioRepository.findByNome(nome, paginacao);
    }

    public Usuario perfilUsuario(Integer id) {
        Optional<Usuario> optional = usuarioRepository.findById(getIdUsuario(id));
        if(optional.isEmpty())
            throw new UsuarioNaoEncontradoException("Ocorreu algum erro, Não foi possivel acessar seu perfil");
        return optional.get();
    }

    public Usuario atualizar(Integer id, AtualizacaoUsuarioRequest request) throws IOException {

        Optional<Usuario> optional = usuarioRepository.findById(getIdUsuario(id));

        if(optional.isEmpty())
            throw new UsuarioNaoEncontradoException("Usuario não encontrado");

        var usuario = request.atualizacaoUsuarioForm(optional.get());

        return usuarioRepository.save(usuario);

    }

    public Usuario atualizarSenha(Integer id, AtualizarSenhaUsuarioRequest form) {

        Optional<Usuario> optional = usuarioRepository.findById(getIdUsuario(id));

        if(optional.isEmpty())
            throw new UsuarioNaoEncontradoException("Usuario não encontrado, Senha não alterada");

        var usuario = form.atualizarSenhaUsuario(optional.get());
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarEmail(Integer id, AtualizarEmailUsuarioRequest form) {
        Optional<Usuario> optional = usuarioRepository.findById(getIdUsuario(id));

        if(optional.isEmpty())
            throw new UsuarioNaoEncontradoException("Usuario não encontrado, E-mail não alterada");

        if (usuarioRepository.existsByEmail(form.getEmail()))
            throw new EmailJaCadastradoException("Email já cadastrado");

        var usuario = form.atualizarEmailUsuario(optional.get());


        return usuarioRepository.save(usuario);
    }


}
