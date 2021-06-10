package br.com.projeto.contratados.rest.model.request.solicitacao;

import br.com.projeto.contratados.domain.entity.Endereco;
import br.com.projeto.contratados.domain.entity.solicitacao.Solicitacao;
import br.com.projeto.contratados.domain.entity.solicitacao.SolicitacaoEmpresaStatus;
import br.com.projeto.contratados.domain.repository.SolicitacaoRepository;
import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
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
public class SolicitacaoEmpresaRequest {

    @NotNull
    private SolicitacaoEmpresaStatus solicitacaoEmpresaStatus;

    private String  enderecoCep;
    private Time horaEntrevista;
    private Date dataEntrevista;

    public Solicitacao solicitacaoEmpresaRequest(Integer id, SolicitacaoRepository solicitacaoRepository) throws IOException {

        var solicitacao = solicitacaoRepository.getOne(id);

        solicitacao.setSolicitacaoEmpresaStatus(this.solicitacaoEmpresaStatus);

        if (this.solicitacaoEmpresaStatus == SolicitacaoEmpresaStatus.ACEITO){
            var viaCEPClient = new ViaCEPClient();
            var viaCEPEndereco = viaCEPClient.getEndereco(enderecoCep);

            var endereco = Endereco.builder()
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
        }

        return solicitacao;
    }

}
