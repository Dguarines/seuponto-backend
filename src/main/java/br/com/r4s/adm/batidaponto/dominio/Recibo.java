package br.com.r4s.adm.batidaponto.dominio;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema="ponto" , name="recibo")
public class Recibo {
	
	private String codigo;
}
