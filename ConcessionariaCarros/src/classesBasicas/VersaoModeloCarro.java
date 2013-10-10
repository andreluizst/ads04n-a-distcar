package classesBasicas;

import javax.persistence.*;

@Entity
public class VersaoModeloCarro extends ObjetoGeral {
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="codModelo", insertable=true, updatable=true, nullable=false)
	private ModeloCarro modelo;
	
	private String descricaoVersModCarro;
	
	// verificar melhor forma de relacionar os itens de série
	//private List<ItemSerieCarro> listaItensSeriesCarro;
	
	// verificar melhor forma de relacionar os acessórios
	//private List<AcessorioCarro> listaAcessorioCarro;
	
	
	
	public VersaoModeloCarro() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public VersaoModeloCarro(ModeloCarro modelo, String descricaoVersModCarro) {
		super();
		this.modelo = modelo;
		this.descricaoVersModCarro = descricaoVersModCarro;
		//this.listaItensSeriesCarro = listaItensSeriesCarro;
		//this.listaAcessorioCarro = listaAcessorioCarro;
	}
	
	
	
	public String getDescricaoVersModCarro() {
		return descricaoVersModCarro;
	}

	public void setDescricaoVersModCarro(String descricaoVersModCarro) {
		this.descricaoVersModCarro = descricaoVersModCarro;
	}
	

	
	
	
}
