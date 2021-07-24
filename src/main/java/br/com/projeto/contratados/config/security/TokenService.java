package br.com.projeto.contratados.config.security;

import br.com.projeto.contratados.config.exception.excecoes.UserAuthException;
import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import br.com.projeto.contratados.domain.entity.user.User;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TokenService {

    @Value("${contratados.jwt.expiration}")
    private Long expiration;

    @Value("${contratados.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        User logado = (User) authentication.getPrincipal();
        var hoje = new Date();
        var dataExpiracao = new Date((hoje.getTime() + expiration));

        return Jwts.builder()
                .setIssuer("API Contratados")
                .setSubject(logado.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Boolean tokenIsValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUser(String token) {
        var claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    public Long getAuthenticatedUsuario() {

        try {
            Optional<Object> principal = (Optional<Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal.isPresent() && principal.get() instanceof Usuario) {
                return ((Usuario)principal.get()).getId();
            }
            throw new UserAuthException("Não foi possível recuperar id do usuário");

        } catch (Exception e) {
            throw new UserAuthException(e.getMessage());
        }
    }

    public Long getAuthenticatedEmpresa() {

        try {
            Optional<Object> principal = (Optional<Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal.isPresent() && principal.get() instanceof Empresa) {
                return ((Empresa)principal.get()).getId();
            }
            throw new UserAuthException("Não foi possível recuperar id da empresa");

        } catch (Exception e) {
            throw new UserAuthException(e.getMessage());
        }
    }

    public Long getAuthenticatedEmpresaSemValidacao() {

        try {
            Optional<Object> principal = (Optional<Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal.isPresent() && principal.get() instanceof Empresa) {
                return ((Empresa)principal.get()).getId();
            }  else {
                return 0L;
            }

        } catch (Exception e) {
            throw new UserAuthException(e.getMessage());
        }

    }


    public User getUser(Authentication authentication) {
        return (User) authentication.getPrincipal();
    }
}
