package br.com.projeto.contratados.domain.entity.empresa;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetorCargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Chaves Estrangeiras
    @ManyToOne
    private AnuncioVaga anuncioVaga;

    @Column(length = 50)
    private String setor;
    @Column(length = 50, nullable = false)
    private String cargo;

}
