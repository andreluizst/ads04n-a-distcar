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
		// TODO Auto-generated constructor stub
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
}
