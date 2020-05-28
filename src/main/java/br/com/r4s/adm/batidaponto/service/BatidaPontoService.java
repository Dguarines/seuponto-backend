package br.com.r4s.adm.batidaponto.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.r4s.adm.batidaponto.dominio.BatidaPonto;
import br.com.r4s.adm.batidaponto.repository.BatidaPontoRepository;
import br.com.r4s.adm.colaborador.dominio.Colaborador;

@Service
public class BatidaPontoService {
	
	@Autowired
	private BatidaPontoRepository repository;
	
	@SuppressWarnings("static-access")
	public BatidaPonto registrarBatidaSimples(Colaborador colaborador, Date agora) {
		
		BatidaPonto batidaPonto = new BatidaPonto().builder()
												   .colaborador(colaborador)
												   .horaBatida(agora)
												   .build();
		
		return repository.save(batidaPonto);
	}
}
