//package br.com.projeto.contratados.usuario;
//
//import br.com.projeto.contratados.ContratadosApplicationTests;
//import br.com.projeto.contratados.domain.entity.usuario.Formacao;
//import br.com.projeto.contratados.domain.entity.usuario.Usuario;
//import br.com.projeto.contratados.domain.service.usuario.FormacaoService;
//import br.com.projeto.contratados.helper.MockMvcHelper;
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
//import java.util.UUID;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@SpringBootTest
//@ActiveProfiles("test")
//@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
//@AutoConfigureMockMvc(addFilters = false)
//@Sql(value = "classpath:cenarios/clean-all.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = "classpath:cenarios/data-test.sql")
//@Sql(value = "classpath:cenarios/clean-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//
//public class FormacaoControllerTest {
//
//    @Autowired
//    private MockMvcHelper mockMvcHelper;
//
//    private final String PATH = "/formacao";
//
//    //---------------------------------------LISTAR - CAMINHO FELIZ------------------------------------------------------
//    @Test
//    public void deveRetornarOkQuandoBuscarPorFormacao() throws Exception{
//        mockMvcHelper.get(PATH)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content[0].id").value(1))
//                .andExpect(jsonPath("$.content[0].descricao").value("FEMA"))
//
//                .andExpect(jsonPath("$.content[1].id").value(2))
//                .andExpect(jsonPath("$.content[1].descricao").value("SPRING BOOT"));
//    }
//
//    @Test
//    public void deveRetornarOkQuandoBuscarPorDescricaoDaFormacao() throws Exception{
//        mockMvcHelper.get(PATH +"?descricao=SPRING BOOT")
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content[0].descricao").value("SPRING BOOT"));
//    }
//
//    //####################################### LISTAR - CAMINHO TRISTE #######################################################
//
//
//
//    //---------------------------------------ATUALIZAR - CAMINHO FELIZ------------------------------------------------------
//
//    @Test
//    public void deveRetornarOkQuandoAtualizarFormacao() throws Exception{
//        Long id = 1L;
//
//        AtualizacaoFormcaoRequest atualizacaoFormcaoRequest = AtualizacaoFormcaoRequest.builder()
//                .descricao("Fundação Educacional Municipal de Assis")
//                .inicio(Date.valueOf("1999-01-15"))
//                .build();
//
//        mockMvcHelper.put(PATH, id, atualizacaoFormcaoRequest)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.descricao").value("Fundação Educacional Municipal de Assis"));
//    }
//
//    //####################################### ATUALIZAR - CAMINHO TRISTE #######################################################
//
//    @Test
//    public void deveRetornarNotFoundQuandoAtualizarUmIdInexistenteDeFormacao() throws Exception {
//        Long id = -1L;
//
//        AtualizacaoFormcaoRequest atualizacaoFormcaoRequest = AtualizacaoFormcaoRequest.builder()
//                .descricao("Fundação Educacional Municipal de Assis")
//                .inicio(Date.valueOf("1999-01-15"))
//                .termino(Date.valueOf("2001-05-15"))
//                .build();
//
//        mockMvcHelper.put(PATH, id, atualizacaoFormcaoRequest)
//                .andExpect(status().isNotFound());
//    }
//
//    //---------------------------------------CADASTRAR - CAMINHO FELIZ------------------------------------------------------
//    @Test
//    public void deveRetornarCreatedQuandoCadastrarUmaFormacao() throws Exception{
//        FormacaoRequest formacaoRequest = FormacaoRequest.builder()
//                .descricao("Curso C#")
//                .inicio(Date.valueOf("1999-01-15"))
//                .termino(Date.valueOf("2001-05-15"))
//                .usuario(Usuario.builder().id(1).build())
//                .build();
//
//        mockMvcHelper.save(PATH, formacaoRequest)
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(3))
//                .andExpect(jsonPath("$.descricao").value("Curso C#"));
//    }
//
//    //####################################### CADASTRAR - CAMINHO TRISTE #######################################################
//    @Test
//    public void deveRetornarBadRequestQuandoNaoCadastrarDescricaoInicioTerminoOuUsuario() throws Exception{
//        FormacaoRequest formacaoRequest = FormacaoRequest.builder()
//                .descricao("ata")
//                .build();
//        mockMvcHelper.save(PATH, formacaoRequest)
//                .andExpect(status().isBadRequest());
//    }
//
//    //---------------------------------------DELETAR - CAMINHO FELIZ------------------------------------------------------
//    @Test
//    public void deveRetornarOkQuandoDeletarFormacao() throws Exception{
//        Integer id = 1;
//        mockMvcHelper.delete(PATH, id)
//                .andExpect(status().isOk());
//    }
//
//    //####################################### DELETAR - CAMINHO TRISTE #######################################################
//    @Test
//    public void deveRetornarNotFoundQuandoDeletarUmIdInexistendeDeFormacao() throws Exception{
//        Integer id = -1;
//        mockMvcHelper.delete(PATH, id)
//                .andExpect(status().isNotFound());
//    }
//}
