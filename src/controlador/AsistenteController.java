package controlador;

import javax.faces.bean.ManagedBean;

import javax.faces.context.FacesContext;

import beans.Asistente;
import beans.Usuario;

//@SuppressWarnings("deprecation")
@ManagedBean
public class AsistenteController {
	
	public String crearCuenta(){
		// get the user values from the input form.
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario asistente = context.getApplication().evaluateExpressionGet(context, "#{asistente}", Asistente.class);
		// show the user data in console
		asistente.crear();
		// put the user object into the POST request 
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("asistente", asistente);
		//Show the next page
		
		return "login-Asistente.xhtml";
	}
	
	public String botonInicioSesion(){
		
		
		//Show the next page
		return "principalAsistente.xhtml";
	}
	
	public String botonRegistrar(){
		
		//Show the next page
		return "registro-Asistente.xhtml";
	}
	
	public String paginaPrincipal() {
		return "principalAdministrador.xhtml";
	}
	
	public String logIN(){
		
		// get the user values from the input form.
		FacesContext context = FacesContext.getCurrentInstance();
		Asistente asistente = context.getApplication().evaluateExpressionGet(context, "#{asistente}", Asistente.class);
	
		
		// show the user data in console
		System.out.println("look: "+asistente.getCorreo()+" "+asistente.getContrasena());
		
		// put the user object into the POST request 
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("asistente", asistente);
		String URL;
		if(asistente.logIn(asistente.getCorreo(), asistente.getContrasena()) == 0) {
			URL = "login-FallidoAsistente.xhtml";
		}
		else {
			URL = "principalAsistente.xhtml";
		}
		//Show the next page
		return URL;
	}
}
