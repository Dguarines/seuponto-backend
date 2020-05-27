package br.com.r4s.adm.batidaponto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.r4s.adm.batidaponto.dominio.BatidaPonto;

@Repository
public interface BatidaPontoRepository extends JpaRepository<BatidaPonto, Long> {

}
