package beans;

public class EncargadoCreator extends UsuarioCreator{

	@Override
	public Usuario createUsuario() {
		
		return new Encargado();
	}
}
