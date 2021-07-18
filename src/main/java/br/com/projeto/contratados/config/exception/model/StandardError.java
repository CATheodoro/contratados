package br.com.projeto.contratados.config.exception.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer status;
    private String error;
    private Long timeStamp;

}
