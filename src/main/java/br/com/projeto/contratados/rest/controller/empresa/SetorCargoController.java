package br.com.projeto.contratados.rest.controller.empresa;


import br.com.projeto.contratados.domain.entity.empresa.SetorCargo;
import br.com.projeto.contratados.domain.service.empresa.SetorCargoService;
import br.com.projeto.contratados.rest.model.request.empresa.setor_cargo.SetorCargoAtualizarRequest;
import br.com.projeto.contratados.rest.model.request.empresa.setor_cargo.SetorCargoRequest;
import br.com.projeto.contratados.rest.model.response.SetorCargoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/setorcargo")
public class SetorCargoController {

    @Autowired
    private SetorCargoService setorCargoService;

    @PostMapping
    public ResponseEntity<SetorCargoResponse> cadastrar (@RequestBody @Valid SetorCargoRequest form, UriComponentsBuilder uriComponentsBuilder){
        var setorCargo = setorCargoService.cadastrar(form);

        var uri = uriComponentsBuilder.path("/setorcargo/{id}").buildAndExpand(setorCargo.getId()).toUri();
        return ResponseEntity.created(uri).body(new SetorCargoResponse(setorCargo));
    }

    @GetMapping
    public ResponseEntity<Page<SetorCargoResponse>> listar (@PageableDefault(size = 20, page = 0) Pageable paginacao){

        Page<SetorCargo> setorCargo = setorCargoService.listar(paginacao);
        return ResponseEntity.ok().body(SetorCargoResponse.converter(setorCargo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SetorCargoResponse> atualizar (@PathVariable Long id, @RequestBody @Valid SetorCargoAtualizarRequest form){
        var setorCargo = setorCargoService.atualizar(id, form);

        return ResponseEntity.ok().body(new SetorCargoResponse(setorCargo));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SetorCargoResponse> deletar (@PathVariable Long id){

        var setorCargo = setorCargoService.deletar(id);
        return ResponseEntity.ok().body(new SetorCargoResponse(setorCargo));

    }
}
