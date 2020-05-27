package br.com.r4s.adm.batidaponto.dominio;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.r4s.adm.colaborador.dominio.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema="ponto" , name="pessoa")
public class BatidaPonto {
	
	public Recibo recibo;
	public Colaborador colaborador;
	public Date horaBatida;
}
