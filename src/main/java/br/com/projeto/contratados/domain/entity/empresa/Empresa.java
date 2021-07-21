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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "empresa")
@PrimaryKeyJoinColumn(name = "id_user")
public class Empresa extends User {

    @OneToMany(mappedBy = "empresa")
    private List<AnuncioVaga> anuncioVaga = new ArrayList<>();
    @Column(length = 100)
    private String descricao;
    private String cnpj;
    private Date dataFundacao;

}
