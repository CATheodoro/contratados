package br.com.projeto.contratados.config.exception.handler;

import br.com.projeto.contratados.config.exception.model.ErroFormularioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroValidacaoHandler {


    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroFormularioResponse> handle(MethodArgumentNotValidException exception){
        List<ErroFormularioResponse> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getFieldErrors();
        fieldErrors.forEach(e ->{
            String menssagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroFormularioResponse erro = new ErroFormularioResponse(e.getField(), menssagem, HttpStatus.BAD_REQUEST.value());
            dto.add(erro);
        });
        return dto;
    }
}