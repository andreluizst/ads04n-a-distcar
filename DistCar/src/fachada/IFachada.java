package fachada;

import java.util.List;

import classesBasicas.Carro;
import classesBasicas.Funcao;
import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoCarro;

public interface IFachada {
	public void salvarFuncao(Funcao funcao) throws Exception;
	public List<Funcao> listarFuncoes() throws Exception;
	public void excluirFuncao(Funcao funcao) throws Exception;
	public List<Funcao> consultarFuncao(Funcao funcao) throws Exception;
	
	
	//Carro
	
		public void salvarCarro(Carro carro) throws Exception;
	
	//Modelo
		
		public void salvarModeloCarro(ModeloCarro modeloCarro) throws Exception;
		public ModeloCarro pesquisarModeloCarro(int codigo);
		
	//Versao
		
		public List<VersaoCarro> listarVersao() throws Exception;
		public VersaoCarro pesquisarVersao(int codigo);
	
	//Item 
		
		public List<ItemSerieCarro> listarItem();
		public void salvarItemSerie(ItemSerieCarro itemSerieCarro)throws Exception;
		public ItemSerieCarro pesquisarItem(int codigo);
		public void removerItem(ItemSerieCarro itemSerieCarro);
		public void alterarItem(ItemSerieCarro itemSerieCarro);
		public List<ItemSerieCarro> pesquisarItens(ItemSerieCarro itemSerieCarro);
		
	//Acessório
	
	





}
