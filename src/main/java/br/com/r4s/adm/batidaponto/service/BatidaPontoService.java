package br.com.r4s.adm.batidaponto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.r4s.adm.batidaponto.dominio.BatidaPonto;
import br.com.r4s.adm.batidaponto.repository.BatidaPontoRepository;

@Service
public class BatidaPontoService {
	
	@Autowired
	private BatidaPontoRepository repository;
	
	public BatidaPonto registrarBatidaSimples(BatidaPonto batidaPonto) {		
		return repository.save(batidaPonto);
	}
	
	public List<BatidaPonto> buscarBatidasDoDiaPorIdPessoa(Long id){
		return repository.buscarListaDeBatidasDeHojePorIdPessoa(id);
	}
}
