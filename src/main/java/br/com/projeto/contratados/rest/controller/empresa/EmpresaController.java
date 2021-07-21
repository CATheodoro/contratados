package br.com.projeto.contratados.rest.controller.empresa;

import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import br.com.projeto.contratados.domain.service.empresa.EmpresaService;
import br.com.projeto.contratados.rest.model.request.empresa.empresa.AtualizarEmailEmpresaRequest;
import br.com.projeto.contratados.rest.model.request.empresa.empresa.AtualizarEmpresaRequest;
import br.com.projeto.contratados.rest.model.request.empresa.empresa.AtualizarSenhaEmpresaRequest;
import br.com.projeto.contratados.rest.model.request.empresa.empresa.EmpresaRequest;
import br.com.projeto.contratados.rest.model.response.empresa.EmpresaResponse;
import br.com.projeto.contratados.rest.model.response.empresa.EmpresaResumidoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    @Transactional
    public ResponseEntity<EmpresaResponse> cadastrar(@RequestBody @Valid EmpresaRequest form, UriComponentsBuilder uriComponentsBuilder) {

        var empresa = empresaService.cadastrar(form);

        var uri = uriComponentsBuilder.path("/empresa/{id}").buildAndExpand(empresa.getId()).toUri();
        return ResponseEntity.created(uri).body(new EmpresaResponse(empresa));
    }

    @GetMapping
    public ResponseEntity<Page<EmpresaResponse>> listar(@RequestParam(required = false) String nome,
                                                                @PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable paginacao) {
        Page<Empresa> empresa = empresaService.listar(nome, paginacao);
        return ResponseEntity.ok(EmpresaResponse.converterEmpresaDto(empresa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResumidoResponse> perfilEmpresa(@PathVariable Long id) {
        Empresa empresa = empresaService.perfilEmpresa(id);
        return ResponseEntity.ok(new EmpresaResumidoResponse(empresa));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EmpresaResponse> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarEmpresaRequest form){
        var empresa = empresaService.atualizar(id, form);
        return ResponseEntity.ok(new EmpresaResponse(empresa));
    }

    @PutMapping("/email/{id}")
    public ResponseEntity<EmpresaResponse> atualizarEmail(@PathVariable Long id, @RequestBody @Valid AtualizarEmailEmpresaRequest form){
        var empresa = empresaService.atualizarEmail(id, form);
        return ResponseEntity.ok().body(new EmpresaResponse(empresa));
    }

    @PutMapping("/senha/{id}")
    public ResponseEntity<EmpresaResponse> atualizarSenha(@PathVariable Long id, @RequestBody @Valid AtualizarSenhaEmpresaRequest form){
        var empresa = empresaService.atualizarSenha(id, form);
        return ResponseEntity.ok().body(new  EmpresaResponse(empresa));
    }

}