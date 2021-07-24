package br.com.projeto.contratados.rest.model.request.solicitacao;

import br.com.projeto.contratados.domain.entity.Endereco;
import br.com.projeto.contratados.domain.entity.solicitacao.Solicitacao;
import br.com.projeto.contratados.domain.entity.solicitacao.SolicitacaoEmpresaStatus;
import br.com.projeto.contratados.domain.repository.SolicitacaoRepository;
import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
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
    @NotNull
    private String descricao;

    private String  enderecoCep;
    @NotNull
    private Time horaEntrevista;
    @NotNull
    private Date dataEntrevista;

    private String complemento;
    @NotNull
    private Integer numero;

    public Solicitacao solicitacaoEmpresaRequest(Solicitacao solicitacao) throws IOException {

        solicitacao.setSolicitacaoEmpresaStatus(this.solicitacaoEmpresaStatus);

        if (this.solicitacaoEmpresaStatus == SolicitacaoEmpresaStatus.ACEITO){

            if(enderecoCep != null){
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

                solicitacao.setEndereco(endereco);
            }


            solicitacao.setDescricao(this.descricao);
            solicitacao.setHoraEntrevista(this.horaEntrevista);
            solicitacao.setDataEntrevista(this.dataEntrevista);
        }

        return solicitacao;
    }

}
