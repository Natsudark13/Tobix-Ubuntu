package beans;

public class AdministradorCreator extends UsuarioCreator {
	
	@Override
	public Usuario createUsuario() {
		
		return new Administrador();
	}
}
