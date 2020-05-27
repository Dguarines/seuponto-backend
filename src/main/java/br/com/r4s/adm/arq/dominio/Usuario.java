package br.com.r4s.adm.arq.dominio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.r4s.adm.pessoas.dominio.Pessoa;
import br.com.r4s.adm.usuarios.interfaces.UsuarioPadrao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(schema="administracao", name="usuario")
public class Usuario implements UsuarioPadrao {
	
	private static final long serialVersionUID = 1583708789730046879L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="usuario_sequence_generator")
	@SequenceGenerator(name="usuario_sequence_generator", sequenceName="administracao.usuario_sequence", allocationSize=1)
	private Long id;
	
	@NotBlank
    @Column(unique = true)
    @Size(min = 1, max = 50)
	private String login;
	
	@NotBlank
	private String senha;
	
	@JoinTable(
        name = "usuario_perfil",schema= "seguranca",
        joinColumns = @JoinColumn(name = "id_usuario",referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_perfil",referencedColumnName = "id")
	)
	@ManyToMany
	private List<Perfil> perfis;

	@OneToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;

	private String foto;

	@Transient
	@JsonIgnore
	private Collection<? extends GrantedAuthority> authorities;
	
	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
		this.authorities = new ArrayList<>();
	}
	
	@JsonIgnore
	@Override
	public String getUsername() {
		return getLogin();
	}
	
	@JsonIgnore
	@Override
	public String getPassword() {
		return getSenha();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}
}
