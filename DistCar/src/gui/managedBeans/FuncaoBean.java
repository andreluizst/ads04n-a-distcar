package gui.managedBeans;

import java.util.ArrayList;
import java.util.List;
//import gui.MessagesController;









import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import classesBasicas.Funcao;
import classesBasicas.Situacao;
//import erro.NegocioExceptionFuncao;
import fachada.Fachada;
import fachada.IFachada;
import gui.MsgPrimeFaces;

@ManagedBean
@SessionScoped
public class FuncaoBean {
	private static final String OP_NOVA = "NOVA Função";
	private static final String OP_ALTERAR = "Alterar Função";
	private static final String OP_VISUALIZAR = "Propriedades da Função";
	private static final String TXT_BTN_CANCELAR = "Cancelar";
	private static final String TXT_BTN_FECHAR = "Fechar";
	
	private IFachada fachada;
	
	private Funcao funcao;
	private Funcao funcaoDePesquisa;
	private String mensagem;
	private List<Funcao> lista;
	private Funcao funcaoSelecionada;
	private Situacao situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	private boolean listaEstaVazia;
	private String tituloOperacao;
	private String textoBotaoFecharOuCancelar;
	private boolean somenteLeitura;
	
	
	
	public FuncaoBean(){
		fachada = Fachada.obterInstancia();
		inicializar();
	}
	
	private void inicializar(){
		novaFuncao();
		if (lista==null)
			lista = new ArrayList<Funcao>();
		else
			lista.clear();
		listaEstaVazia = true;
		funcaoDePesquisa = new Funcao();
		funcaoSelecionada = null;
		tituloOperacao = FuncaoBean.OP_NOVA;
	}
	
	private void prepararParaExibirDados(Funcao obj){
		if (obj != null)
			funcao = obj;
	}

	
	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public String getMensagem() {
		return mensagem;
	}
	
	public String alterar(){
		if (listaEstaVazia)
			return null;
		prepararParaExibirDados(funcaoSelecionada);
		tituloOperacao = FuncaoBean.OP_ALTERAR;
		textoBotaoFecharOuCancelar = FuncaoBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return "funcao-prop";
	}
	
	public String novo(){
		novaFuncao();
		tituloOperacao = FuncaoBean.OP_NOVA;
		textoBotaoFecharOuCancelar = FuncaoBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return "funcao-prop";
	}
	
	private void novaFuncao(){
		funcao = new Funcao();
		funcao.setDescricao("Digite a descrição aqui");
	}
	
	public void excluir(){
		if (listaEstaVazia)
			return;
		try{
			funcao = funcaoSelecionada;
			fachada.excluirFuncao(funcao);
			MsgPrimeFaces.exibirMensagemInfomativa("Função " + funcaoSelecionada.getDescricao() + " excluida com sucesso!");
			novaFuncao();
			listarAjax();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	
	
	public void fechar(ActionEvent actionevent){
		lista.clear();
		listaEstaVazia = true;
		funcaoSelecionada = null;
	}
	
	public void limpar(){
		funcaoDePesquisa = new Funcao();
		situacaoSelecionada = null;
	}
	
	
	public void salvarAjax(ActionEvent actionEvent){
		try{
			Fachada.obterInstancia().salvarFuncao(funcao);
			novaFuncao();
			MsgPrimeFaces.exibirMensagemInfomativa("Função salva com sucesso!");
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public String salvar(){
		try{
			Fachada.obterInstancia().salvarFuncao(funcao);
			MsgPrimeFaces.exibirMensagemInfomativa("Função salva com sucesso!");
			novaFuncao();
			return "funcao";
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
		return null;
	}
	
	public void consultar(){
		try{
			atualizarLista(fachada.consultarFuncao(funcaoDePesquisa));
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public String visualizar(){
		if (listaEstaVazia)
			return null;
		prepararParaExibirDados(funcaoSelecionada);
		tituloOperacao = FuncaoBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = FuncaoBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		return "funcao-prop";
	}
	

	private void atualizarLista(List<Funcao> lista) {
		if (lista == null)
			this.lista.clear();
		else
			this.lista = lista;
		listaEstaVazia = this.lista.size()>0?false:true;
	}

	public void listarAjax(){
		atualizarLista(listar());
	}
	
	public List<Funcao> listar(){
		try{
			//lista = null;
			atualizarLista(Fachada.obterInstancia().listarFuncoes());
			return lista;
		}catch(Exception ex){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao listar: ", ex.getMessage()));
		}
		return lista;
	}
	
	public List<Funcao> getFuncoes(){
		return lista;
	}

	public Funcao getFuncaoSelecionada() {
		return funcaoSelecionada;
	}

	public void setFuncaoSelecionada(Funcao funcaoSelecionada) {
		this.funcaoSelecionada = funcaoSelecionada;
	}

	public Situacao getSituacaoSelecionada() {
		return situacaoSelecionada;
	}

	public void setSituacaoSelecionada(Situacao situacaoSelecionada) {
		this.situacaoSelecionada = situacaoSelecionada;
	}

	public Situacao[] getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(Situacao[] situacoes) {
		this.situacoes = situacoes;
	}

	public Funcao getFuncaoDePesquisa() {
		return funcaoDePesquisa;
	}

	public void setFuncaoDePesquisa(Funcao funcaoDePesquisa) {
		this.funcaoDePesquisa = funcaoDePesquisa;
	}



	public boolean isListaEstaVazia() {
		return listaEstaVazia;
	}
	
	public String carregarPagina(){
		inicializar();
		return "funcao";
	}



	public String getTituloOperacao() {
		return tituloOperacao;
	}



	public void setTituloOperacao(String tituloOperacao) {
		this.tituloOperacao = tituloOperacao;
	}

	public String getTextoBotaoFecharOuCancelar() {
		return textoBotaoFecharOuCancelar;
	}

	public boolean isSomenteLeitura() {
		return somenteLeitura;
	}
	
	
	
}
