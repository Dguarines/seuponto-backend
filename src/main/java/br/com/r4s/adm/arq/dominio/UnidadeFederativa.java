package br.com.r4s.adm.arq.dominio;

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
@Table(schema="administracao" , name="unidade_federativa")
public class UnidadeFederativa implements Serializable {
   
    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="unidade_federativa_sequence_generator")
	@SequenceGenerator(name="unidade_federativa_sequence_generator", sequenceName="administracao.unidade_federativa_sequence", allocationSize=1)
    private Long id;

    @Column(name="codigo_ibge")
    private Long codigoIBGE;

    private String sigla;

    private String nome;
}