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
public class UnidadeFederativaResponseDTO implements Serializable {

	private static final long serialVersionUID = 1236785532144758728L;

	private Long id;

    private String sigla;

    private String nome;
}
