package br.com.projeto.contratados.rest.model.request.empresa.anuncio_vaga;

import br.com.projeto.contratados.domain.entity.Endereco;
import br.com.projeto.contratados.domain.entity.empresa.AnuncioVaga;
import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;
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
    @NotNull
    private Empresa empresa;

    private String enderecoCep;

    @NotNull
    private Time cargaHoraria;
    @NotNull @NotEmpty
    private String requisitos;
    private Float salario;

    public AnuncioVaga converter() throws IOException {

        //Buscar Cep
        ViaCEPClient viaCEPClient = new ViaCEPClient();
        ViaCEPEndereco viaCEPEndereco = viaCEPClient.getEndereco(enderecoCep);

        Endereco endereco = Endereco.builder()
                .cep(viaCEPEndereco.getCep())
                .logradouro(viaCEPEndereco.getLogradouro())
                .complemento(viaCEPEndereco.getComplemento())
                .bairro(viaCEPEndereco.getBairro())
                .localidade(viaCEPEndereco.getLocalidade())
                .uf(viaCEPEndereco.getUf())
                .ibge(viaCEPEndereco.getIbge())
                .build();

        return AnuncioVaga.builder()
                .empresa(this.empresa)
                .endereco(endereco)
                .cargaHoraria(this.cargaHoraria)
                .requisitos(this.requisitos)
                .salario(this.salario)
                .statusAnuncio(true)
                .dataPostagem(LocalDateTime.now())
                .build();
    }
}
