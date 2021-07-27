package br.com.projeto.contratados.rest.controller.empresa;

import br.com.projeto.contratados.domain.entity.empresa.AnuncioVaga;
import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import br.com.projeto.contratados.domain.service.empresa.AnuncioVagaService;
import br.com.projeto.contratados.rest.model.request.empresa.anuncio_vaga.AnuncioVagaAtualizarRequest;
import br.com.projeto.contratados.rest.model.request.empresa.anuncio_vaga.AnuncioVagaAtualizarStatusRequest;
import br.com.projeto.contratados.rest.model.request.empresa.anuncio_vaga.AnuncioVagaRequest;
import br.com.projeto.contratados.rest.model.response.anuncioVaga.AnuncioVagaDetalhadoResponse;
import br.com.projeto.contratados.rest.model.response.anuncioVaga.AnuncioVagaResponse;
import br.com.projeto.contratados.rest.model.response.anuncioVaga.AnuncioVagaResumidoResponse;
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
@RequestMapping("/anunciovaga")
public class AnuncioVagaController {
    @Autowired
    private AnuncioVagaService anuncioVagaService;

    @PostMapping
    public ResponseEntity<AnuncioVagaDetalhadoResponse> cadastrar(@RequestBody @Valid AnuncioVagaRequest form, UriComponentsBuilder uriComponentsBuilder) throws IOException {
        var anuncioVaga = anuncioVagaService.cadastrar(form);

        var uri = uriComponentsBuilder.path("/anunciovaga/{id}").buildAndExpand(anuncioVaga.getId()).toUri();
        return ResponseEntity.created(uri).body(new AnuncioVagaDetalhadoResponse(anuncioVaga));
    }

    @GetMapping
    public ResponseEntity<Page<AnuncioVagaResponse>> listar(@PageableDefault(size = 10, page = 0, sort = "dataPostagem", direction = Sort.Direction.DESC) Pageable paginacao) {

        Page<AnuncioVaga> anuncioVaga = anuncioVagaService.listar(paginacao);
        return ResponseEntity.ok(AnuncioVagaResponse.converter(anuncioVaga));
    }

    @GetMapping("/usuariovagas")
    public ResponseEntity<Page<AnuncioVagaResumidoResponse>> listarResumida(
            @RequestParam (name = "localidade", required = false) String localidade,
            @RequestParam (name = "cargo", required = false) String cargo,
            @PageableDefault(size = 10, page = 0, sort = "dataPostagem", direction = Sort.Direction.DESC) Pageable paginacao) {

        Page<AnuncioVaga> anuncioVaga = anuncioVagaService.listarResumida(paginacao, localidade, cargo);
        return ResponseEntity.ok(AnuncioVagaResumidoResponse.converter(anuncioVaga));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnuncioVagaDetalhadoResponse> detalhado(@PathVariable Long id) {

        AnuncioVaga anuncioVaga = anuncioVagaService.detalhado(id);
        return ResponseEntity.ok(new AnuncioVagaDetalhadoResponse(anuncioVaga));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnuncioVagaResponse> atualizar(@PathVariable Long id, @RequestBody @Valid AnuncioVagaAtualizarRequest form) throws IOException {

        var anuncioVaga = anuncioVagaService.atualizar(id, form);
        return ResponseEntity.ok(new AnuncioVagaResponse(anuncioVaga));

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<AnuncioVagaResponse> atualizarStatus(@PathVariable Long id, @RequestBody @Valid AnuncioVagaAtualizarStatusRequest form){
        var anuncioVaga = anuncioVagaService.atualizarStatus(id, form);
        return ResponseEntity.ok(new AnuncioVagaResponse(anuncioVaga));
    }

}
