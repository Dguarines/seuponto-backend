package br.com.r4s.adm.arq.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MunicipioResponseDTO implements Serializable {

	private static final long serialVersionUID = 476710762603699660L;

	private Long id;

    private String nome;

    private UnidadeFederativaResponseDTO unidadeFederativa;
}
