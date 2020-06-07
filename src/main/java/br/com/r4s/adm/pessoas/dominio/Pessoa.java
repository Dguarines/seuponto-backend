package br.com.r4s.adm.pessoas.dominio;

import java.io.Serializable;
import java.time.LocalDate;

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
import br.com.r4s.adm.empresa.dominio.Empresa;
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
@Table(schema="administracao" , name="pessoa")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1747088982987689573L;

    @Id
    @EqualsAndHashCode.Include
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pessoa_sequence_generator")
	@SequenceGenerator(name="pessoa_sequence_generator", sequenceName="administracao.pessoa_sequence", allocationSize=1)
    private Long id;
    
    @Column(name = "nome", length = 256)
    private String nome;

    private String cpf;

    @Column(name="data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "email", length = 256)
    private String email;
    
    @Column(name = "telefone", length = 11)
    private String telefone;
    
    @Column(name = "celular", length = 11)
    private String celular;

    private String sexo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_endereco")
    private Endereco endereco;

    @Column(name = "profissao", length = 256)
    private String profissao;

    @OneToOne
    @JoinColumn(name="id_empresa")
    private Empresa empresa;
}
