package br.com.r4s.adm.arq.autenticacao.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.r4s.adm.arq.dominio.Papel;
import br.com.r4s.adm.arq.dominio.Perfil;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Long> {
    List<Papel> findByPerfisIn(Perfil perfil);
    
    @Query(value = "SELECT p FROM Papel p WHERE p IN :papeis")
    List<Papel> findPapeisByNomes(@Param("papeis") Collection<Papel> papeis);
}
