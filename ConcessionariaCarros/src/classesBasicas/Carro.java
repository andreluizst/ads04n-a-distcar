package classesBasicas;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Carro extends ObjetoGeral {
	
	@Column(unique=true)
	private String chassi;
	@Column(length=50, nullable=false)
	private String cor;
	@Column(length=10, nullable=false)
	private Integer anoFabricacao;
	@ManyToOne(cascade=CascadeType.PERSIST)
	private VersaoModeloCarro versaoModeloCarro;
	//@ManyToOne
	//@Cascade(CascadeType.PERSIST)
	//private ModeloCarro modeloCarro;
	//@ManyToOne
	//@Cascade(CascadeType.PERSIST)
	//private VersaoModeloCarro versaoModeloCarro;
	private Double valorCarro;
	public String getChassi() {
		return chassi;
	}
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public Integer getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(Integer anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	public VersaoModeloCarro getVersaoModeloCarro() {
		return versaoModeloCarro;
	}
	public void setVersaoModeloCarro(VersaoModeloCarro versaoModeloCarro) {
		this.versaoModeloCarro = versaoModeloCarro;
	}
	public Double getValorCarro() {
		return valorCarro;
	}
	public void setValorCarro(Double valorCarro) {
		this.valorCarro = valorCarro;
	}
	public Carro(String chassi, String cor, Integer anoFabricacao,
			VersaoModeloCarro versaoModeloCarro, Double valorCarro) {
		super();
		this.chassi = chassi;
		this.cor = cor;
		this.anoFabricacao = anoFabricacao;
		this.versaoModeloCarro = versaoModeloCarro;
		this.valorCarro = valorCarro;
	}
	public Carro() {
		super();
	}
	@Override
	public String toString() {
		return "Carro [chassi=" + chassi + ", cor=" + cor + ", anoFabricacao="
				+ anoFabricacao + ", versaoModeloCarro=" + versaoModeloCarro
				+ ", valorCarro=" + valorCarro + "]";
	}
	
	
}
