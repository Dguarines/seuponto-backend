package br.com.r4s.adm.empresa.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema="administracao" , name="empresa")
public class Empresa implements Serializable {
		
	private static final long serialVersionUID = 6015415481282895758L;

	@Id
    @EqualsAndHashCode.Include
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="empresa_sequence_generator")
	@SequenceGenerator(name="empresa_sequence_generator", sequenceName="administracao.empresa_sequence", allocationSize=1)
    private Long id;
	
	@Column(name = "nome_empresa")
	private String NomeEmpresa;
	
	@Column(name = "razao_social")
	private String razaoSocial;
	
	private String codigo;
	
	private String cnpj;	
}
