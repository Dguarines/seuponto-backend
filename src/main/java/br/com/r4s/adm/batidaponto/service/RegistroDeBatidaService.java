package br.com.r4s.adm.batidaponto.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.r4s.adm.batidaponto.dominio.BatidaPonto;
import br.com.r4s.adm.batidaponto.dominio.Recibo;
import br.com.r4s.adm.batidaponto.repository.BatidaPontoRepository;
import br.com.r4s.adm.colaborador.dominio.Colaborador;

@Service
public class RegistroDeBatidaService {
	
	@Autowired
	private BatidaPontoRepository repository;
	
	@SuppressWarnings("static-access")
	public BatidaPonto registrarBatidaSimples(Colaborador colaborador, Date agora) {
		
		Recibo recibo = new Recibo().builder().codigo(UUID.randomUUID().toString()).build();
		BatidaPonto batidaPonto = new BatidaPonto().builder()
												   .colaborador(colaborador)
												   .horaBatida(agora)
												   .recibo(recibo).build();
		
		return batidaPonto;
		//return repository.save(batidaPonto);
	}
}
