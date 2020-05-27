package br.com.r4s.adm.arq.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.r4s.adm.arq.dominio.CaminhoFuncionalidade;
import br.com.r4s.adm.arq.service.CaminhoFuncionalidadeService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CaminhoFuncionalidadeController {

	private final CaminhoFuncionalidadeService caminhoFuncionalidadeService;

	@GetMapping("/caminho-funcionalidade")
	@PreAuthorize("hasRole('LISTAR_CAMINHO_FUNCIONALIDADE')")
	public ResponseEntity<List<CaminhoFuncionalidade>> listarTodos() {
		return ResponseEntity.ok(caminhoFuncionalidadeService.findAll());
	}
	
}
