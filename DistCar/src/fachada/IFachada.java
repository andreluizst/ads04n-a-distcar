package fachada;

import java.util.List;

import classesBasicas.*;

import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoCarro;

public interface IFachada {
	
	//***********************************************************************************
	//************************* O R G A N I Z A C I O N A L *****************************
	//***********************************************************************************
	
	// *************************  F U N Ç Ã O  *******************************************
	public void salvarFuncao(Funcao funcao) throws Exception;
	public List<Funcao> listarFuncoes() throws Exception;
	public void excluirFuncao(Funcao funcao) throws Exception;
	public List<Funcao> consultarFuncao(Funcao funcao) throws Exception;
	
	//**************************  C E N T R O  **********************************************
	public void salvarCentro(Centro centro) throws Exception;
	public void excluirCentro(Centro centro) throws Exception;
	public List<Centro> consultarCentro(Centro centro) throws Exception;
	public List<Centro> listarCentros() throws Exception;
	
	//**************************  F U N C I O N Á R I O  ************************************
	public void salvarFuncionario(Funcionario funcionario) throws Exception;
	public void excluirFuncionario(Funcionario funcaionario) throws Exception;
	public List<Funcionario> consultarFuncionario(Funcionario funcaionario) throws Exception;
	
	//**************************  D E P A R T A M E N T O  **********************************
	public void salvarDepartamento(Departamento depto) throws Exception;
	public void excluirDepartamento(Departamento depto) throws Exception;
	public List<Departamento> consultarDepartamento(Departamento depto) throws Exception;
	public List<Departamento> listarDepartamentos() throws Exception;
	
	//*******************************  G E S T O R  *****************************************
	public void salvarGestor(Gestor gestor) throws Exception;
	public void excluirGestor(Gestor gestor) throws Exception;
	public List<Gestor> consultarGestor(Gestor gestor) throws Exception;
	public List<Gestor> listarGestores() throws Exception;
	
	//************************  T I P O   G E R E N C I A  **********************************
	public void salvarTipoGerencia(TipoGerencia tipoGerencia) throws Exception;
	public void excluirGerencia(TipoGerencia tipoGerencia) throws Exception;
	public List<TipoGerencia> consultarGerencia(TipoGerencia tipoGerencia) throws Exception;
	public List<TipoGerencia> listarTiposGerencia() throws Exception;
	
	
	//***********************************************************************************
	//*******************************  V E N D A S  *************************************
	//***********************************************************************************
	
	//****************************  C A R R O  **********************************************
	public void salvarCarro(Carro carro) throws Exception;
	public void salvarItemSerie(ItemSerieCarro itemSerieCarro)throws Exception;
	public void salvarVersaoModeloCarro(VersaoCarro versaoCarro) throws Exception;
	public void salvarModeloCarro(ModeloCarro modeloCarro) throws Exception;
	public List<VersaoCarro> listarVersao() throws Exception;
	public VersaoCarro pesquisarVersao(int codigo);
	public ModeloCarro pesquisarModeloCarro(int codigo);
	public List<ItemSerieCarro> listarItem();
	
	//****************************  C L I E N T E  ***************************************
	public void salvarCliente(Pessoa cliente) throws Exception;
	public void excluirCliente(Pessoa cliente) throws Exception;
	public List<Pessoa> consultarCliente(Pessoa cliente) throws Exception;
	public List<Pessoa> listarClientes() throws Exception;
	
	//***********************  F A B R I C A N T E  ***************************************
	public void salvarFabricante(Fabricante fabricante) throws Exception;
	public void excluirFabricante(Fabricante fabricante) throws Exception;
	public List<Fabricante> consultarFabricante(Fabricante fabricante) throws Exception;
	public List<Fabricante> listarFabricantes() throws Exception;	
	
	
	
	//***********************************************************************************
	//*************************  M O V I M E N T A Ç Ã O  *******************************
	//***********************************************************************************
	
	
}
