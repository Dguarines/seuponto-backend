package br.com.r4s.adm.batidaponto.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.r4s.adm.batidaponto.dominio.BatidaPonto;
import br.com.r4s.adm.colaborador.dominio.Colaborador;


@RunWith(SpringRunner.class)
public class RegistroDeBatidaServiceTest {

	private BatidaPontoService service;
	
	@Before
	public void setUp() {
		service = new BatidaPontoService();
	}
	
	@Test
	public void aoRealizarRegistroDeBatidaUsandoParametrosMinimos_deveRetornarBatidaComReciboPreenchido() {
		//Cenario
		Date agora = new Date();
		Colaborador colaborador = Colaborador.builder()
											 .id(1L)
											 .build();
		
		//Acao
		BatidaPonto batida = service.registrarBatidaSimples(colaborador, agora);
		
		//Validação
		Assert.assertEquals(Long.valueOf(1L), batida.getColaborador().getId());
	}
}
