package br.com.projeto.contratados.rest.controller.usuario;

import br.com.projeto.contratados.domain.service.usuario.FormacaoService;
import br.com.projeto.contratados.rest.model.response.FormacaoResponse;
import br.com.projeto.contratados.rest.model.request.usuario.formacao.AtualizacaoFormcaoRequest;
import br.com.projeto.contratados.rest.model.request.usuario.formacao.FormacaoRequest;
import br.com.projeto.contratados.domain.entity.usuario.Formacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/formacao")
public class FormacaoController {

    @Autowired
    private FormacaoService formacaoService;

    @PostMapping
    private ResponseEntity<FormacaoResponse> cadastrar(@RequestBody @Valid FormacaoRequest form, UriComponentsBuilder uriComponentsBuilder){
        Formacao formacao = formacaoService.cadastrar(form);

        URI uri = uriComponentsBuilder.path("/formacao/{id}").buildAndExpand(formacao.getId()).toUri();
        return ResponseEntity.created(uri).body(new FormacaoResponse(formacao));
    }

    @GetMapping()
    private ResponseEntity<Page<FormacaoResponse>> listar(@RequestParam (required = false) String descricao,
                                                          @PageableDefault(page = 0, size = 30, sort = "descricao", direction = Sort.Direction.ASC)Pageable paginacao){
        Page<Formacao> formacao = formacaoService.listar(descricao, paginacao);
        return ResponseEntity.ok(FormacaoResponse.converterFormacaoDto(formacao));

    }

    @PutMapping("/{id}")
    private ResponseEntity<FormacaoResponse> atualizar(@PathVariable Integer id, @RequestBody @Valid AtualizacaoFormcaoRequest form){

        Formacao formacao = formacaoService.atualizar(id, form);
        return ResponseEntity.ok(new FormacaoResponse(formacao));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<FormacaoResponse> deletar(@PathVariable Integer id){

        Formacao formacao = formacaoService.deletar(id);
        return ResponseEntity.ok(new FormacaoResponse(formacao));

    }

}
