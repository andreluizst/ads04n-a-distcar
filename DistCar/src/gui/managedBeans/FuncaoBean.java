package gui.managedBeans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
//import gui.MessagesController;



import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
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
	private IFachada fachada;
	
	private Funcao funcao;
	private Funcao funcaoDePesquisa;
	private String mensagem;
	private List<Funcao> lista;
	private Date data;
	private Funcao funcaoSelecionada;
	private Situacao situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	
	public FuncaoBean(){
		fachada = Fachada.obterInstancia();
		novaFuncao();
		funcaoDePesquisa = new Funcao();
		data = Calendar.getInstance().getTime();
	}
	
	

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
		if (funcaoSelecionada != null)
			funcao = funcaoSelecionada;
		return "funcao-prop";
	}
	
	public String novo(){
		novaFuncao();
		return "funcao-prop";
	}
	
	private void novaFuncao(){
		funcao = new Funcao();
		funcao.setDescricao("Digite a descrição aqui");
	}
	
	public void excluir(){
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
	

	private void atualizarLista(List<Funcao> lista) {
		if (lista == null)
			this.lista.clear();
		else
			this.lista = lista;
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
}
