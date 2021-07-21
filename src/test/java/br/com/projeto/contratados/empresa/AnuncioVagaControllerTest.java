package br.com.projeto.contratados.empresa;

import br.com.projeto.contratados.ContratadosApplicationTests;
import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import br.com.projeto.contratados.domain.entity.empresa.EmpresaBuilder;
import br.com.projeto.contratados.helper.MockMvcHelper;
import br.com.projeto.contratados.rest.model.request.empresa.anuncio_vaga.AnuncioVagaAtualizarRequest;
import br.com.projeto.contratados.rest.model.request.empresa.anuncio_vaga.AnuncioVagaAtualizarStatusRequest;
import br.com.projeto.contratados.rest.model.request.empresa.anuncio_vaga.AnuncioVagaRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Time;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@AutoConfigureMockMvc(addFilters = false)
@Sql(value = "classpath:cenarios/clean-all.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:cenarios/data-test.sql")
@Sql(value = "classpath:cenarios/clean-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class AnuncioVagaControllerTest {
    @Autowired
    private MockMvcHelper mockMvcHelper;

    private final String PATH = "/anunciovaga";


    //---------------------------------------LISTAR - CAMINHO FELIZ------------------------------------------------------
    @Test
    public void deveRetornarOkQuandoBuscarAnuncioVaga() throws Exception {
        mockMvcHelper.get(PATH)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].requisitos").value("Saber Java"))

                .andExpect(jsonPath("$.content[1].id").value(2))
                .andExpect(jsonPath("$.content[1].requisitos").value("Saber C#, SPRING BOOT e BOOTSTRAP"));
    }

    //####################################### LISTAR - CAMINHO TRISTE #######################################################


    //---------------------------------------ATUALIZAR - CAMINHO FELIZ------------------------------------------------------
    @Test
    public void deveRetornarOkQuandoAtualizarAnuncioVaga() throws Exception {
        Long id = 1L;

        AnuncioVagaAtualizarRequest anuncioVagaAtualizarRequest = AnuncioVagaAtualizarRequest.builder()
                .enderecoCep("19804310")
                .cargaHoraria(Time.valueOf("8:50:00"))
                .requisitos("Atualizado em Java EE")
                .salario(9099.99F)
                .build();

        mockMvcHelper.put(PATH, id, anuncioVagaAtualizarRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.requisitos").value("Atualizado em Java EE"));
    }


    @Test
    public void deveRetornarOkQuandoAtualizarStatusAnuncioVaga() throws Exception{
        long id = 1L;

        AnuncioVagaAtualizarStatusRequest anuncioVagaAtualizarStatusRequest = new AnuncioVagaAtualizarStatusRequest();
        anuncioVagaAtualizarStatusRequest.setStatusAnuncio(false);

        mockMvcHelper.put(PATH + "/status", id, anuncioVagaAtualizarStatusRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.statusAnuncio").value(false));
    }

    //####################################### ATUALIZAR - CAMINHO TRISTE #######################################################
    @Test
    public void deveRetornarNotFoundQuandoAtualizarUmIdInexistente() throws Exception{
        long id = -1L;

        AnuncioVagaAtualizarRequest anuncioVagaAtualizarRequest = AnuncioVagaAtualizarRequest.builder()
                .enderecoCep("19804310")
                .cargaHoraria(Time.valueOf("8:50:00"))
                .requisitos("Atualizado em Java EE")
                .salario(9099.99F)
                .build();

        mockMvcHelper.put(PATH, id, anuncioVagaAtualizarRequest)
                .andExpect(status().isNotFound());
    }

    //---------------------------------------CADASTRAR - CAMINHO FELIZ------------------------------------------------------
    @Test
    public void deveRetornarCreatedQuandoCriarUmAnuncioVaga() throws  Exception{

        AnuncioVagaRequest anuncioVagaRequest = AnuncioVagaRequest.builder()
                .enderecoCep("19804310")
                .cargaHoraria(Time.valueOf("8:50:00"))
                .requisitos("Criado em Java EE")
                .salario(9099.99F)
                .build();

        mockMvcHelper.save(PATH, anuncioVagaRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.requisitos").value("Criado em Java EE"));

    }
    //####################################### CADASTRAR - CAMINHO TRISTE #######################################################
    @Test
    public void deveRetornarBadRequestQuandoNaoEspecificarEmpresaEnderecoCepCargaHorariaOuRequesitos() throws  Exception{

        AnuncioVagaRequest anuncioVagaRequest = AnuncioVagaRequest.builder()
                .cargaHoraria(Time.valueOf("8:50:00"))
                .requisitos("Criado em Java EE")
                .salario(9099.99F)
                .build();

        mockMvcHelper.save(PATH, anuncioVagaRequest)
                .andExpect(status().isBadRequest());

    }
}
