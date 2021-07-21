package br.com.projeto.contratados.config.exception.handler;

import br.com.projeto.contratados.config.exception.excecoes.*;
import br.com.projeto.contratados.config.exception.model.StandardError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<StandardError> emailJaCadastrado(EmailJaCadastradoException e, HttpServletRequest request){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<StandardError> usuarioNaoEncontrado(UsuarioNaoEncontradoException e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgumentException(IllegalArgumentException e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(FormacaoNaoEncontradaException.class)
    public ResponseEntity<StandardError> formacaoNaoEncontrada(FormacaoNaoEncontradaException e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(ExperienciaNaoEncontradaException.class)
    public ResponseEntity<StandardError> experienciaNaoEncontrada(ExperienciaNaoEncontradaException e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(EmpresaNaoEncontradaException.class)
    public ResponseEntity<StandardError> empresaNaoEncontrada(EmpresaNaoEncontradaException e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(AnuncioVagaNaoEncontradoException.class)
    public ResponseEntity<StandardError> anuncioVagaNaoEncontrado(AnuncioVagaNaoEncontradoException e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(SetorCargoNaoEncontradoException.class)
    public ResponseEntity<StandardError> setorCargoNaoEncontrado(SetorCargoNaoEncontradoException e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(AutenticacaoInvalidaException.class)
    public ResponseEntity<StandardError> altenticacaoInvalida(AutenticacaoInvalidaException e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .error(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }

    @ExceptionHandler(SolicitacaoNaoEncontradaException.class)
    public ResponseEntity<StandardError> solicitacaoNaoEncontrada(SolicitacaoNaoEncontradaException e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(NaoFoiPossivelAtualizarSolicitacaoEmpresaException.class)
    public ResponseEntity<StandardError> naoFoiPossivelAtualizarSolicitacaoEmpresa(NaoFoiPossivelAtualizarSolicitacaoEmpresaException e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(NaoFoiPossivelAtualizarConfirmacaoUsuarioException.class)
    public ResponseEntity<StandardError> naoFoiPossivelAtualizarConfirmacaoUsuario(NaoFoiPossivelAtualizarConfirmacaoUsuarioException e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(SolicitacaoJaEnviadaException.class)
    public ResponseEntity<StandardError> solicitacaoJaEnviada(SolicitacaoJaEnviadaException e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(UserAuthException.class)
    public ResponseEntity<StandardError> userAuthException(UserAuthException e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }


    @ExceptionHandler(SenhaIncorretaException.class)
    public ResponseEntity<StandardError> senhaIncorretaException(SenhaIncorretaException e){

        StandardError err =  StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
