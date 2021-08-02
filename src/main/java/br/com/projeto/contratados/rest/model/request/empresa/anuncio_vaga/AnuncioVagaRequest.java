package br.com.projeto.contratados.rest.model.request.empresa.anuncio_vaga;

import br.com.projeto.contratados.domain.entity.Endereco;
import br.com.projeto.contratados.domain.entity.empresa.AnuncioVaga;
import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
public class AnuncioVagaRequest {

    @NotNull @NotEmpty
    private String titulo;
    @NotNull @NotEmpty
    private String requisitos;
    private String descricao;

    @NotNull @NotEmpty
    private String enderecoCep;
    @NotNull @NotEmpty
    private String complemento;
    private Integer numero;

    private Time cargaHoraria;
    private Float salario;


    public AnuncioVaga converter(Empresa empresa) throws IOException {

        //Buscar Cep
        var viaCEPClient = new ViaCEPClient();
        var viaCEPEndereco = viaCEPClient.getEndereco(enderecoCep);

        var endereco = Endereco.builder()
                .cep(viaCEPEndereco.getCep())
                .logradouro(viaCEPEndereco.getLogradouro())
                .complemento(this.complemento)
                .bairro(viaCEPEndereco.getBairro())
                .localidade(viaCEPEndereco.getLocalidade())
                .uf(viaCEPEndereco.getUf())
                .ibge(viaCEPEndereco.getIbge())
                .numero(this.numero)
                .build();

        return AnuncioVaga.builder()
                .empresa(empresa)
                .endereco(endereco)
                .titulo(this.titulo)
                .descricao(this.descricao)
                .cargaHoraria(this.cargaHoraria)
                .requisitos(this.requisitos)
                .salario(this.salario)
                .statusAnuncio(true)
                .dataPostagem(LocalDateTime.now())
                .build();
    }
}
