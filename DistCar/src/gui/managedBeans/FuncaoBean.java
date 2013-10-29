package gui.managedBeans;

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
@ViewScoped
public class FuncaoBean {
	private IFachada fachada;
	
	private Funcao funcao;
	private String mensagem;
	private List<Funcao> lista;
	private Date data;
	private Funcao funcaoSelecionada;
	private Situacao situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	
	public FuncaoBean(){
		fachada = Fachada.obterInstancia();
		novaFuncao();
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
	
	public void alterar(){
		if (funcaoSelecionada != null)
			funcao = funcaoSelecionada;
	}
	
	public void novaFuncao(){
		funcao = new Funcao();
		funcao.setDescricao("Digite a descrição aqui");
	}
	
	public void excluir(){
		try{
			//funcao = funcaoSelecionada;
			fachada.excluirFuncao(funcaoSelecionada);
			/*FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", 
							"Função " + funcaoSelecionada.getDescricao() + " excluida com sucesso!")
					);*/
			MsgPrimeFaces.exibirMensagemInfomativa("Função " + funcaoSelecionada.getDescricao() + " excluida com sucesso!");
			novaFuncao();
		}catch(Exception ex){
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir: ", ex.getMessage()));
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public void salvarAjax(ActionEvent actionEvent){
		try{
			//if (funcao.getDataUltimaAtualizacao() == null)
			funcao.setDataUltimaAtualizacao(Calendar.getInstance());
			if (funcao.getSituacao() == null)
				funcao.setSituacao(Situacao.ATIVO);
			Fachada.obterInstancia().salvarFuncao(funcao);
			novaFuncao();
			MsgPrimeFaces.exibirMensagemInfomativa("Função salva com sucesso!");
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "Função salva com sucesso!"));
		}catch(Exception ex){
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Salvar Error: ", ex.getMessage()));
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public String salvar(){
		try{
			funcao.setDataUltimaAtualizacao(Calendar.getInstance());
			funcao.setSituacao(Situacao.ATIVO);
			if (funcao.getCodigo() != null)
				throw new Exception("funcao.codigo != null !!");
			Fachada.obterInstancia().salvarFuncao(funcao);
			listar();
			mensagem = "Função salva com sucesso!";
			funcao = new Funcao();
		}catch(Exception ex){
			mensagem = ex.getMessage();
		}
		return null;
	}
	
	public void listarAjax(ActionEvent actionEvent){
		lista = listar();
	}
	
	public List<Funcao> listar(){
		try{
			lista = null;
			lista = Fachada.obterInstancia().listarFuncoes();
			mensagem = "Funções listadas com sucesso!";
			return lista;
		}catch(Exception ex){
			mensagem = ex.getMessage();
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
}
