package classesBasicas;

import java.util.Calendar;
import javax.persistence.*;

@MappedSuperclass
public abstract class ObjetoGeral {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer codigo;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataUltimaAtualizacao;
	
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Calendar getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}
	public void setDataUltimaAtualizacao(Calendar dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}
	public Situacao getSituacao() {
		return situacao;
	}
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	
}
