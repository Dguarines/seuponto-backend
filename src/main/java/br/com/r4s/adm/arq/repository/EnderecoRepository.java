package br.com.r4s.adm.arq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.r4s.adm.arq.dominio.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
