package br.com.r4s.adm.arq.dominio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(schema="seguranca" , name="papel")
@Data
public class Papel implements GrantedAuthority {

	private static final long serialVersionUID = 5112916254968336828L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="papel_sequence_generator")
	@SequenceGenerator(name="papel_sequence_generator", sequenceName="seguranca.papel_sequence", allocationSize=1)
	private Long id;
	
	private String nome;

	@JsonIgnore
	@ManyToMany(mappedBy = "papeis")
	private List<Perfil> perfis;

	@Override
	public String toString() {
		return "Permissao [id=" + id + ", nome=" + nome + "]";
	}

	@JsonIgnore
	@Override
	public String getAuthority() {
		return this.nome;
	}
}
