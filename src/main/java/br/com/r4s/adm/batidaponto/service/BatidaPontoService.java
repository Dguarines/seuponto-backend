package br.com.r4s.adm.batidaponto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.r4s.adm.batidaponto.dominio.BatidaPonto;
import br.com.r4s.adm.batidaponto.repository.BatidaPontoRepository;
import br.com.r4s.adm.colaborador.dominio.Colaborador;
import br.com.r4s.adm.colaborador.repository.ColaboradorRepository;

@Service
public class BatidaPontoService {
	
	@Autowired
	private BatidaPontoRepository repository;
	
	public BatidaPonto registrarBatidaSimples(BatidaPonto batidaPonto) {		
		return repository.save(batidaPonto);
	}
}
