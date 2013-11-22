package gui.managedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import seguranca.LoginInvalidoException;
import seguranca.Usuario;
import fachada.Fachada;
import fachada.IFachada;

@ManagedBean
@SessionScoped
public class LoginBean {

	private String login;
	private String senha;
	private Usuario usuarioLogado;
	private IFachada fachada = Fachada.obterInstancia();
	
	public String efetuarLogin(){
		try {
			usuarioLogado = fachada.efetuarLogin(login, senha);
		} catch (LoginInvalidoException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login/Senha inexistente"));
		}
		return "/index.xhtml?faces-redirect=true";
	}
	
	public String efetuarLogin2(){
		try {
			usuarioLogado = fachada.efetuarLogin(login, senha);
			return "/home.xhtml?faces-redirect=true";	
		} catch (LoginInvalidoException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login/Senha inexistente"));
		}
		return "/index.xhtml?faces-redirect=true";
	}
	
	public String efetuarLogoff(){
		usuarioLogado = null;
		return "/index.xhtml?faces-redirect=true";
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
}
