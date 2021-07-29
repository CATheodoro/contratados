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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
@PrimaryKeyJoinColumn(name = "id_user")
public class Usuario extends User {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "usuario")
    private List<Formacao> formacao = new ArrayList<>();
    @OneToMany(mappedBy = "usuario")
    private List<Experiencia> experiencia = new ArrayList<>();
    @OneToMany(mappedBy = "usuario")
    private List<Solicitacao> solicitacao = new ArrayList<>();

    @Embedded
    private Endereco endereco;

    private Date dataNascimento;

    @Enumerated(EnumType.STRING)
    private StatusUsuario status;

    private String linkCurriculo;

}
