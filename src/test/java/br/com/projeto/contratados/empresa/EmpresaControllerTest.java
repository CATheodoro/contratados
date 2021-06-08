package br.com.projeto.contratados.empresa;

import br.com.projeto.contratados.helper.MockMvcHelper;
import br.com.projeto.contratados.rest.model.request.empresa.empresa.AtualizarEmailEmpresaRequest;
import br.com.projeto.contratados.rest.model.request.empresa.empresa.AtualizarEmpresaRequest;
import br.com.projeto.contratados.rest.model.request.empresa.empresa.EmpresaRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@AutoConfigureMockMvc
@Sql(value = "classpath:cenarios/clean-all.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:cenarios/data-test.sql")
@Sql(value = "classpath:cenarios/clean-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class EmpresaControllerTest {
    @Autowired
    private MockMvcHelper mockMvcHelper;

    private final String PATH = "/empresa";

    //---------------------------------------LISTAR - CAMINHO FELIZ------------------------------------------------------
    @Test
    public void deveRetornarOkQuandoBuscarPorEmpresa() throws Exception{
        mockMvcHelper.get(PATH)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[1].id").value(2))
                .andExpect(jsonPath("$.content[1].nomeFantasia").value("empresa"))

                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].nomeFantasia").value("TotTV"));
    }

    @Test
    public void deveRetornarOkQuandoBuscarPorNomeFantasiaDeEmpresa() throws Exception{
        mockMvcHelper.get(PATH + "?nomeFantasia=empresa")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(2))
                .andExpect(jsonPath("$.content[0].nomeFantasia").value("empresa"));
    }

    //####################################### LISTAR - CAMINHO TRISTE #######################################################



    //---------------------------------------ATUALIZAR - CAMINHO FELIZ------------------------------------------------------
    @Test
    public void deveRetornarOkQuandoAtualizarEmpresa() throws Exception{
        Long id = 1l;

        AtualizarEmpresaRequest atualizarEmpresaRequest = AtualizarEmpresaRequest.builder()
                .nomeFantasia("Yamaha")
                .descricao("Confia que é a melhor")
                .celular(123456)
                .telefone(7891011)
                .cnpj("12343523")
                .dataFundacao(Date.valueOf("2001-05-15"))
                .build();

        mockMvcHelper.put(PATH, id, atualizarEmpresaRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nomeFantasia").value("Yamaha"));
    }

    @Test
    public void deveRetornarOkQuandoAtualizarEmailEmpresaa() throws Exception{
        Long id = 1l;

        AtualizarEmailEmpresaRequest atualizarEmailEmpresaRequest = new AtualizarEmailEmpresaRequest();
        atualizarEmailEmpresaRequest.setEmail("um@emai.com");

        mockMvcHelper.put(PATH + "/email", id, atualizarEmailEmpresaRequest)
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("um@emai.com"));

    }

    //####################################### ATUALIZAR - CAMINHO TRISTE #######################################################

    @Test
    public void deveRetornarNotFoundQuandoAtualizarUmIdInexistentedeEmpresa() throws Exception{
        Long id = -1l;

        AtualizarEmpresaRequest atualizarEmpresaRequest = AtualizarEmpresaRequest.builder()
                .nomeFantasia("Yamaha")
                .descricao("Confia que é a melhor")
                .celular(123456)
                .telefone(7891011)
                .cnpj("12343523")
                .dataFundacao(Date.valueOf("2001-05-15"))
                .build();

        mockMvcHelper.put(PATH, id, atualizarEmpresaRequest)
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveRetornarBadRequestQuandoAtualizarUmEmailJaExistenteEmpresaa() throws Exception{
        Long id = 1l;

        AtualizarEmailEmpresaRequest atualizarEmailEmpresaRequest = new AtualizarEmailEmpresaRequest();
        atualizarEmailEmpresaRequest.setEmail("empresa@ouatlook.com");

        mockMvcHelper.put(PATH + "/email", id, atualizarEmailEmpresaRequest)
                .andExpect(status().isBadRequest());

    }

    //---------------------------------------CADASTRAR - CAMINHO FELIZ------------------------------------------------------
    @Test
    public void deveRetornarCreatedQuandoCadastrarEmpresa() throws Exception{

        EmpresaRequest empresaRequest = EmpresaRequest.builder()
                .nomeFantasia("Yamaha")
                .email("um@email.com")
                .senha("123")
                .descricao("Confia que é a melhor")
                .celular(123456)
                .telefone(7891011)
                .cnpj("12343523")
                .dataFundacao(Date.valueOf("2001-05-15"))
                .build();

        mockMvcHelper.save(PATH, empresaRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.nomeFantasia").value("Yamaha"));
    }

    //####################################### CADASTRAR - CAMINHO TRISTE #######################################################

    @Test
    public void deveRetornarBadRequestQuandoNaoInserirNomeFantasiaDescricaoOuSenha() throws Exception{

        EmpresaRequest empresaRequest = EmpresaRequest.builder()
                .nomeFantasia("Yamaha")
                .descricao("Confia que é a melhor")
                .celular(123456)
                .telefone(7891011)
                .cnpj("12343523")
                .dataFundacao(Date.valueOf("2001-05-15"))
                .build();

        mockMvcHelper.save(PATH, empresaRequest)
                .andExpect(status().isBadRequest());
    }
}
