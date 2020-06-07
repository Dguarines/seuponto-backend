package br.com.r4s.adm.batidaponto.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.r4s.adm.arq.dominio.Endereco;
import br.com.r4s.adm.colaborador.dominio.Colaborador;
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
@Table(schema="ponto" , name="batida_ponto")
public class BatidaPonto implements Serializable {
	
	private static final long serialVersionUID = -1636358686288147664L;

	@Id
    @EqualsAndHashCode.Include
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="batida_ponto_sequence_generator")
	@SequenceGenerator(name="batida_ponto_sequence_generator", sequenceName="ponto.batida_ponto_sequence", allocationSize=1)
    private Long id;
		
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_colaborador")
	public Colaborador colaborador;
	
	@Column(name = "hora_batida")
	public Date horaBatida; 
	
	@OneToOne
	@JoinColumn(name="id_endereco")
	public Endereco endereco;
}
