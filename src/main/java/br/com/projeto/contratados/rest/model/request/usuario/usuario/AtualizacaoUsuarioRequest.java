package br.com.projeto.contratados.rest.model.request.usuario.usuario;

import br.com.projeto.contratados.domain.entity.Endereco;
import br.com.projeto.contratados.domain.entity.usuario.StatusUsuario;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import br.com.projeto.contratados.domain.repository.usuario.UsuarioRepository;
import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Date;

@Getter
@Setter
@Builder
public class AtualizacaoUsuarioRequest {

    private String enderecoCep;

    private String nome;
    private Date dataNascimento;
    private String celular;
    private String telefone;
    //private Image fotoPerfil;
    //private Image curriculo;
    private StatusUsuario status;


    public Usuario atualizacaoUsuarioForm(Integer id, UsuarioRepository usuarioRepository) throws IOException {
        var usuario = usuarioRepository.getOne(id);

        if (this.nome != null && !this.nome.isEmpty())
            usuario.setNome(this.nome);
        usuario.setDataNascimento((this.dataNascimento));
        usuario.setCelular(this.celular);
        usuario.setTelefone(this.telefone);
        usuario.setStatus(this.status);

        if (enderecoCep !=null) {
            var viaCEPClient = new ViaCEPClient();
            var viaCEPEndereco = viaCEPClient.getEndereco(enderecoCep);

            usuario.setEndereco(Endereco.builder()
                    .cep(viaCEPEndereco.getCep())
                    .logradouro(viaCEPEndereco.getLogradouro())
                    .complemento(viaCEPEndereco.getComplemento())
                    .bairro(viaCEPEndereco.getBairro())
                    .localidade(viaCEPEndereco.getLocalidade())
                    .uf(viaCEPEndereco.getUf())
                    .ibge(viaCEPEndereco.getIbge())
                    .build()
            );
        }

        return usuario;
    }
}