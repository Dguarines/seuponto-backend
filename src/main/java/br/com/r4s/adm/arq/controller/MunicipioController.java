package br.com.r4s.adm.arq.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.r4s.adm.arq.dominio.MunicipioResponse;
import br.com.r4s.adm.arq.service.MunicipioService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/municipios")
public class MunicipioController {

    private final MunicipioService service;

    @GetMapping
	public List<MunicipioResponse> findAll() {
		return service.findAllProjetado();
	}

	@GetMapping("/estado/{id}")
	public List<MunicipioResponse> findByIdEstado(@PathVariable Long id) {
		return service.findByIdEstado(id);
	}
}