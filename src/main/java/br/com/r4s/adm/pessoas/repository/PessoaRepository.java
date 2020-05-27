package br.com.r4s.adm.pessoas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.r4s.adm.pessoas.dominio.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	public Pessoa findByCpf(String cpf);

	public List<Pessoa> findPessoasByCpf(String cpf);
}
