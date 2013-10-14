package classesBasicas;


import javax.persistence.*;


@Entity
public class ModeloCarro extends ObjetoGeral {
	
	@Column(length=100, nullable=false)
	private String descricaoModeloCarro;
	@ManyToOne(cascade=CascadeType.PERSIST)
	private MarcaCarro marcaCarro;
	@Column(length=10, nullable=false)
	private Integer anoModelo;
	
	public String getDescricaoModeloCarro() {
		return descricaoModeloCarro;
	}
	public void setDescricaoModeloCarro(String descricaoModeloCarro) {
		this.descricaoModeloCarro = descricaoModeloCarro;
	}
	public MarcaCarro getMarcaCarro() {
		return marcaCarro;
	}
	public void setMarcaCarro(MarcaCarro marcaCarro) {
		this.marcaCarro = marcaCarro;
	}
	public Integer getAnoModelo() {
		return anoModelo;
	}
	public void setAnoModelo(Integer anoModelo) {
		this.anoModelo = anoModelo;
	}
	public ModeloCarro(String descricaoModeloCarro, MarcaCarro marcaCarro,
			Integer anoModelo) {
		super();
		this.descricaoModeloCarro = descricaoModeloCarro;
		this.marcaCarro = marcaCarro;
		this.anoModelo = anoModelo;
	}
	public ModeloCarro() {
		super();
	}
	@Override
	public String toString() {
		return "ModeloCarro [descricaoModelocarro=" + descricaoModeloCarro
				+ ", marcaCarro=" + marcaCarro + ", anoModelo=" + anoModelo
				+ "]";
	}

}
