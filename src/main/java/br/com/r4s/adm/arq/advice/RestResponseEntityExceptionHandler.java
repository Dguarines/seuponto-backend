package br.com.r4s.adm.arq.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.r4s.adm.arq.messages.ErrorMessage;
import br.com.r4s.adm.arq.messages.SourceMessage;
import br.com.r4s.adm.exception.RegraNegocioException;
import br.com.r4s.adm.exception.ResourceException;
import br.com.r4s.adm.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;

@ControllerAdvice
@AllArgsConstructor
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private final SourceMessage messageSource;
	
	@ExceptionHandler(value = { ResourceException.class })
    protected ResponseEntity<ErrorMessage> handleResourceException(ResourceException e) {
        return generateResponse(e);
    }
	
    @ExceptionHandler(value = { ResourceNotFoundException.class })
    protected ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException e) {
        return generateResponse(e, e.getIdResource());
    }
    
    
    @ExceptionHandler(value = { RegraNegocioException.class })
    protected ResponseEntity<ErrorMessage> handleRegraNegocioException(RegraNegocioException e) {
        return generateResponse(e, e.getParametros());
    }

    @ExceptionHandler(value = { AccessDeniedException.class })
    private ResponseEntity<ErrorMessage> handleAccessDeniedException(AccessDeniedException e) {
    	return generateResponse(new ResourceException(HttpStatus.FORBIDDEN, "acesso.negado"));
    }
    
    @ExceptionHandler(value = { Exception.class })
    private ResponseEntity<ErrorMessage> handleException(Exception e) {
    	return generateResponse(new ResourceException(HttpStatus.INTERNAL_SERVER_ERROR, "erro.nao.esperado"));
    }
	
    private ResponseEntity<ErrorMessage> generateResponse(ResourceException e, Object... params) {
        return ResponseEntity.status(e.getHttpStatus()).body(getErrorMessage(e, params));
    }

    private ErrorMessage getErrorMessage(ResourceException e, Object... params) {
        return ErrorMessage.builder()
                           .code(e.getHttpStatus().value())
                           .codeDescription(e.getHttpStatus().getReasonPhrase())
                           .message(messageSource.getMessage(e.getMessageKey(), params))
                           .build();
    }
}
