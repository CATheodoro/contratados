package br.com.projeto.contratados.domain.entity.solicitacao;

import br.com.projeto.contratados.domain.entity.Endereco;
import br.com.projeto.contratados.domain.entity.empresa.AnuncioVaga;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Solicitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private AnuncioVaga anuncioVaga;
    private String descricao;
    @Embedded
    private Endereco endereco;

    private SolicitacaoEmpresaStatus solicitacaoEmpresaStatus;

    private SolicitacaoUsuarioStatus solicitacaoUsuarioStatus;

    private Time horaEntrevista;
    private Date dataEntrevista;
    private LocalDateTime dataCriacaoSolicitacao;
}
