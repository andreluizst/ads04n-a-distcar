package classesBasicas;

import java.util.List;

import javax.persistence.*;

@Entity
public class VersaoModeloCarro extends ObjetoGeral {
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="codModelo", insertable=true, updatable=true, nullable=false)
	private ModeloCarro modelo;
	
	private String descricaoVersModCarro;
	
	// verificar melhor forma de relacionar os itens de s�rie
	private List<ItemSerieCarro> listaItensSeriesCarro;
	
	// verificar melhor forma de relacionar os acess�rios
	private List<AcessorioCarro> listaAcessorioCarro;
	
	
	
	public VersaoModeloCarro() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public VersaoModeloCarro(ModeloCarro modelo, String descricaoVersModCarro,
			List<ItemSerieCarro> listaItensSeriesCarro,
			List<AcessorioCarro> listaAcessorioCarro) {
		super();
		this.modelo = modelo;
		this.descricaoVersModCarro = descricaoVersModCarro;
		this.listaItensSeriesCarro = listaItensSeriesCarro;
		this.listaAcessorioCarro = listaAcessorioCarro;
	}
	
	
	
	public String getDescricaoVersModCarro() {
		return descricaoVersModCarro;
	}

	public void setDescricaoVersModCarro(String descricaoVersModCarro) {
		this.descricaoVersModCarro = descricaoVersModCarro;
	}
	
	public List<ItemSerieCarro> getListaItensSeriesCarro() {
		return listaItensSeriesCarro;
	}
	
	public void setListaItensSeriesCarro(List<ItemSerieCarro> listaItensSeriesCarro) {
		this.listaItensSeriesCarro = listaItensSeriesCarro;
	}
	
	public List<AcessorioCarro> getListaAcessorioCarro() {
		return listaAcessorioCarro;
	}
	
	public void setListaAcessorioCarro(List<AcessorioCarro> listaAcessorioCarro) {
		this.listaAcessorioCarro = listaAcessorioCarro;
	}
	
	
	
}
