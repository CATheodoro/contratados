package br.com.projeto.contratados.rest.model.request.solicitacao;

import br.com.projeto.contratados.domain.entity.Endereco;
import br.com.projeto.contratados.domain.entity.solicitacao.Solicitacao;
import br.com.projeto.contratados.domain.repository.SolicitacaoInterface;
import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@Builder
public class SolicitacaoAtualizarEmpresaRequest {

    @NotNull
    private String enderecoCep;
    @NotNull
    private Time horaEntrevista;
    @NotNull
    private Date dataEntrevista;


    public Solicitacao atualizar(Integer id, SolicitacaoInterface solicitacaoInterface) throws IOException {
        Solicitacao solicitacao = solicitacaoInterface.getOne(id);

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


        solicitacao.setEndereco(endereco);
        solicitacao.setHoraEntrevista(this.horaEntrevista);
        solicitacao.setDataEntrevista(this.dataEntrevista);

        return solicitacao;
    }

}
