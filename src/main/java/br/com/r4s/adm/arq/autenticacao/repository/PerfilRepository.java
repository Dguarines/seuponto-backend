package br.com.r4s.adm.arq.autenticacao.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.r4s.adm.arq.dominio.Papel;
import br.com.r4s.adm.arq.dominio.Perfil;
import br.com.r4s.adm.arq.dominio.Usuario;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    List<Perfil> findByUsuariosIn(Usuario usuario);

    @Query(value =
            "SELECT " +
            "   EXISTS ( " +
            "       SELECT 1 FROM seguranca.perfil " +
            "       WHERE " +
            "       UPPER(nome) = UPPER(:nome) " +
            "       AND id <> :idPerfil " +
            "   )", nativeQuery = true)
    boolean verificaNomePerfilExistente(@Param("nome") String nome, @Param("idPerfil") Long idPerfil);

    @Query(value = 
    "select exists ( " + 
    "   select 1 " + 
    "   from ( " + 
    "      select " + 
    "           pp.id_perfil, array_agg(pp.id_papel) as papeis " + 
    "      from " + 
    "           seguranca.perfil_papel pp " + 
    "       group by " + 
    "           pp.id_perfil " + 
    "   ) c " + 
    "   where " + 
    "       c.papeis = (select array_agg(id) from seguranca.papel where id in (:papeis) ) " +
    "       and c.id_perfil <> :idPerfil " + 
    ") ", nativeQuery = true)
    boolean verificaPerfilComOsMesmosPapeis(@Param("papeis") Collection<Papel> papeis, @Param("idPerfil") Long idPerfil);

    @Query(value =
            "SELECT " +
            "   EXISTS ( " +
            "       SELECT 1 " +
            "       FROM seguranca.usuario_perfil  " +
            "       WHERE id_perfil = :idPerfil " +
            "   )", nativeQuery = true)
    boolean verificaPerfilEmUsoPorUsuario(@Param("idPerfil") Long idPerfil);
}
