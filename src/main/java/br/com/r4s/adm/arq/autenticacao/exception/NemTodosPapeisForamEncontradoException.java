package br.com.r4s.adm.arq.autenticacao.exception;

public class NemTodosPapeisForamEncontradoException extends RuntimeException {
	
	private static final long serialVersionUID = 4792169361198632048L;

	public NemTodosPapeisForamEncontradoException() {
		super("Nem todos os papeis informados foram encontrados.");
	}
}
