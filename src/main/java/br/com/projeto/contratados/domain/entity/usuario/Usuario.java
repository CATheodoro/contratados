package br.com.projeto.contratados.domain.entity.usuario;

import br.com.projeto.contratados.domain.entity.Endereco;
import br.com.projeto.contratados.domain.entity.Perfil;
import br.com.projeto.contratados.domain.entity.solicitacao.Solicitacao;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Chaves Estrangeiras
    @OneToMany(mappedBy = "usuario")
    private List<Formacao> formacao = new ArrayList<>();
    @OneToMany(mappedBy = "usuario")
    private List<Experiencia> experiencia = new ArrayList<>();
    @OneToMany(mappedBy = "usuario")
    private List<Solicitacao> solicitacao = new ArrayList<>();
    @Embedded
    private Endereco endereco;

    @Column(length = 50, nullable = false, unique = true)
    private String email;
    @Column(length = 60, nullable = false)
    private String senha;
    @Column(length = 50, nullable = false)
    private String nome;
    private Date dataNascimento;

    @Column(length = 20)
    private String celular;
    @Column(length = 20)
    private String telefone;

    //private Image fotoPerfil;
    //private Image curriculo;

    @Enumerated(EnumType.STRING)
    private StatusUsuario status = StatusUsuario.DISPONIVEL;
    private LocalDateTime dataCriacaoPerfil;


    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enable;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() { return accountNonExpired; }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
