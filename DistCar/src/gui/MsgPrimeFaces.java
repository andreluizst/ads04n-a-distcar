package gui;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MsgPrimeFaces {
	public static void exibirMensagemDeErro(String mensagem){
    	FacesContext.getCurrentInstance().addMessage(
    			"ERRO", new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO", mensagem));
    }
    
    public static void exibirMensagemDeErro(String titulo, String mensagem){
    	FacesContext.getCurrentInstance().addMessage(
    			"ERRO", new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo, mensagem));
    }
    
    public static void exibirMensagemInfomativa(String mensagem){
    	FacesContext.getCurrentInstance().addMessage(
    			"Informação", new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação", mensagem));
    }
    
    public static void exibirMensagemInfomativa(String titulo, String mensagem){
    	FacesContext.getCurrentInstance().addMessage(
    			"Informação", new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensagem));
    }
    
    public static void exibirMensagemDeAviso(String mensagem){
    	FacesContext.getCurrentInstance().addMessage(
    			"Aviso", new FacesMessage(FacesMessage.SEVERITY_WARN,"Informação", mensagem));
    }
    
    public static void exibirMensagemDeAviso(String titulo, String mensagem){
    	FacesContext.getCurrentInstance().addMessage(
    			"Aviso", new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, mensagem));
    }
    
    public static void exibirMensagemFatal(String mensagem){
    	FacesContext.getCurrentInstance().addMessage(
    			"FALHA", new FacesMessage(FacesMessage.SEVERITY_FATAL,"FALHA", mensagem));
    }
    
    public static void exibirMensagemFatal(String titulo, String mensagem){
    	FacesContext.getCurrentInstance().addMessage(
    			"FALHA", new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, mensagem));
    }
}
