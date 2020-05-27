package br.com.r4s.adm.arq.dominio;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(schema="seguranca" , name="perfil")
@Data
public class Perfil implements Entidade {
    
  private static final long serialVersionUID = 1968722401911020909L;
  
  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="perfil_sequence_generator")
  @SequenceGenerator(name="perfil_sequence_generator", sequenceName="seguranca.perfil_sequence", allocationSize=1)
  private Long id;

  @Column(length = 50)
  private String nome;

  @Column(length = 200)
  private String descricao;

  @JoinTable(
    name = "perfil_papel",schema= "seguranca",
    joinColumns = @JoinColumn(name = "id_perfil",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "id_papel",referencedColumnName = "id")
  )
  @ManyToMany
  @JsonIgnoreProperties("papeis")
	private Collection<Papel> papeis = new ArrayList<>();

	@JsonIgnore
	@ManyToMany(mappedBy = "perfis")
	private Collection<Usuario> usuarios;
}