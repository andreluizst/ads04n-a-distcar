package gui.managedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import seguranca.TipoUsuario;
import seguranca.Usuario;
import seguranca.Perfil;
import fachada.Fachada;

@ManagedBean
@ViewScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	private String senhaRepetida;

	public String salvarUsuario() {
		if (!usuario.getSenhaAtual().equals(senhaRepetida)) {
			FacesContext.getCurrentInstance().addMessage("senhaRep",
					new FacesMessage("Repetição de senha não confere"));
			return null;
		} else {
			//Fachada.obterInstancia().inserirUsuario(usuario);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Usuário cadastrado com sucesso!"));			
			return "/index.xhtml?faces-redirect=true";
		}
	}
	
	public boolean isShowDadosAdicionais(){
		if (usuario.getTipo() != null && usuario.getTipo() == TipoUsuario.ADMINISTRADOR){
			return true;
		}
		return false;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenhaRepetida() {
		return senhaRepetida;
	}

	public void setSenhaRepetida(String senhaRepetida) {
		this.senhaRepetida = senhaRepetida;
	}

	public TipoUsuario[] getTiposUsuario(){
		return TipoUsuario.values();
	}
}
