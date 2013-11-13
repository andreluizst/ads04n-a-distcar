package fachada;

import java.util.List;



/*import classesBasicas.Carro;
import classesBasicas.Funcao;
import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoCarro;
*/
import classesBasicas.*;

public interface IFachada {
	//***********************************************************************************
	//************************* O R G A N I Z A C I O N A L *****************************
	//***********************************************************************************
	
	// *************************  F U N Ç Ã O  *******************************************
	public void salvarFuncao(Funcao funcao) throws Exception;
	public List<Funcao> listarFuncoes() throws Exception;
	public void excluirFuncao(Funcao funcao) throws Exception;
	public List<Funcao> consultarFuncao(Funcao funcao) throws Exception;
	public Funcao pegarFuncaoPorId(Integer codigo) throws Exception;
	
	//**************************  C E N T R O  **********************************************
	public void salvarCentro(Centro centro) throws Exception;
	public void excluirCentro(Centro centro) throws Exception;
	public List<Centro> consultarCentro(Centro centro) throws Exception;
	public List<Centro> listarCentros() throws Exception;
	public Centro pegarCentroPorId(Integer codigo) throws Exception;
	
	//**************************  F U N C I O N Á R I O  ************************************
	public void salvarFuncionario(Funcionario funcionario) throws Exception;
	public void excluirFuncionario(Funcionario funcaionario) throws Exception;
	public List<Funcionario> consultarFuncionario(Funcionario funcaionario) throws Exception;
	
	//**************************  D E P A R T A M E N T O  **********************************
	public void salvarDepartamento(Departamento depto) throws Exception;
	public void excluirDepartamento(Departamento depto) throws Exception;
	public List<Departamento> consultarDepartamento(Departamento depto) throws Exception;
	public List<Departamento> listarDepartamentos() throws Exception;
	public Departamento pegarDepartamentoPorId(Integer codigo) throws Exception;
	
	//*******************************  G E S T O R  *****************************************
	public void salvarGestor(Gestor gestor) throws Exception;
	public void excluirGestor(Gestor gestor) throws Exception;
	public List<Gestor> consultarGestor(Gestor gestor) throws Exception;
	public List<Gestor> listarGestores() throws Exception;
	public Gestor pegarGestorPorId(Integer codigo) throws Exception;
	
	//************************  T I P O   G E R E N C I A  **********************************
	public void salvarTipoGerencia(TipoGerencia tipoGerencia) throws Exception;
	public void excluirTipoGerencia(TipoGerencia tipoGerencia) throws Exception;
	public List<TipoGerencia> consultarTipoGerencia(TipoGerencia tipoGerencia) throws Exception;
	public List<TipoGerencia> listarTiposGerencia() throws Exception;
	
	
	//******************  T I P O S   L O G R A D O U R O S  *********************************
	public void salvarTipoLogradouro(TipoLogradouro tipoLogradouro) throws Exception;
	public List<TipoLogradouro> listarTiposLogradouros() throws Exception;
	public TipoLogradouro pegarTipoLogradouroPorId(Integer codigo) throws Exception;
	
	
	//***************************  C I D A D E  S  *******************************************
	public void salvarCidade(Cidade cidade) throws Exception;
	public List<Cidade> listarCidades() throws Exception;
	public List<Cidade> consultarCidadesPorUF(UnidadeFederativa uf) throws Exception;
	public List<Cidade> consultarCidade(Cidade cidade) throws Exception;
	public Cidade pegarCidadePorId(Integer codigo) throws Exception;
	public void excluirCidade(Cidade cidade) throws Exception;
	
	//*******************  U N I D A D E   F E D E R A T I V A  ****************************
	public List<UnidadeFederativa> listarUFs() throws Exception;
	public UnidadeFederativa pegarUnidadeFederativaPorId(Integer codigo) throws Exception;
	
	
	//***********************  P E S S O A     J U R Í D I C A  *************************
	public List<PessoaJuridica> listarPJ() throws Exception;
	public PessoaJuridica pegarPessoaJuridicaPorId(Integer codigo) throws Exception;
	
	//************************  E S C O L A R I D A D E  ********************************
	public void salvarEscolaridade(Escolaridade escolaridade) throws Exception;
	public List<Escolaridade> consultarEscolaridade(Escolaridade escolaridade) throws Exception;
	public void excluirEscolaridade(Escolaridade escolaridade) throws Exception;
	public Escolaridade pegarEscolaridadePorId(Integer codigo) throws Exception;
	public List<Escolaridade> listarEscolaridades() throws Exception;
	
	//***********************************************************************************
	//*******************************  V E N D A S  *************************************
	//***********************************************************************************
	

	
	//****************************  C L I E N T E  ***************************************
	public void salvarCliente(Cliente cliente) throws Exception;
	public void excluirCliente(Cliente cliente) throws Exception;
	public List<Cliente> pesquisarCliente(Cliente exemplo) throws Exception;
	public List<Cliente> consultarCliente(Cliente cliente) throws Exception;
	public List<Cliente> listarClientes() throws Exception;
	
	//***********************  F A B R I C A N T E  ***************************************
	public void salvarFabricante(Fabricante fabricante) throws Exception;
	public void excluirFabricante(Fabricante fabricante) throws Exception;
	public List<Fabricante> consultarFabricante(Fabricante fabricante) throws Exception;
	public List<Fabricante> listarFabricantes() throws Exception;	
	public Fabricante consultarFabricantePorId(Integer codigo) throws Exception;
	
	
	
	//***********************************************************************************
	//*************************  M O V I M E N T A Ç Ã O  *******************************
	//***********************************************************************************
	
	
	//Carro
	
	public void salvarCarro(Carro carro);
	public void removerCarro(Carro carro);
	public List<Carro> listarCarros();
	public Carro pesquisarCarroCodigo(int codigo);
	public List<Carro> consultarCarros(Carro carro);
		
	//Marca
	
	public void salvarMarcaCarro(MarcaCarro marcaCarro);
	public void removerMarcaCarro(MarcaCarro marcaCarro);
	public List<MarcaCarro> listarMarcasCarros();
	public MarcaCarro pesquisarMarcasCarroCodigo(int codigo);
	public List<MarcaCarro> consultarMarcasCarros(MarcaCarro marcaCarro);
	public Fabricante pesquisarFabricanteCodigo(int codigo);
	
	//Modelo
		
	public void salvarModeloCarro(ModeloCarro modeloCarro);
	public void removerModeloCarro(ModeloCarro modeloCarro);
	public List<ModeloCarro> listarModelosCarros();
	public ModeloCarro pesquisarModelosCarroCodigo(int codigo);
	public List<ModeloCarro> consultarModelosCarros(ModeloCarro modeloCarro);
		
	//Versao
		
	public void salvarVersao(VersaoCarro versaoCarro);
	public void removerVersao(VersaoCarro versaoCarro);
	public List<VersaoCarro> listarVersoes();
	public VersaoCarro pesquisarVersaoCodigo(int codigo);
	public List<VersaoCarro> consultarVersoes(VersaoCarro versaoCarro);
	public List<ItemSerieCarro> listarItensPorModelo(ModeloCarro modelo);
	public List<AcessorioCarro> listarAcessoriosPorModelo(ModeloCarro modelo);
		
	//Item 
		
	public void salvarItemSerie(ItemSerieCarro itemSerieCarro)throws Exception;
	public void removerItem(ItemSerieCarro itemSerieCarro);
	public List<ItemSerieCarro> listarItens();
	public ItemSerieCarro pesquisarItemCodigo(int codigo);
	public List<ItemSerieCarro> consultarItens(ItemSerieCarro itemSerieCarro);
		
	//Acessório
	
	public void salvarAcessorio(AcessorioCarro acessorioCarro);
	public void removerAcessorio(AcessorioCarro acessorioCarro);
	public List<AcessorioCarro> listarAcessorios();
	public AcessorioCarro pesquisarAcessorioCodigo(int codigo);
	public List<AcessorioCarro> consultarAcessorios(AcessorioCarro acessorioCarro);
		
}
