package br.com.r4s.adm.arq.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.r4s.adm.arq.dominio.Funcionalidade;
import br.com.r4s.adm.arq.dominio.Perfil;
import br.com.r4s.adm.arq.service.FuncionalidadeService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/funcionalidade")
public class FuncionalidadeController {

	private final FuncionalidadeService funcionalidadeService;

	@GetMapping("/perfil/{id}")
	public ResponseEntity<List<Funcionalidade>> obterFuncionalidadesPorPerfil(@PathVariable Long id) {
		Perfil perfil = Perfil.builder().id(id).build();
		
		return ResponseEntity.ok(funcionalidadeService.findByPerfil(perfil));
	}
	
}
