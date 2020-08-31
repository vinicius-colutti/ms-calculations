package com.colutti.calculation.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.colutti.calculation.exception.ExceptionRespose;
import com.colutti.calculation.exception.UnsuportedMathOperationException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionRespose> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionRespose exceptionRespose = new ExceptionRespose(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionRespose, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UnsuportedMathOperationException.class)
	public final ResponseEntity<ExceptionRespose> handleBadRequestExceptions(Exception ex, WebRequest request) {
		ExceptionRespose exceptionRespose = new ExceptionRespose(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionRespose, HttpStatus.BAD_REQUEST);
	}


}
