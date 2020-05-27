package br.com.r4s.adm.usuarios.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import br.com.r4s.adm.arq.dominio.Usuario;
import br.com.r4s.adm.arq.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UsuarioController {

	private final UsuarioService usuarioService;

	private final PasswordEncoder passwordEncoder;

	@GetMapping("/usuarios/me")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Usuario> usuarioLogado(Principal principal) {
		return ResponseEntity.ok(usuarioService.findByLogin(principal.getName()));
	}

	@GetMapping("/usuarios")
	@PreAuthorize("hasRole('LISTAR_USUARIO')")
	public ResponseEntity<List<Usuario>> listarTodos() {
		return ResponseEntity.ok(usuarioService.findAll());
	}

	@PostMapping("/usuarios")
	@PreAuthorize("hasRole('CADASTRAR_USUARIO')")
	public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario novoUsuario) {
		novoUsuario.setSenha(passwordEncoder.encode(novoUsuario.getPassword()));
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(novoUsuario));
	}

	@GetMapping("/usuarios/{id}")
	@PreAuthorize("hasRole('LISTAR_USUARIO')")
	public ResponseEntity<Usuario> obterUsuario(@PathVariable Long id) {
		return ResponseEntity.ok(usuarioService.findById(id));
	}

	@PutMapping("/usuarios/{id}")
	@PreAuthorize("hasRole('ALTERAR_USUARIO')")
	public ResponseEntity<Usuario> substituirUsuario(@RequestBody Usuario novoUsuario, @PathVariable Long id) {
		novoUsuario.setSenha(passwordEncoder.encode(novoUsuario.getPassword()));
		Usuario usuario = usuarioService.findById(id);
		if (!StringUtils.isEmpty(novoUsuario.getUsername())) {
			usuario.setLogin(novoUsuario.getUsername());	
		}
		if (!StringUtils.isEmpty(novoUsuario.getPassword())) {
			usuario.setSenha(novoUsuario.getPassword());	
		}
		if (!StringUtils.isEmpty(novoUsuario.getFoto())) {
			usuario.setFoto(novoUsuario.getFoto());
		}
		
		return ResponseEntity.accepted().body(usuarioService.save(usuario));
	}

	@PutMapping("/usuarios/atualizar-perfis/{id}")
	@PreAuthorize("hasRole('IMPLANTAR_PERFIL_USUARIO')")
	public ResponseEntity<Usuario> atualizarPerfisUsuario(@RequestBody Usuario usuario, @PathVariable Long id) {
		return ResponseEntity.accepted().body(usuarioService.save(usuario));
	}
	
	@DeleteMapping("/usuarios/{id}")
	@PreAuthorize("hasRole('REMOVER_USUARIO')")
	public ResponseEntity<Usuario> deletarUsuario(@PathVariable Long id) {
		usuarioService.deleteById(id);
		return ResponseEntity.accepted().build();
	}
}
