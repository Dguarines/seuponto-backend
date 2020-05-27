package br.com.r4s.adm.arq.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.r4s.adm.arq.dominio.UnidadeFederativa;
import br.com.r4s.adm.arq.dominio.UnidadeFederativaResponse;


@Repository
public interface UnidadeFederativaRepository extends JpaRepository<UnidadeFederativa, Long> {

    @Cacheable(cacheNames="ufs")
    @Query(
        value = " SELECT id, nome, sigla " +
                " FROM administracao.unidade_federativa " +
                " ORDER BY nome",
        nativeQuery = true
    )
    public List<UnidadeFederativaResponse> findAllProjetadoByOrderByNome();
}