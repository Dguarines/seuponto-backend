package br.com.r4s.adm.arq.autenticacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.r4s.adm.arq.dominio.Funcionalidade;

@Repository
public interface FuncionalidadeRepository extends JpaRepository<Funcionalidade, Long> {
    @Query(
        value =
            " select f.* " +
            " from seguranca.perfil_papel pp " +
            " join administracao.funcionalidade f on f.id_papel = pp.id_papel " +
            " where pp.id_perfil = ?1 ",
        nativeQuery = true)
    List<Funcionalidade> findByIdPerfil(Long idPerfil);
}
