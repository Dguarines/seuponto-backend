package br.com.r4s.adm.arq.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.r4s.adm.arq.dominio.Municipio;
import br.com.r4s.adm.arq.dominio.MunicipioResponse;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

      @Cacheable(cacheNames="allMunicipios")
      @Query(
            value = " SELECT m.id, m.nome, uf.id as iduf " + 
                    " FROM administracao.municipio m " +
                    " JOIN administracao.unidade_federativa uf ON uf.id = m.id_unidade_federativa" + 
                    " ORDER BY m.nome", 
            nativeQuery = true
      )
      public List<MunicipioResponse> findAllProjetadoByOrderByNome();

      @Cacheable(cacheNames="municipiosPorEstado")
      @Query(
            value = " SELECT m.id, m.nome, uf.id as iduf " + 
                    " FROM administracao.municipio m " +
                    " JOIN administracao.unidade_federativa uf ON uf.id = m.id_unidade_federativa" + 
                    " WHERE uf.id = :idEstado " +
                    " ORDER BY m.nome", 
            nativeQuery = true
      )
      public List<MunicipioResponse> findByIdEstado(@Param("idEstado") Long idEstado);
}