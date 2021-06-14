package br.com.projeto.contratados.usuario;

import br.com.projeto.contratados.ContratadosApplication;
import br.com.projeto.contratados.ContratadosApplicationTests;
import br.com.projeto.contratados.domain.entity.Endereco;
import br.com.projeto.contratados.domain.entity.usuario.StatusUsuario;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import br.com.projeto.contratados.helper.MockMvcHelper;
import br.com.projeto.contratados.rest.model.request.usuario.usuario.AtualizacaoUsuarioRequest;
import br.com.projeto.contratados.rest.model.request.usuario.usuario.AtualizarEmailUsuarioRequest;
import br.com.projeto.contratados.rest.model.request.usuario.usuario.AtualizarSenhaUsuarioRequest;
import br.com.projeto.contratados.rest.model.request.usuario.usuario.UsuarioRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.method.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@AutoConfigureMockMvc(addFilters = false)
@Sql(value = "classpath:cenarios/clean-all.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:cenarios/data-test.sql")
@Sql(value = "classpath:cenarios/clean-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class UsuarioControllerTest {

    @Autowired
    private MockMvcHelper mockMvcHelper;

    private final String PATH = "/usuario";

    //---------------------------------------LISTAR - CAMINHO FELIZ------------------------------------------------------
    @Test
    public void deveRetornarOkQuandoBuscarPorUsuario() throws Exception {
        mockMvcHelper.get(PATH)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].email").value("carlos@email.com"))
                .andExpect(jsonPath("$.content[0].nome").value("Carlos Alexandre"))

                .andExpect(jsonPath("$.content[1].id").value(2))
                .andExpect(jsonPath("$.content[1].email").value("ruan@email.com"))
                .andExpect(jsonPath("$.content[1].nome").value("Ruan"));
    }

    @Test
    public void deveRetornarOkQuandoBuscarPorNomeDeUsuario() throws Exception{
        mockMvcHelper.get(PATH + "?nome=Carlos Alexandre")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].nome").value("Carlos Alexandre"));

    }
    //####################################### LISTAR - CAMINHO TRISTE #######################################################



    //---------------------------------------ATUALIZAR - CAMINHO FELIZ------------------------------------------------------
    @Test
    public void deveRetornarOkQuandoAtualizarUsuario() throws Exception{

        Long id = 1L;

        AtualizacaoUsuarioRequest atualizacaoUsuarioRequest = AtualizacaoUsuarioRequest.builder()
                .nome("Bocao")
                .build();

        mockMvcHelper.put(PATH, id, atualizacaoUsuarioRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Bocao"));
    }


    @Test
    public void deveRetornarOkQuandoAtualizarEmailUsuario() throws Exception{
        Long id = 1L;

        AtualizarEmailUsuarioRequest atualizarEmailUsuario = new AtualizarEmailUsuarioRequest();
                atualizarEmailUsuario.setEmail("umEmail@gmail.com");

        mockMvcHelper.put(PATH + "/email", id, atualizarEmailUsuario)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("umEmail@gmail.com"));
    }

    //####################################### ATUALIZAR - CAMINHO TRISTE #######################################################
    @Test
    public void deveRetornarNotFoundQuandoAtualizarUmIdInexistenteDeUsuario() throws Exception{
        Long id = -1L;

        AtualizacaoUsuarioRequest atualizacaoUsuarioRequest = AtualizacaoUsuarioRequest.builder()
                .nome("Guilhermos guilherminios")
                .build();

        mockMvcHelper.put(PATH, id, atualizacaoUsuarioRequest)
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveRetornarBadRequestQuandoAtualizarUmEmailJaExistenteDeUsuario() throws Exception{
        Long id = 1L;

        AtualizarEmailUsuarioRequest atualizarEmailUsuarioRequest = new AtualizarEmailUsuarioRequest();
            atualizarEmailUsuarioRequest.setEmail("carlos@email.com");

            mockMvcHelper.put(PATH + "/email", id, atualizarEmailUsuarioRequest)
                    .andExpect(status().isBadRequest());
    }


    //---------------------------------------CADASTRAR - CAMINHO FELIZ------------------------------------------------------
    @Test
    public void deveRetornarCreatedQuandoCadastrarUsuario() throws Exception {



        UsuarioRequest usuarioRequest = UsuarioRequest.builder()
                .enderecoCep("19804310")
                .email("carlosatheodorodamasceno@gmail.com")
                .senha(new BCryptPasswordEncoder().encode("123456"))
                .nome("Jorge Alexandre")
                .dataNascimento(Date.valueOf("2001-05-15"))
                .celular("1922222")
                .telefone("1880000")
                .status(StatusUsuario.DISPONIVEL)
                .build();

        mockMvcHelper.save(PATH, usuarioRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.nome").value("Jorge Alexandre"));
    }

    //####################################### CADASTRAR - CAMINHO TRISTE #######################################################
    @Test
    public void deveRetornarBadRequestQuandoNaoCadastrarEmailSenhaOuUsuario() throws Exception{
        UsuarioRequest usuarioRequest = UsuarioRequest.builder()
                .dataNascimento(Date.valueOf("2001-05-15"))
                .celular("1922222")
                .telefone("1880000")
                .status(StatusUsuario.DISPONIVEL)
                .build();

        mockMvcHelper.save(PATH, usuarioRequest)
                .andExpect(status().isBadRequest());
    }


}
