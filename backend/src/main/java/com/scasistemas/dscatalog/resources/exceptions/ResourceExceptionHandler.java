package com.scasistemas.dscatalog.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.scasistemas.dscatalog.services.exceptions.EntityNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e,HttpServletRequest request){
	
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Recurso não encontrado");
		err.setMessage(e.getMessage()); //Pegando a mensagem definida no service e que entrou no parametro da função
		err.setPath(request.getRequestURI()); ///Para pegar o caminho da requisição
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err); //método status permite customizar o status que vai retornar | Retornando no corpo o objeto instanciado
		
		
		
	}

}
