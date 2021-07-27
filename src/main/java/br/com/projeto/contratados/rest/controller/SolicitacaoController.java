package br.com.projeto.contratados.rest.controller;

import br.com.projeto.contratados.domain.entity.solicitacao.Solicitacao;
import br.com.projeto.contratados.domain.entity.solicitacao.SolicitacaoEmpresaStatus;
import br.com.projeto.contratados.domain.service.SolicitacaoService;
import br.com.projeto.contratados.rest.model.request.solicitacao.SolicitacaoAtualizarEmpresaRequest;
import br.com.projeto.contratados.rest.model.request.solicitacao.SolicitacaoEmpresaStatusRequest;
import br.com.projeto.contratados.rest.model.request.solicitacao.SolicitacaoRequest;
import br.com.projeto.contratados.rest.model.request.solicitacao.SolicitacaoUsuarioRequest;
import br.com.projeto.contratados.rest.model.response.SolicitacaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @PostMapping
    public ResponseEntity<SolicitacaoResponse> cadastrar(@RequestBody @Valid SolicitacaoRequest form, UriComponentsBuilder uriComponentsBuilder){
        var solicitacao = solicitacaoService.cadastrar(form);

        var uri = uriComponentsBuilder.path("/solicitacao/{id}").buildAndExpand(solicitacao.getId()).toUri();
        return ResponseEntity.created(uri).body(new SolicitacaoResponse(solicitacao));
    }

    @GetMapping
    public ResponseEntity<Page<SolicitacaoResponse>> listar(@RequestParam (name = "status",required = false) SolicitacaoEmpresaStatus status,
                                                            @RequestParam (name = "anuncioId",required = false) Long anuncioId,
                                                            @PageableDefault(size = 10,page = 0,sort = "dataCriacaoSolicitacao", direction = Sort.Direction.DESC)Pageable paginacao){
        Page<Solicitacao> solicitacao = solicitacaoService.listar(status, anuncioId, paginacao);
        return ResponseEntity.ok().body(SolicitacaoResponse.converter(solicitacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoResponse> getSolicitacao(@PathVariable Long id){
        Solicitacao solicitacao = solicitacaoService.getSolicitacao(id);
        return ResponseEntity.ok().body(new SolicitacaoResponse(solicitacao));
    }

    @PutMapping("/empresaatualizar/{id}")
    public ResponseEntity<SolicitacaoResponse> atualizarSolicitacaoEmpresa(@PathVariable Long id, @RequestBody @Valid SolicitacaoAtualizarEmpresaRequest form) throws IOException {

        var solicitacao = solicitacaoService.atualizarSolicitacaoEmpresa(id, form);
        return ResponseEntity.ok().body(new SolicitacaoResponse(solicitacao));
    }

    @PutMapping("/empresa/{id}")
    public ResponseEntity<SolicitacaoResponse> solicitacaoEmpresa(@PathVariable Long id, @RequestBody @Valid SolicitacaoEmpresaStatusRequest form) throws IOException {
        var solicitacao = solicitacaoService.solicitacaoEmpresa(id ,form);

        return ResponseEntity.ok().body(new SolicitacaoResponse(solicitacao));
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<SolicitacaoResponse> solicitacaoUsuario(@PathVariable Long id, @RequestBody @Valid SolicitacaoUsuarioRequest form) {
        var solicitacao = solicitacaoService.solicitacaoUsuario(id ,form);

        return ResponseEntity.ok().body(new SolicitacaoResponse(solicitacao));
    }
}