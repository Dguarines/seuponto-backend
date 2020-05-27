package br.com.r4s.adm.arq.autenticacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.r4s.adm.arq.dominio.CaminhoFuncionalidade;

@Repository
public interface CaminhoFuncionalidadeRepository extends JpaRepository<CaminhoFuncionalidade, Long> {
    public List<CaminhoFuncionalidade> findByFuncionalidadeIsNull();
}
