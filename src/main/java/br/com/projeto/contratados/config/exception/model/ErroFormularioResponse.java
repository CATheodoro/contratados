package br.com.projeto.contratados.config.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErroFormularioResponse {
    private String campo;
    private String erro;
}
