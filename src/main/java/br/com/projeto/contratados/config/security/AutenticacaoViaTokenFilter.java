package br.com.projeto.contratados.config.security;

import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import br.com.projeto.contratados.domain.repository.empresa.EmpresaRepository;
import br.com.projeto.contratados.domain.repository.usuario.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;
    private EmpresaRepository empresaRepository;

    public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository,EmpresaRepository empresaRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
        this.empresaRepository = empresaRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(httpServletRequest);
        Boolean valido = tokenService.tokenIsValid(token);
        if(valido){
            autenticarUsuario(token);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void autenticarUsuario(String token) {
        String emailUsuario = tokenService.getEmailUsuario(token); //Antes era findById
        Optional<Usuario> usuario = usuarioRepository.findByUserEmail(emailUsuario);
        Optional<Empresa> empresa = empresaRepository.findByUserEmail(emailUsuario);
        UsernamePasswordAuthenticationToken authentication = null;
        if(usuario.isPresent()){
            var user = usuario.get();
            authentication = new UsernamePasswordAuthenticationToken(usuario, null, user.getUser().getAuthorities());
        }
        if(empresa.isPresent()){
            var emp = empresa.get();
           authentication = new UsernamePasswordAuthenticationToken(usuario, null, emp.getUser().getAuthorities());
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest httpServletRequest) {

        String token = httpServletRequest.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer")){
            return null;
        }
        return token.substring(7, token.length());
    }

}
