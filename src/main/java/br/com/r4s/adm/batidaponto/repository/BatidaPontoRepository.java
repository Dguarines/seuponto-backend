package br.com.r4s.adm.batidaponto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.r4s.adm.batidaponto.dominio.BatidaPonto;

@Repository
public interface BatidaPontoRepository extends JpaRepository<BatidaPonto, Long> {

	@Query(value = " SELECT bp "
			      +" FROM BatidaPonto bp "
			      +" WHERE bp.colaborador.pessoa.id = :id "
			      +" AND to_char(bp.horaBatida, 'YYYY-MM-DD') = to_char(CURRENT_DATE(), 'YYYY-MM-DD') ")
	List<BatidaPonto> buscarListaDeBatidasDeHojePorIdPessoa(@Param("id") Long id);
}
