package br.com.projeto.contratados.rest.controller.empresa;

import br.com.projeto.contratados.domain.service.empresa.EmpresaService;
import br.com.projeto.contratados.rest.model.request.empresa.empresa.AtualizarEmailEmpresaRequest;
import br.com.projeto.contratados.rest.model.request.empresa.empresa.AtualizarSenhaEmpresaRequest;
import br.com.projeto.contratados.rest.model.response.EmpresaResponse;
import br.com.projeto.contratados.rest.model.request.empresa.empresa.AtualizarEmpresaRequest;
import br.com.projeto.contratados.rest.model.request.empresa.empresa.EmpresaRequest;
import br.com.projeto.contratados.domain.entity.empresa.Empresa;
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
import java.net.URI;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    @Transactional
    private ResponseEntity<EmpresaResponse> cadastrar(@RequestBody @Valid EmpresaRequest form, UriComponentsBuilder uriComponentsBuilder) {

        Empresa empresa = empresaService.cadastrar(form);

        URI uri = uriComponentsBuilder.path("/empresa/{id}").buildAndExpand(empresa.getId()).toUri();
        return ResponseEntity.created(uri).body(new EmpresaResponse(empresa));
    }

    @GetMapping
    private ResponseEntity<Page<EmpresaResponse>> listar(@RequestParam(required = false) String nomeFantasia,
                                                                @PageableDefault(page = 0, size = 10, sort = "nomeFantasia", direction = Sort.Direction.ASC) Pageable paginacao) {
        Page<Empresa> empresa = empresaService.listar(nomeFantasia, paginacao);
        return ResponseEntity.ok(EmpresaResponse.converterEmpresaDto(empresa));
    }

    @PutMapping("/{id}")
    @Transactional
    private ResponseEntity<EmpresaResponse> atualizar(@PathVariable Integer id, @RequestBody @Valid AtualizarEmpresaRequest form){
        Empresa empresa = empresaService.atualizar(id, form);
        return ResponseEntity.ok(new EmpresaResponse(empresa));
    }

    @PutMapping("/email/{id}")
    private ResponseEntity<EmpresaResponse> atualizarEmail(@PathVariable Integer id, @RequestBody @Valid AtualizarEmailEmpresaRequest form){
        Empresa empresa = empresaService.atualizarEmail(id, form);
        return ResponseEntity.ok().body(new EmpresaResponse(empresa));
    }

    @PutMapping("/senha/{id}")
    private ResponseEntity<EmpresaResponse> atualizarSenha(@PathVariable Integer id, @RequestBody @Valid AtualizarSenhaEmpresaRequest form){
        Empresa empresa = empresaService.atualizarSenha(id, form);
        return ResponseEntity.ok().body(new  EmpresaResponse(empresa));
    }
}