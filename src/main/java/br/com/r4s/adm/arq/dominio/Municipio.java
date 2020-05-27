package br.com.r4s.adm.arq.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(schema="administracao" , name="municipio")
public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="municipio_sequence_generator")
	@SequenceGenerator(name="municipio_sequence_generator", sequenceName="administracao.municipio_sequence", allocationSize=1)
    private Long id;

    private String nome;

    @Column(name="codigo_ibge")
    private Long codigoIBGE;

    private Float latitude;

    private Float longitude;

    private boolean capital;

    @ManyToOne
    @JoinColumn(name = "id_unidade_federativa")
    private UnidadeFederativa unidadeFederativa;
    
}