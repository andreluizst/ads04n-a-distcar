package gui.managedBeans;

import fachada.Fachada;
import fachada.IFachada;
import gui.MsgPrimeFaces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import classesBasicas.Cidade;
import classesBasicas.Departamento;
import classesBasicas.Funcao;
import classesBasicas.Funcionario;
import classesBasicas.Situacao;
import classesBasicas.TipoLogradouro;
import classesBasicas.UnidadeFederativa;

@ManagedBean
@SessionScoped
public class FuncionarioBean {
	private static final String OP_NOVA = "NOVO Funcionário";
	private static final String OP_ALTERAR = "Alterar Funcionário";
	private static final String OP_VISUALIZAR = "Propriedades do Funcionário";
	private static final String TXT_BTN_CANCELAR = "Cancelar";
	private static final String TXT_BTN_FECHAR = "Fechar";
	
	private IFachada fachada;
	
	private Funcionario funcionario;
	private Funcionario funcionarioParaPesquisa;
	private List<Funcionario> lista;
	private Funcionario funcionarioSelecionado;
	private Situacao situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	private boolean listaEstaVazia;
	private String tituloOperacao;
	private List<TipoLogradouro> tiposLogradouros; 
	private List<Cidade> cidades;
	private List<UnidadeFederativa> unidadesFederativas;
	private List<Departamento> departamentos;
	private List<Funcao> funcoes;
	private String textoBotaoFecharOuCancelar;
	private boolean somenteLeitura;
	
	
	
	public FuncionarioBean(){
		fachada = Fachada.obterInstancia();
		inicializar();
	}
	
	private void inicializar(){
		novoFuncionario();
		if (lista==null)
			lista = new ArrayList<Funcionario>();
		else
			lista.clear();
		listaEstaVazia = true;
		funcionarioParaPesquisa = new Funcionario();
		funcionarioSelecionado = null;
		tituloOperacao = FuncionarioBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = FuncionarioBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		try{
			tiposLogradouros = fachada.listarTiposLogradouros();
			cidades = fachada.listarCidades();
			unidadesFederativas = fachada.listarUFs();
			departamentos = fachada.listarDepartamentos();
			funcoes = fachada.listarFuncoes();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}


	public String alterar(){
		if (listaEstaVazia)
			return null;
		if (funcionarioSelecionado != null)
			funcionario = funcionarioSelecionado;
		tituloOperacao = FuncionarioBean.OP_ALTERAR;
		textoBotaoFecharOuCancelar = FuncionarioBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return "funcionario-prop";
	}
	
	public String novo(){
		novoFuncionario();
		tituloOperacao = FuncionarioBean.OP_NOVA;
		textoBotaoFecharOuCancelar = FuncionarioBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return "funcionario-prop";
	}
	
	private void novoFuncionario(){
		funcionario = new Funcionario();
		//funcionario.setNome("Digite um nome aqui");
	}
	
	public void excluir(){
		if (listaEstaVazia)
			return;
		try{
			funcionario = funcionarioSelecionado;
			fachada.excluirFuncionario(funcionario);
			MsgPrimeFaces.exibirMensagemInfomativa("Funcionário " + funcionarioSelecionado.getNome() + " excluido com sucesso!");
			novoFuncionario();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public void fechar(ActionEvent actionevent){
		lista.clear();
		listaEstaVazia = true;
		funcionarioSelecionado = null;
		somenteLeitura = true;
	}
	
	public String salvar(){
		try{
			fachada.salvarFuncionario(funcionario);
			MsgPrimeFaces.exibirMensagemInfomativa("Funcionário salvo com sucesso!");
			novoFuncionario();
			somenteLeitura = true;
			return "funcionario";
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
		return null;
	}
	
	public void consultar(){
		try{
			atualizarLista(fachada.consultarFuncionario(funcionarioParaPesquisa));
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public String visualizar(){
		if (listaEstaVazia)
			return null;
		if (funcionarioSelecionado != null)
			funcionario = funcionarioSelecionado;
		tituloOperacao = FuncionarioBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = FuncionarioBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		return "funcionario-prop";
	}
	

	private void atualizarLista(List<Funcionario> lista) {
		if (lista == null)
			this.lista.clear();
		else
			this.lista = lista;
		listaEstaVazia = this.lista.size()>0?false:true;
	}
	
	public void limpar(){
		funcionarioParaPesquisa = new Funcionario();
		situacaoSelecionada = null;
	}
	
	public String carregarPagina(){
		inicializar();
		return "funcionario";
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Funcionario getFuncionarioParaPesquisa() {
		return funcionarioParaPesquisa;
	}

	public void setFuncionarioParaPesquisa(Funcionario funcionarioParaPesquisa) {
		this.funcionarioParaPesquisa = funcionarioParaPesquisa;
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public Situacao getSituacaoSelecionada() {
		return situacaoSelecionada;
	}

	public void setSituacaoSelecionada(Situacao situacaoSelecionada) {
		this.situacaoSelecionada = situacaoSelecionada;
	}

	public List<Funcionario> getLista() {
		return lista;
	}

	public Situacao[] getSituacoes() {
		return situacoes;
	}

	public boolean isListaEstaVazia() {
		return listaEstaVazia;
	}

	public String getTituloOperacao() {
		return tituloOperacao;
	}

	public List<TipoLogradouro> getTiposLogradouros() {
		return tiposLogradouros;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public List<UnidadeFederativa> getUnidadesFederativas() {
		return unidadesFederativas;
	}

	public String getTextoBotaoFecharOuCancelar() {
		return textoBotaoFecharOuCancelar;
	}

	public boolean isSomenteLeitura() {
		return somenteLeitura;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public List<Funcao> getFuncoes() {
		return funcoes;
	}

	
	
		
}
