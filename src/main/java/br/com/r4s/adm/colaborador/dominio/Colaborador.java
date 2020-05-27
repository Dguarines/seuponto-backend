package br.com.r4s.adm.colaborador.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.r4s.adm.empresa.dominio.Empresa;
import br.com.r4s.adm.pessoas.dominio.Pessoa;
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
@Table(schema="administracao" , name="colaborador")
public class Colaborador implements Serializable {
	
	private static final long serialVersionUID = 1112170583334481972L;

	@Id
    @EqualsAndHashCode.Include
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="colaborador_sequence_generator")
	@SequenceGenerator(name="colaborador_sequence_generator", sequenceName="administracao.colaborador_sequence", allocationSize=1)
    private Long id;
	
	private Pessoa pessoa;
	
	private Empresa empresa;
}	
