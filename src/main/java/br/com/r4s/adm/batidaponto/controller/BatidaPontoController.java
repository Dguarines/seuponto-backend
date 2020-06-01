package br.com.r4s.adm.batidaponto.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.r4s.adm.batidaponto.dominio.BatidaPonto;
import br.com.r4s.adm.batidaponto.service.BatidaPontoService;
import br.com.r4s.adm.colaborador.dominio.Colaborador;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BatidaPontoController {
	
	@Autowired
	private BatidaPontoService service;
	
	@GetMapping("/batida")
	public String teste() {
		return "OK";
	}
	
	@PostMapping("/batida")
	public ResponseEntity<BatidaPonto> cadastrar(@RequestBody Colaborador colaborador) {
		
		Date horaBatida = new Date();
		return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarBatidaSimples(colaborador, horaBatida));
	}
}
