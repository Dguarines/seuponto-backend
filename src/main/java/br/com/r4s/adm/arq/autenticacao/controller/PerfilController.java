package br.com.r4s.adm.arq.autenticacao.controller;

import static org.springframework.util.StringUtils.isEmpty;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.r4s.adm.arq.autenticacao.exception.NemTodosPapeisForamEncontradoException;
import br.com.r4s.adm.arq.autenticacao.service.PapelService;
import br.com.r4s.adm.arq.autenticacao.service.PerfilService;
import br.com.r4s.adm.arq.dominio.Perfil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PerfilController {

	private final PerfilService perfilService;
	private final PapelService papelService;
	
	@GetMapping("/perfis")
	@PreAuthorize("hasRole('LISTAR_PERFIL')")
	public ResponseEntity<List<Perfil>> listarTodos() {
		return ResponseEntity.ok(perfilService.findAll());
	}

	@PostMapping("/perfis")
	@PreAuthorize("hasRole('CADASTRAR_PERFIL')")
	public ResponseEntity<Perfil> criarPerfil(@RequestBody Perfil novoPerfil) {
		return ResponseEntity.status(HttpStatus.CREATED).body(perfilService.save(novoPerfil));
	}

	@GetMapping("/perfis/{id}")
	@PreAuthorize("hasRole('LISTAR_PERFIL')")
	public ResponseEntity<Perfil> obterPerfil(@PathVariable Long id) {
		return ResponseEntity.ok(perfilService.findById(id));
	}

	@PutMapping("/perfis/{id}")
	@PreAuthorize("hasRole('ALTERAR_PERFIL')")
	public ResponseEntity<Perfil> substituirPerfil(@RequestBody Perfil novoPerfil, @PathVariable Long id) {
		Perfil perfil = perfilService.findById(id);
		boolean todosPapeisExistem = papelService.verificaSeTodosPapeisExistem(novoPerfil.getPapeis());
		
		if (!todosPapeisExistem) {
			throw new NemTodosPapeisForamEncontradoException();
		}
		
		if (!isEmpty(novoPerfil.getNome())) {
			perfil.setNome(novoPerfil.getNome());	
		}

		if (!isEmpty(novoPerfil.getDescricao())) {
			perfil.setDescricao(novoPerfil.getDescricao());	
		}
		
		if (!isEmpty(novoPerfil.getPapeis())) {
			perfil.setPapeis(novoPerfil.getPapeis());	
		}
		
		if (!isEmpty(novoPerfil.getUsuarios())) {
			perfil.setUsuarios(novoPerfil.getUsuarios());	
		}
		
		return ResponseEntity.accepted().body(perfilService.save(perfil));
	}
	
	@DeleteMapping("/perfis/{id}")
	@PreAuthorize("hasRole('REMOVER_PERFIL')")
	public ResponseEntity<Perfil> deletarPerfil(@PathVariable Long id) {
		perfilService.deleteById(id);
		return ResponseEntity.accepted().build();
	}
}
