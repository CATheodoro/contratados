package br.com.projeto.contratados.config.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ErroFormularioResponse {
    private final String campo;
    private final String error;
    private final int status;
}
