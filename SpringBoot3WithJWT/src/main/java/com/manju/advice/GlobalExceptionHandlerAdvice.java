package com.manju.advice;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.manju.exception.RecordNotFoundException;

import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authorization.AuthorizationDeniedException;

//@ControllerAdvice
@RestControllerAdvice   // Global exception handling
public class GlobalExceptionHandlerAdvice 
{
	Logger log=LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);
	
	@ExceptionHandler({RuntimeException.class,Exception.class})
	public ResponseEntity<?> handleException(Exception ex){
		Throwable t=ex.getCause();
		 int i=0;
		 String msg=ex.getMessage();
		 while(t!=null)
		 {
			 msg=msg+t.getMessage();
			 i++;
			 System.out.println(i+"=>"+t.getMessage());
			 t=t.getCause();
		 }
		 log.error(msg);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
	}
//	  @ExceptionHandler(RecordNotFoundException.class)
//	  @ResponseStatus(HttpStatus.NOT_FOUND)
//	  String accountNotFoundHandler(RecordNotFoundException ex) {
//		log.error(ex.getMessage());
//	    return ex.getMessage();
//	  }
//	
//	  @ExceptionHandler(AuthorizationDeniedException.class )
//	    public ResponseEntity<String> handleAuthorizationDeniedException(AuthorizationDeniedException ex) {
//
//		   	String errmsg=ex.getMessage();
//		   	log.error(errmsg);
//	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errmsg);
//	    }
//	  
//	   @ExceptionHandler(AuthenticationException.class )
//	    public ResponseEntity<String> handleAuthenticationException(Exception ex) {
//
//		   	String errmsg="Invalid Credential!! Authentication Failed";
//		   	log.error(errmsg);
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errmsg);
//	    }
//	  
//	  
//	  
//	  @ExceptionHandler(Exception.class)
//	  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	  String accountNotFoundHandler1(Exception ex) {
//		 String msg=" "+ex.getMessage()+ " ";
//		 Throwable t=ex.getCause();
//		 int i=0;
//		 
//		 while(t!=null)
//		 {
//			 msg=msg+t.getMessage();
//			 i++;
//			 System.out.println(i+"=>"+t.getMessage());
//			 t=t.getCause();
//		 }
//		 log.error(msg);
//		 //ex.printStackTrace();
//	    return msg;
//	  }
//	  
	  
	
}

/*
 * 
 * 
 * Spring 3.2 brings support for a global @ExceptionHandler with the @ControllerAdvice annotation.

This enables a mechanism that breaks away from the older MVC model and makes use of ResponseEntity along with the type safety and flexibility of @ExceptionHandler:
 * @ResponseBody signals that this advice is rendered straight into the response body.

@ExceptionHandler configures the advice to only respond if an EmployeeNotFoundException is thrown.

@ResponseStatus says to issue an HttpStatus.NOT_FOUND, i.e. an HTTP 404.

The body of the advice generates the content. In this case, it gives the message of the exception.
*/
