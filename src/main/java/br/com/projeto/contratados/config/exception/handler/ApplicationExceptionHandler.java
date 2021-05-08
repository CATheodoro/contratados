package br.com.projeto.contratados.config.exception.handler;

import br.com.projeto.contratados.config.exception.*;
import br.com.projeto.contratados.config.exception.model.StandardError;
import br.com.projeto.contratados.domain.entity.usuario.Experiencia;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(EmailJaCadastrado.class)
    public ResponseEntity<StandardError> emailJaCadastrado(EmailJaCadastrado e, HttpServletRequest request){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(UsuarioNaoEncontrado.class)
    public ResponseEntity<StandardError> usuarioNaoEncontrado(UsuarioNaoEncontrado e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgumentException(IllegalArgumentException e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(FormacaoNaoEncontrada.class)
    public ResponseEntity<StandardError> formacaoNaoEncontrada(FormacaoNaoEncontrada e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(ExperienciaNaoEncontrada.class)
    public ResponseEntity<StandardError> experienciaNaoEncontrada(ExperienciaNaoEncontrada e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(EmpresaNaoEncontrada.class)
    public ResponseEntity<StandardError> empresaNaoEncontrada(EmpresaNaoEncontrada e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(AnuncioVagaNaoEncontrado.class)
    public ResponseEntity<StandardError> anuncioVagaNaoEncontrado(AnuncioVagaNaoEncontrado e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(SetorCargoNaoEncontrado.class)
    public ResponseEntity<StandardError> setorCargoNaoEncontrado(SetorCargoNaoEncontrado e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(AltenticacaoInvalida.class)
    public ResponseEntity<StandardError> altenticacaoInvalida(AltenticacaoInvalida e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }

    @ExceptionHandler(SolicitacaoNaoEncontrada.class)
    public ResponseEntity<StandardError> solicitacaoNaoEncontrada(SolicitacaoNaoEncontrada e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(NaoFoiPossivelAtualizarSolicitacaoEmpresa.class)
    public ResponseEntity<StandardError> naoFoiPossivelAtualizarSolicitacaoEmpresa(NaoFoiPossivelAtualizarSolicitacaoEmpresa e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(NaoFoiPossivelAtualizarConfirmacaoUsuario.class)
    public ResponseEntity<StandardError> naoFoiPossivelAtualizarConfirmacaoUsuario(NaoFoiPossivelAtualizarConfirmacaoUsuario e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(SolicitacaoJaEnviada.class)
    public ResponseEntity<StandardError> solicitacaoJaEnviada(SolicitacaoJaEnviada e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
