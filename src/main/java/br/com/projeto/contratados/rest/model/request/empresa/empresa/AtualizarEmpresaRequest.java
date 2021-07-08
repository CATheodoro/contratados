package br.com.projeto.contratados.rest.model.request.empresa.empresa;

import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import br.com.projeto.contratados.domain.repository.empresa.EmpresaRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class AtualizarEmpresaRequest {

    private String nomeFantasia;
    private String descricao;
    private Integer celular;
    private Integer telefone;
    private String cnpj;
    private Date dataFundacao;

    public Empresa atualizacaoEmpresaForm(Empresa empresa){

        if(this.nomeFantasia != null && !this.nomeFantasia.isEmpty())
            empresa.setNomeFantasia(this.nomeFantasia);
        empresa.setDescricao(this.descricao);
        empresa.setCelular(this.celular);
        empresa.setTelefone(this.telefone);
        empresa.setCnpj(this.cnpj);
        empresa.setDataFundacao(this.dataFundacao);

        return empresa;
    }
}
