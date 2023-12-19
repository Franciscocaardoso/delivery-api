package br.com.senior.burger_place.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandlerError {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFoundException(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseError(e));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity duplicateKeyException(DuplicateKeyException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseError(e));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
        var error = e.getFieldErrors();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNotFoundException (NoSuchElementException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseError(e));
    }

    private record DadosErroValidacao(String field, String message){
        public DadosErroValidacao(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
    private record ResponseError(String message){
        public ResponseError(RuntimeException exception){
            this(exception.getMessage());
        }
    }
}
