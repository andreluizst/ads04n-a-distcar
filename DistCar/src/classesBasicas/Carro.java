package classesBasicas;


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
	@ManyToOne
	private VersaoCarro versaoCarro;
	//@ManyToOne
	//@Cascade(CascadeType.PERSIST)
	//private ModeloCarro modeloCarro;
	//@ManyToOne
	//@Cascade(CascadeType.PERSIST)
	//private VersaoCarro versaoModeloCarro;
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
	public VersaoCarro getVersaoCarro() {
		return versaoCarro;
	}
	public void setVersaoCarro(VersaoCarro versaoCarro) {
		this.versaoCarro = versaoCarro;
	}
	public Double getValorCarro() {
		return valorCarro;
	}
	public void setValorCarro(Double valorCarro) {
		this.valorCarro = valorCarro;
	}
	public Carro() {
		super();
	}
	public Carro(String chassi, String cor, Integer anoFabricacao,
			VersaoCarro versaoCarro, Double valorCarro) {
		super();
		this.chassi = chassi;
		this.cor = cor;
		this.anoFabricacao = anoFabricacao;
		this.versaoCarro = versaoCarro;
		this.valorCarro = valorCarro;
	}
	@Override
	public String toString() {
		return "Carro [chassi=" + chassi + ", cor=" + cor + ", anoFabricacao="
				+ anoFabricacao + ", versaoCarro=" + versaoCarro
				+ ", valorCarro=" + valorCarro + "]";
	}
	
	
}
