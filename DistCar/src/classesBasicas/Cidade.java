package classesBasicas;

//import comum.ObjetoGeral;
import javax.persistence.*;

@Entity
public class Cidade extends ObjetoGeral {

	@Column(length=80, nullable=false)
	private String nome;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="coduf", insertable=true, updatable=true)
	private UnidadeFederativa unidadeFederativa;
	
	
	
	public Cidade() {
		super();
		unidadeFederativa = new UnidadeFederativa();
	}
	
	public Cidade(String nome, UnidadeFederativa unidadeFederativa) {
		super();
		this.nome = nome;
		this.unidadeFederativa = unidadeFederativa;
	}



	public UnidadeFederativa getUnidadeFederativa() {
		return unidadeFederativa;
	}

	public void setUnidadeFederativa(UnidadeFederativa unidadeFederativa) {
		this.unidadeFederativa = unidadeFederativa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString(){
		return getNome() +"/" + getUnidadeFederativa().getSigla();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime
				* result
				+ ((unidadeFederativa == null) ? 0 : unidadeFederativa
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Cidade))
			return false;
		Cidade other = (Cidade) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (unidadeFederativa == null) {
			if (other.unidadeFederativa != null)
				return false;
		} else if (!unidadeFederativa.equals(other.unidadeFederativa))
			return false;
		return true;
	}
	
	
}
