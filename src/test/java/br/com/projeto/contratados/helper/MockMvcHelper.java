package br.com.projeto.contratados.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@Component
@AllArgsConstructor
public class MockMvcHelper {


    private final ObjectMapper objectMapper;
    private final MockMvc mockMvc;


    public ResultActions save(String path, Object request) throws Exception {

        return mockMvc.perform(post(path)
                .locale(Constantes.PT_BR)
                .characterEncoding(Constantes.DEFAULT_ENCODING)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));
    }

    public ResultActions get(String path) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(path)
                .locale(Constantes.PT_BR)
                .characterEncoding(Constantes.DEFAULT_ENCODING)
                .contentType(MediaType.APPLICATION_JSON));
    }

    public ResultActions getWithParams(String path, MultiValueMap<String, String> queryParams) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(path)
                .queryParams(queryParams)
                .locale(Constantes.PT_BR)
                .characterEncoding(Constantes.DEFAULT_ENCODING)
                .contentType(MediaType.APPLICATION_JSON));
    }

    public ResultActions put(String path, Long id, Object request) throws Exception {
        String idConvertido = Objects.isNull(id) ? "" : id.toString();
        return mockMvc.perform(MockMvcRequestBuilders.put(path +"/"+ idConvertido)
                .locale(Constantes.PT_BR)
                .characterEncoding(Constantes.DEFAULT_ENCODING)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));
    }

//    public ResultActions delete(String path, UUID id) throws Exception {
//        return mockMvc.perform(MockMvcRequestBuilders.delete(path + "/" + id.toString())
//                .locale(Constantes.PT_BR)
//                .characterEncoding(Constantes.DEFAULT_ENCODING)
//                .contentType(MediaType.APPLICATION_JSON));
//    }

    public ResultActions delete(String path, Long id) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.delete(path + "/" + id.toString())
                .locale(Constantes.PT_BR)
                .characterEncoding(Constantes.DEFAULT_ENCODING)
                .contentType(MediaType.APPLICATION_JSON));
    }

    public ResultMatcher resultMatcherMensagemErro(String erro) {
        return MockMvcResultMatchers.jsonPath("$.message").value(erro);
    }

    public ResultMatcher resultMatcherMensagemErroDetalhada(String erro) {
        return MockMvcResultMatchers.jsonPath("$.detailedMessage").value(erro);
    }

    public ResultMatcher resultMatcherDetalhesErro(String erro) {
        return MockMvcResultMatchers.jsonPath("$.details[0].message").value(erro);
    }

    public ResultMatcher resultMatcherDetalhesErroDetalhado(String erro) {
        return MockMvcResultMatchers.jsonPath("$.details[0].detailedMessage").value(erro);
    }

    public <T> T getResponse(MvcResult result, Class<T> type) {
        try {
            return objectMapper.readValue(result.getResponse().getContentAsByteArray(), type);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível converter a response");
        }
    }

    public <T> T getResponse(MvcResult result, TypeReference<T> type) {
        try {
            return objectMapper.readValue(result.getResponse().getContentAsByteArray(), type);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível converter a response");
        }
    }

    public ResultActions patch(String path, UUID id, Object request) throws Exception {
        String idConvertido = Objects.isNull(id) ? "" : id.toString();
        return mockMvc.perform(MockMvcRequestBuilders.patch(path + "/" + idConvertido)
                .locale(Constantes.PT_BR)
                .characterEncoding(Constantes.DEFAULT_ENCODING)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));
    }
}
