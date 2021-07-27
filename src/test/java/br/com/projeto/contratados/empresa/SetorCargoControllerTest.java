package br.com.projeto.contratados.empresa;

import br.com.projeto.contratados.ContratadosApplicationTests;
import br.com.projeto.contratados.domain.entity.empresa.AnuncioVaga;
import br.com.projeto.contratados.helper.MockMvcHelper;
import br.com.projeto.contratados.rest.model.request.empresa.setor_cargo.SetorCargoAtualizarRequest;
import br.com.projeto.contratados.rest.model.request.empresa.setor_cargo.SetorCargoRequest;
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

public class SetorCargoControllerTest {
    @Autowired
    private MockMvcHelper mockMvcHelper;

    private final String PATH = "/setorcargo";

    //---------------------------------------LISTAR - CAMINHO FELIZ------------------------------------------------------
    @Test
    public void deveRetornarOkQuandoBuscarPorSetorCargo() throws Exception{
        mockMvcHelper.get(PATH)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].cargo").value("Desenvolvedor"))

                .andExpect(jsonPath("$.content[1].id").value(2))
                .andExpect(jsonPath("$.content[1].setor").value("Medicina"));
    }


    //####################################### LISTAR - CAMINHO TRISTE #######################################################


    //---------------------------------------ATUALIZAR - CAMINHO FELIZ------------------------------------------------------
    @Test
    public void deveRetornarOkQuandoAtualizarSetorCargo() throws  Exception{
        Long id = 1L;

        SetorCargoAtualizarRequest setorCargoAtualizarRequest = SetorCargoAtualizarRequest.builder()
                .cargo("Pedreiro")
                .setor("Industrial")
                .build();

        mockMvcHelper.put(PATH, id, setorCargoAtualizarRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cargo").value("Pedreiro"));
    }

    //####################################### ATUALIZAR - CAMINHO TRISTE #######################################################
    @Test
    public void deveRetornarNotFoundQuandoAtualizarUmIdInexistenteSetorCargo() throws  Exception{
        Long id = -1L;

        SetorCargoAtualizarRequest setorCargoAtualizarRequest = SetorCargoAtualizarRequest.builder()
                .cargo("Pedreiro")
                .setor("Industrial")
                .build();

        mockMvcHelper.put(PATH, id, setorCargoAtualizarRequest)
                .andExpect(status().isNotFound());
    }


    //---------------------------------------CADASTRAR - CAMINHO FELIZ------------------------------------------------------
    @Test
    public void deveRetornarCreatedQuandoCriarSetorCargo() throws  Exception{
        SetorCargoRequest setorCargoRequest = SetorCargoRequest.builder()
                .anuncioVagaId(1L)
                .cargo("Pedreiro")
                .setor("Industrial")
                .build();

        mockMvcHelper.save(PATH, setorCargoRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.cargo").value("Pedreiro"));
    }


    //####################################### CADASTRAR - CAMINHO TRISTE #######################################################
    @Test
    public void deveRetornarBadRequestQuandoCriarSemCargoSetorOuAnuncioVagaIDSetorCargo() throws  Exception{
        SetorCargoRequest setorCargoRequest = SetorCargoRequest.builder()
                .anuncioVagaId(1L)
                .cargo("Pedreiro")

                .build();

        mockMvcHelper.save(PATH, setorCargoRequest)
                .andExpect(status().isBadRequest());
    }


    //---------------------------------------DELETAR - CAMINHO FELIZ------------------------------------------------------
    @Test
    public void deveRetornarOkQuandoDeletarSetorCargo() throws Exception{
        Long id = 1L;
        mockMvcHelper.delete(PATH, id)
                .andExpect(status().isOk());
    }

    //####################################### DELETAR - CAMINHO TRISTE #######################################################
    @Test
    public void deveRetornarNotFoundQuandoDeletarIdInexistenteSetorCargo() throws Exception{
        Long id = -1L;
        mockMvcHelper.delete(PATH, id)
                .andExpect(status().isNotFound());
    }

}
