package br.com.r4s.adm.arq.autenticacao.exception;

import br.com.r4s.adm.arq.dominio.Entidade;

public class EntidadeNaoEncontradoException extends RuntimeException {
	
	private static final long serialVersionUID = -2072810461571541694L;
	
	public EntidadeNaoEncontradoException(Entidade entity) {
		super("Não foi possível encontrar a entidade " + entity.getClass().getSimpleName() + " com o identificador " + entity.getId());
	}
}
