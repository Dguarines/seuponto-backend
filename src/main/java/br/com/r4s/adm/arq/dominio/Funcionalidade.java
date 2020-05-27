package br.com.r4s.adm.arq.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@Data
@Table(schema="administracao" , name="funcionalidade")
public class Funcionalidade implements Serializable {

	private static final long serialVersionUID = 6980567391672840151L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="funcionalidade_sequence_generator")
	@SequenceGenerator(name="funcionalidade_sequence_generator", sequenceName="administracao.funcionalidade_sequence", allocationSize=1)
	private Long id;
	
	@Column(length = 50)
	private String nome;
	
	@Column(length = 200)
	private String descricao;

	@OneToOne(optional = false)
	@JoinColumn(name = "id_papel")
    private Papel papel;

}
