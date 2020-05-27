package br.com.r4s.adm.arq.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.r4s.adm.arq.dominio.UnidadeFederativaResponse;
import br.com.r4s.adm.arq.service.UnidadeFederativaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/ufs")
public class UnidadeFederativaController {

    private final UnidadeFederativaService service;

    @GetMapping
	public List<UnidadeFederativaResponse> findAll() {
		return service.findAllProjetado();
	}
}