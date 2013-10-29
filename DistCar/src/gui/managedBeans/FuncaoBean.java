package gui.managedBeans;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import classesBasicas.Funcao;
import classesBasicas.Situacao;
//import erro.NegocioExceptionFuncao;
import fachada.Fachada;

@ManagedBean
public class FuncaoBean {
	private Funcao funcao;
	private String mensagem;
	private List<Funcao> lista;
	private Date data;
	
	public FuncaoBean(){
		funcao = new Funcao();
		funcao.setDescricao("Digite descrição aqui");
		//funcao.getDescricao()
		//funcao.getSalario();
		//funcao.getSituacao();
		listar();
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
	
	public void novo(ActionEvent actionEvent){
		funcao = new Funcao();
	}
	
	public void salvarAjax(ActionEvent actionEvent){
		try{
			funcao.setDataUltimaAtualizacao(Calendar.getInstance());
			funcao.setSituacao(Situacao.ATIVO);
			//if (funcao.getCodigo() != null)
				//throw new Exception("funcao.codigo != null !!");
			Fachada.obterInstancia().salvarFuncao(funcao);
			//listar();
			mensagem = "Função salva com sucesso!";
			funcao = new Funcao();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "Função salva com sucesso!"));
		}catch(Exception ex){
			mensagem = ex.getMessage();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Salvar Error: ", ex.getMessage()));
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
		return mensagem;
	}
	
	public void listarAjax(ActionEvent actionEvent){
		lista = listar();
	}
	
	public List<Funcao> listar(){
		try{
			lista = Fachada.obterInstancia().listarFuncoes();
			mensagem = "Funções listadas com sucesso!";
			return lista;
		}catch(Exception ex){
			mensagem = ex.getMessage();
		}
		return lista;
	}
	
	public List<Funcao> getFuncoes(){
		return lista;
	}
}
