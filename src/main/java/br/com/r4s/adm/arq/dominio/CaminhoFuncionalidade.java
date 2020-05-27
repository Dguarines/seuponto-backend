package br.com.r4s.adm.arq.dominio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@JsonPropertyOrder({"id", "nome", "descricao", "caminhoModuloFuncionalidadePai", "funcionalidade", "caminhosModuloFuncionalidade"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Data
@Table(schema="administracao" , name="caminho_funcionalidade")
public class CaminhoFuncionalidade implements Serializable {

	private static final long serialVersionUID = -4056470509213920957L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="caminho_funcionalidade_sequence_generator")
	@SequenceGenerator(name="caminho_funcionalidade_sequence_generator", sequenceName="administracao.caminho_funcionalidade_sequence", allocationSize=1)
	private Long id;
	
	@Column(length = 50)
	private String nome;
	
	@Column(length = 200)
	private String descricao;

	
	@OneToOne(optional = false)
	@JoinColumn(name = "id_funcionalidade")
    private Funcionalidade funcionalidade;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "id_caminho_funcionalidade_pai")
	private CaminhoFuncionalidade caminhoModuloFuncionalidadePai;
	
	@JsonBackReference
	@JsonInclude(Include.NON_EMPTY)
	@OneToMany(mappedBy = "caminhoModuloFuncionalidadePai")
	private List<CaminhoFuncionalidade> caminhosModuloFuncionalidade;

}
