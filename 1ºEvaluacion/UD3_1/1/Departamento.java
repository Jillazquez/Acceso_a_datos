package EJ_01;

public class Departamento {
	int codigo;
	String nombre;
	String ubicacion;
	
	
	
	public Departamento(int codigo, String nombre, String ubicacion) {	
		this.codigo = codigo;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
	}
	public int getCodigo() {
		return codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public String toString() {
		return "Departamento [codigo=" +codigo+ ", nombre=" + nombre + ", ubicacion=" + ubicacion + "]";
	}
	
	
}
