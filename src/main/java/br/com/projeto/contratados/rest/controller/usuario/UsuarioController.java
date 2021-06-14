package br.com.projeto.contratados.rest.controller.usuario;

import br.com.projeto.contratados.config.security.TokenService;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import br.com.projeto.contratados.domain.service.usuario.UsuarioService;
import br.com.projeto.contratados.rest.model.request.usuario.usuario.AtualizacaoUsuarioRequest;
import br.com.projeto.contratados.rest.model.request.usuario.usuario.AtualizarEmailUsuarioRequest;
import br.com.projeto.contratados.rest.model.request.usuario.usuario.AtualizarSenhaUsuarioRequest;
import br.com.projeto.contratados.rest.model.request.usuario.usuario.UsuarioRequest;
import br.com.projeto.contratados.rest.model.response.UsuarioResponse;
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
import java.io.IOException;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioResponse> cadastrar (@RequestBody @Valid UsuarioRequest form, UriComponentsBuilder uriComponentsBuilder) throws IOException {
        var usuario = usuarioService.cadastrar(form);

        var uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioResponse(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioResponse>> listar (@RequestParam (required = false) String nome,
                                                          @PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable paginacao){

        Page<Usuario> usuario = usuarioService.listar(nome, paginacao);
        return ResponseEntity.ok(UsuarioResponse.converter(usuario));
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioResponse> atualizar (@PathVariable Integer id, @RequestBody @Valid AtualizacaoUsuarioRequest form) throws IOException {
        var usuario = usuarioService.atualizar(id, form);

        return ResponseEntity.ok(new UsuarioResponse(usuario));
    }

    @PutMapping("/senha/{id}")
    @Transactional
    public ResponseEntity<UsuarioResponse> atualizarSenha (@PathVariable Integer id, @RequestBody @Valid AtualizarSenhaUsuarioRequest form) {
        var usuario = usuarioService.atualizarSenha(id, form);
        return ResponseEntity.ok(new UsuarioResponse(usuario));
    }

    @PutMapping("/email/{id}")
    @Transactional
    public ResponseEntity<UsuarioResponse> atualizarEmail (@PathVariable Integer id, @RequestBody @Valid AtualizarEmailUsuarioRequest form) {
        var usuario = usuarioService.atualizarEmail(id, form);
        return ResponseEntity.ok(new UsuarioResponse(usuario));
    }
}