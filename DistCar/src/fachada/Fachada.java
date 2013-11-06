package fachada;

import java.util.List;

import negocio.ControladorCarro;
import negocio.ControladorOrganizacional;
import classesBasicas.Carro;
import classesBasicas.Centro;
import classesBasicas.Cidade;
import classesBasicas.Departamento;
import classesBasicas.Fabricante;
import classesBasicas.Funcao;
import classesBasicas.Funcionario;
import classesBasicas.Gestor;
import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.Pessoa;
import classesBasicas.TipoGerencia;
import classesBasicas.TipoLogradouro;
import classesBasicas.UnidadeFederativa;
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
	//******************  C R U D    O R G A N I Z A C I O N A L ************************
	//***********************************************************************************
	
	//*****************************  F U N Ç Ã O  ***************************************
	@Override
	public void salvarFuncao(Funcao funcao) throws Exception {
		// TODO Auto-generated method stub
		if (ctrlOrg.funcaoExiste(funcao))
			ctrlOrg.alterarFuncao(funcao);
		else
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
		if (ctrlOrg.centroExiste(centro))
			ctrlOrg.alterarCentro(centro);
		else
			ctrlOrg.inserirCentro(centro);
	}
	
	@Override
	public void excluirCentro(Centro centro) throws Exception{
		if (ctrlOrg.centroExiste(centro))
			ctrlOrg.removerCentro(centro);
	}
	
	@Override
	public List<Centro> consultarCentro(Centro centro) throws Exception{
		return ctrlOrg.consultarCentro(centro);
	}
	
	@Override
	public List<Centro> listarCentros() throws Exception{
		return ctrlOrg.listarCentros();
	}
	
	@Override
	public Centro pegarCentroPorId(Integer codigo) throws Exception {
		return ctrlOrg.pegarCentroPorId(codigo);
	}
	
	//**************************  F U N C I O N Á R I O  ************************************
	@Override
	public void salvarFuncionario(Funcionario funcionario) throws Exception{
		if (ctrlOrg.funcionarioExiste(funcionario))
			ctrlOrg.alterarFuncionario(funcionario);
		else
			ctrlOrg.inserirFuncionario(funcionario);
	}
	
	@Override
	public void excluirFuncionario(Funcionario funcaionario) throws Exception{
		ctrlOrg.removerFuncionario(funcaionario);
	}
	
	@Override
	public List<Funcionario> consultarFuncionario(Funcionario funcaionario) throws Exception{
		return ctrlOrg.pesquisarFuncionario(funcaionario);
	}
	
	//**************************  D E P A R T A M E N T O  **********************************
	@Override
	public void salvarDepartamento(Departamento depto) throws Exception{
		if (ctrlOrg.departamentoExiste(depto))
			ctrlOrg.alterarDepartamento(depto);
		else
			ctrlOrg.inserirDepartamento(depto);
	}
	
	@Override
	public void excluirDepartamento(Departamento depto) throws Exception{
		ctrlOrg.removerDepartamento(depto);
	}
	
	@Override
	public List<Departamento> consultarDepartamento(Departamento depto) throws Exception{
		return ctrlOrg.pesquisarDepartamento(depto);
	}
	
	@Override
	public List<Departamento> listarDepartamentos() throws Exception{
		return ctrlOrg.listarDepartamentos();
	}
	
	@Override
	public Departamento pegarDepartamentoPorId(Integer codigo) throws Exception {
		return ctrlOrg.pegarDepartamentoPorId(codigo);
	}
	
	//*******************************  G E S T O R  *****************************************
	@Override
	public void salvarGestor(Gestor gestor) throws Exception{
		if (ctrlOrg.gestorExiste(gestor))
			ctrlOrg.alterarGestor(gestor);
		else
			ctrlOrg.inserirGestor(gestor);
	}
	
	@Override
	public void excluirGestor(Gestor gestor) throws Exception{
		ctrlOrg.removerGestor(gestor);
	}
	
	@Override
	public List<Gestor> consultarGestor(Gestor gestor) throws Exception{
		return ctrlOrg.pesquisarGestor(gestor);
	}
	
	@Override
	public List<Gestor> listarGestores() throws Exception{
		return ctrlOrg.listarGestores();
	}
	
	@Override
	public Gestor pegarGestorPorId(Integer codigo) throws Exception {
		return ctrlOrg.pegarGestorPorId(codigo);
	}
	
	//************************  T I P O   G E R E N C I A  **********************************
	@Override
	public void salvarTipoGerencia(TipoGerencia tipoGerencia) throws Exception{
		if (ctrlOrg.tipoGerenciaExiste(tipoGerencia))
			ctrlOrg.alterarTipoGerencia(tipoGerencia);
		else
			ctrlOrg.inserirTipoGerencia(tipoGerencia);
	}
	
	@Override
	public void excluirGerencia(TipoGerencia tipoGerencia) throws Exception{
		ctrlOrg.removerTipoGerencia(tipoGerencia);
	}
	
	@Override
	public List<TipoGerencia> consultarGerencia(TipoGerencia tipoGerencia) throws Exception{
		//Falta implementar
		return null;
	}
	
	@Override
	public List<TipoGerencia> listarTiposGerencia() throws Exception{
		return ctrlOrg.listarTiposGerencia();
	}
	
	//*********************  T I P O   L O G R A D O U R O  **********************************
	@Override
	public void salvarTipoLogradouro(TipoLogradouro tipoLogradouro) throws Exception{
		if (ctrlOrg.tipoLogradouroExiste(tipoLogradouro))
			ctrlOrg.alterarTipoLogradouro(tipoLogradouro);
		else
			ctrlOrg.inserirTipoLogradouro(tipoLogradouro);
	}
	
	@Override
	public List<TipoLogradouro> listarTiposLogradouros() throws Exception{
		return ctrlOrg.listarTiposLogradouros();
	}
	
	//****************************  C I D A D E  *****************************************
	@Override
	public void salvarCidade(Cidade cidade) throws Exception{
		if (ctrlOrg.cidadeExiste(cidade))
			ctrlOrg.alterarCidade(cidade);
		else
			ctrlOrg.inserirCidade(cidade);
	}
	
	@Override
	public List<Cidade> listarCidades() throws Exception{
		return ctrlOrg.listarCidades();
	}
	
	@Override
	public Cidade pegarCidadePorId(Integer codigo) throws Exception{
		return ctrlOrg.pegarCidadePorId(codigo);
	}
	
	//*******************  U N I D A D E   F E D E R A T I V A  ****************************
	@Override
	public List<UnidadeFederativa> listarUFs() throws Exception{
		return ctrlOrg.listarUFs();
	}
	
	
	//***********************************************************************************
	//********************* F I M   C R U D    Organizacional ***************************
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

	@Override
	public ItemSerieCarro pesquisarItem(int codigo) {
		return this.controladorCarro.pesquisarItem(codigo);
	}

	@Override
	public void removerItem(ItemSerieCarro itemSerieCarro) {
		this.controladorCarro.remover(itemSerieCarro);
		
	}

	@Override
	public void alterarItem(ItemSerieCarro itemSerieCarro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ItemSerieCarro> pesquisarItens(ItemSerieCarro itemSerieCarro) {
		return this.controladorCarro.pesquisarItens(itemSerieCarro);
		
	}

	@Override
	public List<ItemSerieCarro> pesquisarPorModelo(Integer codigo) {
		return this.controladorCarro.pesquisarPorModelo(codigo);
	}


}
