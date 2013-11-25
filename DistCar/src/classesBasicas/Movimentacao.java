package classesBasicas;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer numero;
	
	private Integer notaFiscal;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="codCtoOrigem", insertable=true, updatable=true, nullable=false)
	private Centro ctoOrigem;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="codCtoDestino", insertable=true, updatable=true)
	private Centro ctoDestino;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, length=15)
	private TipoMovimentacao tipoMovimentacao;
	
	@Temporal(TemporalType.DATE)
	private Date dataMovimentacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUltimaAtualizacao;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, length=10)
	private SituacaoMovimentacao situacao;
	
	
}
