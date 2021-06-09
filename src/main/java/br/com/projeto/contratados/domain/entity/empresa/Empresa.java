package br.com.projeto.contratados.domain.entity.empresa;

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
public class Empresa {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //Chaves Estrangeiras
    @OneToMany(mappedBy = "empresa")
    private List<AnuncioVaga> anuncioVaga = new ArrayList<>();
    @Column(length = 50, nullable = false)
    private String nomeFantasia;
    private String descricao;
    private Integer celular;
    private Integer telefone;
    private String cnpj;
    private Date dataFundacao;
    private LocalDateTime dataCriacaoPerfil;
    @Embedded
    private User user;

}
