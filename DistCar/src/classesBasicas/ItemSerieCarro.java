package classesBasicas;


import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class ItemSerieCarro extends ObjetoGeral{

	private String descricao;
	private double valorItemSerie;
	@ManyToOne
	private ModeloCarro modeloCarro;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValorItemSerie() {
		return valorItemSerie;
	}
	public void setValorItemSerie(double valorItemSerie) {
		this.valorItemSerie = valorItemSerie;
	}
	public ModeloCarro getModeloCarro() {
		return modeloCarro;
	}
	public void setModeloCarro(ModeloCarro modeloCarro) {
		this.modeloCarro = modeloCarro;
	}
	
	
	public ItemSerieCarro(Integer codigo, String descricao,ModeloCarro modeloCarro, double valorItemSerie,
			Calendar dataUltimaAtualizacao,Situacao situacao) {
		super(codigo, dataUltimaAtualizacao, situacao);
		this.descricao = descricao;
		this.valorItemSerie = valorItemSerie;
		this.modeloCarro = modeloCarro;
	}
	public ItemSerieCarro() {
		super();
	}
	@Override
	public String toString() {
		return "ItemSerieCarro [descricao=" + descricao + ", valorItemSerie="
				+ valorItemSerie + ", modeloCarro=" + modeloCarro + "]";
	}
	
	
	
}
