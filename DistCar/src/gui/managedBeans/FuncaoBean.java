package gui.managedBeans;

import java.util.Calendar;

import javax.faces.bean.ManagedBean;

import classesBasicas.Funcao;
import classesBasicas.Situacao;
//import erro.NegocioExceptionFuncao;
import fachada.Fachada;

@ManagedBean
public class FuncaoBean {
	private Funcao funcao;
	private String mensagem;
	
	public FuncaoBean(){
		funcao = new Funcao();
		//funcao.getDescricao()
		//funcao.getSalario();
		//funcao.getSituacao();
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
	
	public String salvar(){
		try{
			funcao.setDataUltimaAtualizacao(Calendar.getInstance());
			funcao.setSituacao(Situacao.ATIVO);
			Fachada.obterInstancia().salvarFuncao(funcao);
			mensagem = "Função salva com sucesso!";
			funcao = new Funcao();
		}catch(Exception ex){
			mensagem = ex.getMessage();
		}
		return mensagem;
	}
}
