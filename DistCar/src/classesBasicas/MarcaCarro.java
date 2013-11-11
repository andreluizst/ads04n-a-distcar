package classesBasicas;


import javax.persistence.*;

@Entity
public class MarcaCarro extends ObjetoGeral {
	
	@Column(unique=true)
	private String descricao;
	@ManyToOne
	private Fabricante fabricante;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Fabricante getFabricante() {
		return fabricante;
	}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	public MarcaCarro() {
		super();
	}
	public MarcaCarro(String descricao, Fabricante fabricante) {
		super();
		this.descricao = descricao;
		this.fabricante = fabricante;
	}
	@Override
	public String toString() {
		return "MarcaCarro [descricao=" + descricao + ", fabricante="
				+ fabricante + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((fabricante == null) ? 0 : fabricante.hashCode());
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
		MarcaCarro other = (MarcaCarro) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (fabricante == null) {
			if (other.fabricante != null)
				return false;
		} else if (!fabricante.equals(other.fabricante))
			return false;
		return true;
	}
	
		
}
