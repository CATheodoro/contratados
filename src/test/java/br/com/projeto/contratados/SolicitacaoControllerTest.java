package br.com.projeto.contratados;

import br.com.projeto.contratados.domain.entity.solicitacao.SolicitacaoEmpresaStatus;
import br.com.projeto.contratados.helper.MockMvcHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@AutoConfigureMockMvc(addFilters = false)
@Sql(value = "classpath:cenarios/clean-all.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:cenarios/data-test.sql")
@Sql(value = "classpath:cenarios/clean-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)


public class SolicitacaoControllerTest {

    @Autowired
    private MockMvcHelper mockMvcHelper;

    private final String PATH = "/solicitacao";


    //---------------------------------------LISTAR - CAMINHO FELIZ------------------------------------------------------
    @Test
    public void deveRetornarOkQuandoBuscarPorSolicitacao() throws Exception{
        mockMvcHelper.get(PATH)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[3].id").value(1))
                .andExpect(jsonPath("$.content[3].solicitacaoEmpresaStatus").value("PENDENTE"))
                .andExpect(jsonPath("$.content[3].solicitacaoUsuarioStatus").value("PENDENTE"))

                .andExpect(jsonPath("$.content[2].id").value(2))
                .andExpect(jsonPath("$.content[2].solicitacaoEmpresaStatus").value("ACEITO"))
                .andExpect(jsonPath("$.content[2].solicitacaoUsuarioStatus").value("ACEITO"))

                .andExpect(jsonPath("$.content[1].id").value(3))
                .andExpect(jsonPath("$.content[1].solicitacaoEmpresaStatus").value("RECUSADO"))
                .andExpect(jsonPath("$.content[1].solicitacaoUsuarioStatus").value("PENDENTE"))

                .andExpect(jsonPath("$.content[0].id").value(4))
                .andExpect(jsonPath("$.content[0].solicitacaoEmpresaStatus").value("ACEITO"))
                .andExpect(jsonPath("$.content[0].solicitacaoUsuarioStatus").value("CANCELADO"));
    }
    //####################################### LISTAR - CAMINHO TRISTE #######################################################



    //---------------------------------------ATUALIZAR - CAMINHO FELIZ------------------------------------------------------



    //####################################### ATUALIZAR - CAMINHO TRISTE #######################################################


    //---------------------------------------CADASTRAR - CAMINHO FELIZ------------------------------------------------------

    //####################################### CADASTRAR - CAMINHO TRISTE #######################################################
}
