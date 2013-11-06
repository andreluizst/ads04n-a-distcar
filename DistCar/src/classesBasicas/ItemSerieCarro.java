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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((modeloCarro == null) ? 0 : modeloCarro.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorItemSerie);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemSerieCarro other = (ItemSerieCarro) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (modeloCarro == null) {
			if (other.modeloCarro != null)
				return false;
		} else if (!modeloCarro.equals(other.modeloCarro))
			return false;
		if (Double.doubleToLongBits(valorItemSerie) != Double
				.doubleToLongBits(other.valorItemSerie))
			return false;
		return true;
	}
	public ItemSerieCarro() {
		super();
	}
	@Override
	public String toString() {
		return "ItemSerieCarro [descricao=" + descricao + ", valorItemSerie="
				+ valorItemSerie + ", modeloCarro=" + modeloCarro
				+ ", getCodigo()=" + getCodigo()
				+ ", getDataUltimaAtualizacao()=" + getDataUltimaAtualizacao()
				+ ", getSituacao()=" + getSituacao() + "]";
	}

	
}
