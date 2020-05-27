package br.com.r4s.adm.arq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.r4s.adm.arq.dominio.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Usuario findByLoginAndSenha(String username, String password);

	public Usuario findByLogin(String username);

	@Query(value = "SELECT EXISTS "
				  +"( SELECT 1 FROM administracao.usuario"
				  +" WHERE login = :login AND id <> :id)",
				nativeQuery = true)
	public boolean existeUsuarioComMesmoLogin(@Param("login") String login,@Param("id")  long id);

	@Query(value = "SELECT EXISTS "
				  +"( SELECT 1 FROM administracao.usuario u"
				  +" JOIN administracao.pessoa p ON p.id = u.id_pessoa "
				  +" WHERE u.id <> :id AND p.cpf = :cpf)",
				nativeQuery = true)
	public boolean existeUsuarioComCPFInformado(@Param("id") long id,@Param("cpf")  String cpf);

}
