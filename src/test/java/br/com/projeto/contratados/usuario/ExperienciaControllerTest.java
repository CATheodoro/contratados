//package br.com.projeto.contratados.usuario;
//
//
//import br.com.projeto.contratados.ContratadosApplicationTests;
//import br.com.projeto.contratados.domain.entity.usuario.Experiencia;
//import br.com.projeto.contratados.domain.entity.usuario.Usuario;
//import br.com.projeto.contratados.helper.MockMvcHelper;
//import br.com.projeto.contratados.rest.model.request.usuario.experiencia.AtualizacaoExperienciaRequest;
//import br.com.projeto.contratados.rest.model.request.usuario.experiencia.ExperienciaRequest;
//import br.com.projeto.contratados.rest.model.request.usuario.formacao.AtualizacaoFormcaoRequest;
//import br.com.projeto.contratados.rest.model.request.usuario.formacao.FormacaoRequest;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.jdbc.Sql;
//
//import java.sql.Date;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@ActiveProfiles("test")
//@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
//@AutoConfigureMockMvc(addFilters = false)
//@Sql(value = "classpath:cenarios/clean-all.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = "classpath:cenarios/data-test.sql")
//@Sql(value = "classpath:cenarios/clean-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//
//
//public class ExperienciaControllerTest {
//    @Autowired
//    private MockMvcHelper mockMvcHelper;
//
//    private final String PATH = "/experiencia";
//
//    //---------------------------------------LISTAR - CAMINHO FELIZ------------------------------------------------------
//    @Test
//    public void deveRetornarOkQuandoBuscarPorExperiencia() throws Exception{
//        mockMvcHelper.get(PATH)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content[1].id").value(1))
//                .andExpect(jsonPath("$.content[1].descricao").value("CEU"))
//
//                .andExpect(jsonPath("$.content[0].id").value(2))
//                .andExpect(jsonPath("$.content[0].descricao").value("ALGUM LUGAR"));
//    }
//
//
//    //####################################### LISTAR - CAMINHO TRISTE #######################################################
//
//
//
//    //---------------------------------------ATUALIZAR - CAMINHO FELIZ------------------------------------------------------
//
//    @Test
//    public void deveRetornarOkQuandoAtualizarExperiencia() throws Exception{
//        Long id = 1L;
//
//        AtualizacaoExperienciaRequest atualizacaoExperienciaRequest = AtualizacaoExperienciaRequest.builder()
//                .descricao("Centro de Artes e Esportes Unificados")
//                .inicio(Date.valueOf("1999-01-15"))
//                .build();
//
//        mockMvcHelper.put(PATH, id, atualizacaoExperienciaRequest)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.descricao").value("Centro de Artes e Esportes Unificados"));
//    }
//
//    //####################################### ATUALIZAR - CAMINHO TRISTE #######################################################
//
//    @Test
//    public void deveRetornarNotFoundQuandoAtualizarUmIdInexistenteDeExperiencia() throws Exception {
//        Long id = -1L;
//
//        AtualizacaoExperienciaRequest atualizacaoExperienciaRequest = AtualizacaoExperienciaRequest.builder()
//                .descricao("Centro de Artes e Esportes Unificados")
//                .inicio(Date.valueOf("1999-01-15"))
//                .termino(Date.valueOf("2001-05-15"))
//                .build();
//
//        mockMvcHelper.put(PATH, id, atualizacaoExperienciaRequest)
//                .andExpect(status().isNotFound());
//    }
//
//    //---------------------------------------CADASTRAR - CAMINHO FELIZ------------------------------------------------------
//    @Test
//    public void deveRetornarCreatedQuandoCadastrarUmaExperiencia() throws Exception{
//        ExperienciaRequest experienciaRequest = ExperienciaRequest.builder()
//                .descricao("Desenvolvedor Java")
//                .inicio(Date.valueOf("1999-01-15"))
//                .termino(Date.valueOf("2001-05-15"))
//                .usuario(Usuario.builder().id(1).build())
//                .build();
//
//        mockMvcHelper.save(PATH, experienciaRequest)
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(3))
//                .andExpect(jsonPath("$.descricao").value("Desenvolvedor Java"));
//    }
//
//    //####################################### CADASTRAR - CAMINHO TRISTE #######################################################
//    @Test
//    public void deveRetornarBadRequestQuandoNaoCadastrarDescricaoInicioTerminoOuUsuario() throws Exception{
//        ExperienciaRequest experienciaRequest = ExperienciaRequest.builder()
//                .descricao("ata")
//                .build();
//        mockMvcHelper.save(PATH, experienciaRequest)
//                .andExpect(status().isBadRequest());
//    }
//
//    //---------------------------------------DELETAR - CAMINHO FELIZ------------------------------------------------------
//    @Test
//    public void deveRetornarOkQuandoDeletarExperiencia() throws Exception{
//        Integer id = 1;
//        mockMvcHelper.delete(PATH, id)
//                .andExpect(status().isOk());
//    }
//
//    //####################################### DELETAR - CAMINHO TRISTE #######################################################
//    @Test
//    public void deveRetornarNotFoundQuandoDeletarUmIdInexistendeDeExperiencia() throws Exception{
//        Integer id = -1;
//        mockMvcHelper.delete(PATH, id)
//                .andExpect(status().isNotFound());
//    }
//
//}
