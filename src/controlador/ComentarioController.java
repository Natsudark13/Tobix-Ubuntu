package controlador;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import beans.Actividad;
import beans.Bloque;
import beans.Comentario;
import beans.Encargado;
import conexion.CRUD;
import services.SingletonAnalizadorSentimientos;
import services.AnalizadorSentimientos;

@ManagedBean
public class ComentarioController {

	public String registrar() {
		// get the user values from the input form.
				FacesContext context = FacesContext.getCurrentInstance();
				Comentario comentario = context.getApplication().evaluateExpressionGet(context, "#{comentario}", Comentario.class);
				Actividad actividad = context.getApplication().evaluateExpressionGet(context, "#{actividad}", Actividad.class);
				
				try {
					comentario.registrarComentario(actividad.getNombreActividad());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
							
				// put the user object into the POST request 
				FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("comentario", comentario);
				
				return "principalAsistente.xhtml";
	}
	
	public String verSentimientos() throws SQLException {
		FacesContext context = FacesContext.getCurrentInstance();
		Actividad actividad = context.getApplication().evaluateExpressionGet(context, "#{actividad}", Actividad.class);
		if(actividad == null) {
			Actividad actividadEmergencias = new Actividad();
		}
		
		
		AnalizadorSentimientos y = SingletonAnalizadorSentimientos.getInstance();
		
		Comentario comentario = new Comentario();
		
		ArrayList<String> lista = comentario.verComentarios(actividad.getNombreActividad());
		
		int contador = 0;
		ArrayList<Comentario> comentarios = new ArrayList<>();
		
		ArrayList<String> temp = y.obtenerComentariosSentimiento(lista, actividad.getTipoActividad());      
		while (temp.size()>contador) {
			
			comentarios.add(new Comentario(temp.get(contador)));
			contador++;
		}


		DataModel<Comentario> temp2 = new ListDataModel(comentarios);
		comentario.setComentarioModel(temp2);
		
		//FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("actividad", actividad);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("comentario", comentario);
		
		
		return"ResultadoSentimientos.xhtml";
	}
	
	public String elegirBloqueC() throws SQLException {
		// get the user values from the input form.
		CRUD crud = new CRUD();
		FacesContext context = FacesContext.getCurrentInstance();
		Bloque bloque = context.getApplication().evaluateExpressionGet(context, "#{bloque}", Bloque.class);
		Actividad actividad = new Actividad();
		actividad.setNombresA(crud.select_Bloque_actividad(bloque.getTematica()));
		System.out.println("look: "+bloque.getTematica()+" "+actividad.getNombresA().get(0));
		
		// put the user object into the POST request 
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("actividad", actividad);
		
		return"ActividadComentario.xhtml";
	}
	
	
}
