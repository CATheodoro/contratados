package br.com.projeto.contratados.domain.entity.empresa;

import br.com.projeto.contratados.domain.entity.Perfil;
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
public class Empresa {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //Chaves Estrangeiras
    @OneToMany(mappedBy = "empresa")
    private List<AnuncioVaga> anuncioVaga = new ArrayList<>();

    @Column(length = 50, nullable = false, unique = true)
    private String email;
    @Column(length = 60, nullable = false)
    private String senha;
    @Column(length = 50, nullable = false)
    private String nomeFantasia;

    private String descricao;
    private Integer celular;
    private Integer telefone;
    private String cnpj;
//    private img fotoPerfil;
    private Date dataFundacao;
    private LocalDateTime dataCriacaoPerfil;

//    @ManyToMany(fetch = FetchType.EAGER)
//    private List<Perfil> perfis = new ArrayList<>();

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enable;

}
