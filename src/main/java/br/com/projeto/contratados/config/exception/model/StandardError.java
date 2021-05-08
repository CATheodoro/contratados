package br.com.projeto.contratados.config.exception.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer status;
    private String message;
    private Long timeStamp;

    public StandardError(Integer status, String message, Long timeStamp) {
        super();
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }



}
