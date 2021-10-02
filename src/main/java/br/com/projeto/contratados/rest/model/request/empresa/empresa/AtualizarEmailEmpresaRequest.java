package br.com.projeto.contratados.rest.model.request.empresa.empresa;

import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import br.com.projeto.contratados.domain.repository.empresa.EmpresaRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AtualizarEmailEmpresaRequest {

    @Size(min = 6, max = 50)
    @NotNull @NotEmpty
    private String oldPassword;

    @Size(min = 6, max = 100)
    @NotNull @NotEmpty @Email
    private String email;

    public Empresa atualizarSenhaEmpresaRequest(Empresa empresa){

        empresa.setEmail(this.email);

        return empresa;
    }
}
