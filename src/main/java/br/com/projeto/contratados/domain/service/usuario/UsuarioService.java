package br.com.projeto.contratados.domain.service.usuario;

import br.com.projeto.contratados.config.exception.excecoes.EmailJaCadastradoException;
import br.com.projeto.contratados.config.exception.excecoes.SenhaIncorretaException;
import br.com.projeto.contratados.config.exception.excecoes.UsuarioNaoEncontradoException;
import br.com.projeto.contratados.config.security.TokenService;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import br.com.projeto.contratados.domain.repository.user.UserRepository;
import br.com.projeto.contratados.domain.repository.usuario.UsuarioRepository;
import br.com.projeto.contratados.rest.model.request.usuario.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private Long getIdUsuario(Long id){
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

    public Usuario perfilUsuario(Long id) {
        if(id == null || id == 0){
            Optional<Usuario> optional = usuarioRepository.findById(getIdUsuario(id));
            if(optional.isEmpty())
                throw new UsuarioNaoEncontradoException("Ocorreu algum erro, Não foi possivel acessar seu perfil");
            return optional.get();
        }
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if(optional.isEmpty())
            throw new UsuarioNaoEncontradoException("Ocorreu algum erro, Não foi possivel acessar o perfil do usuáiro");
        return optional.get();

    }

    public Usuario atualizar(Long id, AtualizacaoUsuarioRequest request) throws IOException {

        Optional<Usuario> optional = usuarioRepository.findById(getIdUsuario(id));

        if(optional.isEmpty())
            throw new UsuarioNaoEncontradoException("Usuario não encontrado");

        var usuario = request.atualizacaoUsuarioForm(optional.get());

        return usuarioRepository.save(usuario);

    }

    public Usuario atualizarSenha(Long id, AtualizarSenhaUsuarioRequest form) {

        Optional<Usuario> optional = usuarioRepository.findById(getIdUsuario(id));
        if(optional.isEmpty())
            throw new UsuarioNaoEncontradoException("Usuario não encontrado, Senha não alterada");

        if(!new BCryptPasswordEncoder().matches(form.getOldPassword(), optional.get().getPassword()))
            throw new SenhaIncorretaException("Senha Incorreta");

        var usuario = form.atualizarSenhaUsuario(optional.get());
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarEmail(Long id, AtualizarEmailUsuarioRequest form) {
        Optional<Usuario> optional = usuarioRepository.findById(getIdUsuario(id));

        if(optional.isEmpty())
            throw new UsuarioNaoEncontradoException("Usuario não encontrado, E-mail não alterada");

        if(!new BCryptPasswordEncoder().matches(form.getOldPassword(), optional.get().getPassword()))
            throw new SenhaIncorretaException("Senha Incorreta");

        if (usuarioRepository.existsByEmail(form.getEmail()))
            throw new EmailJaCadastradoException("Email já cadastrado");

        var usuario = form.atualizarEmailUsuario(optional.get());


        return usuarioRepository.save(usuario);
    }


    public Usuario atualizarStatus(AtualizarStatusUsuarioRequest form) {
        Optional<Usuario> optional = usuarioRepository.findById(getIdUsuario(null));

        if(optional.isEmpty())
            throw new UsuarioNaoEncontradoException("Usuario não encontrado, status não pode ser alterado");

        var usuario = form.atualizarStatusUsuario(optional.get());

        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarCurriculo(AtualizarCurriculoUsuarioRequest form) {
        Optional<Usuario> optional = usuarioRepository.findById(getIdUsuario(null));

        if(optional.isEmpty())
            throw new UsuarioNaoEncontradoException("Usuario não encontrado, currículo não pode ser alterado");

        var usuario = form.atualizarCurriculoUsuarioRequest(optional.get());

        return usuarioRepository.save(usuario);
    }
}
