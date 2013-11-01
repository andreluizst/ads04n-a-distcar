package fachada;

import java.util.List;

import classesBasicas.Carro;
import classesBasicas.Funcao;
import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoModeloCarro;

public interface IFachada {
	public void salvarFuncao(Funcao funcao) throws Exception;
	public List<Funcao> listarFuncoes() throws Exception;
	public void excluirFuncao(Funcao funcao) throws Exception;
	public List<Funcao> consultarFuncao(Funcao funcao) throws Exception;
	
	public void salvarCarro(Carro carro) throws Exception;
	public void salvarItemSerie(ItemSerieCarro itemSerieCarro)throws Exception;
	public void salvarVersaoModeloCarro(VersaoModeloCarro versaoModeloCarro) throws Exception;
	public void salvarModeloCarro(ModeloCarro modeloCarro) throws Exception;
	public List<VersaoModeloCarro> listarVersao() throws Exception;
	public VersaoModeloCarro pesquisarVersao(int codigo);
	public ModeloCarro pesquisarModeloCarro(int codigo);
}
