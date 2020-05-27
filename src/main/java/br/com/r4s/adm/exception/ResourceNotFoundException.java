package br.com.r4s.adm.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends ResourceException {

    private static final long serialVersionUID = -914122688120086301L;
    
	private Long idResource;

    public ResourceNotFoundException(Long idResource) {
        super(HttpStatus.NOT_FOUND, "recurso.nao.existente");
        this.idResource = idResource;
    }
}
