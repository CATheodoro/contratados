package br.com.projeto.contratados.rest.controller.usuario;

import br.com.projeto.contratados.domain.entity.usuario.Experiencia;
import br.com.projeto.contratados.domain.service.usuario.ExperienciaService;
import br.com.projeto.contratados.rest.model.request.usuario.experiencia.AtualizacaoExperienciaRequest;
import br.com.projeto.contratados.rest.model.request.usuario.experiencia.ExperienciaRequest;
import br.com.projeto.contratados.rest.model.response.ExperienciaResponse;
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
@RequestMapping("/experiencia")
public class ExperienciaController {

    @Autowired
    private ExperienciaService experienciaService;

    @PostMapping
    public ResponseEntity<ExperienciaResponse> cadastrar(@RequestBody @Valid ExperienciaRequest form, UriComponentsBuilder uriComponentsBuilder){

        var experiencia = experienciaService.cadastrar(form);

        var uri = uriComponentsBuilder.path("/experiencia/{id}").buildAndExpand(experiencia.getId()).toUri();
        return ResponseEntity.created(uri).body(new ExperienciaResponse(experiencia));
    }

    @GetMapping
    public ResponseEntity<Page<ExperienciaResponse>> listar(@PathVariable(required = false) String descricao ,
                                                                        @PageableDefault(page = 0, size = 30, sort = "descricao",direction = Sort.Direction.ASC)Pageable paginacao){
        Page<Experiencia> experiencia = experienciaService.listar(descricao, paginacao);
        return ResponseEntity.ok(ExperienciaResponse.converter(experiencia));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperienciaResponse> atualizar(@PathVariable Long id, @RequestBody AtualizacaoExperienciaRequest form){
        var experiencia = experienciaService.atualizar(id, form);

        return ResponseEntity.ok(new ExperienciaResponse(experiencia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExperienciaResponse> deletar(@PathVariable Long id) {

        var experiencia = experienciaService.deletar(id);
        return ResponseEntity.ok(new ExperienciaResponse(experiencia));
    }

}
