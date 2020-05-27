package br.com.r4s.adm.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ResourceException extends RuntimeException {

	private static final long serialVersionUID = 5160167229074331297L;
	
	private HttpStatus httpStatus;
    private String messageKey;

    public ResourceException(HttpStatus httpStatus, String messageKey) {
        super(messageKey);
        this.httpStatus = httpStatus;
        this.messageKey = messageKey;
    }
}
