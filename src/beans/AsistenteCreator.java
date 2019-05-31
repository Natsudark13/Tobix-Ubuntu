package beans;

public class AsistenteCreator extends UsuarioCreator{

	@Override
	public Usuario createUsuario() {
		
		return new Asistente();
	}

}
