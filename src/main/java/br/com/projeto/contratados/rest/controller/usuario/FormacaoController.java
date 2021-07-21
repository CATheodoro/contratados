package br.com.projeto.contratados.rest.controller.usuario;

import br.com.projeto.contratados.domain.entity.usuario.Formacao;
import br.com.projeto.contratados.domain.service.usuario.FormacaoService;
import br.com.projeto.contratados.rest.model.request.usuario.formacao.AtualizacaoFormcaoRequest;
import br.com.projeto.contratados.rest.model.request.usuario.formacao.FormacaoRequest;
import br.com.projeto.contratados.rest.model.response.FormacaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/formacao")
public class FormacaoController {

    @Autowired
    private FormacaoService formacaoService;

    @PostMapping
    public ResponseEntity<FormacaoResponse> cadastrar(@RequestBody @Valid FormacaoRequest form, UriComponentsBuilder uriComponentsBuilder){
        var formacao = formacaoService.cadastrar(form);

        var uri = uriComponentsBuilder.path("/formacao/{id}").buildAndExpand(formacao.getId()).toUri();
        return ResponseEntity.created(uri).body(new FormacaoResponse(formacao));
    }

    @GetMapping()
    public ResponseEntity<Page<FormacaoResponse>> listar(@RequestParam (required = false) String descricao,
                                                          @PageableDefault(page = 0, size = 30, sort = "descricao", direction = Sort.Direction.ASC)Pageable paginacao){
        Page<Formacao> formacao = formacaoService.listar(descricao, paginacao);
        return ResponseEntity.ok(FormacaoResponse.converter(formacao));

    }

    @PutMapping("/{id}")
    public ResponseEntity<FormacaoResponse> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoFormcaoRequest form){

        var formacao = formacaoService.atualizar(id, form);
        return ResponseEntity.ok(new FormacaoResponse(formacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FormacaoResponse> deletar(@PathVariable Long id){

        var formacao = formacaoService.deletar(id);
        return ResponseEntity.ok(new FormacaoResponse(formacao));

    }

}
