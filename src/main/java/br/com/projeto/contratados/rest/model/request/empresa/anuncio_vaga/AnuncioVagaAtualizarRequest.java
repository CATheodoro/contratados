package br.com.projeto.contratados.rest.model.request.empresa.anuncio_vaga;

import br.com.projeto.contratados.domain.entity.Endereco;
import br.com.projeto.contratados.domain.entity.empresa.AnuncioVaga;
import br.com.projeto.contratados.domain.repository.empresa.AnuncioVagaRepository;
import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.sql.Time;

@Getter
@Setter
@Builder
public class AnuncioVagaAtualizarRequest {


    private String titulo;
    private String requisitos;
    private String descricao;

    private String enderecoCep;
    private String complemento;
    private Integer numero;

    private Time cargaHoraria;
    private Float salario;


    public AnuncioVaga converter(AnuncioVaga anuncioVaga) throws IOException {

        if (enderecoCep != null){
            var viaCEPClient = new ViaCEPClient();
            var viaCEPEndereco = viaCEPClient.getEndereco(enderecoCep);

            anuncioVaga.setEndereco(Endereco.builder()
                    .cep(viaCEPEndereco.getCep())
                    .logradouro(viaCEPEndereco.getLogradouro())
                    .complemento(this.complemento)
                    .bairro(viaCEPEndereco.getBairro())
                    .localidade(viaCEPEndereco.getLocalidade())
                    .uf(viaCEPEndereco.getUf())
                    .ibge(viaCEPEndereco.getIbge())
                    .numero(this.numero)
                    .build());
        }

        if(this.descricao != null)
            anuncioVaga.setDescricao(this.descricao);
        if(this.titulo != null)
            anuncioVaga.setTitulo(this.titulo);

        anuncioVaga.setCargaHoraria(this.cargaHoraria);
        if (this.requisitos !=null)
            anuncioVaga.setRequisitos(this.requisitos);

        anuncioVaga.setSalario(this.salario);


        return anuncioVaga;
    }
}
