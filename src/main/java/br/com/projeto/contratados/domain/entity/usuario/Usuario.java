package br.com.projeto.contratados.domain.entity.usuario;

import br.com.projeto.contratados.domain.entity.Endereco;
import br.com.projeto.contratados.domain.entity.solicitacao.Solicitacao;
import br.com.projeto.contratados.domain.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

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

    @Column(length = 50, nullable = false)
    private String nome;
    private Date dataNascimento;

    @Column(length = 20)
    private String celular;
    @Column(length = 20)
    private String telefone;

    @Enumerated(EnumType.STRING)
    private StatusUsuario status;
    private LocalDateTime dataCriacaoPerfil;

    @Embedded
    private User user;
}
