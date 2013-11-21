package fachada;

import java.util.List;

import negocio.ControladorCarro;
import negocio.ControladorOrganizacional;
import classesBasicas.AcessorioCarro;
import classesBasicas.Carro;
import classesBasicas.Centro;
import classesBasicas.Cidade;
import classesBasicas.Cliente;
import classesBasicas.Departamento;
import classesBasicas.Escolaridade;
import classesBasicas.Fabricante;
import classesBasicas.Funcao;
import classesBasicas.Funcionario;
import classesBasicas.Gestor;
import classesBasicas.ItemSerieCarro;
import classesBasicas.MarcaCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.PessoaJuridica;
import classesBasicas.TipoGerencia;
import classesBasicas.TipoLogradouro;
import classesBasicas.UnidadeFederativa;
import classesBasicas.VersaoCarro;

public class Fachada implements IFachada {
	private static Fachada instancia;
	private ControladorOrganizacional ctrlOrg;
	private ControladorCarro controladorCarro;
	
	private Fachada(){
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
	
	@Override
	public Funcao pegarFuncaoPorId(Integer codigo) throws Exception{
		return ctrlOrg.pegarFuncaoPorId(codigo);
	}
	
	//************************  E S C O L A R I D A D E  ********************************
	@Override
	public void salvarEscolaridade(Escolaridade escolaridade) throws Exception{
		if (ctrlOrg.escolaridadeExiste(escolaridade))
			ctrlOrg.alterarEscolaridade(escolaridade);
		else
			ctrlOrg.inserirEscolaridade(escolaridade);
	}
	
	@Override
	public List<Escolaridade> consultarEscolaridade(Escolaridade escolaridade) throws Exception{
		return ctrlOrg.consultarEscolaridade(escolaridade);
	}
	
	@Override
	public void excluirEscolaridade(Escolaridade escolaridade) throws Exception{
		ctrlOrg.excluirEscolaridade(escolaridade);
	}
	
	@Override
	public Escolaridade pegarEscolaridadePorId(Integer codigo) throws Exception{
		return ctrlOrg.pegarEscolaridadePorId(codigo);
	}
	
	@Override
	public List<Escolaridade> listarEscolaridades() throws Exception{
		return ctrlOrg.listarEscolaridades();
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
	public List<Funcionario> consultarFuncionario(Funcionario funcionario) throws Exception{
		return ctrlOrg.consultarFuncionario(funcionario);
	}
	
	@Override
	public Funcionario pegarFuncionarioPorId(Integer codigo) throws Exception{
		return ctrlOrg.pegarFuncionarioPorId(codigo);
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
	public void excluirTipoGerencia(TipoGerencia tipoGerencia) throws Exception{
		ctrlOrg.removerTipoGerencia(tipoGerencia);
	}
	
	@Override
	public List<TipoGerencia> consultarTipoGerencia(TipoGerencia tipoGerencia) throws Exception{
		return ctrlOrg.listarTiposGerencia();
	}
	
	@Override
	public List<TipoGerencia> listarTiposGerencia() throws Exception{
		return ctrlOrg.listarTiposGerencia();
	}
	
	@Override
	public TipoGerencia pegarTipoGerenciaPorId(Integer codigo) throws Exception{
		return ctrlOrg.pegarTipoGerenciaPorId(codigo);
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
	
	@Override
	public TipoLogradouro pegarTipoLogradouroPorId(Integer codigo) throws Exception{
		return ctrlOrg.pegarTipoLogradouroPorId(codigo);
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
	public void excluirCidade(Cidade cidade) throws Exception{
		ctrlOrg.excluirCidade(cidade);
	}
	
	@Override
	public List<Cidade> listarCidades() throws Exception{
		return ctrlOrg.listarCidades();
	}
	
	@Override
	public List<Cidade> consultarCidade(Cidade cidade) throws Exception {
		return ctrlOrg.consultarCidade(cidade);
	}
	
	@Override
	public List<Cidade> consultarCidadesPorUF(UnidadeFederativa uf) throws Exception{
		return ctrlOrg.consultarCidadesPorUF(uf);
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
	
	@Override
	public UnidadeFederativa pegarUnidadeFederativaPorId(Integer codigo) throws Exception{
		return ctrlOrg.pegarUfPorId(codigo);
	}
	
	
	//***********************  P E S S O A     J U R Í D I C A  *************************
	@Override
	public List<PessoaJuridica> listarPJ() throws Exception{
		return ctrlOrg.listarPJ();
	}
	
	@Override
	public PessoaJuridica pegarPessoaJuridicaPorId(Integer codigo) throws Exception{
		return ctrlOrg.pegarPessoaJuridicaPorId(codigo);
	}
	
	
	//***********************************************************************************
	//********************* F I M   C R U D    Organizacional ***************************
	//***********************************************************************************
	
	//**************************  C L I E N T E  ****************************************
	@Override
	public void salvarCliente(Cliente cliente) throws Exception {
		if (ctrlOrg.clienteExiste(cliente))
			ctrlOrg.alterarCliente(cliente);
		else
			ctrlOrg.inserirCliente(cliente);
	}

	@Override
	public void excluirCliente(Cliente cliente) throws Exception {
		ctrlOrg.excluirCliente(cliente);
		
	}
	
	@Override
	public List<Cliente> pesquisarCliente(Cliente exemplo) throws Exception{
		return ctrlOrg.pesquisarCliente(exemplo);
	}

	@Override
	public List<Cliente> consultarCliente(Cliente cliente) throws Exception {
		return ctrlOrg.consultarCliente(cliente);
	}

	@Override
	public List<Cliente> listarClientes() throws Exception {
		return ctrlOrg.listarClientes();
	}
	
	//*************************  F A B R I C A N T E  ***********************************
	@Override
	public void salvarFabricante(Fabricante fabricante) throws Exception {
		if (ctrlOrg.fabricanteExiste(fabricante))
			ctrlOrg.alterarFabricante(fabricante);
		else
			ctrlOrg.inserirFabricante(fabricante);
	}

	@Override
	public void excluirFabricante(Fabricante fabricante) throws Exception {
		ctrlOrg.excluirFabricante(fabricante);
	}

	@Override
	public List<Fabricante> consultarFabricante(Fabricante fabricante)
			throws Exception {
		return ctrlOrg.consultarFabricante(fabricante);
	}

	@Override
	public List<Fabricante> listarFabricantes() throws Exception {
		return ctrlOrg.listarFabricantes();
	}
	
	@Override
	public Fabricante consultarFabricantePorId(Integer codigo) throws Exception{
		return ctrlOrg.consultarFabricantePorId(codigo);
	}


	//Controlador Carro - Felipe Carlos
	
	//Carro
	@Override
	public void salvarCarro(Carro carro) {
		this.controladorCarro.inserir(carro);
	}

	@Override
	public void removerCarro(Carro carro) {
		this.controladorCarro.remover(carro);
	}

	@Override
	public List<Carro> listarCarros() {
		return this.controladorCarro.listarCarros();
	}

	@Override
	public Carro pesquisarCarroCodigo(Integer codigo) {
		return this.controladorCarro.pesquisarCarro(codigo);
	}

	@Override
	public List<Carro> consultarCarros(Carro carro) {
		return this.controladorCarro.pesquisarCarros(carro);
	}

	//Marca
	
	@Override
	public void salvarMarcaCarro(MarcaCarro marcaCarro) {
		this.controladorCarro.inserir(marcaCarro);
	}

	@Override
	public void removerMarcaCarro(MarcaCarro marcaCarro) {
		this.controladorCarro.removerMarca(marcaCarro);
	}

	@Override
	public List<MarcaCarro> listarMarcasCarros() {
		return this.controladorCarro.listarMarcas();
	}

	@Override
	public MarcaCarro pesquisarMarcasCarroCodigo(Integer codigo) {
		return this.controladorCarro.pesquisarMarcaCodigo(codigo);
	}

	@Override
	public List<MarcaCarro> consultarMarcasCarros(MarcaCarro marcaCarro) {
		return this.controladorCarro.pesquisarMarcas(marcaCarro);
	}
	
	public Fabricante pesquisarFabricanteCodigo(Integer codigo){
		return this.pesquisarFabricanteCodigo(codigo);
	}

	//Modelo
	
	@Override
	public void salvarModeloCarro(ModeloCarro modeloCarro) {
		this.controladorCarro.inserir(modeloCarro);
		
	}

	@Override
	public void removerModeloCarro(ModeloCarro modeloCarro) {
		this.controladorCarro.remover(modeloCarro);;
	}

	@Override
	public List<ModeloCarro> listarModelosCarros() {
		return this.controladorCarro.listarModelos();
	}

	@Override
	public ModeloCarro pesquisarModelosCarroCodigo(Integer codigo) {
		return this.controladorCarro.pesquisarModeloCarro(codigo);
	}

	@Override
	public List<ModeloCarro> consultarModelosCarros(ModeloCarro modeloCarro) {
		return this.controladorCarro.pesquisarModeloCarros(modeloCarro);
	}

	@Override
	public List<MarcaCarro> pesquisarMarcaPorFabr(Integer codigo) {
		return this.controladorCarro.pesquisarMarcaPorFabr(codigo);
	}
	

	@Override
	public List<ModeloCarro> pesquisarModeloPorMarca(Integer codigo) {
		return this.controladorCarro.pesquisarModeloPorMarca(codigo);
	}

	
	//Versao
	
	@Override
	public void salvarVersao(VersaoCarro versaoCarro) {
		this.controladorCarro.inserir(versaoCarro);
	}

	@Override
	public void removerVersao(VersaoCarro versaoCarro) {
		this.controladorCarro.remover(versaoCarro);
	}

	@Override
	public List<VersaoCarro> listarVersoes() {
		return this.controladorCarro.listarVersoes();
	}

	@Override
	public VersaoCarro pesquisarVersaoCodigo(Integer codigo) {
		return this.controladorCarro.pesquisarVersaoCodigo(codigo);
	}

	@Override
	public List<VersaoCarro> consultarVersoes(VersaoCarro versaoCarro) {
		return this.controladorCarro.pesquisarVersoes(versaoCarro);
	}
	
	@Override
	public List<VersaoCarro> pesquisarVersaoPorModelo(Integer codigo) {
		return this.controladorCarro.pesquisarVersaoPorModelo(codigo);
	}

	//Item Série
	
	@Override
	public void salvarItemSerie(ItemSerieCarro itemSerieCarro) throws Exception {
		this.controladorCarro.inserir(itemSerieCarro);
	}

	@Override
	public void removerItem(ItemSerieCarro itemSerieCarro) {
		this.controladorCarro.remover(itemSerieCarro);
	}

	@Override
	public List<ItemSerieCarro> listarItens() {
		return this.controladorCarro.listarItens();
	}

	@Override
	public ItemSerieCarro pesquisarItemCodigo(Integer codigo) {
		return this.controladorCarro.pesquisarItemCodigo(codigo);
	}

	@Override
	public List<ItemSerieCarro> consultarItens(ItemSerieCarro itemSerieCarro) {
		return this.controladorCarro.pesquisarItens(itemSerieCarro);
	}

	@Override
	public List<ItemSerieCarro> listarItensPorModelo(ModeloCarro modelo) {
		return this.controladorCarro.listarItensPorModelo(modelo);
	}

	//Acessorio
	
	@Override
	public void salvarAcessorio(AcessorioCarro acessorioCarro) {
		this.controladorCarro.inserir(acessorioCarro);
	}

	@Override
	public void removerAcessorio(AcessorioCarro acessorioCarro) {
		this.controladorCarro.removerAcessorio(acessorioCarro);
	}

	@Override
	public List<AcessorioCarro> listarAcessorios() {
		return this.controladorCarro.listarAcessorios();
	}

	@Override
	public AcessorioCarro pesquisarAcessorioCodigo(Integer codigo) {
		return this.controladorCarro.pesquisarAcessorioCarroCodigo(codigo);
	}

	@Override
	public List<AcessorioCarro> consultarAcessorios(AcessorioCarro acessorioCarro) {
		return this.controladorCarro.pesquisarAcessorios(acessorioCarro);
	}

	@Override
	public List<AcessorioCarro> listarAcessoriosPorModelo(ModeloCarro modelo) {
		return this.controladorCarro.listarAcessoriosPorModelo(modelo);
	}

	
}
