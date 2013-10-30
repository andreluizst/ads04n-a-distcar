package classesBasicas;


import javax.persistence.*;


@Entity
public class VersaoModeloCarro extends ObjetoGeral {
	
	private double valorVersao;
	@Column(unique=true)
	private String descricaoVersaoModeloCarro;
	@ManyToOne
	private ModeloCarro modeloCarro;
	
	public double getValorVersao() {
		return valorVersao;
	}
	public void setValorVersao(double valorVersao) {
		this.valorVersao = valorVersao;
	}
	public String getDescricaoVersaoModeloCarro() {
		return descricaoVersaoModeloCarro;
	}
	public void setDescricaoVersaoModeloCarro(String descricaoVersaoModeloCarro) {
		this.descricaoVersaoModeloCarro = descricaoVersaoModeloCarro;
	}
	public ModeloCarro getModeloCarro() {
		return modeloCarro;
	}
	public void setModeloCarro(ModeloCarro modeloCarro) {
		this.modeloCarro = modeloCarro;
	}

	public VersaoModeloCarro(double valorVersao,
			String descricaoVersaoModeloCarro, ModeloCarro modeloCarro) {
		super();
		this.valorVersao = valorVersao;
		this.descricaoVersaoModeloCarro = descricaoVersaoModeloCarro;
		this.modeloCarro = modeloCarro;
		
	}
	
	public VersaoModeloCarro() {
		super();
	}
	@Override
	public String toString() {
		return "VersaoModeloCarro [valorVersao=" + valorVersao
				+ ", descricaoVersaoModeloCarro=" + descricaoVersaoModeloCarro
				+ ", modeloCarro=" + modeloCarro + "]";
	}
	
}
