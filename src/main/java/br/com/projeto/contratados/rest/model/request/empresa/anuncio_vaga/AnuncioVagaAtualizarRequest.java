package br.com.projeto.contratados.rest.model.request.empresa.anuncio_vaga;

import br.com.projeto.contratados.domain.entity.Endereco;
import br.com.projeto.contratados.domain.entity.empresa.AnuncioVaga;
import br.com.projeto.contratados.domain.repository.empresa.AnuncioVagaRepository;
import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.sql.Time;

@Getter
@Setter
@Builder
public class AnuncioVagaAtualizarRequest {

    private String enderecoCep;
    private Time cargaHoraria;
    private String requisitos;
    private Float salario;


    public AnuncioVaga converter(AnuncioVaga anuncioVaga) throws IOException {

        if (enderecoCep != null){
            var viaCEPClient = new ViaCEPClient();
            var viaCEPEndereco = viaCEPClient.getEndereco(enderecoCep);

            anuncioVaga.setEndereco(Endereco.builder()
                    .cep(viaCEPEndereco.getCep())
                    .logradouro(viaCEPEndereco.getLogradouro())
                    .complemento(viaCEPEndereco.getComplemento())
                    .bairro(viaCEPEndereco.getBairro())
                    .localidade(viaCEPEndereco.getLocalidade())
                    .uf(viaCEPEndereco.getUf())
                    .ibge(viaCEPEndereco.getIbge())
                    .build());
        }


        if (this.cargaHoraria != null)
            anuncioVaga.setCargaHoraria(this.cargaHoraria);
        if (this.requisitos !=null && !this.requisitos.isEmpty())
            anuncioVaga.setRequisitos(this.requisitos);
        if (this.requisitos !=null)
            anuncioVaga.setSalario(this.salario);


        return anuncioVaga;
    }
}
