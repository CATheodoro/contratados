package br.com.projeto.contratados.rest.model.request.empresa.empresa;

import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import br.com.projeto.contratados.domain.repository.empresa.EmpresaRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AtualizarSenhaEmpresaRequest {
    @NotNull @NotEmpty
    private String oldPassword;

    @NotNull @NotEmpty
    private String password;

    public Empresa atualizarSenhaEmpresaRequest(Empresa empresa){
        empresa.setPassword(new BCryptPasswordEncoder().encode(this.password));
        return empresa;
    }
}
