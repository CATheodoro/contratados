package br.com.projeto.contratados.rest.model.request.empresa.empresa;

import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import br.com.projeto.contratados.domain.repository.empresa.EmpresaRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AtualizarSenhaEmpresaRequest {

    @Size(min = 6, max = 50)
    @NotNull @NotEmpty
    private String oldPassword;

    @Size(min = 6, max = 50)
    @NotNull @NotEmpty
    private String password;

    public Empresa atualizarSenhaEmpresaRequest(Empresa empresa){
        empresa.setPassword(new BCryptPasswordEncoder().encode(this.password));
        return empresa;
    }
}
