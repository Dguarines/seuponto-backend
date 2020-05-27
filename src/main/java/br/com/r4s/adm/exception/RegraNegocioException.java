package br.com.r4s.adm.exception;

import org.springframework.http.HttpStatus;

import br.com.r4s.adm.exception.ResourceException;
import lombok.Getter;

/**
 * RegraNegocioException
 */
@Getter
public class RegraNegocioException extends ResourceException {

    private static final long serialVersionUID = 370347438711544648L;

    private Object[] parametros;

    public RegraNegocioException(String erroRegraNegocioKey, Object ...params) {
        super(HttpStatus.BAD_REQUEST, erroRegraNegocioKey);
        this.parametros = params;
    }

}