package fachada;

import java.util.List;

import negocio.ControladorCarro;
import negocio.ControladorOrganizacional;
import classesBasicas.Carro;
import classesBasicas.Centro;
import classesBasicas.Departamento;
import classesBasicas.Fabricante;
import classesBasicas.Funcao;
import classesBasicas.Funcionario;
import classesBasicas.Gestor;
import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.Pessoa;
import classesBasicas.TipoGerencia;
import classesBasicas.VersaoCarro;

public class Fachada implements IFachada {
	private static Fachada instancia;
	private ControladorOrganizacional ctrlOrg;
	private ControladorCarro controladorCarro;
	
	public Fachada(){
		ctrlOrg = new ControladorOrganizacional();
		this.controladorCarro = new ControladorCarro();
	}
	
	public static Fachada obterInstancia(){
		if (instancia == null)
			instancia = new Fachada();
		return instancia;
	}
	
	//***********************************************************************************
	//***********************  C R U D    Organizacional ********************************
	//***********************************************************************************
	
	//******************** Função ******************************
	@Override
	public void salvarFuncao(Funcao funcao) throws Exception {
		// TODO Auto-generated method stub
		//ctrlFuncao
		ctrlOrg.inserirFuncao(funcao);
	}
	
	@Override
	public void excluirFuncao(Funcao funcao) throws Exception {
		// TODO Auto-generated method stub
		if (ctrlOrg.funcaoExiste(funcao))
			ctrlOrg.removerFuncao(funcao);
	}

	@Override
	public List<Funcao> listarFuncoes() throws Exception {
		// TODO Auto-generated method stub
		return ctrlOrg.listarFuncoes();
	}
	
	@Override
	public List<Funcao> consultarFuncao(Funcao funcao) throws Exception{
		return ctrlOrg.pesquisarFuncao(funcao);
	}
	
	
	//**************************  C E N T R O  **********************************************
	@Override
	public void salvarCentro(Centro centro) throws Exception{
		
	}
	
	@Override
	public void excluirCentro(Centro centro) throws Exception{
		
	}
	
	@Override
	public List<Centro> consultarCentro(Centro centro) throws Exception{
		//Falta implementar
		return null;
	}
	
	@Override
	public List<Centro> listarCentros() throws Exception{
		// Falta implementar
		return null;
	}
	
	//**************************  F U N C I O N Á R I O  ************************************
	@Override
	public void salvarFuncionario(Funcionario funcionario) throws Exception{
		
	}
	
	@Override
	public void excluirFuncionario(Funcionario funcaionario) throws Exception{
		
	}
	
	@Override
	public List<Funcionario> consultarFuncionario(Funcionario funcaionario) throws Exception{
		//Falta implementar
		return null;
	}
	
	//**************************  D E P A R T A M E N T O  **********************************
	@Override
	public void salvarDepartamento(Departamento depto) throws Exception{
		
	}
	
	@Override
	public void excluirDepartamento(Departamento depto) throws Exception{
		
	}
	
	@Override
	public List<Departamento> consultarDepartamento(Departamento depto) throws Exception{
		//Falta implementar
		return null;
	}
	
	@Override
	public List<Departamento> listarDepartamentos() throws Exception{
		//Falta implementar
		return null;
	}
	
	//*******************************  G E S T O R  *****************************************
	@Override
	public void salvarGestor(Gestor gestor) throws Exception{
		
	}
	
	@Override
	public void excluirGestor(Gestor gestor) throws Exception{
		
	}
	
	@Override
	public List<Gestor> consultarGestor(Gestor gestor) throws Exception{
		//Falta implementar
				return null;
	}
	
	@Override
	public List<Gestor> listarGestores() throws Exception{
		//Falta implementar
		return null;
	}
	
	//************************  T I P O   G E R E N C I A  **********************************
	@Override
	public void salvarTipoGerencia(TipoGerencia tipoGerencia) throws Exception{
		
	}
	
	@Override
	public void excluirGerencia(TipoGerencia tipoGerencia) throws Exception{
		
	}
	
	@Override
	public List<TipoGerencia> consultarGerencia(TipoGerencia tipoGerencia) throws Exception{
		//Falta implementar
		return null;
	}
	
	@Override
	public List<TipoGerencia> listarTiposGerencia() throws Exception{
		//Falta implementar
		return null;
	}
	
	
	//***********************************************************************************
	//**************** F I M   C R U D    Organizacional ********************************
	//***********************************************************************************

	//Carro - Felipe Carlos
	
	@Override
	public void salvarCarro(Carro carro) throws Exception {
		// TODO Auto-generated method stub
		this.controladorCarro.inserir(carro);
	}

	@Override
	public void salvarItemSerie(ItemSerieCarro itemSerieCarro) throws Exception {
		// TODO Auto-generated method stub
		this.controladorCarro.inserir(itemSerieCarro);
	}

	@Override
	public void salvarVersaoModeloCarro(VersaoCarro versaoCarro)
			throws Exception {
		// TODO Auto-generated method stub
		this.controladorCarro.inserir(versaoCarro);
		
	}

	@Override
	public void salvarModeloCarro(ModeloCarro modeloCarro) throws Exception {
		// TODO Auto-generated method stub
		this.controladorCarro.inserir(modeloCarro);
	}

	@Override
	public List<VersaoCarro> listarVersao() throws Exception {
		return this.controladorCarro.consultarTodasVersao();
	}
	
	public List<ModeloCarro> listarModelo() throws Exception {
		return this.controladorCarro.consultarTodosModelos();
	}

	@Override
	public VersaoCarro pesquisarVersao(int codigo) {
		return this.controladorCarro.pesquisarVersao(codigo);
	}

	@Override
	public ModeloCarro pesquisarModeloCarro(int codigo) {
		return this.controladorCarro.pesquisarModeloCarro(codigo);
	}

	@Override
	public List<ItemSerieCarro> listarItem() {
		// TODO Auto-generated method stub
		return this.controladorCarro.listarItem();
	}

	@Override
	public void salvarCliente(Pessoa cliente) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluirCliente(Pessoa cliente) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Pessoa> consultarCliente(Pessoa cliente) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pessoa> listarClientes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void salvarFabricante(Fabricante fabricante) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluirFabricante(Fabricante fabricante) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Fabricante> consultarFabricante(Fabricante fabricante)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fabricante> listarFabricantes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
