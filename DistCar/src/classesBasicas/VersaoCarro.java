package classesBasicas;


import javax.persistence.*;


@Entity
public class VersaoCarro extends ObjetoGeral {
	
	private double valor;
	@Column(unique=true)
	private String descricao;
	@ManyToOne
	private ModeloCarro modeloCarro;
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public ModeloCarro getModeloCarro() {
		return modeloCarro;
	}
	public void setModeloCarro(ModeloCarro modeloCarro) {
		this.modeloCarro = modeloCarro;
	}
	public VersaoCarro() {
		super();
	}
	public VersaoCarro(double valor, String descricao, ModeloCarro modeloCarro) {
		super();
		this.valor = valor;
		this.descricao = descricao;
		this.modeloCarro = modeloCarro;
	}
	@Override
	public String toString() {
		return "VersaoCarro [valor=" + valor + ", descricao=" + descricao
				+ ", modeloCarro=" + modeloCarro + "]";
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
		temp = Double.doubleToLongBits(valor);
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
		VersaoCarro other = (VersaoCarro) obj;
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
		if (Double.doubleToLongBits(valor) != Double
				.doubleToLongBits(other.valor))
			return false;
		return true;
	}
	
}
