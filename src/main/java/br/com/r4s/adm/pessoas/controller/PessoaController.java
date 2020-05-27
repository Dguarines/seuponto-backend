package br.com.r4s.adm.pessoas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.r4s.adm.pessoas.dominio.Pessoa;
import br.com.r4s.adm.pessoas.interfaces.PessoaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
    private PessoaService service;

    @GetMapping("/exists/{cpf}")
	public Boolean buscarPorCpf(@PathVariable(required = true) String cpf) {
		return service.jaExistePessoaComEsteCPF(cpf);
	}

	@GetMapping("/{cpf}")
	public Pessoa findByCPF(@PathVariable(required = true) String cpf) {
		return service.findByCpf(cpf);
    }
    
    @GetMapping
	public List<Pessoa> findAll() {
		return service.findAll();
	}

}
