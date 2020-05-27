package br.com.r4s.adm.arq.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(schema="administracao" , name="endereco")
public class Endereco implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="endereco_sequence_generator")
	@SequenceGenerator(name="endereco_sequence_generator", sequenceName="administracao.endereco_sequence", allocationSize=1)
    private Long id;

    @Column(name = "cep", length = 10)
    private String cep;

    @Column(name = "logradouro", length = 256)
    private String logradouro;
    
    @Column(name = "complemento", length = 256)
    private String complemento;
    
    @Column(name = "bairro", length = 256)
    private String bairro;
    
    @Column(name = "numero", length = 16)
    private String numero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_municipio")
    private Municipio municipio;
}