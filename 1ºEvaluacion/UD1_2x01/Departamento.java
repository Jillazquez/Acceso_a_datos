package AD_2x01;

public class Departamento {
	private int codigo;
	private String nombre;
	private String ubicacion;
	
	public Departamento(int codigo, String nombre, String ubicacion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
	}
	
	public Departamento(String lineaTexto) {
		String[] partes = lineaTexto.split(";");
		
		if(partes.length == 3) {
			this.codigo = Integer.parseInt(partes[0]);
			this.nombre = partes[1];
			this.ubicacion = partes[2];
		}
	}
	
	
    public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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

	// Método toString para representación de texto
    @Override
    public String toString() {
        return "Departamento [codigo=" + codigo + ", nombre=" + nombre + ", ubicacion=" + ubicacion + "]";
    }
    
 // Método toStringWithSeparators para representación de texto en fichero
    public String toStringWithSeparators() {
        return codigo + ";" + nombre + ";" + ubicacion;
    }
	
}
