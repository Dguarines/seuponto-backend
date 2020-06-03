package br.com.r4s.adm.batidaponto.dominio;

import br.com.r4s.adm.colaborador.dominio.Colaborador;
import lombok.Data;

@Data
public class BatidaPontoRequest {
	
	private Long id;
		
	public Colaborador colaborador;
		
	public String horaBatida; 
}
